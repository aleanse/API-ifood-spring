package com.aleanse.ifood.controller;

import com.aleanse.ifood.exception.EntidadeEmUsoException;
import com.aleanse.ifood.exception.EntidadeNaoEncontradaException;
import com.aleanse.ifood.model.Cozinha;
import com.aleanse.ifood.repository.CozinhaRepository;
import com.aleanse.ifood.service.CadastroCozinhaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {
    @Autowired
    private CozinhaRepository cozinhaRepository;
    @Autowired
    private CadastroCozinhaService cadastroCozinhaService;

    @GetMapping
    public List<Cozinha> listar(){
        return cozinhaRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cozinha salvar(@RequestBody Cozinha cozinha){
        return cadastroCozinhaService.salvarCozinha(cozinha);

    }
    @PutMapping("/{id}")
    public Cozinha editar(@PathVariable Long id, @RequestBody Cozinha cozinha){

        Cozinha cozinhaAtual = cadastroCozinhaService.buscarOuFalhar(id);
        cozinhaAtual.setNome(cozinha.getNome());
        Cozinha cozinhaAtualizada = cadastroCozinhaService.salvarCozinha(cozinhaAtual);
        return cadastroCozinhaService.salvarCozinha(cozinhaAtualizada);

    }
    @GetMapping("/{id}")
    public Cozinha listarCozinha(@PathVariable Long id){
        return cadastroCozinhaService.buscarOuFalhar(id);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
       cadastroCozinhaService.excluir(id);

    }

}
