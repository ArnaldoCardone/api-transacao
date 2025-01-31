package com.cardone.api_transacao.controllers;

import com.cardone.api_transacao.business.services.TransacaoService;
import com.cardone.api_transacao.controllers.dto.TransacaoRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private TransacaoService service;

    @PostMapping
    public ResponseEntity<Void> adicionarTransacao(@RequestBody TransacaoRequestDTO dto){

        service.adicionarTransacao(dto);
            return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    @DeleteMapping
    public ResponseEntity<Void> excluirTransacao(){
        service.limparTransacoes();
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
