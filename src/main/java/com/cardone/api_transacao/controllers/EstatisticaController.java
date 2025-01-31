package com.cardone.api_transacao.controllers;

import com.cardone.api_transacao.business.services.EstatiscaService;
import com.cardone.api_transacao.business.services.TransacaoService;
import com.cardone.api_transacao.controllers.dto.EstaticasResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {

    @Autowired
    private EstatiscaService service;

    @GetMapping
    @Operation(description = "Realiza os calculos estatisticos das transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cálculos realizados com sucesso"),
            @ApiResponse(responseCode = "400 ", description = "Erro no calculo das estatisticas"),
            @ApiResponse(responseCode = "500 ", description = "Erro interno do serviço")
    })

    public ResponseEntity<EstaticasResponseDTO> buscarEstatisticas(
                          @RequestParam(value = "intervaloTempo", required = false, defaultValue = "60") Integer intervaloTempo){

        EstaticasResponseDTO dto = service.calculaEstatisticas(intervaloTempo);

        return  ResponseEntity.ok(dto);
    }
}
