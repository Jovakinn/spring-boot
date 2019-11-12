package com.mainacad.service;

import com.mainacad.ApplicationRunner;
import com.mainacad.entity.*;
import com.mainacad.service.interfaces.CartService;
import com.mainacad.service.interfaces.ItemService;
import com.mainacad.service.interfaces.OrderService;
import com.mainacad.service.interfaces.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringJUnitConfig(ApplicationRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderServiceTest  {

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Autowired
    CartService cartService;

    @Autowired
    ItemService itemService;

    @Test
    public void testGetAndUpdateAndDelete() throws NullPointerException {

        // creating user
        User user = new User();
        user.setEmail("ignatenko2207@gmail.com");
        user.setFirstName("Alex");
        user.setLastName("Ignatenko");
        user.setLogin("ignatenko2207");
        user.setPassword("12345");
        user.setProfile(Profile.ADMIN);

        user = userService.save(user);

        // creating cart
        Cart cart = new Cart();
        cart.setStatus(Status.OPEN);
        cart.setUser(user);
        cart.setTime(1l);
        cart = cartService.save(cart);

        // creating item
        Item item = new Item();
        item.setArticle("Per111000");
        item.setInitPrice(11111);
        item.setName("Perforator");
        item.setPrice(122222);
        item = itemService.save(item);

        // creating order
        Order order = new Order();
        order.setAmount(1);
        order.setCart(cart);
        order.setItem(item);

        order = orderService.save(order);

        Order savedOrder = orderService.findOne(order.getId());

        assertEquals(java.util.Optional.ofNullable(savedOrder.getAmount()), Optional.of(1));
        order.setAmount(555);

        Order updatedOrder = orderService.update(order);

        assertEquals(updatedOrder.getId(), savedOrder.getId());
        assertNotEquals(updatedOrder.getAmount(), savedOrder.getAmount());

        assertEquals((int)updatedOrder.getAmount(), 555);

        orderService.delete(updatedOrder.getId());
        Optional<Order> deletedOrder = orderService.findById(savedOrder.getId());

        assertEquals(deletedOrder, Optional.empty());
    }
}