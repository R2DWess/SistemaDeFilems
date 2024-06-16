package com.wzzy.virtualmovies.movie;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Data
@Entity
public class Movie {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false, length = 36)
    private UUID id;

    private String titulo;
    private int ano;
    private int duracaoEmMinutos;

    @ElementCollection
    private List<String> genero;

    private String diretor;

    @ElementCollection
    private List<String> roteiristas;

    @ElementCollection
    private List<String> atores;

    private String poster;
    private int metascore;
    private String videoUrl;
    private String category;

    public Movie() {
    }

    public Movie(UUID id, String titulo, int ano, int duracaoEmMinutos, List<String> genero, String diretor, List<String> roteiristas, List<String> atores, String poster, int metascore, String videoUrl, String category) {
        this.id = id;
        this.titulo = titulo;
        this.ano = ano;
        this.duracaoEmMinutos = duracaoEmMinutos;
        this.genero = genero;
        this.diretor = diretor;
        this.roteiristas = roteiristas;
        this.atores = atores;
        this.poster = poster;
        this.metascore = metascore;
        this.videoUrl = videoUrl;
        this.category = category;
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getDuracaoEmMinutos() {
        return duracaoEmMinutos;
    }

    public void setDuracaoEmMinutos(int duracaoEmMinutos) {
        this.duracaoEmMinutos = duracaoEmMinutos;
    }

    public List<String> getGenero() {
        return genero;
    }

    public void setGenero(List<String> genero) {
        this.genero = genero;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public List<String> getRoteiristas() {
        return roteiristas;
    }

    public void setRoteiristas(List<String> roteiristas) {
        this.roteiristas = roteiristas;
    }

    public List<String> getAtores() {
        return atores;
    }

    public void setAtores(List<String> atores) {
        this.atores = atores;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getMetascore() {
        return metascore;
    }

    public void setMetascore(int metascore) {
        this.metascore = metascore;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
