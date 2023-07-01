package com.github.marcelooo616.service.impl;


import com.github.marcelooo616.domain.entity.Cliente;
import com.github.marcelooo616.domain.entity.ItemPedido;
import com.github.marcelooo616.domain.entity.Pedido;
import com.github.marcelooo616.domain.entity.Produto;
import com.github.marcelooo616.domain.enums.StatusPedido;
import com.github.marcelooo616.domain.repository.Clientes;
import com.github.marcelooo616.domain.repository.ItemsPedido;
import com.github.marcelooo616.domain.repository.Pedidos;
import com.github.marcelooo616.domain.repository.Produtos;
import com.github.marcelooo616.exception.PedidoNaoEncontradoExceptio;
import com.github.marcelooo616.exception.RegraNegocioException;
import com.github.marcelooo616.rest.dto.ItemPedidoDTO;
import com.github.marcelooo616.rest.dto.PedidoDTO;
import com.github.marcelooo616.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final Pedidos pedidosRepository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;
    private final ItemsPedido itemsPedidoRepository;


    @Override
    @Transactional
    public Pedido salvar( PedidoDTO dto ) {

        Integer idCLiente = dto.getCliente();
        Cliente cliente = clientesRepository.findById(idCLiente)
                .orElseThrow(() -> new RegraNegocioException(HttpStatus.NOT_FOUND, "Codigo de cliente invalido"));

        Pedido pedido = new Pedido();

        pedido.setTotal(dto.getTotal());
        pedido.setDataPedito(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
        pedidosRepository.save(pedido);
        itemsPedidoRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);


        return pedido;
    }


    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return pedidosRepository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido statusPedido) {
        pedidosRepository.findById(id)
                .map(pedido -> {
                    pedido.setStatus(statusPedido);
                    return pedidosRepository.save(pedido);
                }).orElseThrow( () -> new PedidoNaoEncontradoExceptio());
    }


    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){

        if(items.isEmpty()){
            throw  new RegraNegocioException(HttpStatus.NOT_FOUND, "Não e possivel realizar um pedido sem items.");
        }

        return items
                .stream()
                .map(dto -> {

                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository.findById(idProduto)
                            .orElseThrow(() -> new RegraNegocioException(HttpStatus.NOT_FOUND, "Codigo de produto invalido" + idProduto));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return  itemPedido;

                }).collect(Collectors.toList());
    }




}
