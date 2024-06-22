package com.wzzy.virtualmovies.usuarios.cadastrar.repository;

import com.wzzy.virtualmovies.usuarios.cadastrar.model.CadastrarUserModel;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;
import java.util.UUID;

public class CadastrarUserRepository {
    @PersistenceContext
    private EntityManager entityManager;

    public CadastrarUserRepository(EntityManager em) {
        this.entityManager = em;
    }

    public Optional<CadastrarUserModel> findById(UUID id) {
        CadastrarUserModel user = entityManager.find(CadastrarUserModel.class, id);
        return user != null ? Optional.of(user) : Optional.empty();
    }

    public CadastrarUserModel save(CadastrarUserModel userModel) {
        if (userModel.getId() == null) {
            entityManager.persist(userModel);
        } else {
            userModel = entityManager.merge(userModel);
        }
        return userModel;
    }

    public void delete(CadastrarUserModel userModel) {
        if (entityManager.contains(userModel)) {
            entityManager.remove(userModel);
        } else {
            entityManager.remove(entityManager.merge(userModel));
        }
    }
}

