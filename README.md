# API de Livraria 📚
Este repositório contém uma pequena API de livraria desenvolvida enquanto estudo Spring Boot. O projeto busca explorar conceitos essenciais desse framework para construção de APIs.

- **Consultar todas as pessoas**: [GET](http://localhost:8080/api/persons) - http://localhost:8080/api/persons
- **Consultar pessoa por ID**: [GET](http://localhost:8080/api/persons/1) - http://localhost:8080/api/persons/1)

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
