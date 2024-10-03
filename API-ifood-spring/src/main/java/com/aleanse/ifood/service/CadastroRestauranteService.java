package com.aleanse.ifood.service;


import com.aleanse.ifood.model.Restaurante;
import com.aleanse.ifood.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {
    @Autowired
    private RestauranteRepository restauranteRepository;

    public Restaurante salvarRestaurante(Restaurante restaurante){
        return restauranteRepository.save(restaurante);
    }
}
