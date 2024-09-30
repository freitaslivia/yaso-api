package br.com.yaso.api.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ErroNegocioException.class)
  public ResponseEntity<Object> handleErroNegocioException(ErroNegocioException ex, WebRequest request) {
    String mensagem = ex.getMessage();
    return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST); // Retorna erro 400
  }

  // Outros métodos de tratamento de exceções podem ser adicionados aqui
}