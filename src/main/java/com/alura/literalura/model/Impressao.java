package com.alura.literalura.model;

import java.util.Set;
import java.util.stream.Collectors;

public class Impressao {
    public static void imprimirLivro(Livro livro) {
        String titulo = livro.getTitulo();

        Set<Autor> autores = livro.getAutor();
        String prefixoAutor = autores.size() > 1 ? "Autores:" : "Autor:";

        String autorFormatado = !livro.getAutor().isEmpty() ?
                livro.getAutor().stream()
                .map(Autor::getNome)
                .collect(Collectors.joining(" | "))
                : "Autor desconhecido";

        String idiomas = livro.getIdiomas() != null && !livro.getIdiomas().isEmpty() ?
                livro.getIdiomas().stream()
                .map(Impressao::traduzirIdioma)
                .collect(Collectors.joining(", "))
                : "Idioma desconhecido";

        int downloads = livro.getDownloads();

        System.out.printf("""
                ----- LIVRO -----
                Título: %s
                %s %s
                Idiomas: %s
                Downloads: %d
                -----------------
                
                """, titulo, prefixoAutor, autorFormatado, idiomas, downloads
        );
    }

    private static String traduzirIdioma(Idioma idioma) {
        return switch (idioma) {
            case de -> "Alemão";
            case en -> "Inglês";
            case es -> "Espanhol";
            case fr -> "Francês";
            case pt -> "Português";
        };
    }

    public static void imprimirAutor(Autor autor) {
        String nome = autor.getNome();
        int dataNascimento = autor.getAnoNascimento();
        int dataFalecimento = autor.getAnoFalecimento();

        System.out.printf("""
                ----- AUTOR(A) -----
                Nome: %s
                Nascimento: %d
                Morte: %d
                --------------------
                
                """, nome, dataNascimento, dataFalecimento);
    }
}
