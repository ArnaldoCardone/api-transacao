package com.cardone.api_transacao.business.services;

import com.cardone.api_transacao.controllers.dto.TransacaoRequestDTO;
import com.cardone.api_transacao.infrastructure.exceptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {

    private final List<TransacaoRequestDTO> listaTransacao = new ArrayList<>();

    public void adicionarTransacao(TransacaoRequestDTO dto){

        log.info("Iniciado o processo de adicionar transação");
        //Verifica se a data/hora é maior que DataHora atual
        if(dto.dataHora().isAfter(OffsetDateTime.now())){
            log.info("Data e Hora é posterior a atual");
            throw new UnprocessableEntity("Data e Hora é posterior a atual");
        }

        //Verifica se o valor informado é maior que zero
        if(dto.valor() < 0 ){
            log.info("Valor informado menor que zero");
            throw new UnprocessableEntity("Valor informado menor que zero");
        }

        listaTransacao.add(dto);
    }

    public void limparTransacoes(){
        log.info("Transações excluídas");
        listaTransacao.clear();
    }

    public List<TransacaoRequestDTO> buscarTransacaoes(Integer intervaloBusca){
        log.info("Recuperando transações do intevalor");
        //Define a data hora do intervalo a ser recuperado
        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);

        //Aplica um filtro na lista buscando todas transações após o data/hora do intevalo calculado
        return  listaTransacao.stream().
                filter(transacoes -> transacoes.dataHora().isAfter(dataHoraIntervalo)).toList();


    }
}
