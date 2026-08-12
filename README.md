# Tribunal RJ - Gestão de Usuários

Aplicação Full Stack desenvolvida para gerenciamento e consulta de usuários do Tribunal RJ.

A solução foi construída utilizando **Spring Boot + Java 21** no backend e **Angular + Angular Material** no frontend, seguindo boas práticas de arquitetura, separação de responsabilidades, segurança, testes automatizados e containerização.

O ambiente completo pode ser executado através do **Docker Compose**, incluindo:

- Frontend Angular
- Backend Spring Boot
- PostgreSQL
- Flyway para versionamento do banco de dados

---

# Tecnologias utilizadas

## Backend

- Java 21
- Spring Boot 4
- Spring Web MVC
- Spring Data JPA
- Hibernate
- Spring Security
- Basic Authentication
- Bean Validation
- Flyway
- PostgreSQL
- Swagger / OpenAPI
- JUnit
- Mockito
- Maven
- Docker

## Frontend

- Angular
- TypeScript
- Angular Material
- RxJS
- HTML
- SCSS
- Nginx
- Docker

## Infraestrutura

- Docker
- Docker Compose
- Docker Hub
- PostgreSQL

---

# Arquitetura

O backend foi estruturado utilizando conceitos de **Arquitetura Hexagonal (Ports and Adapters)**.

A separação das responsabilidades permite reduzir o acoplamento entre as regras de negócio e tecnologias externas, como banco de dados e camada HTTP.

Estrutura conceitual:

```text
                    ┌──────────────────────┐
                    │       Angular        │
                    │      Frontend        │
                    └──────────┬───────────┘
                               │
                               │ HTTP / REST
                               ▼
                    ┌──────────────────────┐
                    │     Controller       │
                    │    Web Adapter       │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │      Use Case        │
                    │ Application Service  │
                    └──────────┬───────────┘
                               │
                         Output Ports
                               │
                               ▼
                    ┌──────────────────────┐
                    │ Persistence Adapter  │
                    │    JPA / Queries     │
                    └──────────┬───────────┘
                               │
                               ▼
                    ┌──────────────────────┐
                    │     PostgreSQL       │
                    └──────────────────────┘
```

Essa abordagem facilita:

- manutenção;
- testes;
- baixo acoplamento;
- separação de responsabilidades;
- evolução da aplicação;
- substituição de tecnologias externas.

---

# Funcionalidades

A aplicação disponibiliza operações para gerenciamento de usuários.

Principais funcionalidades:

- Cadastro de usuário
- Consulta de usuário por ID
- Consulta de usuários por origem
- Exclusão de usuário
- Validação dos dados de entrada
- Tratamento de exceções
- Autenticação HTTP Basic
- Documentação da API através do Swagger/OpenAPI

---

# Swagger / OpenAPI

A API possui documentação interativa utilizando **Swagger/OpenAPI**.

Após iniciar a aplicação, acesse:

### Swagger UI

`http://localhost:8080/swagger-ui/index.html`

### OpenAPI JSON

`http://localhost:8080/v3/api-docs`

---

# Autenticação

Os endpoints da API utilizam **HTTP Basic Authentication**.

Para realizar os testes pelo Swagger, clique no botão **Authorize** e utilize as credenciais padrão do ambiente local:

```text
Usuário: admin
Senha: admin123
```

Após a autenticação, os endpoints poderão ser executados diretamente pela interface do Swagger.