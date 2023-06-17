package com.github.marcelooo616.restController;


import com.github.marcelooo616.domain.repository.Clientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api/clientes")
public class ClienteController {


    @Autowired
    Clientes repository;


    @GetMapping("/hello/{nome}")
    @ResponseBody
    public String helloCliente(@PathVariable("nome") String nome){
        return String.format("Hello %s ", nome);
    }
}
