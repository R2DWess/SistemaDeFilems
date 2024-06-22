package com.wzzy.virtualmovies.usuarios.cadastrar.dtos;

import lombok.Data;
import java.time.LocalDate;
import java.util.UUID;

@Data
public class CadastrarUserDto {

    private UUID id;
    private String password;
    private String cpf;
    private String fullname;
    private String socialname;
    private LocalDate birthdate;
    private String rg;
    private String cellphone;
    private String email;
    private boolean isAdmin;
}
