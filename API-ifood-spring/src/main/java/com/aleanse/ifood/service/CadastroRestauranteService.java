package com.aleanse.ifood.service;


import com.aleanse.ifood.exception.EntidadeNaoEncontradaException;
import com.aleanse.ifood.model.Cozinha;
import com.aleanse.ifood.model.Restaurante;
import com.aleanse.ifood.repository.CozinhaRepository;
import com.aleanse.ifood.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CadastroRestauranteService {
    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private CozinhaRepository cozinhaRepository;

    public Restaurante salvarRestaurante(Restaurante restaurante){
        Optional<Cozinha> cozinhaId = cozinhaRepository.findById(restaurante.getCozinha().getId());

        if(cozinhaId.isEmpty()){
            throw new EntidadeNaoEncontradaException(
                    String.format("Não existe um cadastro de cozinha com código %d",restaurante.getCozinha().getId()));
        }else {
            restaurante.setCozinha(cozinhaId.get());
            return restauranteRepository.save(restaurante);
        }



    }
}
