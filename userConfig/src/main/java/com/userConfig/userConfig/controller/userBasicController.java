package com.userConfig.userConfig.controller;

import com.userConfig.userConfig.model.UserBasic;
import com.userConfig.userConfig.repository.UserBasicRepository;
import com.userConfig.userConfig.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
//TODO 
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
            usuario = enderecoService.buscaEnderecoCep(usuario);
            UserBasic salvo = userRepo.save(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<?> buscarUsuarioPorCpf(@PathVariable String cpf) {
        Optional<UserBasic> encontrado = userRepo.findAll()
                .stream()
                .filter(u -> u.getCpf().equals(cpf))
                .findFirst();

        return encontrado.map(ResponseEntity::ok)
                         .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND)
                                               .body("Usuário com CPF " + cpf + " não encontrado."));
    }
}
