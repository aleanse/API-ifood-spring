package com.aleanse.ifood.controller;


import com.aleanse.ifood.exception.EntidadeNaoEncontradaException;
import com.aleanse.ifood.model.Restaurante;
import com.aleanse.ifood.repository.RestauranteRepository;
import com.aleanse.ifood.service.CadastroRestauranteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
    @Autowired
    private RestauranteRepository restauranteRepository;
    @Autowired
    private CadastroRestauranteService cadastroRestauranteService;


    @GetMapping
    public List<Restaurante> listar(){
        return restauranteRepository.findAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> listarRestaurante(@PathVariable Long id){
        try {
            Restaurante restaurante = restauranteRepository.findById(id).get();
            return ResponseEntity.ok(restaurante);
        }catch (Exception e){
            System.out.println(e);
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Restaurante restaurante){
        try {
            Restaurante restauranteSalvo = cadastroRestauranteService.salvarRestaurante(restaurante);
            return ResponseEntity.status(HttpStatus.CREATED).body(restauranteSalvo);
        } catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }


    }




}
