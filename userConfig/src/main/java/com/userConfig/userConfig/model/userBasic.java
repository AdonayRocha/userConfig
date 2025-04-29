package com.userConfig.userConfig.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.Entity;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.userConfig.userConfig.service.EnderecoService;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Getter
@Setter
public class userBasic {
    @NotNull(message = "O nome não pode ser nulo")
    @NotEmpty(message = "O nome não pode ser vazio")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
    private String nome;

    @NotNull(message = "O email não pode ser nulo")
    @NotEmpty(message = "O email não pode ser vazio")
    private String email;

    @NotNull(message = "O CPF não pode ser nulo")
    @NotEmpty(message = "O CPF não pode ser vazio")
    @Size(min = 11, max = 11, message = "O CPF deve ter 11 caracteres")
    private String cpf;

    @NotNull(message = "O número da residência não pode ser nulo")
    @NotEmpty(message = "O número da residência não pode ser vazio")
    @Size (min = 1, max = 7, message = "O número da residência deve ter entre 1 e 10 caracteres")
    private String numeroResidencial; 

    @NotNull(message = "O CEP não pode ser nulo")
    @NotEmpty(message = "O CEP não pode ser vazio")
    private String cep;

    private String rua;
    private String cidade;

    // API - ViaCep
    @JsonIgnore
    @Autowired
    private EnderecoService enderecoService;

    public void preencherEndereco(userBasic usuario) {
        if (usuario.getCep() != null && !usuario.getCep().isEmpty()) {
            EnderecoCep endereco = enderecoService.buscaEnderecoCep(usuario.getCep());
            if (endereco != null) {
                String logradouro = endereco.getLogradouro();
                String localidade = endereco.getLocalidade();

                // Verifica se logradouro ou localidade são nulos ou vazios
                if (logradouro == null || logradouro.isEmpty() || localidade == null || localidade.isEmpty()) {
                    throw new IllegalArgumentException("Erro ao preencher endereço: Rua ou cidade não encontrados para o CEP informado.");
                }

                usuario.setRua(logradouro);
                usuario.setCidade(localidade);
            } else {
                throw new IllegalArgumentException("Erro ao preencher endereço: Nenhum endereço encontrado para o CEP informado.");
            }
        }
    }

    // Métodos

    // Formata o CEP em "00000-000"
    public void formatarCep() {
        if (this.cep != null && this.cep.matches("\\d{8}")) { // Verifica se o CEP tem 8 dígitos
            this.cep = this.cep.substring(0, 5) + "-" + this.cep.substring(5);
        }
    }

}
