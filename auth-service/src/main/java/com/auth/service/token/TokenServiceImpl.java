package com.auth.service.token;

import com.auth.model.JwtModel;
import com.auth.repository.tokenrepository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    @Autowired
    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public JwtModel save(JwtModel model) {
        return tokenRepository.save(model);
    }

    @Override
    public void deleteByDeviceId(String deviceId) {
        tokenRepository.deleteByDeviceId(deviceId);
    }

    @Override
    public void deleteByClientId(String id) {
        tokenRepository.deleteByUserId(id);
    }

    @Transactional(readOnly=true)
    public JwtModel getByClientId(String clientId) {
        return tokenRepository.getByUserId(clientId);
    }

    @Transactional(readOnly=true)
    @Override
    public JwtModel getByToken(String token) {
        return tokenRepository.getByToken(token);
    }

    @Transactional(readOnly=true)
    @Override
    public List<JwtModel> getByDeviceId(String deviceId) {
        return tokenRepository.getByDeviceId(deviceId);
    }
}
