package com.cardone.api_transacao.bussiness.service;

import com.cardone.api_transacao.business.services.TransacaoService;
import com.cardone.api_transacao.controllers.dto.EstaticasResponseDTO;
import com.cardone.api_transacao.controllers.dto.TransacaoRequestDTO;
import com.cardone.api_transacao.infrastructure.exceptions.UnprocessableEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {

    @InjectMocks
    TransacaoService transacaoService;

    TransacaoRequestDTO transacao;

    EstaticasResponseDTO estatisticas;

    @BeforeEach
    void setUp(){
        transacao = new TransacaoRequestDTO(20.0, OffsetDateTime.now());
        estatisticas = new EstaticasResponseDTO(1l, 20.0, 20.0, 20.0, 20.0);
    }

    @Test
    void adicionarTransacaoShloudReturnSucessWhenDadosOK(){

        transacaoService.adicionarTransacao(transacao);

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacaoes(5000);

        assertTrue(transacoes.contains(transacao));
    }

    @Test
    void adicionarTransacaoShloudThrowExceptionWhenDadosZerados(){

        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class,
                () -> transacaoService.adicionarTransacao(new TransacaoRequestDTO(-10.0, OffsetDateTime.now())));

        assertEquals("Valor informado menor que zero", exception.getMessage());
    }

    @Test
    void adicionarTransacaoShloudThrowExceptionWhenDataPosterior(){

        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class,
                () -> transacaoService.adicionarTransacao(new TransacaoRequestDTO(10.0, OffsetDateTime.now().plusDays(1))));

        assertEquals("Data e Hora é posterior a atual", exception.getMessage());
    }

    @Test
    void limparTransacoesShouldLimparListaWhenChamadoOK(){

        transacaoService.limparTransacoes();

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacaoes(5000);

        assertTrue(transacoes.isEmpty());
    }

    @Test
    void buscarTransacaoesShouldReturnDadosWherDentrodoIntervalo(){

        TransacaoRequestDTO dto = new TransacaoRequestDTO(10.00, OffsetDateTime.now().minusHours(1));

        transacaoService.adicionarTransacao(transacao);
        transacaoService.adicionarTransacao(dto);

        List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacaoes(60);

        assertTrue(transacoes.contains(transacao));
        assertFalse(transacoes.contains(dto));
    }


}
