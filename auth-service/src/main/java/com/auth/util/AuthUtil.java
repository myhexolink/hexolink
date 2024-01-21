package com.auth.util;

import io.grpc.alts.AuthorizationUtil;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import java.util.Optional;

import static com.auth.util.Constants.TOKEN_BEARER_PREFIX;

public class AuthUtil {

    private AuthUtil() {
    }

//    public static String getTokenFromRequest(String bearerToken) {
//        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_BEARER_PREFIX)) {
//            return bearerToken.substring(7);
//        }
//        return bearerToken;
//    }
//
//    public static String getTokenFromRequest(HttpServletRequest request) {
//        return getTokenFromRequest(request.getHeader(Constants.AUTHORIZATION_HEADER_NAME));
//    }

    public static String getTokenFromRequest(String bearerToken) {
        return Optional.ofNullable(bearerToken)
                .filter(StringUtils::hasText)
                .map(token -> token.startsWith(TOKEN_BEARER_PREFIX) ? token.substring(7) : token)
                .orElse(null);
    }

    public static String getTokenFromRequest(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader(Constants.AUTHORIZATION_HEADER_NAME))
                .or(() -> Optional.ofNullable(request.getParameter(Constants.AUTHORIZATION_HEADER_NAME)))
                .map(AuthUtil::getTokenFromRequest) // Вызов метода обработки токена
                .orElse(null);
    }
}
