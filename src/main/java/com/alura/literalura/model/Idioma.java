package com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.util.Arrays;

public enum Idioma {
    de("de"),
    en("en"),
    es("es"),
    fr("fr"),
    pt("pt");

    private final String codigo;

    Idioma(String codigo) {
        this.codigo = codigo;
    }

    @JsonCreator
    public static Idioma fromCodigo(String codigo) {
        return Arrays.stream(values())
                .filter(i -> i.codigo.equalsIgnoreCase(codigo))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Idioma inválido: " + codigo));
    }
}