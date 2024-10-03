package com.aleanse.ifood.service;

import com.aleanse.ifood.exception.EntidadeEmUsoException;
import com.aleanse.ifood.exception.EntidadeNaoEncontradaException;
import com.aleanse.ifood.model.Cozinha;
import com.aleanse.ifood.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CadastroCozinhaService {
    @Autowired
    CozinhaRepository cozinhaRepository;
    public Cozinha salvarCozinha(Cozinha cozinha){

        return cozinhaRepository.save(cozinha);
    }
    public void excluir(Long id){
        try {
            Optional<Cozinha> cozinha = cozinhaRepository.findById(id);
            cozinhaRepository.delete(cozinha.get());

        }catch (NoSuchElementException e){
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de cozinha com código %d",id));

        }
        catch (DataIntegrityViolationException e){
            throw new EntidadeEmUsoException(
                    String.format("Cozinha de código %d não pode ser removida, pois está em uso",id));

        }


    }
}
