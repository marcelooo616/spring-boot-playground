package com.github.marcelooo616.restController;


import com.github.marcelooo616.domain.entity.ItemPedido;
import com.github.marcelooo616.domain.entity.Pedido;
import com.github.marcelooo616.rest.dto.InformacaoItemPedidoDTO;
import com.github.marcelooo616.rest.dto.InformacoesPedidioDTO;
import com.github.marcelooo616.rest.dto.PedidoDTO;
import com.github.marcelooo616.service.PedidoService;


import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save( @RequestBody PedidoDTO dto ){
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }


    @GetMapping("{id}")
    public InformacoesPedidioDTO getById(@PathVariable Integer id){
        return service.obterPedidoCompleto(id)
                .map(p -> converter(p))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

    }

    private  InformacoesPedidioDTO converter(Pedido pedido){
        return InformacoesPedidioDTO
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedito().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .items(converter(pedido.getPedidos()))
                .build();
    }

    private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens){
        if(CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }

        return itens.stream().map(
                item -> InformacaoItemPedidoDTO
                        .builder()
                .descricaoProduto(item.getProduto().getDescricao())
                .precoUnitario(item.getProduto().getPreco())
                .quantidade(item.getQuantidade())
                .build()
        ).collect(Collectors.toList());

    }


}
