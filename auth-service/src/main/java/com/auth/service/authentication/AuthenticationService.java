package com.auth.service.authentication;

import com.auth.to.Device;

public interface AuthenticationService {
    String authenticate(String firebaseToken);
}
