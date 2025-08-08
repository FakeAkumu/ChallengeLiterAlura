# LiterAlura - Challenge Back End Alura
Aplicação que obtém informações de livros e autores através do Gutendex, salva-as em um banco de dados e as retorna através do console.

---

## Tecnologias utilizadas
- Java 17
- Spring Boot 3.5.3
- Spring Data JPA
- Jackson Core
- PostgreSQL
- Maven

---

## Funcionalidades

- Busca de livros no banco de dados por título, autor, autores vivos em determinado ano ou idioma
- Busca de autores no banco de dados por nome
- Listagem de todos os livros ou autores salvos no banco de dados
- Listagem dos 10 livros mais baixados

---

## Utilização

### Pré-requisitos:

- Java 17
- PostgreSQL
- Maven
- Cliente REST: Postman ou Insomnia

---

### Preparação:

1. Edite application.properties
(Ou tenha as variáveis de ambiente necessárias para o funcionamento da aplicação)
  Por exemplo:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```

2. Crie o banco de dados necessário
   No exemplo anterior seria um banco de nome "literalura"

3. Inicie a aplicação pelo LiteraluraApplication

---

### Funções disponíveis:

No console será dado um menu, com o nome das funções e o número associado à elas:
```
1 - Buscar livro pelo título
2 - Buscar autor pelo nome
3 - Buscar livros por autor
4 - Listar livros registrados
5 - Listar autores registrados
6 - Listar autores vivos em um determinado ano
7 - Listar livros em um determinado idioma
8 - Top 10 livros mais baixados
9 - Registrar livro por título ou nome do autor
10 - Registrar até 5 livros por título ou nome do autor
0 - Sair
```

Para selecionar uma opção você deve digitar o número no console e pressionar enter, em seguida siga os passos que aparecerão no console.

  Por exemplo:

  Digitar 1 fará com que a aplicação peça que eu insira o título a ser buscado, após inserido será devolvido o resultado da busca.
  Após satisfeito com o resultado, digitar 0 encerrará a aplicação
Importante notar que toda interação entre o usuário e a aplicação ocorre pelo console.

Importante destacar as duas últimas opções, 9 e 10:
- Registrar livro por título ou nome do autor
  - Faz a busca pelo título de um livro ou nome de um autor no Gutendex e salvará o primeiro resultado
- Registrar até 5 livros por título ou nome do autor
  - Segue a mesma lógica do anterior, porém salva os primeiros cinco resultados.

---
