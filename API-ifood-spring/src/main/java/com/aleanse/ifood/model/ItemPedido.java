package com.aleanse.ifood.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(nullable = false)
    private BigDecimal precoUnitario;

    @Column(nullable = false)
    private BigDecimal precoTotal;

    @Column(nullable = false)
    private String observacao;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Pedido pedido;


}
