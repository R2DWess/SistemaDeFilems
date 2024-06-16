package com.wzzy.virtualmovies.usuarios.cadastrar.services;

import com.wzzy.virtualmovies.database.DatabaseUtil;
import com.wzzy.virtualmovies.usuarios.cadastrar.model.CadastrarUserModel;
import com.wzzy.virtualmovies.usuarios.cadastrar.repository.CadastrarUserRepository;


import java.sql.*;
import java.util.Optional;
import java.util.UUID;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CadastrarUserService {

    private final Map<UUID, CadastrarUserModel> users = new ConcurrentHashMap<>();

    public CadastrarUserModel save(CadastrarUserModel cadastrarUserModel) {
        if (cadastrarUserModel.getId() == null) {
            cadastrarUserModel.setId(UUID.fromString(UUID.randomUUID().toString()));
        }
        users.put(UUID.fromString(cadastrarUserModel.getId().toString()), cadastrarUserModel);
        return cadastrarUserModel;
    }

    public List<CadastrarUserModel> findAll() {
        return new ArrayList<>(users.values());
    }

    public Optional<CadastrarUserModel> findById(UUID id) {
        return Optional.ofNullable(users.get(id));
    }

    public boolean existsByEmail(String email) {
        return users.values().stream().anyMatch(user -> email.equals(user.getEmail()));
    }

    public boolean existsByCpf(String cpf) {
        return users.values().stream().anyMatch(user -> cpf.equals(user.getCpf()));
    }

    public boolean existsBySocialname(String socialname) {
        return users.values().stream().anyMatch(user -> socialname.equals(user.getSocialname()));
    }

    public void delete(CadastrarUserModel userModel) {
        users.remove(userModel.getId().toString());
    }

    public Optional<CadastrarUserModel> findBySocialname(String socialname) {
        return users.values().stream()
                .filter(user -> socialname.equals(user.getSocialname()))
                .findFirst();
    }

    public boolean deleteById(UUID id) {
        return users.remove(id) != null; // Retorna true se um usuário foi realmente removido
    }

    public boolean cadastrarUser(CadastrarUserModel newUser) {
        if (!existsByEmail(newUser.getEmail()) && !existsByCpf(newUser.getCpf())) {
            newUser.setId(UUID.fromString(UUID.randomUUID().toString())); // Assegura que o novo usuário tenha um ID único
            save(newUser);
            return true;
        }
        return false;
    }
}

