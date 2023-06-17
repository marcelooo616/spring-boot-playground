package drop.controller;


import drop.model.entities.Cliente;
import drop.model.entities.Comentario;
import drop.model.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Access;
import java.util.List;

@RestController
public class ComentarioController {


    @Autowired
    ComentarioRepository repository;

    @GetMapping("/comentarios")
    public List<Comentario> getAllClientes(){
        return repository.findAll();
    }
}
