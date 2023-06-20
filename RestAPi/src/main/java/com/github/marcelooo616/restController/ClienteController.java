package com.github.marcelooo616.restController;


import com.github.marcelooo616.domain.entity.Cliente;
import com.github.marcelooo616.domain.repository.Clientes;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/clientes")
public class ClienteController {



    Clientes clientes;

    public ClienteController(Clientes clientes) {
        this.clientes = clientes;
    }

    @GetMapping(value = "/{id}")
    public Cliente getClienteById(@PathVariable("id") Integer id){
       return clientes
               .findById(id)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontrado" ));

    }

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody  Cliente cliente){
        return clientes.save(cliente);

    }

    @DeleteMapping("/{id}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
         clientes.findById(id)
                 .map(cliente -> {
                     clientes.delete(cliente);
                     return cliente;
                 })
                 .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente deletado com suceso !!"));


    }



    @PutMapping("/{id}/atualizar")
    @ResponseStatus(HttpStatus.NO_CONTENT)

    public Cliente update(@PathVariable Integer id,@RequestBody Cliente cliente){
        return clientes.findById(id).map(clienteAtulizado -> {
                    cliente.setId(clienteAtulizado.getId());
                    clientes.save(cliente);
                    return  clienteAtulizado;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

    }

    @GetMapping
    public List<Cliente> find(Cliente filtro){
        //TODO revisar Exemple
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        return clientes.findAll(example);


    }




}
