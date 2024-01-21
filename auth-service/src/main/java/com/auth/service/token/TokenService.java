package com.auth.service.token;

import com.auth.model.JwtModel;

import java.util.List;

public interface TokenService {

    JwtModel save(JwtModel model);

    void deleteByDeviceId(String deviceId);

    void deleteByClientId(String clientId);

    JwtModel getByClientId(String clientId);

    JwtModel getByToken(String token);

    List<JwtModel> getByDeviceId(String deviceId);
}

