package com.messenger.util.exception;

public class AuthenticationException extends RuntimeException {
    public static final String AUTHENTICATION_EXCEPTION = "exception.common.notAuthenticated";

    public AuthenticationException() {
        super("google authentication exception");
    }
}
