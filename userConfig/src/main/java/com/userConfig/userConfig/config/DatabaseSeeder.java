package com.userConfig.userConfig.config;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.userConfig.userConfig.model.RoleUser;
import com.userConfig.userConfig.model.UserBasic;
import com.userConfig.userConfig.repository.UserBasicRepository;
import com.userConfig.userConfig.service.EnderecoService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DatabaseSeeder implements CommandLineRunner {

    private final UserBasicRepository userRepository;
    private final EnderecoService enderecoService;

    @Override
    public void run(String... args) throws Exception {

        if (userRepository.count() == 0) {
            List<UserBasic> usuarios = List.of(
                    UserBasic.builder()
                            .nome("João Silva")
                            .cpf("12345678901")
                            .email("joao.silva@example.com")
                            .senha("Senha@123")
                            .role(RoleUser.USER)
                            .numeroResidencial("123")
                            .cep("01001000") 
                            .build(),

                    UserBasic.builder()
                            .nome("Maria Oliveira")
                            .cpf("98765432100")
                            .email("maria.oliveira@example.com")
                            .senha("Senha@123")
                            .role(RoleUser.USER)
                            .numeroResidencial("456")
                            .cep("04003010")
                            .build());

            for (UserBasic usuario : usuarios) {
                usuario.setEnderecoService(enderecoService); 
                usuario.preencherEndereco(usuario); 
                usuario.formatarCep();
                userRepository.save(usuario);
            }

            System.out.println("Usuários de teste adicionados com sucesso.");
        }
    }
}
