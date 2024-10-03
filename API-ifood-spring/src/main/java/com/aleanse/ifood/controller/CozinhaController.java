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
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Cozinha> editar(@PathVariable Long id, @RequestBody Cozinha cozinha){

        Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(id);
        if (cozinhaAtual.isEmpty()){
            System.out.println("Cozinha não encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            cozinhaAtual.get().setNome(cozinha.getNome());
           // uma alternativa seria: BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");
            Cozinha cozinhaAtualizada = cadastroCozinhaService.salvarCozinha(cozinhaAtual.get());
            return ResponseEntity.status(HttpStatus.OK).body(cozinhaRepository.save(cozinhaAtualizada));
        }

    }
    @GetMapping("/{id}")
    public ResponseEntity<Cozinha> listarCozinha(@PathVariable Long id){
        Optional<Cozinha> cozinha = cozinhaRepository.findById(id);
        if (cozinha.isEmpty()){
            System.out.println("Cozinha não encontrada");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }else return ResponseEntity.ok(cozinha.get());

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Cozinha> deletar(@PathVariable Long id){
        try {
            cadastroCozinhaService.excluir(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

        } catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

        }

        catch (EntidadeEmUsoException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }


    }


}
