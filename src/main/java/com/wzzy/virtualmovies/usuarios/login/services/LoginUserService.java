package com.wzzy.virtualmovies.usuarios.login.services;

import com.wzzy.virtualmovies.usuarios.login.model.LoginResponseDto;
import com.wzzy.virtualmovies.usuarios.login.model.LoginUserModel;
import com.wzzy.virtualmovies.usuarios.login.repository.LoginUserRepository;

import javax.persistence.EntityManager;
import java.util.Optional;

public class LoginUserService {

    private final LoginUserRepository loginUserRepository;
    private final EntityManager entityManager;

    public LoginUserService(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.loginUserRepository = new LoginUserRepository(entityManager);
    }

    public Optional<LoginResponseDto> login(String email, String password) {
        Optional<LoginUserModel> user = loginUserRepository.findUniqueByEmail(email);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            LoginResponseDto loginResponse = new LoginResponseDto();
            loginResponse.setId(user.get().getId());
            loginResponse.setCpf(user.get().getCpf());
            loginResponse.setFullname(user.get().getFullname());
            loginResponse.setSocialname(user.get().getSocialname());
            loginResponse.setEmail(user.get().getEmail());
            loginResponse.setRg(user.get().getRg());
            loginResponse.setCellphone(user.get().getCellphone());
            loginResponse.setBirthdate(user.get().getBirthdate());
            loginResponse.setAdmin(user.get().isAdmin());
            return Optional.of(loginResponse);
        }
        return Optional.empty();
    }
}
