package com.aleanse.ifood.service;


import com.aleanse.ifood.model.Cozinha;
import com.aleanse.ifood.repository.CozinhaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CozinhaService {
    @Autowired
    private CozinhaRepository cozinhaRepository;


    public void exibirCozinhas() {
        List<Cozinha> cozinhas = cozinhaRepository.findAll();
        cozinhas.forEach(s -> System.out.println(s.getNome()));
    }
    public void salvarCozinha(Cozinha cozinha){
        cozinhaRepository.save(cozinha);
    }

    public Cozinha pegarCozinhaPorId(Long id){
        Optional<Cozinha> cozinha = cozinhaRepository.findById(id);
        if (cozinha.isPresent()) {
            return cozinha.get();
        }else {
        return null;}
    }
    public void editarCozinha(Long id){
        Optional<Cozinha> cozinhaOptional = cozinhaRepository.findById(id);
        if (cozinhaOptional.isPresent()){
            cozinhaOptional.get().setNome("brasileira");
            cozinhaRepository.save(cozinhaOptional.get());
        }else {
            System.out.println("Cozinha não encontrada");
        }
    }
    public void deletarCozinha(Long id){
        Optional<Cozinha> cozinhaOptional = cozinhaRepository.findById(id);
        if (cozinhaOptional.isPresent()){
            cozinhaRepository.delete(cozinhaOptional.get());
            cozinhaRepository.flush();

        }else {
            System.out.println("Cozinha não encontrada");
        }
    }

}
