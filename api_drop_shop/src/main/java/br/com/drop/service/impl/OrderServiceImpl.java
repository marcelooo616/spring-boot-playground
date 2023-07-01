package br.com.drop.service.impl;


import br.com.drop.model.dto.ItemDTO;
import br.com.drop.model.dto.OrderDTO;
import br.com.drop.model.entities.Items;
import br.com.drop.model.entities.Order;
import br.com.drop.model.entities.Product;
import br.com.drop.model.entities.User;
import br.com.drop.model.exeption.BusinessRule;
import br.com.drop.repository.ItemsRepository;
import br.com.drop.repository.OrderRepository;
import br.com.drop.repository.ProductRepository;
import br.com.drop.repository.UserRepository;
import br.com.drop.service.OrderService;
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
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ItemsRepository itemsRepository;


    @Override
    @Transactional
    public Order save(OrderDTO dto) {

        Integer user_id = dto.getUser();
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new BusinessRule(HttpStatus.NOT_FOUND,"Invalid user code!"));

        Order order = new Order();
        order.setDate(LocalDate.now());
        order.setDelivery_status("Processing delivery");
        order.setPayment_status("Processing payment");
        order.setUser(user);
        order.setTotal_value(dto.getTotal_value());

        List<Items> itemsList = convertItems(order, dto.getItems());
        orderRepository.save(order);
        itemsRepository.saveAll(itemsList);
        order.setItemsList(itemsList);

        return order;
    }

    @Override
    public Optional<Order> showOrder(Integer id) {
        return orderRepository.findByIdFetchItens(id);
    }

    private List<Items> convertItems(Order order, List<ItemDTO> items){

        if(items.isEmpty()){
            throw  new BusinessRule(HttpStatus.NOT_FOUND,"It is not possible to place an order without items.");
        }

        return items
                .stream()
                .map(dto -> {

                    Integer product_id = dto.getProduct();
                    Product product = productRepository.findById(product_id)
                            .orElseThrow(() -> new BusinessRule(HttpStatus.NOT_FOUND,"Invalid product code!" + product_id));

                    Items itemsOrder = new Items();
                    itemsOrder.setAmount(dto.getAmount());
                    itemsOrder.setOrder(order);
                    itemsOrder.setProduct(product);
                    return itemsOrder;

                }).collect(Collectors.toList());


    }
}
