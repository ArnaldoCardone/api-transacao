package com.cardone.api_transacao.bussiness.service;

import com.cardone.api_transacao.business.services.EstatiscaService;
import com.cardone.api_transacao.business.services.TransacaoService;
import com.cardone.api_transacao.controllers.dto.EstaticasResponseDTO;
import com.cardone.api_transacao.controllers.dto.TransacaoRequestDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Collections;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EstatisticaServiceTest {

    @InjectMocks
    EstatiscaService estatiscaService;

    @Mock
    TransacaoService transacaoService;

    TransacaoRequestDTO transacao;
    EstaticasResponseDTO estatisticas;

    @BeforeEach
    public void setup(){
        transacao = new TransacaoRequestDTO(20D, OffsetDateTime.now());
        estatisticas = new EstaticasResponseDTO(1L,20D,20D,20D,20D);
    }

    @Test
    public void calculaEstatistacaShouldReturnEstatisticasWhenValuesOK(){
        when(transacaoService.buscarTransacaoes(60))
                .thenReturn(Collections.singletonList(transacao));
        EstaticasResponseDTO resultado = estatiscaService.calculaEstatisticas(60);

        verify(transacaoService, times(1)).buscarTransacaoes(60);
        Assertions.assertThat(resultado).usingRecursiveComparison().isEqualTo(estatisticas);
    }

    @Test
    void calcularEstatisticasShouldReturnEmptyWhenListaVazia() {

        EstaticasResponseDTO estasticaEsperado =
                new EstaticasResponseDTO(0l, 0.0, 0.0, 0.0, 0.0);

        when(transacaoService.buscarTransacaoes(60))
                .thenReturn(Collections.emptyList());

        EstaticasResponseDTO resultado = estatiscaService.calculaEstatisticas(60);

        verify(transacaoService, times(1)).buscarTransacaoes(60);
        Assertions.assertThat(resultado).usingRecursiveComparison().isEqualTo(estasticaEsperado);
    }
}
