package com.github.marcelooo616.restController;


import com.github.marcelooo616.service.PedidoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }
}
