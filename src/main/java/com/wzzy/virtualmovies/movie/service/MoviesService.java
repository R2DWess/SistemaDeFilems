package com.wzzy.virtualmovies.movie.service;

import com.wzzy.virtualmovies.movie.Movie;
import com.wzzy.virtualmovies.movie.repository.MovieRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.UUID;

public class MoviesService {
    private final EntityManager entityManager;
    private final MovieRepository movieRepository;

    public MoviesService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.movieRepository = new MovieRepository(entityManager);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public Movie getMovieByTitulo(String titulo) {
        List<Movie> movies = movieRepository.findByTitulo(titulo);
        return movies.isEmpty() ? null : movies.get(0);
    }

    public void save(Movie movie) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            if (movie.getId() == null) {
                movie.setId(UUID.randomUUID());
            }
            movieRepository.save(movie);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao salvar o filme", e);
        }
    }

    public boolean updateByTitulo(String titulo, Movie updatedMovie) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Movie existingMovie = getMovieByTitulo(titulo);
            if (existingMovie == null) {
                return false;
            }
            existingMovie.setAno(updatedMovie.getAno());
            existingMovie.setCategory(updatedMovie.getCategory());
            existingMovie.setDiretor(updatedMovie.getDiretor());
            existingMovie.setDuracaoEmMinutos(updatedMovie.getDuracaoEmMinutos());
            existingMovie.setMetascore(updatedMovie.getMetascore());
            existingMovie.setPoster(updatedMovie.getPoster());
            existingMovie.setTitulo(updatedMovie.getTitulo());
            existingMovie.setVideoUrl(updatedMovie.getVideoUrl());
            existingMovie.setAtores(updatedMovie.getAtores());
            existingMovie.setGenero(updatedMovie.getGenero());
            existingMovie.setRoteiristas(updatedMovie.getRoteiristas());
            movieRepository.save(existingMovie);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao atualizar o filme", e);
        }
    }

    public boolean deleteByTitulo(String titulo) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Movie movie = getMovieByTitulo(titulo);
            if (movie == null) {
                return false;
            }
            movieRepository.delete(movie);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao deletar o filme", e);
        }
    }
}
