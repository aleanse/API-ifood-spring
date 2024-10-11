package com.aleanse.ifood.service;


import com.aleanse.ifood.exception.EntidadeNaoEncontradaException;
import com.aleanse.ifood.model.Cidade;
import com.aleanse.ifood.model.Cozinha;
import com.aleanse.ifood.model.Estado;
import com.aleanse.ifood.model.Restaurante;
import com.aleanse.ifood.repository.CidadeRepository;
import com.aleanse.ifood.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Estado deletar(Long id) {
        Optional<Estado> estado = estadoRepository.findById(id);
        List<Cidade> cidade = cidadeRepository.findByEstado_Id(id);

        if (estado.isEmpty()){
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de restaurante com código %d",id));
        }
        else if (!cidade.isEmpty()){
            throw new EntidadeNaoEncontradaException(
                    String.format("Estado de código %d não pode ser removida, pois está em uso",id));
        }
        else {
            estadoRepository.delete(estado.get());
            return estado.get();
        }
    }
}
