<h1 align="center"> LiterAlura </h1>
<p align="center"> Projeto para curso Back-End da Alura. </p>
<p align="center"> Usa do Gutendex para obter informações de livros e autores, salvá-los em um banco de dados e exibir informações salvas. </p>
<p align="center"> É necessário um software como o IntelliJ IDEA para ser devidamente acessado e utilizado. </p>

# Funcionalidade
Ao iniciar, a aplicação irá apresentar no console um menu indicando os comandos disponíveis:
<p>1 - Buscar livro pelo título</p>
<p>2 - Buscar autor pelo nome</p>
<p>3 - Buscar livros por autor</p>
<p>4 - Listar livros registrados</p>
<p>5 - Listar autores registrados</p>
<p>6 - Listar autores vivos em um determinado ano</p>
<p>7 - Listar livros em um determinado idioma</p>
<p>8 - Top 10 livros mais baixados</p>
<p>0 - Sair</p>

<h4> Um pouco mais de detalhes no que cada um faz, em ordem crescente: </h4>

  0 - Irá finalizar a aplicação.

  Os seguintes buscarão os dados indicados no banco de dados:

  1 - Irá buscar e listar no console quaisquer livros que contenham, em seu título, a String dada pelo usuário.

  2 - Irá buscar e listar no console quaisquer autores que contenham, em seu nome, a String dada pelo usuário.

  3 - Irá buscar e listar no console quaisquer livros que possuam um autor que contenha, em seu nome, a String dada pelo usuário.

  4 - Irá listar no console todos os livros até então salvos no banco de dados.

  5 - Irá listar no console todos os autores até então salvos no banco de dados.

  6 - Irá listar no console todos os autores que possuam data de nascimento menor que ou igual e uma data de falecimento maior que o Integer dado pelo usuário.

  7 - Irá listar no console os idiomas disponíveis e, após entrada do usuário, fará o memso com todos os livros disponíveis no idioma escolhido.

  8 - Irá filtrar os 10 livros no banco de dados com maior contagem de downloads e irá listá-los em ordem decrescente no console. 

Há também outros dois comandos não listados, utilizados para buscar informações no Gutendex e salvá-las no banco de dados. Eles foram comentados no código e portanto não podem ser utilizados por padrão. (Linhas 79-85 e 216-311 da classe Principal)

9 - Busca no Gutendex o primeiro resultado correspondente à String fornecida e o salva no banco de dados, a String pode ser relacionada tanto ao título do livro quanto ao nome do autor.

10 - Busca no Gutendex os primeiros 5 resultados correspondente à String fornecida e os salva no banco de dados, a String pode ser relacionada tanto aos títulos dos livros quanto aos nomes dos autores.

# Tecnologias Utilizadas
- ```Java 17```
- ```IntelliJ IDEA```
