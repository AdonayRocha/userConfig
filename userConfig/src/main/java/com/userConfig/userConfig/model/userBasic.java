package com.userConfig.userConfig.model;

import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.userConfig.userConfig.service.EnderecoService;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USERS")

public class UserBasic implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @NotNull(message = "O nome não pode ser nulo")
    @NotEmpty(message = "O nome não pode ser vazio")
    @Size(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
    private String nome;

    @NotNull(message = "O CPF não pode ser nulo")
    @NotEmpty(message = "O CPF não pode ser vazio")
    @Size(min = 11, max = 11, message = "O CPF deve ter 11 caracteres")
    private String cpf;

    @NotNull(message = "O email não pode ser nulo")
    @NotEmpty(message = "O email não pode ser vazio")
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[@#$%^&+=!]).{8,}$", message = "A senha deve ter pelo menos 8 caracteres, incluindo uma letra, um número e um caractere especial")
    @NotNull(message = "A senha não pode ser nula")
    @NotEmpty(message = "A senha não pode ser vazia")
    private String senha;

    @NotNull(message = "A função não pode ser nula")
    private RoleUser role;

    // Endereço Residencial
    @NotNull(message = "O número da residência não pode ser nulo")
    @NotEmpty(message = "O número da residência não pode ser vazio")
    @Size(min = 1, max = 7, message = "O número da residência deve ter entre 1 e 10 caracteres")
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

    public void preencherEndereco() {
    if (this.cep != null && !this.cep.isEmpty()) {
        EnderecoCep endereco = enderecoService.buscaEnderecoCep(this.cep); // Passa apenas o CEP como String
        if (endereco != null) {
            String logradouro = endereco.getLogradouro();
            String localidade = endereco.getLocalidade();

            // Verifica se logradouro ou localidade são nulos ou vazios
            if (logradouro == null || logradouro.isEmpty() || localidade == null || localidade.isEmpty()) {
                throw new IllegalArgumentException(
                        "Erro ao preencher endereço: Rua ou cidade não encontrados para o CEP informado.");
            }

            this.setRua(logradouro);
            this.setCidade(localidade);
        } else {
            throw new IllegalArgumentException(
                    "Erro ao preencher endereço: Nenhum endereço encontrado para o CEP informado.");
        }
    }
}

    // Métodos

    // Métodos Authentication
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }
    

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.nome;
    }

    // Formata o CEP em "00000-000"
    public void formatarCep() {
        if (this.cep != null && this.cep.matches("\\d{8}")) { // Verifica se o CEP tem 8 dígitos
            this.cep = this.cep.substring(0, 5) + "-" + this.cep.substring(5);
        }
    }

}