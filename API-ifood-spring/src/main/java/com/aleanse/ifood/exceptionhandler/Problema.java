package com.aleanse.ifood.exceptionhandler;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Problema {
    private LocalDateTime dataHora;
    private String mensagem;
}
