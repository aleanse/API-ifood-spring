package com.aleanse.ifood.exceptionhandler;

import com.aleanse.ifood.exception.EntidadeEmUsoException;
import com.aleanse.ifood.exception.EntidadeNaoEncontradaException;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


import java.util.stream.Collectors;


@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatusCode status,
                                                                  WebRequest request) {
        logger.error("Erro ao processar a mensagem HTTP: {}", ExceptionUtils.getStackTrace(ex));

        Throwable rootCause = ExceptionUtils.getRootCause(ex);
        if (rootCause instanceof InvalidFormatException){
            return handleInvalidFormatException((InvalidFormatException) rootCause,headers,status,request);
        }

        String detail = "o corpo da requisição está invalido, Verifique erro de sintaxe.";
        Problem problema = Problem.builder()
                .title("entidade não encontrada")
                .status(status.value())
                .detail(detail).build();

        return handleExceptionInternal(ex, problema , headers, status, request);
    }
    private ResponseEntity<Object> handleInvalidFormatException(InvalidFormatException ex,
                                            HttpHeaders headers, HttpStatusCode status,
                                            WebRequest request) {
        ex.getPath().forEach(ref -> System.out.println(ref.getFieldName()));
        String path = ex.getPath().stream()
                .map(ref -> ref.getFieldName())
                .collect(Collectors.joining("."));
        String detail = String.format("a propriedade %s recebeu o valor %s," +
                "que é de um tipo invalido. Corriga e informe um valor compátivel com o tipo %s.",
                path,ex.getValue(),ex.getTargetType().getSimpleName());
        Problem problem = Problem.builder()
                .title("entidade não encontrada")
                .status(status.value())
                .detail(detail).build();
        return handleExceptionInternal(ex,problem, headers,status,request);

    }



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
    public ResponseEntity<?> handleEntidadeEmUsoException(
            EntidadeEmUsoException e){
        HttpStatus status = HttpStatus.CONFLICT;
        Problem problema = Problem.builder()
                .title("Entidade em uso")
                .status(status.value())
                .detail(e.getMessage()).build();
        return ResponseEntity.status(status).body(problema);

    }
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
                                                             HttpHeaders headers,
                                                             HttpStatusCode statusCode,
                                                             WebRequest request) {
         body = Problem.builder()
                .title((String) body)
                .status(statusCode.value()).build();
        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }


}
