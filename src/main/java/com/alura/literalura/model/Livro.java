package com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    private int downloads;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "livro_autores",
            joinColumns = @JoinColumn(name = "livro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )
    private Set<Autor> autor;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "livro_idiomas", joinColumns = @JoinColumn(name = "livro_id"))
    private Set<Idioma> idiomas;

    public Livro() {}

    public Livro(DadosLivro dados) {
        this.titulo = dados.titulo();
        this.autor = dados.autor().stream()
                .map(a -> new Autor(a.nome(), a.dataNascimento(), a.dataFalecimento()))
                .collect(Collectors.toSet());
        this.idiomas = dados.idiomas().stream()
                .map(Idioma::fromCodigo)
                .collect(Collectors.toSet());
        this.downloads = dados.downloads();
    }

    public String getTitulo() {
        return titulo;
    }

    public Set<Autor> getAutor() {
        return autor;
    }

    public Set<Idioma> getIdiomas() {
        return idiomas;
    }

    public int getDownloads() {
        return downloads;
    }

    public void setAutor(Set<Autor> autor) {
        this.autor = autor;
    }
}