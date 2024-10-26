package com.aleanse.ifood.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data // Lombok que gera getter, setter, equals, hashcode e toString
@Entity
public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private BigDecimal taxaFrete;


    @JsonIgnoreProperties("hibernateLazyInitializer")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Cozinha cozinha;

    @JsonIgnore
    @CreationTimestamp
    @Column(nullable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dataCadastro;

    @JsonIgnore
    @UpdateTimestamp
    @Column(nullable = false,columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dataAtualizacao;

    @ManyToMany
    @JoinTable(name = "restaurante_forma_pagamento",
    joinColumns = @JoinColumn(name = "restaurante_id"),
    inverseJoinColumns = @JoinColumn(name = "forma_pagamento_id"))
    private List<FormaPagamento> formasPagamento =  new ArrayList<>();


    @OneToMany(mappedBy = "restaurante")
    private List<Produto> produto = new ArrayList<>();


    @Embedded
    @JsonIgnore
    private Endereco endereco;

    @PrePersist
    protected void onCreate() {
        dataCadastro = LocalDateTime.now(); // Define a data de cadastro
        dataAtualizacao = LocalDateTime.now(); // Define a data de atualização
    }


    @PreUpdate
    protected void onUpdate() {
        dataAtualizacao = LocalDateTime.now(); // Atualiza a data de atualização
    }
    public Restaurante() {
    }


}




