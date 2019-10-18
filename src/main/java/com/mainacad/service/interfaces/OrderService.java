package com.mainacad.service.interfaces;

import com.mainacad.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    Order save(Order order);
    Order update(Order order);
    Order findOne(Integer id);
    Order findOneByAmount(Integer amount);
    Optional<Order> findById(Integer id);
    List<Order> findByCart(Integer cartId);
    List<Order> findAll();
    void delete(Integer id);

}
