package com.cardone.api_transacao.business.services;

import com.cardone.api_transacao.controllers.dto.EstaticasResponseDTO;
import com.cardone.api_transacao.controllers.dto.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstatiscaService {

    @Autowired
    public TransacaoService transacaoService;

    public EstaticasResponseDTO calculaEstatisticas(Integer intervaloTempo){

        log.info("Calculo das estatisticas do periodo");
        List<TransacaoRequestDTO> lista = transacaoService.buscarTransacaoes(intervaloTempo);

        // Pega os valores recuperados e transforma para que possamos chamar as funções DoubleSummaryStatistics
        DoubleSummaryStatistics estatisticas = lista.stream().mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();

        //Retorna os valores calculados pelo DoubleSummaryStatistics
        return new EstaticasResponseDTO(estatisticas.getCount(),
                                        estatisticas.getSum(),
                                        estatisticas.getAverage(),
                                        estatisticas.getMin(),
                                        estatisticas.getMax() );
    }
}
