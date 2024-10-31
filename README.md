# API de Livraria 📚
Este repositório contém uma pequena API de livraria desenvolvida enquanto estudo Spring Boot. O projeto busca explorar conceitos essenciais desse framework para construção de APIs.

- **Consultar todas as pessoas**:
  - **Método**: [GET](http://localhost:8080/api/persons)
  - **URL**: http://localhost:8080/api/persons

- **Consultar pessoa por ID**: 
  - **Método**: [GET](http://localhost:8080/api/persons/1)
  - **URL**: http://localhost:8080/api/persons/{id}
  - ***Exemplo 1***: http://localhost:8080/api/persons/1
  - ***Exemplo 2***: http://localhost:8080/api/persons/4

- **Cadastrar pessoa**: 
  - **Método**: POST
  - **URL**: `http://localhost:8080/api/persons`
  - **Body**:
    ```json
    {
      "name": "Pessoa Teste",
      "email": "pessoa.teste@example.com"
    }
    ```
- **Deletar pessoa por ID**: 
  - **Método**: [DELETE](http://localhost:8080/api/persons/1)
  - **URL**: http://localhost:8080/api/persons/{id}
  - ***Exemplo 1***: http://localhost:8080/api/persons/1
  - ***Exemplo 2***: http://localhost:8080/api/persons/4
