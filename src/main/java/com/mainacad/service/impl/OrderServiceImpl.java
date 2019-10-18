package com.mainacad.service.impl;

import com.mainacad.dao.OrderDAO;
import com.mainacad.entity.Order;
import com.mainacad.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDAO orderDAO;

    @Override
    public Order save(Order order) {
        return orderDAO.save(order);
    }

    @Override
    public Order update(Order order) {
        if (order.getId() != null && findOne(order.getId()) != null) {
            return orderDAO.saveAndFlush(order);
        }
        return null;
    }

    @Override
    public Order findOne(Integer id) {
        Optional<Order> orderWrapper = orderDAO.findById(id);
        if (orderWrapper.isPresent()){
            return orderWrapper.get();
        }
        return null;
    }

    @Override
    public Order findOneByAmount(Integer amount) {
        List<Order> orders = orderDAO.findAllByAmount(amount);
        if (!orders.isEmpty()){
            orders.get(0);
        }
        return null;
    }

    @Override
    public Optional<Order> findById(Integer id) {
        return orderDAO.findById(id);
    }

    @Override
    public List<Order> findByCart(Integer cartId) {
        List<Order> orders = orderDAO.findByCart(cartId);
        if (!orders.isEmpty()){
            orders.get(0);
        }
        return null;
    }

    @Override
    public List<Order> findAll() {
        return orderDAO.findAll();
    }

    @Override
    public void delete(Integer id) {
        Order order = findOne(id);
        if (order != null) {
            orderDAO.delete(order);
        }
    }
}
