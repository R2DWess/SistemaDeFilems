package com.wzzy.virtualmovies.usuarios.cadastrar.services;

import com.wzzy.virtualmovies.usuarios.cadastrar.model.CadastrarUserModel;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CadastrarUserService {

    private EntityManager entityManager;

    public CadastrarUserService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public CadastrarUserModel save(CadastrarUserModel user) {
        if (user.getId() == null) {
            user.setId(UUID.randomUUID());
        }
        entityManager.getTransaction().begin();
        CadastrarUserModel managedUser = entityManager.merge(user);
        entityManager.persist(managedUser);
        entityManager.getTransaction().commit();
        return managedUser;
    }


    public List<CadastrarUserModel> findAll() {
        TypedQuery<CadastrarUserModel> query = entityManager.createQuery("SELECT u FROM CadastrarUserModel u", CadastrarUserModel.class);
        return query.getResultList();
    }

    public Optional<CadastrarUserModel> findById(String id) {
        CadastrarUserModel user = entityManager.find(CadastrarUserModel.class, id);
        return user != null ? Optional.of(user) : Optional.empty();
    }

    public void deleteById(String id) {
        CadastrarUserModel user = entityManager.find(CadastrarUserModel.class, id);
        if (user != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(user);
            entityManager.getTransaction().commit();
        }
    }
}
