package com.mainacad.service.interfaces;

import com.mainacad.entity.Order;

import java.util.List;

public interface OrderService {

    Order save(Order order);
    Order update(Order order);
    Order findOne(Integer id);
    Order findOneByAmount(Integer amount);
    List<Order> findAll();
    void delete(Integer id);

}
