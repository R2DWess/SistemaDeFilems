package com.wzzy.virtualmovies.movie.repository;

import com.wzzy.virtualmovies.movie.Movie;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

public class MovieRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public MovieRepository(EntityManager em) {
        this.entityManager = em;
    }

    public Movie save(Movie movie) {
        if (movie.getId() == null) {
            entityManager.persist(movie);
        } else {
            movie = entityManager.merge(movie);
        }
        return movie;
    }

    public Movie findById(UUID id) {
        return entityManager.find(Movie.class, id);
    }

    public List<Movie> findAll() {
        return entityManager.createQuery("SELECT m FROM Movie m", Movie.class).getResultList();
    }

    public List<Movie> findByTitulo(String titulo) {
        TypedQuery<Movie> query = entityManager.createQuery("SELECT m FROM Movie m WHERE m.titulo = :titulo", Movie.class);
        query.setParameter("titulo", titulo);
        return query.getResultList();
    }

    public void delete(Movie movie) {
        if (entityManager.contains(movie)) {
            entityManager.remove(movie);
        } else {
            entityManager.remove(entityManager.merge(movie));
        }
    }
}
