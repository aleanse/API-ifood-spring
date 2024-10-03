package com.aleanse.ifood.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Estado estado;

    public Cidade() {
    }


}
