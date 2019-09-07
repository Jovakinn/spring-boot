package com.mainacad.dao;

import com.mainacad.ApplicationRunner;
import com.mainacad.entity.Cart;
import com.mainacad.entity.Profile;
import com.mainacad.entity.Status;
import com.mainacad.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringJUnitConfig(ApplicationRunner.class)
@ActiveProfiles("dev")
class CartDAOTest {

    @Autowired
    CartDAO cartDAO;
    UserDAO userDAO;

    @Test
    public void getConnectionFactory() {
        assertNotNull(cartDAO.getConnectionFactory());
        assertTrue(cartDAO.getConnectionFactory().getClass().getSimpleName().equals("PostgresFactory"));
    }

    @Test
    public void testSaveAndDelete(){
        User user = new User();
        userDAO.save(user);

        Cart cart = new Cart();
        cart.setStatus(Status.DELIVERED);
        cart.setTime(1l);
        cart.setUser(user);

        Cart testCart = cartDAO.save(cart);

        assertNotNull(testCart);
        assertNotNull(testCart.getId());

        cartDAO.delete(cart);
        assertNull(cartDAO.findOne(testCart.getId()));
    }

    @Test
    public void testGetAll(){
        User user = new User();
        user.setEmail("ignatenko2207@gmail.com");
        user.setFirstName("Alex");
        user.setLastName("Ignatenko");
        user.setLogin("ignatenko2207");
        user.setPassword("12345");
        user.setProfile(Profile.ADMIN);
        userDAO.save(user);

        Cart cart = new Cart();
        cart.setStatus(Status.DELIVERED);
        cart.setTime(1l);
        cart.setUser(user);

        Cart cart2 = new Cart();
        cart.setStatus(Status.OPEN);
        cart.setTime(2l);
        cart.setUser(user);

        Cart savedCart1 = cartDAO.save(cart);
        Cart savedCart2 = cartDAO.save(cart2);

        List<Cart> carts = cartDAO.findAll();

        assertNotNull(carts);
        assertTrue(carts.size() >= 2);

        cartDAO.delete(savedCart1);
        cartDAO.delete(savedCart2);
    }

}