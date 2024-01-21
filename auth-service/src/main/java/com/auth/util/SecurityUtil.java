package com.auth.util;

import com.auth.model.JwtModel;
import com.auth.model.Role;
import com.auth.repository.tokenrepository.TokenRepository;
import com.auth.to.Device;
import com.auth.to.GoogleUserTo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.DirectDecrypter;
import com.nimbusds.jose.crypto.DirectEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Map;

import static com.auth.util.Constants.*;

@Component
public class SecurityUtil {

    private final ObjectMapper jacksonObjectMapper;

    private final TokenRepository tokenRepository;

    @Autowired
    private SecurityUtil(ObjectMapper jacksonObjectMapper, TokenRepository tokenRepository) {
        this.jacksonObjectMapper = jacksonObjectMapper;
        this.tokenRepository = tokenRepository;
    }

    public SecretKey getSecureKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance(AES_ENCRYPTION);
        keyGen.init(128);
        return keyGen.generateKey();
    }

    private SecretKey getStoredSecretKey(String token) {
        JwtModel storedToken = new JacksonObjectMapper()
                .convertValue(
                        tokenRepository.getByToken(token),
                        JwtModel.class
                );
        return new SecretKeySpec(
                storedToken.getSecretKey(),
                0,
                storedToken.getSecretKey().length,
                AES_ENCRYPTION
        );
    }

    public String generateAccessToken(String id, Role role, SecretKey key) {
        Map<String, Object> clientInfo = Map.of(
                ID_CLIENT_INFO, id,
                ROLE_CLIENT_INFO, role
        );
        Payload payload = new Payload(clientInfo);
        String jweHeader = null;
        try {
            JWEHeader header = new JWEHeader(JWEAlgorithm.DIR, EncryptionMethod.A128GCM);
            JWEObject jweObject = new JWEObject(header, payload);
            jweObject.encrypt(new DirectEncrypter(key));
            jweHeader = jweObject.serialize();
        } catch (JOSEException e) {
            e.printStackTrace();
        }
        return jweHeader;
    }

    public Map<String, Object> getClientInfo(String token) throws ParseException, JOSEException {
        SecretKey storedSecretKey = getStoredSecretKey(token);
        Payload payload;
        JWEObject jweObject = JWEObject.parse(token);
        jweObject.decrypt(new DirectDecrypter(storedSecretKey));
        payload = jweObject.getPayload();
        return payload.toJSONObject();
    }

    public Device getDevice(HttpServletRequest request) throws IOException {
        return jacksonObjectMapper.readValue(request.getInputStream(), Device.class);
    }

    public GoogleUserTo getGoogleSignedInUser(HttpServletRequest request) throws IOException {
        return jacksonObjectMapper.readValue(request.getInputStream(), GoogleUserTo.class);
    }
}
