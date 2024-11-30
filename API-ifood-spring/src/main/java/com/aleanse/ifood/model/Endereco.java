package com.aleanse.ifood.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Embeddable
public class Endereco {

    private String cep;


    private String logradouro;


    private String numero;


    private String complemento;

    private String bairro;

    @JoinColumn(name = "cidade_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cidade cidade;

    public Endereco(){

    }


}
