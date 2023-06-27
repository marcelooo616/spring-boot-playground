package com.github.marcelooo616.restController;


import com.github.marcelooo616.domain.entity.Pedido;
import com.github.marcelooo616.rest.dto.PedidoDTO;
import com.github.marcelooo616.service.PedidoService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save( @RequestBody PedidoDTO dto ){
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }


}
