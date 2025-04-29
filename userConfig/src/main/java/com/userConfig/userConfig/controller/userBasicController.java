package com.userConfig.userConfig.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.userConfig.userConfig.model.userBasic;
import com.userConfig.userConfig.service.EnderecoService;

@RestController
@RequestMapping("/usuarios")
public class userBasicController {

    @Autowired
    private EnderecoService enderecoService;

    // Temporário em "ToDo"
    @PostMapping
    public ResponseEntity<?> criarUsuario(@RequestBody userBasic usuario) {
        try {
            // Preenche o endereço antes de salvar
            usuario.preencherEndereco(usuario);

            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        } catch (IllegalArgumentException e) {
            // Retorna erro 400 caso ocorra uma exceção
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<userBasic> buscarUsuario(@PathVariable String cpf) {
        return ResponseEntity.ok(new userBasic());
    }

}
