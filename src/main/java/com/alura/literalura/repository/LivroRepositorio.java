package com.alura.literalura.repository;

import com.alura.literalura.model.Idioma;
import com.alura.literalura.model.Livro;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivroRepositorio extends JpaRepository<Livro, Long> {
    List<Livro> findAllByTitulo(String titulo);

    List<Livro> findByTituloContainingIgnoreCase(String titulo);

    @Query("SELECT l FROM Livro l JOIN l.idiomas i WHERE i = :idioma")
    List<Livro> findByIdioma(Idioma idioma);

    @Query("SELECT l FROM Livro l ORDER BY l.downloads DESC LIMIT 10")
    List<Livro> top10LivrosMaisBaixados(Pageable pageable);
}