package drop.controller;


import drop.model.entities.Produto;
import drop.model.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProdutoController {

    @Autowired
    private ProdutoRepository repository;

    @GetMapping("/produtos")
    public List<Produto> getAll(){
        return  repository.findAll();
    }

    @GetMapping("/produtos/categoria/{categoria}")
    public List<Produto> getByCategory(@PathVariable String categoria){
        return repository.findByCategory(categoria.toLowerCase());
    }
}
