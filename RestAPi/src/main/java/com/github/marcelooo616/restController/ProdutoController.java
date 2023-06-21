package com.github.marcelooo616.restController;


import com.github.marcelooo616.domain.entity.Cliente;
import com.github.marcelooo616.domain.entity.Produto;
import com.github.marcelooo616.domain.repository.Produtos;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    Produtos produtos;

    public ProdutoController(Produtos produtos) {
        this.produtos = produtos;
    }


    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save(@RequestBody Produto produto){
        return produtos.save(produto);
    }

    @DeleteMapping("{id}/deletar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable("id") Integer id){
        produtos.findById(id)
                .map(produto -> {
                    produtos.delete(produto);
                    return produto;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao encontrado!"));

    }

    @PutMapping("/{id}/atualizar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Produto update(@PathVariable Integer id, @RequestBody Produto produto){
        return produtos.findById(id)
                .map(produtoAtualizado -> {
                    produto.setId(produtoAtualizado.getId());
                    produtos.save(produto);
                    return produtoAtualizado;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto nao encontrado!"));
    }

    @GetMapping("/{id}")
    public Produto getProdutoById(@PathVariable("id") Integer id){
        return produtos.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado!"));
    }

    @GetMapping("/buscar")
    public List<Produto> findAllFilter(Produto filtro){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        return produtos.findAll(example);

    }



    @GetMapping("/all")
    public List<Produto> findAllProduct(){
        return produtos.findAll();
    }



}
