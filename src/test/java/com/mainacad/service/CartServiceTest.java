package com.mainacad.service;

import com.mainacad.ApplicationRunner;
import com.mainacad.entity.Cart;
import com.mainacad.entity.Status;
import com.mainacad.entity.User;
import com.mainacad.service.interfaces.CartService;
import com.mainacad.service.interfaces.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This test is working incorrect because of relation to another bean,
 * to create Cart we need other beans but Spring told us, that
 * it is NullPointerException...
 */


@SpringJUnitConfig(ApplicationRunner.class)
@ActiveProfiles("dev")
class CartServiceTest {

    @Autowired
    CartService cartService;
    UserService userService;

    @Test
    public void testGetAndUpdate() {
        User user = new User();
        userService.save(user);

        Cart cart = new Cart();
        cart.setStatus(Status.DELIVERED);
        cart.setTime(1l);
        cart.setUser(user);

        cartService.save(cart);

        Cart savedCart = cartService.findOne(cart.getId());

        assertEquals(savedCart.getUser(), user);
        savedCart.setStatus(Status.OPEN);

        Cart updatedCart = cartService.update(savedCart);

        assertEquals(updatedCart.getStatus(), Status.OPEN);
    }

}