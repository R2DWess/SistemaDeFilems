package com.wzzy.virtualmovies.usuarios.cadastrar.model;

import com.wzzy.virtualmovies.movie.Movie;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_USERS_STREAM")
public class CadastrarUserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String fullname;

    private String socialname;

    @Column(nullable = false)
    private LocalDate birthdate;

    private String rg;

    @Column(nullable = false)
    private String cellphone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToMany
    private List<Movie> movieList;

    @OneToMany
    private List<Movie> favoriteMovies;

    @Column(nullable = false)
    private boolean isAdmin;

    public CadastrarUserModel() {
    }

    public CadastrarUserModel(String cpf, String fullname, String socialname, LocalDate birthdate, String rg, String cellphone, String email, String password, List<Movie> movieList, List<Movie> favoriteMovies, boolean isAdmin) {
        this.id = UUID.randomUUID();
        this.cpf = cpf;
        this.fullname = fullname;
        this.socialname = socialname;
        this.birthdate = birthdate;
        this.rg = rg;
        this.cellphone = cellphone;
        this.email = email;
        this.password = password;
        this.movieList = movieList;
        this.favoriteMovies = favoriteMovies;
        this.isAdmin = isAdmin;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getSocialname() {
        return socialname;
    }

    public void setSocialname(String socialname) {
        this.socialname = socialname;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public List<Movie> getFavoriteMovies() {
        return favoriteMovies;
    }

    public void setFavoriteMovies(List<Movie> favoriteMovies) {
        this.favoriteMovies = favoriteMovies;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}
