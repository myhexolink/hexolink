package com.auth.service.authentication;

import com.auth.model.JwtModel;
import com.auth.model.User;
import com.auth.service.token.TokenService;
import com.auth.service.userservice.UserService;
import com.auth.to.GoogleUserTo;
import com.auth.util.AuthUtil;
import com.auth.util.SecurityUtil;
import com.auth.util.converter.UserConverter;
import com.auth.util.exception.GoogleAuthenticationException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Collections;
import java.util.Map;

import static com.auth.model.Role.USER;
import static com.auth.util.Constants.SIGN_IN_PROVIDER_CONSTANT;
import static com.auth.util.Constants.TOKEN_BEARER_PREFIX;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final SecurityUtil securityUtil;
    private final AuthenticationManager authenticationManager;
    private final FirebaseAuth firebaseAuth;
    private final UserService userService;
    private final TokenService tokenService;

    public String authenticate(String firebaseToken) {
        FirebaseToken decodedToken;
        try {
            decodedToken = firebaseAuth.verifyIdToken(AuthUtil.getTokenFromRequest(firebaseToken));
        } catch (FirebaseAuthException e) {
            throw new GoogleAuthenticationException();
        }
        Map<String, String> firebase = (Map<String, String>) decodedToken.getClaims().get("firebase");
        String signInProvider = firebase.get(SIGN_IN_PROVIDER_CONSTANT);
        GoogleUserTo googleUserTo = new GoogleUserTo();
        googleUserTo.setName(decodedToken.getName());
        googleUserTo.setEmail(decodedToken.getEmail());
        googleUserTo.setSignInProvider(signInProvider);
        SecretKey secureKey = null;
        try {
            secureKey = securityUtil.getSecureKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        User user = userService.getByEmail(googleUserTo.getEmail());
        if (user == null) {
            User newFromTo = UserConverter.createNewFromTo(
                    googleUserTo,
                    Collections.singleton(USER)
            );
            newFromTo.setAvatar(getAvatarFile(decodedToken.getPicture()));
            user = userService.create(newFromTo);
        }

        String token = securityUtil.generateAccessToken(user.getId().toString(), USER, secureKey);
        tokenService.save(new JwtModel(token, user.getId(), user.getEmail(), secureKey.getEncoded()));
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        googleUserTo,
                        token,
                        Collections.singleton(USER)
                )
        );
        return TOKEN_BEARER_PREFIX + token;
    }

    private byte[] getAvatarFile(String fileUrl) {
        URL url;
        try {
            url = new URL(fileUrl);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            int responseCode = httpConn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = httpConn.getInputStream();
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

                int bytesRead;
                byte[] buffer = new byte[1024];
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                byte[] fileBytes = outputStream.toByteArray();
                outputStream.close();
                inputStream.close();
                httpConn.disconnect();

                return fileBytes;
            } else {
                throw new IOException("Failed to download file: " + fileUrl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
