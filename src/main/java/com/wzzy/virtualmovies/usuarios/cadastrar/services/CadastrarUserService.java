package com.wzzy.virtualmovies.usuarios.cadastrar.services;

import com.wzzy.virtualmovies.movie.Movie;
import com.wzzy.virtualmovies.usuarios.cadastrar.model.CadastrarUserModel;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CadastrarUserService {

    private EntityManager em;

    public CadastrarUserService(EntityManager em) {
        this.em = em;
    }

    public List<CadastrarUserModel> findAll() {
        TypedQuery<CadastrarUserModel> query = em.createQuery("SELECT u FROM CadastrarUserModel u", CadastrarUserModel.class);
        return query.getResultList();
    }

    public Optional<CadastrarUserModel> findById(UUID id) {
        return Optional.ofNullable(em.find(CadastrarUserModel.class, id));
    }

    public CadastrarUserModel save(CadastrarUserModel user) {
        em.getTransaction().begin();
        if (user.getId() == null) {
            user.setId(UUID.randomUUID());
            em.persist(user);
        } else {
            em.merge(user);
        }
        em.getTransaction().commit();
        return user;
    }

    public boolean deleteById(UUID id) {
        em.getTransaction().begin();
        CadastrarUserModel user = em.find(CadastrarUserModel.class, id);
        if (user != null) {
            em.remove(user);
            em.getTransaction().commit();
            return true;
        }
        em.getTransaction().rollback();
        return false;
    }

    public boolean addFavoriteMovie(UUID userId, UUID movieId) {
        em.getTransaction().begin();
        CadastrarUserModel user = em.find(CadastrarUserModel.class, userId);
        Movie movie = em.find(Movie.class, movieId);
        if (user != null && movie != null) {
            user.getFavoriteMovies().add(movie);
            em.merge(user);
            em.getTransaction().commit();
            return true;
        }
        em.getTransaction().rollback();
        return false;
    }
}
