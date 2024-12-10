package com.aleanse.ifood.controller;

import com.aleanse.ifood.exception.EstadoNaoEncontradoException;
import com.aleanse.ifood.model.Cidade;
import com.aleanse.ifood.repository.CidadeRepository;
import com.aleanse.ifood.service.CadastroCidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeController {
    @Autowired
    private CidadeRepository cidadeRepository;
    @Autowired
    private CadastroCidadeService cadastroCidadeService;


    @GetMapping
    public List<Cidade> listar(){
        return cidadeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> listarPorId(@PathVariable Long id){

        Cidade cidade = cadastroCidadeService.buscarOuFalhar(id);
        return ResponseEntity.ok(cidade);

    }
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Cidade cidade){
        try {
            Cidade cidadeSalva = cadastroCidadeService.salvarCidade(cidade);
            return ResponseEntity.status(HttpStatus.CREATED).body(cidadeSalva);
        } catch (EstadoNaoEncontradoException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Cidade cidade){
        try {
            Cidade CidadeAtualizada = cadastroCidadeService.atualizarCidade(id,cidade);
            return ResponseEntity.ok(CidadeAtualizada);
        } catch (EstadoNaoEncontradoException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){

        cadastroCidadeService.deletar(id);
    }




}
