package com.wzzy.virtualmovies.usuarios.login.repository;

import com.wzzy.virtualmovies.usuarios.login.model.LoginUserModel;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class LoginUserRepository {
    private EntityManager em;

    public LoginUserRepository(EntityManager em) {
        this.em = em;
    }

    public List<LoginUserModel> findByEmail(String email) {
        TypedQuery<LoginUserModel> query = em.createQuery(
                "SELECT u FROM LoginUserModel u WHERE u.email = :email", LoginUserModel.class);
        query.setParameter("email", email);
        return query.getResultList();
    }

    public Optional<LoginUserModel> findUniqueByEmail(String email) {
        List<LoginUserModel> results = findByEmail(email);
        if (results.size() == 1) {
            return Optional.of(results.get(0));
        } else {
            return Optional.empty();
        }
    }
}
