package com.wzzy.virtualmovies.usuarios.cadastrar.repository;

import com.wzzy.virtualmovies.usuarios.cadastrar.model.CadastrarUserModel;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CadastrarUserRepository {
    private EntityManager em;

    public CadastrarUserRepository(EntityManager em) {
        this.em = em;
    }

    public Optional<CadastrarUserModel> findById(UUID id) {
        CadastrarUserModel user = em.find(CadastrarUserModel.class, id);
        return user != null ? Optional.of(user) : Optional.empty();
    }

    public boolean existsByEmail(String email) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(u) FROM CadastrarUserModel u WHERE u.email = :email", Long.class);
        query.setParameter("email", email);
        return query.getSingleResult() > 0;
    }

    public boolean existsByCpf(String cpf) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(u) FROM CadastrarUserModel u WHERE u.cpf = :cpf", Long.class);
        query.setParameter("cpf", cpf);
        return query.getSingleResult() > 0;
    }

    public Optional<CadastrarUserModel> findByEmail(String email) {
        TypedQuery<CadastrarUserModel> query = em.createQuery(
                "SELECT u FROM CadastrarUserModel u WHERE u.email = :email", CadastrarUserModel.class);
        query.setParameter("email", email);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public CadastrarUserModel save(CadastrarUserModel userModel) {
        if (userModel.getId() == null) {
            em.persist(userModel);
        } else {
            userModel = em.merge(userModel);
        }
        return userModel;
    }

    public void delete(CadastrarUserModel userModel) {
        if (em.contains(userModel)) {
            em.remove(userModel);
        } else {
            em.remove(em.merge(userModel));
        }
    }

    public List<CadastrarUserModel> findAll() {
        return em.createQuery("SELECT u FROM CadastrarUserModel u", CadastrarUserModel.class).getResultList();
    }

    public boolean existsBySocialname(String socialname) {
        TypedQuery<Long> query = em.createQuery(
                "SELECT COUNT(u) FROM CadastrarUserModel u WHERE u.socialname = :socialname", Long.class);
        query.setParameter("socialname", socialname);
        return query.getSingleResult() > 0;
    }

    public Optional<CadastrarUserModel> findBySocialname(String socialname) {
        TypedQuery<CadastrarUserModel> query = em.createQuery(
                "SELECT u FROM CadastrarUserModel u WHERE u.socialname = :socialname", CadastrarUserModel.class);
        query.setParameter("socialname", socialname);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

}
