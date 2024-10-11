package com.aleanse.ifood.controller;

import com.aleanse.ifood.exception.EntidadeNaoEncontradaException;
import com.aleanse.ifood.model.Cidade;
import com.aleanse.ifood.model.Estado;
import com.aleanse.ifood.repository.CidadeRepository;
import com.aleanse.ifood.repository.EstadoRepository;
import com.aleanse.ifood.service.CadastroCidadeService;
import com.aleanse.ifood.service.CadastroEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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
        try {
            Cidade cidade = cidadeRepository.findById(id).get();
            return ResponseEntity.ok(cidade);
        }catch (NoSuchElementException e){
            String mensagem = String.format("Cidade de código %d não encontrada",id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
        }
    }
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Cidade cidade){
        try {
            Cidade cidadeSalva = cadastroCidadeService.salvarCidade(cidade);
            return ResponseEntity.status(HttpStatus.CREATED).body(cidadeSalva);
        } catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Cidade cidade){
        try {
            Cidade CidadeAtualizada = cadastroCidadeService.atualizarCidade(id,cidade);
            return ResponseEntity.ok(CidadeAtualizada);
        } catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        try {
            cadastroCidadeService.deletar(id);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }


}
