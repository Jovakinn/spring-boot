package com.mainacad.controller;

import com.mainacad.ApplicationRunner;
import com.mainacad.entity.*;
import com.mainacad.service.interfaces.CartService;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.net.URI;
import java.net.URISyntaxException;

@SpringJUnitConfig(ApplicationRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"json", "test"})
class CartControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @MockBean
    CartService cartService;

    @MockBean
    UserService userService;

    @Test
    void createCart() throws URISyntaxException, NullPointerException {
        User user = new User();
        user.setLogin("12344");
        user.setEmail("max05012004@gmail.com");
        user.setFirstName("Max");
        user.setLastName("Xam");
        user.setPassword("111");
        user.setProfile(Profile.CLIENT);

        Cart cart = new Cart();
        cart.setStatus(Status.DELIVERED);
        cart.setTime(1l);
        cart.setUser(user);

        Mockito.when(cartService.save(Mockito.any(Cart.class))).thenReturn(cart);

        RequestEntity<Cart> request = new RequestEntity<>(cart, HttpMethod.POST, new URI("/cart"));

        ResponseEntity<Cart> response = testRestTemplate.exchange(request, Cart.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

        Mockito.verify(cartService, Mockito.times(1)).save(Mockito.any(Cart.class));
    }

    @Test
    void updateCart() throws URISyntaxException, NullPointerException {
        User user = new User();
        user.setLogin("12344");
        user.setEmail("max05012004@gmail.com");
        user.setFirstName("Max");
        user.setLastName("Xam");
        user.setPassword("111");
        user.setProfile(Profile.CLIENT);
        userService.save(user);

        Cart cart = new Cart();
        cart.setStatus(Status.DELIVERED);
        cart.setTime(1l);
        cart.setUser(user);
        cartService.save(cart);

        Mockito.when(cartService.update(Mockito.any(Cart.class))).thenReturn(cart);

        RequestEntity<Cart> request = new RequestEntity<>(cart, HttpMethod.PUT, new URI("/cart"));

        ResponseEntity<Cart> response = testRestTemplate.exchange(request, Cart.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

        Mockito.verify(cartService, Mockito.times(1)).update(Mockito.any(Cart.class));
    }

    @Test
    void getOneOrList() throws URISyntaxException, NullPointerException {
        Cart cart = new Cart();

        Mockito.when(cartService.findOne(1)).thenReturn(cart);

        RequestEntity<Cart> request = new RequestEntity<>(HttpMethod.GET, new URI("/cart/1"));

        ResponseEntity<Cart> response = testRestTemplate.exchange(request, Cart.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

        Mockito.verify(cartService, Mockito.times(1)).findOne(Mockito.anyInt());
    }

    @Test
    void delete() throws URISyntaxException, NullPointerException {
        Mockito.doNothing().when(cartService).delete(Mockito.anyInt());

        RequestEntity<Cart> request = new RequestEntity<>(HttpMethod.DELETE, new URI("/cart/222"));

        ResponseEntity response = testRestTemplate.exchange(request, Object.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

        Mockito.verify(cartService, Mockito.times(1)).delete(Mockito.anyInt());
    }
}