package com.cardone.api_transacao.controllers;

import com.cardone.api_transacao.business.services.EstatiscaService;
import com.cardone.api_transacao.business.services.TransacaoService;
import com.cardone.api_transacao.controllers.dto.EstaticasResponseDTO;
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
    public ResponseEntity<EstaticasResponseDTO> buscarEstatisticas(
                          @RequestParam(value = "intervaloTempo", required = false, defaultValue = "60") Integer intervaloTempo){

        EstaticasResponseDTO dto = service.calculaEstatisticas(intervaloTempo);

        return  ResponseEntity.ok(dto);
    }
}
