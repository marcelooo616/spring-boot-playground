package br.com.drop.service;

import br.com.drop.model.dto.OrderDTO;
import br.com.drop.model.entities.Order;

import java.util.Optional;

public interface OrderService {

    Order save(OrderDTO dto);

    Optional<Order> showOrder(Integer id);
}
