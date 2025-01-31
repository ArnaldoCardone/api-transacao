package com.cardone.api_transacao.controllers.dto;

import java.time.DateTimeException;
import java.time.OffsetDateTime;

public record TransacaoRequestDTO(Double valor, OffsetDateTime dataHora) {
}
