package com.TingTing.ecommerce.service;

import com.TingTing.ecommerce.dto.product.ProductDTO;
import com.TingTing.ecommerce.exceptions.AuthenticationFailException;
import com.TingTing.ecommerce.model.AuthenticationToken;
import com.TingTing.ecommerce.model.User;
import com.TingTing.ecommerce.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AuthenticationService {
    @Autowired
    TokenRepository tokenRepository;

    public void saveConfirmationToken(AuthenticationToken authenticationToken) {
        tokenRepository.save(authenticationToken);
    }

    public AuthenticationToken getToken(User user) {
        return tokenRepository.findByUser(user);
    }

    public User getUser(String token) {
      final AuthenticationToken authenticationToken = tokenRepository.findByToken(token);
       if (Objects.isNull(authenticationToken)) {
           return null;
       }
       return authenticationToken.getUser();
    }
    public void authenticate(String token) throws AuthenticationFailException {
        // check null
        if (Objects.isNull(token)) {
            // if null then throw an exception
            throw new AuthenticationFailException("token not present");
        }
        if (Objects.isNull(getUser(token))) {
            throw new AuthenticationFailException("token not valid");
        }
    }

}
