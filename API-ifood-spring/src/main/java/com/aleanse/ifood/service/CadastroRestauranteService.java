package com.aleanse.ifood.service;


import com.aleanse.ifood.exception.EntidadeNaoEncontradaException;
import com.aleanse.ifood.exception.RestauranteNaoEncontradoException;
import com.aleanse.ifood.model.Cozinha;
import com.aleanse.ifood.model.Estado;
import com.aleanse.ifood.model.Restaurante;
import com.aleanse.ifood.repository.CozinhaRepository;
import com.aleanse.ifood.repository.RestauranteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class CadastroRestauranteService {
    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private CozinhaRepository cozinhaRepository;
    @Autowired
    private CadastroCozinhaService cadastroCozinha;

    public Restaurante salvarRestaurante(Restaurante restaurante){
        Cozinha cozinha = cadastroCozinha.buscarOuFalhar(restaurante.getCozinha().getId());

        if (restaurante.getNome() == null || restaurante.getNome().isEmpty()) {
            throw new RestauranteNaoEncontradoException(
                    String.format("Nome do restaurante é obrigatório"));
        } else {
            restaurante.setCozinha(cozinha);
            return restauranteRepository.save(restaurante);
        }


    }

    public Restaurante atualizarRestaurante(Long id, Restaurante restaurante) {
        Optional<Restaurante> restauranteAtual = restauranteRepository.findById(id);

        Optional<Cozinha> cozinhaId = cozinhaRepository.findById(restaurante.getCozinha().getId());
        if (restauranteAtual.isEmpty()){
            throw new RestauranteNaoEncontradoException(
                    String.format("Não existe um cadastro de restaurante com códido %d ",id));
        }
        restauranteAtual.get().setNome(restaurante.getNome());
        restauranteAtual.get().setCozinha(cozinhaId.get());
        restauranteAtual.get().setTaxaFrete(restaurante.getTaxaFrete());



        return restauranteRepository.save(restauranteAtual.get());
    }

    public Restaurante atualizarParcial(Long id, Map<String,Object> campos) {
        Optional<Restaurante> restauranteAtual = restauranteRepository.findById(id);
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restaurante = objectMapper.convertValue(campos, Restaurante.class);

        if (restauranteAtual.isEmpty()){
            throw new RestauranteNaoEncontradoException(
                    String.format("Não existe um cadastro de restaurante com códido %d ",id));
        }else{
            campos.forEach((chave, valor) -> {
                Field field = ReflectionUtils.findField(Restaurante.class, chave);
                field.setAccessible(true);
                Object novoValor = ReflectionUtils.getField(field, restaurante);
                ReflectionUtils.setField(field, restauranteAtual.get(), novoValor);
            });
        }
        return restauranteRepository.save(restauranteAtual.get());
    }
    public Restaurante buscarOuFalha(Long id){
        return restauranteRepository.findById(id).orElseThrow(() -> new RestauranteNaoEncontradoException(
                String.format("Não existe um cadastro de restaurante com códido %d ",id)
                )
        );
    }
}
