package com.userConfig.userConfig.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.userConfig.userConfig.model.userBasic;
import com.userConfig.userConfig.repository.userBasicRepository;
import com.userConfig.userConfig.service.EnderecoService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final userBasicRepository userRepository;
    private final EnderecoService enderecoService;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() == 0) {
            List<userBasic> usuarios = List.of(
                    userBasic.builder()
                            .nome("João Silva")
                            .cpf("12345678901")
                            .email("joao.silva@example.com")
                            .senha("Senha@123")
                            .role("ROLE_USER")
                            .numeroResidencial("123")
                            .cep("01001000") 
                            .build(),

                    userBasic.builder()
                            .nome("Maria Oliveira")
                            .cpf("98765432100")
                            .email("maria.oliveira@example.com")
                            .senha("Senha@123")
                            .role("ROLE_ADMIN")
                            .numeroResidencial("456")
                            .cep("04003010")
                            .build());

            for (userBasic usuario : usuarios) {
                usuario.setEnderecoService(enderecoService); 
                usuario.preencherEndereco(usuario); 
                usuario.formatarCep();
                userRepository.save(usuario);
            }

            System.out.println("Usuários de teste adicionados com sucesso.");
        }
    }
}
