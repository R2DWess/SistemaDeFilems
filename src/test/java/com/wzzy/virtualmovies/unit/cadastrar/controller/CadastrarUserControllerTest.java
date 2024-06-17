package com.wzzy.virtualmovies.usuarios.cadastrar.handler;

import com.wzzy.virtualmovies.usuarios.cadastrar.model.CadastrarUserModel;
import com.wzzy.virtualmovies.usuarios.cadastrar.services.CadastrarUserService;
import com.wzzy.virtualmovies.usuarios.login.services.LoginUserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CadastrarUserController.class)
public class CadastrarUserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CadastrarUserService cadastrarUserService;

    @MockBean
    private LoginUserService loginUserService;

    @Test
    @DisplayName("Teste de sucesso no registro do usuário")
    public void testRegisterUser_Success() throws Exception {
        Mockito.when(cadastrarUserService.cadastrarUser(any(CadastrarUserModel.class))).thenReturn(true);

        mockMvc.perform(post("/usuarios/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\",\"cpf\":\"12345678900\",\"nome\":\"Teste\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Teste de falha no registro do usuário")
    public void testRegisterUser_Failure() throws Exception {
        Mockito.when(cadastrarUserService.cadastrarUser(any(CadastrarUserModel.class))).thenReturn(false);

        mockMvc.perform(post("/usuarios/cadastrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"test@example.com\",\"cpf\":\"12345678900\",\"nome\":\"Teste\"}"))
                .andExpect(status().isBadRequest());
    }
}
