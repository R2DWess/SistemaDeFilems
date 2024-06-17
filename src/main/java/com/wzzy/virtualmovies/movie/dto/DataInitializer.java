package com.wzzy.virtualmovies.movie.dto;

import com.wzzy.virtualmovies.movie.Movie;
import com.wzzy.virtualmovies.movie.service.MoviesService;
import com.wzzy.virtualmovies.movie.repository.MovieRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;

public class DataInitializer {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("systemMoviesUnit");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        try {
            MovieRepository movieRepository = new MovieRepository(em);

            MoviesService moviesService = new MoviesService((EntityManager) movieRepository);

            initializeMovies(moviesService);

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.err.println("Initialization failed: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }

    private static void initializeMovies(MoviesService moviesService) {
        Movie avatar = new Movie();
        avatar.setTitulo("Avatar");
        avatar.setAno(2009);
        avatar.setDuracaoEmMinutos(162);
        avatar.setGenero(Arrays.asList("Ação", "Aventura", "Fantasia"));
        avatar.setDiretor("James Cameron");
        avatar.setRoteiristas(Arrays.asList("James Cameron"));
        avatar.setAtores(Arrays.asList("Sam Worthington", "Zoe Saldana", "Sigourney Weaver", "Stephen Lang"));
        avatar.setPoster("https://xl.movieposterdb.com/09_08/2009/499549/xl_499549_cd69e2ea.jpg");
        avatar.setMetascore(83);

        moviesService.save(avatar);

        System.out.println("Avatar initialized successfully.");
    }
}
