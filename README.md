# Challenge Back-End

Dando continuidade ao nosso processo, temos um desafio para te propor! \o/

Nossos associados são aficionados por Star Wars e com isso, queremos criar um jogo com algumas informações da franquia.

Para possibilitar a equipe de front criar essa aplicação, queremos desenvolver uma API que contenha os dados dos planetas.

Requisitos:
- A API deve ser REST
- Para cada planeta, os seguintes dados devem ser obtidos do banco de dados da aplicação, sendo inserido manualmente:
  - Nome
  - Clima
  - Terreno
- Para cada planeta também devemos ter a quantidade de aparições em filmes, que podem ser obtidas pela API pública do Star Wars: https://swapi.co/.

Funcionalidades desejadas:
-	Adicionar um planeta (com nome, clima e terreno)
-	Listar planetas
-	Buscar por nome
-	Buscar por ID
-	Remover planeta

**Linguagens que usamos:** Kotlin, Scala, Java  
**Bancos que usamos:**  PostgreSQL-11 with JSONB
# Solução
A solução para o desafio da Ame Digital foi feita utilizando Spring Boot e o banco de dados PostgreSQL-11 para o ambiente de produção e o H2 para os testes unitários. Toda a documentação da API foi toda feita no Swagger.

## Tecnologias
- Spring Boot
- Spring MVC
- Spring Data JPA
- Swagger
- PostgreSQL
- H2 Database
- JUnit 5
- Mockito
## Práticas
- SOLID
- API REST
- Utilização de DTO
- Injeção de Dependências
- Exception Handler
- Documentação pelo Swagger
- Testes Unitários
