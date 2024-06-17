package com.wzzy.virtualmovies.unit.login;

import com.wzzy.virtualmovies.usuarios.login.handler.LoginUserController;
import com.wzzy.virtualmovies.usuarios.login.model.LoginResponseDto;
import com.wzzy.virtualmovies.usuarios.login.services.LoginUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LoginUserControllerTest {

    @Mock
    private LoginUserService loginUserService;

    @InjectMocks
    private LoginUserController loginUserController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(loginUserController).build();
    }

    @Test
    public void testLogin_Success() throws Exception {
        when(loginUserService.login(anyString(), anyString())).thenReturn(Optional.of(new LoginResponseDto()));
        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\": \"test@example.com\", \"password\": \"password\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testLogin_Exception() throws Exception {
        when(loginUserService.validateLogin(anyString(), anyString())).thenReturn(false);

        mockMvc.perform(post("/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\": \"test@example.com\", \"password\": \"wrongpassword\"}"))
                .andExpect(status().isUnauthorized());
    }
}
