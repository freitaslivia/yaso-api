package br.com.yaso.api.service;

public class ErroNegocioException extends RuntimeException{
    public ErroNegocioException(String mensagem) {
        super(mensagem);
    }

}
