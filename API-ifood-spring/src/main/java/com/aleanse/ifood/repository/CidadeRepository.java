package com.aleanse.ifood.repository;
import com.aleanse.ifood.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    List<Cidade> findByEstado_Id(Long estadoId);
}
