package com.wzzy.virtualmovies.movie.service;

import com.wzzy.virtualmovies.movie.Movie;
import com.wzzy.virtualmovies.movie.repository.MovieRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.UUID;

public class MoviesService {
    @PersistenceContext
    private EntityManager entityManager;

    private MovieRepository movieRepository;

    public MoviesService(EntityManager em) {
        this.entityManager = em;
        this.movieRepository = new MovieRepository(em);
    }

    public Movie save(Movie movie) {
        entityManager.getTransaction().begin();
        if (movie.getId() == null) {
            movie.setId(UUID.randomUUID());
        }
        movie = entityManager.merge(movie);
        entityManager.getTransaction().commit();
        return movie;
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie findById(UUID id) {
        return movieRepository.findById(id);
    }

    public List<Movie> findByTitulo(String titulo) {
        return movieRepository.findByTitulo(titulo);
    }

    public boolean deleteById(UUID id) {
        Movie movie = findById(id);
        if (movie != null) {
            movieRepository.delete(movie);
            return true;
        }
        return false;
    }
}
