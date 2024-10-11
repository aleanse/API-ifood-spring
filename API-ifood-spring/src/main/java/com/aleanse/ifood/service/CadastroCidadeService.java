package com.aleanse.ifood.service;

import com.aleanse.ifood.exception.EntidadeEmUsoException;
import com.aleanse.ifood.exception.EntidadeNaoEncontradaException;
import com.aleanse.ifood.model.Cidade;
import com.aleanse.ifood.model.Cozinha;
import com.aleanse.ifood.model.Estado;
import com.aleanse.ifood.repository.CidadeRepository;
import com.aleanse.ifood.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CadastroCidadeService {
    @Autowired
    CidadeRepository cidadeRepository;
    @Autowired
    EstadoRepository estadoRepository;
    public Cidade salvarCozinha(Cidade cidade){
        return cidadeRepository.save(cidade);
    }
    public void excluir(Long id){
        try {
            Optional<Cidade> cidade = cidadeRepository.findById(id);
            cidadeRepository.delete(cidade.get());

        }catch (NoSuchElementException e){
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de cidade com código %d",id));

        }
    }
    public Cidade salvarCidade(Cidade cidade) {
        Optional<Estado> estado = estadoRepository.findById(cidade.getEstado().getId());
        if (cidade.getNome() == null || cidade.getNome().isEmpty()) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Nome da cidade é obrigatório"));
        } else if (estado.isEmpty() || estado.get().getId() == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de estado com código %d", cidade.getEstado().getId()));
        } else {
            cidade.setEstado(estado.get());
            return cidadeRepository.save(cidade);
        }
    }

    public Cidade atualizarCidade(Long id, Cidade cidade) {
        Optional<Cidade> cidadeAtual = cidadeRepository.findById(id);
        Optional<Estado> estado = estadoRepository.findById(cidade.getEstado().getId());
        if (cidade.getNome() == null || cidade.getNome().isEmpty()) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Nome da cidade é obrigatório"));
        } else if (cidadeAtual.isEmpty()) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de cidade com código %d", id));
        } else if (estado.isEmpty() || estado.get().getId() == null) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de estado com código %d", cidade.getEstado().getId()));
        } else {
            cidadeAtual.get().setNome(cidade.getNome());
            cidadeAtual.get().setEstado(estado.get());
            return cidadeRepository.save(cidadeAtual.get());
        }
    }
    public void deletar(Long id) {
        Optional<Cidade> cidade = cidadeRepository.findById(id);
        if (cidade.isEmpty()) {
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de cidade com código %d", id));
        }else {
            cidadeRepository.delete(cidade.get());
        }

    }


}
