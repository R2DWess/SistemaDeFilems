package com.wzzy.virtualmovies.usuarios.cadastrar.services;

import com.wzzy.virtualmovies.usuarios.cadastrar.model.CadastrarUserModel;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CadastrarUserService {

    @PersistenceContext
    private EntityManager entityManager;

    public CadastrarUserService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public CadastrarUserModel save(CadastrarUserModel user) {
        entityManager.getTransaction().begin();
        if (user.getId() == null) {
            user.setId(UUID.randomUUID());
            entityManager.persist(user);
        } else {
            user = entityManager.merge(user);
        }
        entityManager.getTransaction().commit();
        return user;
    }

    public List<CadastrarUserModel> findAll() {
        return entityManager.createQuery("SELECT u FROM CadastrarUserModel u", CadastrarUserModel.class).getResultList();
    }

    public Optional<CadastrarUserModel> findById(UUID id) {
        CadastrarUserModel user = entityManager.find(CadastrarUserModel.class, id);
        return user != null ? Optional.of(user) : Optional.empty();
    }

    public boolean deleteById(UUID id) {
        CadastrarUserModel user = entityManager.find(CadastrarUserModel.class, id);
        if (user != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(user);
            entityManager.getTransaction().commit();
            return true;
        }
        return false;
    }
}
