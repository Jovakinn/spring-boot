package com.mainacad.service;

import com.mainacad.ApplicationRunner;
import com.mainacad.entity.Cart;
import com.mainacad.entity.Profile;
import com.mainacad.entity.Status;
import com.mainacad.entity.User;
import com.mainacad.service.interfaces.CartService;
import com.mainacad.service.interfaces.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(ApplicationRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CartServiceTest {

    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;

    @Test
    public void testGetAndUpdateAndDelete() throws NullPointerException {
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

        Cart savedCart = cartService.findOne(cart.getId());

        assertEquals(savedCart.getUser(), user);
        savedCart.setStatus(Status.OPEN);

        Cart updatedCart = cartService.update(savedCart);

        assertEquals(updatedCart.getStatus(), Status.OPEN);

        cartService.delete(updatedCart.getId());
        Optional<Cart> deletedCart = cartService.findById(savedCart.getId());

        assertEquals(deletedCart, Optional.empty());
    }

}