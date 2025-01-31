package com.cardone.api_transacao.controllers;

import com.cardone.api_transacao.business.services.TransacaoService;
import com.cardone.api_transacao.controllers.dto.TransacaoRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    @Autowired
    private TransacaoService service;

    @PostMapping
    @Operation(description = "Responsável por adicionar transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transação criada com sucesso"),
            @ApiResponse(responseCode = "422 ", description = "Transação não aceita devido a validações"),
            @ApiResponse(responseCode = "400 ", description = "Requisição incorreta"),
            @ApiResponse(responseCode = "500 ", description = "Erro interno do serviço")
    })
    public ResponseEntity<Void> adicionarTransacao(@RequestBody TransacaoRequestDTO dto){

        service.adicionarTransacao(dto);
            return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @DeleteMapping
    @Operation(description = "Exclue todas as transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transação excluidas com sucesso"),
            @ApiResponse(responseCode = "400 ", description = "Requisição incorreta"),
            @ApiResponse(responseCode = "500 ", description = "Erro interno do serviço")
    })
    public ResponseEntity<Void> excluirTransacao(){
        service.limparTransacoes();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
