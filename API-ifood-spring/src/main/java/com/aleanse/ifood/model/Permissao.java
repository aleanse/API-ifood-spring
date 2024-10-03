package com.aleanse.ifood.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Permissao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(nullable = false)
    private String nome;

    @JoinColumn(nullable = false)
    private String descricao;

    public Permissao() {
    }




}
