package com.github.marcelooo616.service;


import com.github.marcelooo616.model.Cliente;
import com.github.marcelooo616.repository.ClientesRepository;

import org.springframework.stereotype.Service;

@Service
public class ClientesService {


    private  ClientesRepository repository;

    //@Autowired
    public ClientesService(ClientesRepository repository){
        this.repository = repository;
    }

   /* @Autowired
    public void setRepository(ClientesRepository repository) {
        this.repository = repository;
    }*/

    public void salvarCliente(Cliente cliente){
        validarCliente(cliente);
        this.repository.persistir(cliente);

    }

    public void validarCliente(Cliente cliente){
            //aplica validações
    }


}

