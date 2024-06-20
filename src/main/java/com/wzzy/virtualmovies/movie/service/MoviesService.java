package com.wzzy.virtualmovies.movie.service;

import com.wzzy.virtualmovies.movie.Movie;
import com.wzzy.virtualmovies.movie.repository.MovieRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

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

    public Movie getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    public Movie save(Movie movie) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Movie savedMovie = movieRepository.save(movie);
            transaction.commit();
            return savedMovie;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao salvar o filme", e);
        }
    }

    public Movie updateById(Long id, Movie updatedMovie) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Movie existingMovie = getMovieById(id);
            if (existingMovie == null) {
                return null;
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
            Movie savedMovie = movieRepository.save(existingMovie);
            transaction.commit();
            return savedMovie;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao atualizar o filme", e);
        }
    }

    public boolean deleteById(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            boolean success = movieRepository.deleteById(id);
            transaction.commit();
            return success;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Erro ao deletar o filme", e);
        }
    }
}
