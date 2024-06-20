package com.wzzy.virtualmovies.movie.repository;

import com.wzzy.virtualmovies.movie.Movie;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.UUID;

public class MovieRepository {

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

    public boolean deleteByTitulo(String titulo) {
        List<Movie> movies = findByTitulo(titulo);
        if (!movies.isEmpty()) {
            delete(movies.get(0));
            return true;
        }
        return false;
    }

    public Movie updateByTitulo(String titulo, Movie updatedMovie) {
        List<Movie> movies = findByTitulo(titulo);
        if (!movies.isEmpty()) {
            Movie existingMovie = movies.get(0);
            existingMovie.setAno(updatedMovie.getAno());
            existingMovie.setDuracaoEmMinutos(updatedMovie.getDuracaoEmMinutos());
            existingMovie.setGenero(updatedMovie.getGenero());
            existingMovie.setDiretor(updatedMovie.getDiretor());
            existingMovie.setRoteiristas(updatedMovie.getRoteiristas());
            existingMovie.setAtores(updatedMovie.getAtores());
            existingMovie.setPoster(updatedMovie.getPoster());
            existingMovie.setMetascore(updatedMovie.getMetascore());
            // Atualiza outros campos conforme necessário
            return save(existingMovie);
        } else {
            throw new RuntimeException("Movie not found with title: " + titulo);
        }
    }
}
