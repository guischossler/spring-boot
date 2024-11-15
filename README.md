# API de Livraria 📚

Este repositório contém uma pequena API de livraria desenvolvida enquanto estudo Spring Boot. O projeto explora conceitos essenciais do framework para construção de APIs.

## Endpoints

Para todas as entidades, seguem os endpoints e exemplos de corpo para operações padrão.

### Operações Padrão

- **Consultar todas**: `GET /api/{entity}`
- **Consultar por ID**: `GET /api/{entity}/{id}`
- **Cadastrar**: `POST /api/{entity}` (verificar o que deve ser passado no body)
- **Deletar por ID**: `DELETE /api/{entity}/{id}`
- **Atualizar**: `PUT /api/{entity}/{id}` (verificar o que deve ser passado no body)
- **Atualizar parcialmente**: `PATCH /api/{entity}/{id}` (verificar o que deve ser passado no body)

### Entidades

| Entidade | URL Base          | Exemplo de Corpo para Cadastro/Atualização (JSON)                                         |
|----------|--------------------|-------------------------------------------------------------------------------------------|
| Person   | `/api/persons`     | `{ "name": "Pessoa Teste", "email": "pessoa.teste@example.com" }`                         |
| Book     | `/api/books`       | `{ "title": "Livro Teste", "author": "Autor Exemplo" }`                                   |
| Rental   | `/api/rentals`     | `{ "personId": 1, "bookId": 1 }`                                                          |
