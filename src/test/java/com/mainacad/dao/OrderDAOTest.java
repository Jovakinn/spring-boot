package com.mainacad.dao;

import com.mainacad.ApplicationRunner;
import com.mainacad.entity.Cart;
import com.mainacad.entity.Item;
import com.mainacad.entity.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(ApplicationRunner.class)
@ActiveProfiles("dev")
class OrderDAOTest {

    @Autowired
    OrderDAO orderDAO;
    ItemDAO itemDAO;
    CartDAO cartDAO;


    @Test
    void testFindUserByLoginAndPassword(){

        Cart cart = new Cart();
        cartDAO.save(cart);

        Item item = new Item();
        itemDAO.save(item);

        Order order = new Order();
        order.setAmount(1);
        order.setCart(cart);
        order.setItem(item);

        Order savedOrder = orderDAO.save(order);
        assertNotNull(savedOrder);


        List<Order> orders = orderDAO.findAllByAmount(savedOrder.getAmount());
        assertNotNull(order);
        assertTrue(!orders.isEmpty());
        assertEquals(orders.get(0).getAmount(), savedOrder.getAmount());

        orderDAO.delete(savedOrder);
    }
}