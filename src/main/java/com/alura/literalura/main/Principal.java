package com.alura.literalura.main;

import com.alura.literalura.model.*;
import com.alura.literalura.repository.AutorRepositorio;
import com.alura.literalura.repository.LivroRepositorio;
import com.alura.literalura.service.ConsumoApi;
import com.alura.literalura.service.ConverteDados;
import com.alura.literalura.model.RespostaApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    private final Scanner leitura = new Scanner(System.in);
    private final ConsumoApi consumo = new ConsumoApi();
    private final ConverteDados conversor = new ConverteDados();
    private final String endereco = "http://gutendex.com/books/?";
    @Autowired
    private final LivroRepositorio repositorioL;
    @Autowired
    private final AutorRepositorio repositorioA;

    public Principal(LivroRepositorio repositorioL, AutorRepositorio repositorioA) {
        this.repositorioL = repositorioL;
        this.repositorioA = repositorioA;
    }

    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    -----------------------------------------------
                    Escolha o número de sua opção:
                    1 - Buscar livro pelo título
                    2 - Buscar autor pelo nome
                    3 - Buscar livros por autor
                    4 - Listar livros registrados
                    5 - Listar autores registrados
                    6 - Listar autores vivos em um determinado ano
                    7 - Listar livros em um determinado idioma
                    8 - Top 10 livros mais baixados
                    0 - Sair
                    """;

            System.out.println(menu);

            try {
                opcao = leitura.nextInt();
                leitura.nextLine();

                switch (opcao) {
                    case 1:
                        buscarLivro();
                        break;
                    case 2:
                        buscarAutor();
                        break;
                    case 3:
                        buscarLivrosPorAutor();
                        break;
                    case 4:
                        listarLivros();
                        break;
                    case 5:
                        listarAutores();
                        break;
                    case 6:
                        listarAutoresVivos();
                        break;
                    case 7:
                        listarLivrosEmIdioma();
                        break;
                    case 8:
                        topDezMaisBaixados();
                        break;
/// Casos referentes às funções utilizadas para registrar livros e autores ao banco de dados
//                    case 9:
//                        registrarLivro();
//                        break;
//                    case 10:
//                        registrarAteCinco();
//                        break;
                    case 0:
                        System.out.println("Saindo...");
                        break;
                    default:
                        System.out.println("Opção inválida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida, digite apenas números. " + e);
                leitura.nextLine();
            }
        }
    }

    private void topDezMaisBaixados() {
        Pageable topDez = PageRequest.of(0, 10);

        List<Livro> livroBusca = repositorioL.top10LivrosMaisBaixados(topDez);

        if (!livroBusca.isEmpty()) {
            livroBusca.forEach(Impressao::imprimirLivro);
        } else {
            System.out.println("Nenhum livro encontrado.");
        }
    }

    private void listarLivrosEmIdioma() {
        System.out.println("""
                Insira um idioma para realizar a busca:
                de - Alemão
                es - Espanhol
                en - Inglês
                fr - Francês
                pt - Português
                """);

        var codigo = leitura.nextLine();

        try {
            Idioma idioma = Idioma.valueOf(codigo);

            List<Livro> livroBusca = repositorioL.findByIdioma(idioma);

            if (!livroBusca.isEmpty()) {
                livroBusca.forEach(Impressao::imprimirLivro);
            } else {
                System.out.println("Não há livros registrados no idioma " + idioma + ".");
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Idioma inválido, tente novamente.");
        }
    }

    private void listarAutoresVivos() {
        System.out.println("Digite o ano para listar autores vivos: ");

        var ano = leitura.nextInt();
        leitura.nextLine();

        List<Autor> autorBusca = repositorioA.findAutoresVivosNoAno(ano);

        if (!autorBusca.isEmpty()) {
            autorBusca.forEach(Impressao::imprimirAutor);
        } else {
            System.out.println("Sem autores vivos no ano " + ano + ".");
        }
    }

    private void buscarLivrosPorAutor() {
        System.out.println("Digite o nome do autor para busca: ");

        var nome = leitura.nextLine();

        List<Autor> autorBusca = repositorioA.findByNomeContainingIgnoreCase(nome);

        if (!autorBusca.isEmpty()) {
            Set<Livro> livros = new HashSet<>();

            for (Autor autor : autorBusca) {
                livros.addAll(repositorioL.findByAutor(autor));
            }

            if (!livros.isEmpty()) {
                livros.forEach(Impressao::imprimirLivro);
            } else {
                System.out.println("Nenhum livro encontrado para o autor.");
            }
        } else {
            System.out.println("Não há autores registrados com o nome " + nome + ".");
        }
    }

    private void buscarAutor() {
        System.out.println("Digite o nome do autor para busca: ");

        var nomeAutor = leitura.nextLine();

        List<Autor> autorBusca = repositorioA.findByNomeContainingIgnoreCase(nomeAutor);

        if (!autorBusca.isEmpty()) {
            autorBusca.forEach(Impressao::imprimirAutor);
        } else {
            System.out.println("Não há autores registrados com o nome " + nomeAutor + ".");
        }
    }

    private void buscarLivro() {
        System.out.println("Digite o título a ser buscado: ");

        var nomeLivro = leitura.nextLine();

        List<Livro> livrosBusca = repositorioL.findByTituloContainingIgnoreCase(nomeLivro);

        if (!livrosBusca.isEmpty()) {
            livrosBusca.forEach(Impressao::imprimirLivro);
        } else {
            System.out.println("Não há livros registrados com o nome " + nomeLivro + ".");
        }
    }

    private void listarLivros() {
        List<Livro> livros = repositorioL.findAll();

        livros.forEach(Impressao::imprimirLivro);
    }

    private void listarAutores() {
        List<Autor> autores = repositorioA.findAll();

        autores.forEach(Impressao::imprimirAutor);
    }
/// Funções utilizadas para o registro de livros e autores ao banco de dados
//    private void registrarLivro() {
//        DadosLivro dados = getDadosLivro();
//
//        if (dados.autor().stream().noneMatch(a -> a.dataNascimento() != null)) {
//            System.out.println("Livro ignorado: Autores inválidos");
//            return;
//        }
//
//        List<Livro> livrosPorTitulo = repositorioL.findAllByTitulo(dados.titulo());
//
//        boolean registrado = livrosPorTitulo.stream()
//                .anyMatch(l -> l.getAutor().stream()
//                        .anyMatch(a -> a.getNome().equalsIgnoreCase(dados.autor().get(0).nome())));
//
//        if (registrado) {
//            System.out.println("Livro já registrado.");
//            return;
//        }
//
//        Set<Autor> autor = dados.autor().stream()
//                .filter(a -> a.dataNascimento() != null)
//                .map(d -> repositorioA.findByNome(d.nome())
//                        .orElseGet(() -> {
//                            Autor novoAutor = new Autor(d.nome(), d.dataNascimento(), d.dataFalecimento());
//                            return repositorioA.save(novoAutor);
//                        }))
//                .collect(Collectors.toSet());
//
//        Livro livro = new Livro(dados);
//
//        livro.setAutor(autor);
//
//        Impressao.imprimirLivro(livro);
//
//        repositorioL.save(livro);
//    }
//
//    private DadosLivro getDadosLivro() {
//        System.out.println("Digite o nome para busca");
//
//        var nome = leitura.nextLine();
//
//        var json = consumo.obterDados(endereco + "search=" + nome.replace(" ", "+"));
//
//        RespostaApi resposta = conversor.obterDados(json, RespostaApi.class);
//
//        return resposta.resposta().get(0);
//    }
//
//    private void registrarAteCinco() {
//        List<DadosLivro> dadosPorAutor = getDadosAteCinco();
//
//        dadosPorAutor.stream()
//                .limit(5)
//                .forEach(dados -> {
//                    if (dados.autor().stream().noneMatch(a -> a.dataNascimento() != null)) {
//                        System.out.println("Livro ignorado: Autores inválidos");
//                        return;
//                    }
//
//                    boolean registrado = repositorioL.findAllByTitulo(dados.titulo()).stream()
//                            .anyMatch(l -> l.getAutor().stream()
//                                    .anyMatch(a -> a.getNome().equalsIgnoreCase(dados.autor().get(0).nome())));
//                    if (registrado) {
//                        System.out.println("Livro já registrado.");
//                        return;
//                    }
//                    Set<Autor> autor = dados.autor().stream()
//                            .map(d -> repositorioA.findByNome(d.nome())
//                                    .orElseGet(() -> repositorioA.save(
//                                            new Autor(d.nome(), d.dataNascimento(), d.dataFalecimento())
//                                    )))
//                            .collect(Collectors.toSet());
//
//                    Livro livro = new Livro(dados);
//
//                    livro.setAutor(autor);
//
//                    repositorioL.save(livro);
//
//                    Impressao.imprimirLivro(livro);
//                });
//    }
//
//    private List<DadosLivro> getDadosAteCinco() {
//        System.out.println("Digite o nome para busca");
//
//        var nome = leitura.nextLine();
//
//        var json = consumo.obterDados(endereco + "search=" + nome.replace(" ", "+"));
//
//        RespostaApi resposta = conversor.obterDados(json, RespostaApi.class);
//
//        return resposta.resposta();
//    }
}
