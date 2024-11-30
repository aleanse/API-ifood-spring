package com.aleanse.ifood.service;

import com.aleanse.ifood.exception.EntidadeEmUsoException;
import com.aleanse.ifood.exception.EntidadeNaoEncontradaException;
import com.aleanse.ifood.exception.EstadoNaoEncontradoException;
import com.aleanse.ifood.model.Cidade;
import com.aleanse.ifood.model.Cozinha;
import com.aleanse.ifood.model.Estado;
import com.aleanse.ifood.repository.CidadeRepository;
import com.aleanse.ifood.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CadastroCidadeService {
    @Autowired
    CidadeRepository cidadeRepository;
    @Autowired
    EstadoRepository estadoRepository;
    @Autowired
    CadastroEstadoService cadastroEstado;

    public Cidade salvarCidade(Cidade cidade) {
        Long estadoId = cidade.getEstado().getId();
        Estado estado = cadastroEstado.buscarOuFalhar(estadoId);
        cidade.setEstado(estado);

        return cidadeRepository.save(cidade);

    }

    public Cidade atualizarCidade(Long id, Cidade cidade) {
        Cidade cidadeAtual = buscarOuFalhar(id);
        Optional<Estado> estado = estadoRepository.findById(cidade.getEstado().getId());
        if (cidade.getNome() == null || cidade.getNome().isEmpty()) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Nome da cidade é obrigatório"));
        }
         else if (estado.isEmpty() || estado.get().getId() == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de estado com código %d", cidade.getEstado().getId()));
        } else {
            cidadeAtual.setNome(cidade.getNome());
            cidadeAtual.setEstado(estado.get());
            return cidadeRepository.save(cidadeAtual);
        }
    }
    public void deletar(Long id) {
        try {
            cidadeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de cidade com código %d",id)
            );
        } catch (DataIntegrityViolationException e ){
            throw new EntidadeNaoEncontradaException(
                    String.format("não é possivel deletar pois cidade de id %d esta em uso",id)
            );
        }


    }
    public Cidade buscarOuFalhar(Long id) {
        return cidadeRepository.findById(id).orElseThrow(() -> new EstadoNaoEncontradoException(
                String.format("Não existe um cadastro de cidade com código %d",id)));
    }


}
