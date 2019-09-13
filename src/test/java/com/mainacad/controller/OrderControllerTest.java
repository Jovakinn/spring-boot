package com.mainacad.controller;

import com.mainacad.ApplicationRunner;
import com.mainacad.entity.*;
import com.mainacad.service.interfaces.CartService;
import com.mainacad.service.interfaces.ItemService;
import com.mainacad.service.interfaces.OrderService;
import com.mainacad.service.interfaces.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.net.URI;
import java.net.URISyntaxException;

@SpringJUnitConfig(ApplicationRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderControllerTest {

    // MAIN PROBLEM IS CREATING BEANS..

    @Autowired
    TestRestTemplate testRestTemplate;

    @MockBean
    OrderService orderService;
    CartService cartService;
    ItemService itemService;
    UserService userService;

    @Test
    void createOrder() throws URISyntaxException, NullPointerException {
        // creating user
        User user = new User();
        user.setEmail("ignatenko2207@gmail.com");
        user.setFirstName("Alex");
        user.setLastName("Ignatenko");
        user.setLogin("ignatenko2207");
        user.setPassword("12345");
        user.setProfile(Profile.ADMIN);

        userService.save(user);

        // creating cart
        Cart cart = new Cart();
        cart.setStatus(Status.OPEN);
        cart.setUser(user);
        cart.setTime(1l);
        cartService.save(cart);

        // creating item
        Item item = new Item();
        item.setArticle("Per111000");
        item.setInitPrice(11111);
        item.setName("Perforator");
        item.setPrice(122222);
        itemService.save(item);

        // creating order
        Order order = new Order();
        order.setAmount(1);
        order.setCart(cart);
        order.setItem(item);

        Mockito.when(orderService.save(Mockito.any(Order.class))).thenReturn(order);

        RequestEntity<Order> request = new RequestEntity(order, HttpMethod.POST, new URI("/order"));

        ResponseEntity<Order> response = testRestTemplate.exchange(request, Order.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

        Mockito.verify(orderService, Mockito.times(1)).save(Mockito.any(Order.class));
    }

    @Test
    void updateOrder() throws URISyntaxException, NullPointerException {
        // creating user
        User user = new User();
        user.setEmail("ignatenko2207@gmail.com");
        user.setFirstName("Alex");
        user.setLastName("Ignatenko");
        user.setLogin("ignatenko2207");
        user.setPassword("12345");
        user.setProfile(Profile.ADMIN);

        userService.save(user);

        // creating cart
        Cart cart = new Cart();
        cart.setStatus(Status.OPEN);
        cart.setUser(user);
        cart.setTime(1l);
        cartService.save(cart);

        // creating item
        Item item = new Item();
        item.setArticle("Per111000");
        item.setInitPrice(11111);
        item.setName("Perforator");
        item.setPrice(122222);
        itemService.save(item);

        // creating order
        Order order = new Order();
        order.setAmount(1);
        order.setCart(cart);
        order.setItem(item);

        Mockito.when(orderService.update(Mockito.any(Order.class))).thenReturn(order);

        RequestEntity<Order> request = new RequestEntity(order, HttpMethod.POST, new URI("/order/update"));

        ResponseEntity<Order> response = testRestTemplate.exchange(request, Order.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

        Mockito.verify(orderService, Mockito.times(1)).update(Mockito.any(Order.class));
    }

    @Test
    void getOneOrList() throws URISyntaxException, NullPointerException {
        Mockito.when(orderService.findOne(1)).thenReturn(orderService.findOne(1));

        RequestEntity<Order> request = new RequestEntity<>(HttpMethod.GET, new URI("/order/1"));

        ResponseEntity<Order> response = testRestTemplate.exchange(request, Order.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

        Mockito.verify(orderService, Mockito.times(1)).findOne(Mockito.anyInt());
    }

    @Test
    void delete() throws URISyntaxException, NullPointerException {
        Mockito.doNothing().when(orderService).delete(Mockito.anyInt());

        RequestEntity<User> request = new RequestEntity<>(HttpMethod.DELETE, new URI("/order/222"));

        ResponseEntity response = testRestTemplate.exchange(request, Object.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

        Mockito.verify(orderService, Mockito.times(1)).delete(Mockito.anyInt());
    }
}