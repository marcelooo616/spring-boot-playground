package drop.controller;



import drop.model.entities.Cliente;
import drop.model.entities.Pedido;
import drop.model.repository.ClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class ClientesController {

    @Autowired
    private ClientesRepository repository;


    @GetMapping("/clientes")
    public List<Cliente> getAllClientes(){
        return repository.findAll();
    }

    @GetMapping("/clientes/nome/{nome}")
    public List<Cliente> getByName(@PathVariable String nome){
        return repository.findByName(nome);
    }

    @GetMapping("/clientes/endereco/{endereco}")
    public List<Cliente> getByEndereco(@PathVariable String endereco){
        return repository.findByEndereco(endereco.toLowerCase());
    }

    @GetMapping("/cliente/{clienteId}/pedidos")
    public List<Cliente> getPedidosCliente(@PathVariable Integer clienteId) {
        return repository.findAllById(clienteId);

    }


}
