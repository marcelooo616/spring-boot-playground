package com.github.marcelooo616.exception;

public class SenhaInvalidaException extends RuntimeException {
    public SenhaInvalidaException(){
        super("Senha invalida");
    }
}
