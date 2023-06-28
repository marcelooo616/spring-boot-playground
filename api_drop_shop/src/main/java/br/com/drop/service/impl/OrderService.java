package br.com.drop.service.impl;

import br.com.drop.model.dto.OrderDTO;
import br.com.drop.model.entities.Order;

public interface OrderService {


    Order save(OrderDTO dto);
}
