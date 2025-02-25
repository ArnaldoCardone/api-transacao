package com.cardone.api_transacao.controller;

import com.cardone.api_transacao.business.services.EstatiscaService;
import com.cardone.api_transacao.controllers.EstatisticaController;
import com.cardone.api_transacao.controllers.dto.EstaticasResponseDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class EstatisticaControllerTest {

        @InjectMocks
        EstatisticaController estatisticasController;

        @Mock
        EstatiscaService estatisticasService;

        EstaticasResponseDTO estatisticas;

        MockMvc mockMvc;

        @BeforeEach
        void setup(){
            mockMvc = MockMvcBuilders.standaloneSetup(estatisticasController).build();
            estatisticas = new EstaticasResponseDTO(1l, 20.0, 20.0, 20.0, 20.0);
        }

        @Test
        void deveBuscarEstatisticasComSucesso() throws Exception {

            when(estatisticasService.calculaEstatisticas(60)).thenReturn(estatisticas);

            mockMvc.perform(get("/estatistica")
                            .param("intervaloBusca", "60")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.count").value(estatisticas.count()))
                    .andExpect(jsonPath("$.min").value(20));

        }
}
