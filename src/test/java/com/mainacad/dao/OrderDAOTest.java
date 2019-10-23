package com.mainacad.dao;

import com.mainacad.ApplicationRunner;
import com.mainacad.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(ApplicationRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderDAOTest {

    @Autowired
    OrderDAO orderDAO;

    @Autowired
    ItemDAO itemDAO;

    @Autowired
    CartDAO cartDAO;


    @Test
    void testFindUserByLoginAndPassword() throws NullPointerException{

        User user = new User();
        user.setId(1);
        user.setEmail("ignatenko2207@gmail.com");
        user.setFirstName("Alex");
        user.setLastName("Ignatenko");
        user.setLogin("ignatenko2207");
        user.setPassword("12345");
        user.setProfile(Profile.ADMIN);

        Cart cart = new Cart();
        cart.setStatus(Status.OPEN);
        cart.setUser(user);
        cart.setTime(1l);
        cartDAO.save(cart);

        Item item = new Item();
        item.setArticle("Per111000");
        item.setInitPrice(11111);
        item.setName("Perforator");
        item.setPrice(122222);
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