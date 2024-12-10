package com.aleanse.ifood.exceptionhandler;

import com.aleanse.ifood.exception.EntidadeEmUsoException;
import com.aleanse.ifood.exception.EntidadeNaoEncontradaException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(EntidadeNaoEncontradaException.class)
    public ResponseEntity<?> handleEntidadeNaoEncontrado(
            EntidadeNaoEncontradaException e){
        HttpStatus status = HttpStatus.NOT_FOUND;

        Problem problema = Problem.builder()
                .title("entidade não encontrada")
                .status(status.value())
                .detail(e.getMessage()).build();


        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problema);

    }

    @ExceptionHandler(EntidadeEmUsoException.class)
    public ResponseEntity<?> handleEntidadeEmUso(
            EntidadeEmUsoException e){
        HttpStatus status = HttpStatus.CONFLICT;
        Problem problema = Problem.builder()
                .title("Entidade em uso")
                .status(status.value())
                .detail(e.getMessage()).build();
        return ResponseEntity.status(status).body(problema);

    }




    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
         body = Problem.builder()
                .title((String) body)
                .status(statusCode.value()).build();
        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }


}
