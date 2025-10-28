# IARA API SQL

Desenvolvimento da *API relacional* do projeto IARA, responsável por integrar e gerenciar os dados armazenados em *bancos SQL (PostgreSQL)*.
Esta API atua como a *camada de persistência e comunicação* entre os módulos do ecossistema *IARA*.

---

## 📚 Sumário

* [💡 Sobre o Projeto](#-sobre-o-projeto)
* [⚙️ Tecnologias Utilizadas](#️-tecnologias-utilizadas)
* [🧩 Como Executar](#-como-executar)
* [🧰 Endpoints / Exemplos de Uso](#-endpoints--exemplos-de-uso)
* [👩‍💻 Autor](#-autor)

---

## 💡 Sobre o Projeto

O *IARA API SQL* foi desenvolvido em *Java (Spring Boot)* com o objetivo de gerenciar informações de produção, controle e monitoramento dos *ábacos industriais* do projeto *IARA*.
A aplicação fornece endpoints REST para manipulação de dados, armazenamento em *PostgreSQL* e integração com os demais serviços da plataforma.

**Principais funcionalidades**:

* CRUD completo de usuários

* Vínculo com gestor direto

* Associação com tipos de acesso

* Associação com fábrica

* Relação entre usuários e fábricas

* Listagem de usuários por fábrica

* Definição de perfis e permissões

* Consulta de acessos ativos por usuário

---

## ⚙️ Tecnologias Utilizadas

* *Linguagem:* Java 17
* *Framework:* Spring Boot
* *Banco de Dados:* PostgreSQL
* *Gerenciador de Dependências:* Maven
* *Documentação:* Swagger UI
* *Outros:* Docker, Spring Data JPA, Lombok, Spring Web

---

### 🐳 Usando Docker

```bash
# Cria a imagem
docker build -t iara-api-sql .

# Executa o container
docker run -p 8080:8080 iara-api-sql
```

---

### 🧱 Localmente

```bash
# Clone o repositório
git clone https://github.com/IARA-TECH/iara-api-sql.git

# Entre na pasta
cd iara-api-sql

# Compile o projeto
mvn clean install

# Execute a aplicação
mvn spring-boot:run
```


> ⚠️ Certifique-se de que o *PostgreSQL* esteja em execução e configurado corretamente no arquivo application.properties ou .env.

---

## 🧰 Endpoints / Exemplos de Uso

| Método | Endpoint                      | Descrição                               |
| ------ | ----------------------------- | --------------------------------------- |
| GET    | /api/v1/users                 | Lista todos os usuários                 |
| GET    | /api/v1/users/{id}            | Busca um usuário pelo ID                |
| POST   | /api/v1/users                 | Cadastra um novo usuário                |
| PUT    | /api/v1/users/{id}            | Atualiza um usuário pelo ID             |
| PUT    | /api/v1/users/deactivate/{id} | Desativa um usuário pelo ID             |
| PUT    | /api/v1/users/reactivate/{id} | Reativa um usuário pelo ID              |
| GET    | /api-docs                     | Documentação da API no Swagger          |

Exemplo de requisição:

```bash
curl -X POST http://localhost:8081/api/v1/users \
  -H "Content-Type: application/json" \
  -d '{
        "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
        "name": "string",
        "email": "string",
        "date_of_birth": "2025-10-28T13:13:28.849Z",
        "user_manager_name": "string",
        "user_manager_id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
        "gender_name": "string",
        "gender_id": 0,
        "factory_name": "string",
        "factory_id": 0,
        "user_photo_url": "string"
      }'
```
---

## 👩‍💻 Autor

*IARA Tech*

Projeto Interdisciplinar desenvolvido por alunos do *1º e 2º ano do Instituto J&F, com o propósito de **otimizar o registro e monitoramento de ábacos industriais*.

📍 São Paulo, Brasil
📧 [iaratech.oficial@gmail.com](mailto:iaratech.oficial@gmail.com)
🌐 [https://github.com/IARA-TECH](https://github.com/IARA-TECH)
