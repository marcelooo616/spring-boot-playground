package drop.controller;


import drop.model.entities.Pedido;
import drop.model.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PedidoController {

    @Autowired
    private PedidoRepository repository;


    @GetMapping("/pedido")
    public List<Pedido> getAllPedidos(){
        return repository.findAll();
    }

    @GetMapping("/pedidos/{id}")
    public List<Pedido> getAllById(@PathVariable Integer id){
        return  repository.findByName(id);
    }




}
