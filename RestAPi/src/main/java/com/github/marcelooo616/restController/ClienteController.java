package com.github.marcelooo616.restController;


import com.github.marcelooo616.domain.entity.Cliente;
import com.github.marcelooo616.domain.repository.Clientes;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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


    @PutMapping("api/clientes/{id}")
    @ResponseBody
    public ResponseEntity update(@PathVariable Integer id,@RequestBody Cliente cliente){
        return clientes
                .findById(id)
                .map(clienteExixtente -> {
                    cliente.setId(clienteExixtente.getId());
                    clientes.save(cliente);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @GetMapping("/api/clientes")
    public ResponseEntity find(Cliente filtro){
        //TODO revisar Exemple
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        List<Cliente> lista = clientes.findAll(example);
        return ResponseEntity.ok(lista);

    }




}
