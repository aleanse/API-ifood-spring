package com.aleanse.ifood.service;


import com.aleanse.ifood.exception.EntidadeEmUsoException;
import com.aleanse.ifood.exception.EntidadeNaoEncontradaException;
import com.aleanse.ifood.exception.EstadoNaoEncontradoException;
import com.aleanse.ifood.model.Cidade;
import com.aleanse.ifood.model.Cozinha;
import com.aleanse.ifood.model.Estado;
import com.aleanse.ifood.model.Restaurante;
import com.aleanse.ifood.repository.CidadeRepository;
import com.aleanse.ifood.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroEstadoService {
    @Autowired
    private EstadoRepository estadoRepository;
    @Autowired
    private CidadeRepository cidadeRepository;
    public Estado salvarEstado(Estado estado) {
        if (estado.getNome() == null || estado.getNome().isEmpty()){
            throw new EntidadeNaoEncontradaException("Nome do estado é obrigatório");
        }else {
            return estadoRepository.save(estado);
        }
    }

    public Estado atualizarEstado(Long id, Estado estado) {
        Optional<Estado> estadoAtual = estadoRepository.findById(id);
        if (estadoAtual.isEmpty()){
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de restaurante com código %d",id));
        } else if (estado.getNome() == null || estado.getNome().isEmpty()){
            throw new EntidadeNaoEncontradaException("Nome do estado é obrigatório");
        } else {
            estadoAtual.get().setNome(estado.getNome());
            return estadoRepository.save(estadoAtual.get());
        }
    }

    public void deletar(Long id) {
        try {
            estadoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new EstadoNaoEncontradoException(
                    String.format("Não existe um cadastro de estado com código %d",id)
            );
        } catch (DataIntegrityViolationException e ){
            throw new EntidadeNaoEncontradaException(
                    String.format("não é possivel deletar pois estado de id %d esta em uso",id)
            );
        }

    }

    public Estado buscarOuFalhar(Long id) {
        return estadoRepository.findById(id).orElseThrow(() -> new EstadoNaoEncontradoException(
                String.format("Não existe um cadastro de estado com código %d",id)));
    }
}
