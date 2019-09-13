package com.mainacad.service;

import com.mainacad.ApplicationRunner;
import com.mainacad.entity.Cart;
import com.mainacad.entity.Item;
import com.mainacad.entity.Order;
import com.mainacad.service.interfaces.CartService;
import com.mainacad.service.interfaces.ItemService;
import com.mainacad.service.interfaces.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This test is working incorrect because of relation to another bean,
 * to create Order we need other beans but Spring told us, that
 * it is NullPointerException...
 */

@SpringJUnitConfig(ApplicationRunner.class)
@ActiveProfiles("dev")
class OrderServiceTest {

    @Autowired
    OrderService orderService;
    CartService cartService;
    ItemService itemService;

//    @Test
    public void testGetAndUpdate() {
        Cart cart = new Cart();
        cartService.save(cart);

        Item item = new Item();
        itemService.save(item);

        Order order = new Order();
        order.setAmount(1);
        order.setCart(cart);
        order.setItem(item);

        orderService.save(order);

        Order savedOrder = orderService.findOne(order.getId());

        assertEquals(java.util.Optional.ofNullable(savedOrder.getAmount()), 1);
        savedOrder.setItem(item);

        Order updatedOrder = orderService.update(savedOrder);

        assertEquals(updatedOrder.getItem(), item);
    }
}