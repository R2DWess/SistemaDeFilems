package com.wzzy.virtualmovies.usuarios.login.services;

import com.wzzy.virtualmovies.usuarios.cadastrar.model.CadastrarUserModel;
import com.wzzy.virtualmovies.usuarios.login.model.LoginResponseDto;
import com.wzzy.virtualmovies.usuarios.login.model.LoginUserModel;
import com.wzzy.virtualmovies.usuarios.login.repository.LoginUserRepository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class LoginUserService {

    private final LoginUserRepository loginUserRepository;
    private final EntityManager entityManager;

    public LoginUserService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.loginUserRepository = new LoginUserRepository(entityManager);
    }

    public LoginUserModel save(LoginUserModel loginUserModel) {
        entityManager.getTransaction().begin();
        LoginUserModel savedUser = loginUserRepository.save(loginUserModel);
        entityManager.getTransaction().commit();
        return savedUser;
    }

    public Optional<LoginUserModel> findById(UUID id) {
        return loginUserRepository.findById(id);
    }

    public boolean existsByEmail(String email){
        return loginUserRepository.existsByEmail(email);
    }

    public void delete(LoginUserModel userModel) {
        entityManager.getTransaction().begin();
        loginUserRepository.delete(userModel);
        entityManager.getTransaction().commit();
    }

    public boolean validateLogin(String email, String password) {
        return loginUserRepository.validateLogin(email, password);
    }

    public Optional<LoginResponseDto> login(String email, String password) {
        Optional<LoginUserModel> user = loginUserRepository.findByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            LoginResponseDto loginResponse = new LoginResponseDto();
            // Manual copy properties since BeanUtils is part of Spring
            loginResponse.setEmail(user.get().getEmail());
            loginResponse.setAdmin(user.get().isAdmin());
            return Optional.of(loginResponse);
        }
        return Optional.empty();
    }

    public boolean cadastrarUser(CadastrarUserModel newUser) {
        if (!existsByEmail(newUser.getEmail()) && !existsByCpf(newUser.getCpf())) {
            LoginUserModel loginUserModel = new LoginUserModel();
            // Manual copy properties since BeanUtils is part of Spring
            loginUserModel.setEmail(newUser.getEmail());
            loginUserModel.setPassword(newUser.getPassword());
            loginUserModel.setAdmin(newUser.isAdmin());
            save(loginUserModel);
            return true;
        }
        return false;
    }

    public boolean existsByCpf(String cpf) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(u) FROM LoginUserModel u WHERE u.cpf = :cpf", Long.class);
        query.setParameter("cpf", cpf);
        return query.getSingleResult() > 0;
    }

    public List<LoginUserModel> findAll() {
        return entityManager.createQuery("SELECT u FROM LoginUserModel u", LoginUserModel.class).getResultList();
    }

    public boolean existsBySocialname(String socialname) {
        TypedQuery<Long> query = entityManager.createQuery(
                "SELECT COUNT(u) FROM LoginUserModel u WHERE u.socialname = :socialname", Long.class);
        query.setParameter("socialname", socialname);
        return query.getSingleResult() > 0;
    }
}