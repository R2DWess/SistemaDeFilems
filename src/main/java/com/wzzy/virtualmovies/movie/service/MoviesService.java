package com.wzzy.virtualmovies.movie.service;

import com.wzzy.virtualmovies.movie.Movie;
import com.wzzy.virtualmovies.movie.repository.MovieRepository;
import com.wzzy.virtualmovies.usuarios.cadastrar.model.CadastrarUserModel;

import javax.persistence.EntityManager;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class MoviesService {

    private MovieRepository movieRepository;

    public MoviesService(EntityManager em) {
        this.movieRepository = new MovieRepository(em);
    }

    private final Map<UUID, Movie> movies = new ConcurrentHashMap<>();
    private final Map<UUID, CadastrarUserModel> users = new ConcurrentHashMap<>();

    public List<String> findAllGenres() {
        return movies.values().stream()
                // Esse trecho
                .flatMap(movie -> movie.getGenero().stream())
                .distinct()
                .collect(Collectors.toList());
    }


    public Movie save(Movie movie) {
        if (movie.getId() == null) {
            movie.setId(UUID.randomUUID().toString());
        }
        movies.put(UUID.fromString(movie.getId()), movie);
        return movie;
    }

    public List<Movie> findAll() {
        return new ArrayList<>(movies.values());
    }

    public Movie findById(UUID id) {
        return movies.get(id);
    }

    public boolean deleteById(UUID id) {
        movies.remove(id);
        return false;
    }

    public void deleteByTitulo(String titulo) {
        movies.values().removeIf(movie -> titulo.equals(movie.getTitulo()));
    }

    public Movie favoriteMovie(String socialName, String titulo) {
        Optional<CadastrarUserModel> userOptional = users.values().stream()
                .filter(user -> socialName.equals(user.getSocialname()))
                .findFirst();
        if (userOptional.isPresent()) {
            CadastrarUserModel user = userOptional.get();
            Optional<Movie> movieOptional = movies.values().stream()
                    .filter(movie -> titulo.equals(movie.getTitulo()))
                    .findFirst();
            if (movieOptional.isPresent()) {
                Movie movie = movieOptional.get();
                user.getFavoriteMovies().add(movie);
                return movie;
            }
        }
        return null;
    }

    public Movie addMovieToList(UUID movieId, UUID userId) {
        Movie movie = findById(movieId);
        CadastrarUserModel user = users.get(userId);
        if (movie != null && user != null) {
            user.getMovieList().add(movie);
            return movie;
        }
        return null;
    }

    public String getMovieVideoUrl(UUID id) {
        Movie movie = findById(id);
        if (movie != null) {
            return movie.getVideoUrl();
        }
        return null;
    }

    public boolean favoriteMovieBySocialNameAndTitle(String socialName, String titulo) {
        Optional<CadastrarUserModel> userOptional = users.values().stream()
                .filter(user -> socialName.equals(user.getSocialname()))
                .findFirst();
        if (userOptional.isPresent()) {
            CadastrarUserModel user = userOptional.get();
            Optional<Movie> movieOptional = movies.values().stream()
                    .filter(movie -> titulo.equals(movie.getTitulo()))
                    .findFirst();
            if (movieOptional.isPresent()) {
                Movie movie = movieOptional.get();
                user.getFavoriteMovies().add(movie);
                return true;
            }
        }
        return false;
    }

    public List<Movie> findByCategory(String category) {
        return movies.values().stream()
                .filter(movie -> movie.getGenero().contains(category))
                .collect(Collectors.toList());
    }

    public List<Movie> findByTitulo(String titulo) {
        return movies.values().stream()
                .filter(movie -> titulo.equals(movie.getTitulo()))
                .collect(Collectors.toList());
    }

    public List<Movie> getAllMovies() {
        return new ArrayList<>(movies.values());
    }
}