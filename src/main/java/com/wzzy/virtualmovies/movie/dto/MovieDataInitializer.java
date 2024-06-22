package com.wzzy.virtualmovies.movie.dto;

import com.wzzy.virtualmovies.movie.service.MoviesService;

public class MovieDataInitializer {
    public void initialize(MoviesService moviesService) {
        DataInitializer.initializeMovies(moviesService);
    }
}