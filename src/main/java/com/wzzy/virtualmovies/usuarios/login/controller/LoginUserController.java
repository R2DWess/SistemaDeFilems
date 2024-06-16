//package com.wzzy.virtualmovies.usuarios.login.controller;
//
//import com.wzzy.virtualmovies.usuarios.cadastrar.model.CadastrarUserModel;
//import com.wzzy.virtualmovies.usuarios.login.model.LoginRequest;
//import com.wzzy.virtualmovies.usuarios.login.model.LoginResponseDto;
//import com.wzzy.virtualmovies.usuarios.login.model.LoginUserModel;
//import com.wzzy.virtualmovies.usuarios.login.services.LoginUserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/login")
//public class LoginUserController {
//
//    @Autowired
//    private LoginUserService loginUserService;
//
//    @PostMapping
//    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody LoginRequest loginRequest) {
//        Optional<LoginResponseDto> user = loginUserService.login(loginRequest.getEmail(), loginRequest.getPassword());
//        if (user.isPresent()) {
//            return ResponseEntity.ok(user.get());
//        } else {
//            return ResponseEntity.status(401).build();
//        }
//    }
//}
