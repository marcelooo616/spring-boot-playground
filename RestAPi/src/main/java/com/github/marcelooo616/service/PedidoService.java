package com.github.marcelooo616.service;


import com.github.marcelooo616.domain.entity.Pedido;
import com.github.marcelooo616.domain.enums.StatusPedido;
import com.github.marcelooo616.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);

    void atualizaStatus(Integer id, StatusPedido  statusPedido);
}
