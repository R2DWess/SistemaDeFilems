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

    public List<Movie> findByCategory(String category) {
        return entityManager.createQuery("SELECT m FROM Movie m WHERE m.category = :category", Movie.class)
                .setParameter("category", category)
                .getResultList();
    }

    public List<Movie> findByTitulo(String titulo) {
        return entityManager.createQuery("SELECT m FROM Movie m WHERE m.titulo = :titulo", Movie.class)
                .setParameter("titulo", titulo)
                .getResultList();
    }

    public List<Movie> findByDiretor(String diretor) {
        return entityManager.createQuery("SELECT m FROM Movie m WHERE m.diretor = :diretor", Movie.class)
                .setParameter("diretor", diretor)
                .getResultList();
    }

    public List<Movie> findByAno(int ano) {
        return entityManager.createQuery("SELECT m FROM Movie m WHERE m.ano = :ano", Movie.class)
                .setParameter("ano", ano)
                .getResultList();
    }

    public List<Movie> findByGenero(String genero) {
        return entityManager.createQuery("SELECT m FROM Movie m WHERE :genero MEMBER OF m.genero", Movie.class)
                .setParameter("genero", genero)
                .getResultList();
    }

    public List<Movie> findByMetascoreGreaterThan(int metascore) {
        return entityManager.createQuery("SELECT m FROM Movie m WHERE m.metascore > :metascore", Movie.class)
                .setParameter("metascore", metascore)
                .getResultList();
    }

    public void deleteByTitulo(String titulo) {
        entityManager.createQuery("DELETE FROM Movie m WHERE m.titulo = :titulo")
                .setParameter("titulo", titulo)
                .executeUpdate();
    }

    public void delete(Movie movie) {
        if (entityManager.contains(movie)) {
            entityManager.remove(movie);
        } else {
            entityManager.remove(entityManager.merge(movie));
        }
    }
}
