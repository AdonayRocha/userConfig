package com.userConfig.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.userConfig.model.Credentials;
import com.userConfig.model.Token;
import com.userConfig.model.UserBasic;
import com.userConfig.service.TokenService;

@RestController
public class AuthController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public Token login(@RequestBody Credentials credentials){
        var auth = new UsernamePasswordAuthenticationToken(credentials.email(), credentials.password());
        var user = (UserBasic) authenticationManager.authenticate(auth).getPrincipal();

        return tokenService.createToken(user);
    }
    
}