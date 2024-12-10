package com.aleanse.ifood.controller;


import com.aleanse.ifood.exception.EntidadeEmUsoException;
import com.aleanse.ifood.exception.EntidadeNaoEncontradaException;
import com.aleanse.ifood.model.Cozinha;
import com.aleanse.ifood.model.Estado;
import com.aleanse.ifood.model.Estado;
import com.aleanse.ifood.repository.CozinhaRepository;
import com.aleanse.ifood.repository.EstadoRepository;
import com.aleanse.ifood.repository.EstadoRepository;
import com.aleanse.ifood.service.CadastroEstadoService;
import com.aleanse.ifood.service.CadastroEstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {
    @Autowired
    private EstadoRepository EstadoRepository;
    @Autowired
    private CadastroEstadoService cadastroEstadoService;


    @GetMapping
    public List<Estado> listar(){
        return EstadoRepository.findAll();
    }
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Estado Estado){
        try {
            Estado EstadoSalvo = cadastroEstadoService.salvarEstado(Estado);
            return ResponseEntity.status(HttpStatus.CREATED).body(EstadoSalvo);
        } catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @GetMapping("/{id}")
    public Estado listarEstadoPorId(@PathVariable Long id){
        return cadastroEstadoService.buscarOuFalhar(id);

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Estado Estado){
        try {
            Estado EstadoAtualizado = cadastroEstadoService.atualizarEstado(id,Estado);
            return ResponseEntity.ok(EstadoAtualizado);
        } catch (EntidadeNaoEncontradaException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        cadastroEstadoService.deletar(id);
    }

}
