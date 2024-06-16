//package com.wzzy.virtualmovies.usuarios.cadastrar.controller;
//
//import com.wzzy.virtualmovies.usuarios.cadastrar.model.CadastrarUserModel;
//import com.wzzy.virtualmovies.usuarios.cadastrar.services.CadastrarUserService;
//import com.wzzy.virtualmovies.usuarios.login.services.LoginUserService;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.UUID;
//
//@RestController
//@CrossOrigin("*")
//@RequestMapping("/usuarios")
//public class CadastrarUserController {
//
//    private final CadastrarUserService cadastrarUserService;
//    private final LoginUserService loginUserService;
//
//    @Autowired
//    public CadastrarUserController(CadastrarUserService cadastrarUserService, LoginUserService loginUserService) {
//        this.cadastrarUserService = cadastrarUserService;
//        this.loginUserService = loginUserService;
//    }
//
//    @PostMapping("/cadastrar")
//    public ResponseEntity<String> register(@RequestBody CadastrarUserModel user) {
//        boolean isRegistered = cadastrarUserService.cadastrarUser(user);
//        if (isRegistered) {
//            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso.");
//        } else {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já existe.");
//        }
//    }
//
//
//    @PostMapping
//    public ResponseEntity<Object> saveUser(@RequestBody @Validated CadastrarUserModel cadastrarUserModel) {
//
//        if (cadastrarUserService.existsByCpf(cadastrarUserModel.getCpf())) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: CPF is already in use!");
//        }
//
//        if (cadastrarUserService.existsByEmail(cadastrarUserModel.getEmail())) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Email is already in use!");
//        }
//
//        if (cadastrarUserService.existsBySocialname(cadastrarUserModel.getSocialname())) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Social name is already in use!");
//        }
//
//        var newUserModel = new CadastrarUserModel();
//        BeanUtils.copyProperties(cadastrarUserModel, newUserModel);
//        return ResponseEntity.status(HttpStatus.CREATED).body(cadastrarUserService.save(newUserModel));
//    }
//
//    @GetMapping
//    public ResponseEntity<List<CadastrarUserModel>> getAllUsers() {
//        return ResponseEntity.status(HttpStatus.OK).body(cadastrarUserService.findAll());
//    }
//
//    @GetMapping("/{socialname}")
//    public ResponseEntity<Object> getUserBySocialname(@PathVariable(value = "socialname") String socialname) {
//        Optional<CadastrarUserModel> userModelOptional = cadastrarUserService.findBySocialname(socialname);
//        if (!userModelOptional.isPresent()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(userModelOptional.get());
//    }
//
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Object> deleteUser(@PathVariable(value = "id") UUID id) {
//        Optional<CadastrarUserModel> userModelOptional = cadastrarUserService.findById(id);
//        if (userModelOptional.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
//        }
//        cadastrarUserService.delete(userModelOptional.get());
//        return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Object> updateUser(@PathVariable(value = "id") UUID id,
//                                             @RequestBody @Validated CadastrarUserModel cadastrarUserModel) {
//        Optional<CadastrarUserModel> userModelOptional = cadastrarUserService.findById(id);
//        if (userModelOptional.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
//        }
//        var existingUserModel = userModelOptional.get();
//        BeanUtils.copyProperties(cadastrarUserModel, existingUserModel);
//        cadastrarUserService.save(existingUserModel);
//        return ResponseEntity.status(HttpStatus.OK).body(cadastrarUserService.save(existingUserModel));
//    }
//}
