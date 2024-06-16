package com.wzzy.virtualmovies.usuarios.login.repository;

import com.wzzy.virtualmovies.usuarios.login.model.LoginUserModel;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class LoginUserRepository {
    private EntityManager em;

    public LoginUserRepository(EntityManager em) {
        this.em = em;
    }

    public Optional<LoginUserModel> findByEmail(String email) {
        TypedQuery<LoginUserModel> query = em.createQuery(
                "SELECT u FROM LoginUserModel u WHERE u.email = :email", LoginUserModel.class);
        query.setParameter("email", email);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public boolean existsByEmail(String email) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(u) FROM LoginUserModel u WHERE u.email = :email", Long.class);
        query.setParameter("email", email);
        return query.getSingleResult() > 0;
    }

    public Optional<LoginUserModel> findById(UUID id) {
        return Optional.ofNullable(em.find(LoginUserModel.class, id));
    }

    public LoginUserModel save(LoginUserModel user) {
        if (user.getId() == null) {
            em.persist(user);
        } else {
            em.merge(user);
        }
        return user;
    }

    public void delete(LoginUserModel user) {
        if (em.contains(user)) {
            em.remove(user);
        } else {
            em.remove(em.merge(user));
        }
    }

    public boolean validateLogin(String email, String password) {
        TypedQuery<LoginUserModel> query = em.createQuery(
                "SELECT u FROM LoginUserModel u WHERE u.email = :email AND u.password = :password", LoginUserModel.class);
        query.setParameter("email", email);
        query.setParameter("password", password);
        try {
            query.getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    // New methods added here

    public boolean existsByCpf(String cpf) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(u) FROM LoginUserModel u WHERE u.cpf = :cpf", Long.class);
        query.setParameter("cpf", cpf);
        return query.getSingleResult() > 0;
    }

    public List<LoginUserModel> findAll() {
        return em.createQuery("SELECT u FROM LoginUserModel u", LoginUserModel.class).getResultList();
    }

    public boolean existsBySocialname(String socialname) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(u) FROM LoginUserModel u WHERE u.socialname = :socialname", Long.class);
        query.setParameter("socialname", socialname);
        return query.getSingleResult() > 0;
    }
}
