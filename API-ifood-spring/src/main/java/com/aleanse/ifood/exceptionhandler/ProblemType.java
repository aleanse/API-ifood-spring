package com.aleanse.ifood.exceptionhandler;

import lombok.Getter;

@Getter
public enum ProblemType {
    ENTIDADE_NAO_ENCONTRADA("Entidade não encontrada");

    private String title;


    ProblemType(String title){
        this.title = title;
    }

}
