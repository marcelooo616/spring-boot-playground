package com.github.marcelooo616.service.impl;


import com.github.marcelooo616.domain.repository.Pedidos;
import com.github.marcelooo616.service.PedidoService;
import org.springframework.stereotype.Service;

@Service
public class PedidoServiceImpl implements PedidoService {

    private Pedidos repositorio;

    public PedidoServiceImpl(Pedidos repositorio) {
        this.repositorio = repositorio;
    }
}
