package com.wzzy.virtualmovies.usuarios.login.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "TB_USERS_STREAM")
public class LoginUserModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false, length = 255)
    private String cpf;

    @Column(nullable = false, length = 255)
    private String fullname;

    @Column(length = 255)
    private String socialname;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(length = 255)
    private String rg;

    @Column(nullable = false, length = 255)
    private String cellphone;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean isAdmin;

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
