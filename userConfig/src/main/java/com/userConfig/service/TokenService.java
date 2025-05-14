package com.userConfig.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import com.userConfig.model.Token;
import com.userConfig.model.UserBasic;

@Service
public class TokenService {

    private Instant expiresAt = LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.ofHours(-3));
    private Algorithm algorithm = Algorithm.HMAC256("secret");

    public Token createToken(UserBasic user){
        var jwt = JWT.create()
            .withSubject(String.valueOf(user.getId()))
            .withClaim("email", user.getEmail())
            .withExpiresAt(expiresAt)
            .sign(algorithm);
        return new Token(jwt, user.getEmail());
    }

    public UserBasic getUserFromToken(String token){
        var verifiedToken = JWT.require(algorithm).build().verify(token);

        return UserBasic.builder()
                .id(Long.valueOf( verifiedToken.getSubject() ))
                .email(verifiedToken.getClaim("email").toString())
                .build();
    }
    
}