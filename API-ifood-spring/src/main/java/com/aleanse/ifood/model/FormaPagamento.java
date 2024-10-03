package com.aleanse.ifood.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class FormaPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(nullable = false)
    private String descricao;

    public FormaPagamento() {
    }


}
