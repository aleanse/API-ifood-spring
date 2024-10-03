package com.aleanse.ifood.repository;

import com.aleanse.ifood.model.Cozinha;
import com.aleanse.ifood.model.Estado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstadoRepository extends JpaRepository<Estado,Long> {
    @Override
    List<Estado> findAll();
}
