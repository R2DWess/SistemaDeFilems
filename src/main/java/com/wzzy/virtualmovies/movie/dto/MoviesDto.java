package com.wzzy.virtualmovies.movie.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class MoviesDto {
    private UUID id;
    private String titulo;
    private int ano;
    private int duracaoEmMinutos;
    private List<String> genero;
    private String diretor;
    private List<String> roteiristas;
    private List<String> atores;
    private String poster;
    private int metascore;
}
