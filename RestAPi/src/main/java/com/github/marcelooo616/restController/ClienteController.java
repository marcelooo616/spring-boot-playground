package com.github.marcelooo616.restController;


import com.github.marcelooo616.domain.entity.Cliente;
import com.github.marcelooo616.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
public class ClienteController {



    Clientes clientes;

    public ClienteController(Clientes clientes) {
        this.clientes = clientes;
    }

    @GetMapping(value = "/api/clientes/{id}")
    @ResponseBody
    public ResponseEntity<Cliente> getClienteById(@PathVariable("id") Integer id){
       Optional<Cliente> cliente =  clientes.findById(id);

       if (cliente.isPresent()){
           return ResponseEntity.ok(cliente.get());
       }

       return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/clientes/cadastro")
    @ResponseBody
    public ResponseEntity<Cliente> save(@RequestBody  Cliente cliente){
        Cliente clienteSalvo = clientes.save(cliente);
        return ResponseEntity.ok(clienteSalvo);
    }
    @DeleteMapping("/api/clientes/{id}/delete")
    @ResponseBody
    public ResponseEntity<Cliente> delete(@PathVariable Integer id){

        Optional<Cliente> cliente = clientes.findById(id);

        if(cliente.isPresent()){
            clientes.delete((cliente.get()));
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}
