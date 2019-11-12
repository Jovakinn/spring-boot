package com.mainacad.dao;

import com.mainacad.ApplicationRunner;
import com.mainacad.entity.Cart;
import com.mainacad.entity.Profile;
import com.mainacad.entity.Status;
import com.mainacad.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(ApplicationRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CartDAOTest {

    @Autowired
    CartDAO cartDAO;

    @Autowired
    UserDAO userDAO;

    @Test
    void findByOpenCartAndUser() {

        User user = new User();
        user.setEmail("ignatenko2207@gmail.com");
        user.setFirstName("Alex");
        user.setLastName("Ignatenko");
        user.setLogin("ignatenko2207");
        user.setPassword("12345");
        user.setProfile(Profile.ADMIN);
        user = userDAO.save(user);

        Cart cart = new Cart();
        cart.setStatus(Status.OPEN);
        cart.setUser(user);
        cart.setTime(1l);

        Cart savedCart = cartDAO.save(cart);

        assertNotNull(savedCart);

        List<Cart> carts = cartDAO.findByOpenCartAndUser(savedCart.getUser().getId());
        assertNotNull(carts);

        cartDAO.delete(savedCart);


    }
}