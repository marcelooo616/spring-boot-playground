package drop.controller;


import ch.qos.logback.core.net.server.Client;
import drop.model.entities.Cliente;
import drop.model.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientesController {

    @Autowired
    private ClientesRepository repository;


    @GetMapping("/clientes")
    public List<Cliente> getAllClientes(){
        return repository.getAll();
    }
}
