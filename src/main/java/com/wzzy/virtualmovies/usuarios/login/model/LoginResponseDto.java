package com.wzzy.virtualmovies.usuarios.login.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class LoginResponseDto {
    private UUID id;
    private String cpf;
    private String fullname;
    private String socialname;
    private String email;
    private String rg;
    private String cellphone;
    private LocalDate birthdate;
    private boolean isAdmin;
}
