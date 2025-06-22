package com.simzaconnections.ecommerce_webapp_backend.service;

import com.simzaconnections.ecommerce_webapp_backend.exceptions.AuthenticationFailException;
import com.simzaconnections.ecommerce_webapp_backend.model.AuthenticationToken;
import com.simzaconnections.ecommerce_webapp_backend.model.User;
import com.simzaconnections.ecommerce_webapp_backend.repository.TokenRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationService {

    private final TokenRepository tokenRepository;

    public AuthenticationService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepository.save(authenticationToken);
    }

    public AuthenticationToken getToken(User user) {
        return tokenRepository.findByUser(user);
    }


    public User getUser(String token) {
        final AuthenticationToken authenticationToken = tokenRepository.findByToken(token);
        if(Objects.isNull(authenticationToken)) {
            return null;
        }
        // authenticationToken is not null
        return authenticationToken.getUser();
    }

    public void authenticate(String token) throws AuthenticationFailException {
        // null check
        if(Objects.isNull(token)) {
            // throw an exception
            throw new AuthenticationFailException("token not present");
        }
        if(Objects.isNull(getUser(token))) {
            throw new AuthenticationFailException("token not valid");
        }
    }
}
