package com.userConfig.controller;

import com.userConfig.model.UserBasic;
import com.userConfig.repository.UserBasicRepository;
import com.userConfig.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class userBasicController {

    @Autowired
    private UserBasicRepository userRepo;

    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<?> criarUsuario(@RequestBody UserBasic usuario) {
        try {
            usuario = enderecoService.preencherEndereco(usuario);
            UserBasic salvo = userRepo.save(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    
}
