package com.wzzy.virtualmovies.movie.service;

import com.wzzy.virtualmovies.movie.Movie;
import com.wzzy.virtualmovies.movie.repository.MovieRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

public class MoviesService {

    private MovieRepository movieRepository;

    public MoviesService(EntityManager em) {
        this.movieRepository = new MovieRepository(em);
    }

    public Movie save(Movie movie) {
        if (movie.getId() == null) {
            movie.setId(UUID.fromString(UUID.randomUUID().toString()));
        }
        return movieRepository.save(movie);
    }


    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    public Movie findById(UUID id) {
        return movieRepository.findById(id);
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
