package com.aleanse.ifood.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;
}
