package com.github.marcelooo616.exception;

public class PedidoNaoEncontradoExceptio extends RuntimeException {
    public PedidoNaoEncontradoExceptio() {
        super("Pedido não encontrado.");
    }
}

