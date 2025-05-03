# 🌟 UserConfig

**UserConfig** é um sistema de gerenciamento de usuários que valida dados e utiliza uma API externa para buscar informações de endereço. Este projeto foi desenvolvido com **Spring Boot** e segue boas práticas de desenvolvimento, incluindo validações robustas e integração com serviços externos.

---

## 🚀 Funcionalidades

- **Gerenciamento de Usuários**:
  - Cadastro de usuários com validação de dados (nome, CPF, e-mail, senha, etc.).
  - Busca de usuários por CPF.

- **Validações Avançadas**:
  - Validação de CPF, e-mail e senha com regras específicas.
  - Mensagens de erro claras para entradas inválidas.

- **Integração com API Externa**:
  - Busca de endereço com base no CEP utilizando a API [ViaCEP](https://viacep.com.br/).

- **Segurança**:
  - Implementação de autenticação e autorização com Spring Security.
  - Suporte a diferentes roles de usuário (`ROLE_USER` e `ROLE_ADMIN`).

---

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.4.4**
  - Spring Web
  - Spring Data JPA
  - Spring Security
- **Lombok** para reduzir boilerplate no código.
- **H2 Database** (ou outro banco de dados relacional, configurável).
- **API ViaCEP** para busca de endereços.
