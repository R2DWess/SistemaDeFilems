package com.wzzy.virtualmovies.unit.movie;

import com.wzzy.virtualmovies.movie.handler.MoviesController;
import com.wzzy.virtualmovies.movie.service.MoviesService;
import com.wzzy.virtualmovies.usuarios.cadastrar.services.CadastrarUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class MoviesControllerTest {

    @Mock
    private MoviesService moviesService;

    @Mock
    private CadastrarUserService cadastrarUserService;

    @InjectMocks
    private MoviesController moviesController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(moviesController).build();
    }

    @Test
    public void testFavoriteMovie_Success() throws Exception {
        when(moviesService.favoriteMovieBySocialNameAndTitle(anyString(), anyString())).thenReturn(true);

        mockMvc.perform(post("/movies/joao_silva/favorite/Avatar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testFavoriteMovie_NotFound() throws Exception {
        when(moviesService.favoriteMovieBySocialNameAndTitle(anyString(), anyString())).thenReturn(false);

        mockMvc.perform(post("/movies/joao_silva/favorite/Avatar")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}
