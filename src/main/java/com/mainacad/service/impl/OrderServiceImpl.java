package com.mainacad.service.impl;

import com.mainacad.dao.OrderDAO;
import com.mainacad.entity.Order;
import com.mainacad.service.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (order.getId() != null && orderDAO.findOne(order.getId()) != null) {
            return orderDAO.update(order);
        }
        return null;
    }

    @Override
    public Order findOne(Integer id) {
        if (id != null) {
            return orderDAO.findOne(id);
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
