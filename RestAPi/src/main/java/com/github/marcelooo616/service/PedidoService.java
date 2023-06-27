package com.github.marcelooo616.service;


import com.github.marcelooo616.domain.entity.Pedido;
import com.github.marcelooo616.rest.dto.PedidoDTO;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);
}
