package com.mainacad.service.interfaces;

import com.mainacad.entity.Cart;
import com.mainacad.entity.User;

import java.util.List;

public interface CartService {

    Cart save(Cart cart);
    Cart update(Cart cart);
    Cart findOne(Integer id);
    List<Cart> findAll();
    List<Cart> findByUser(User user);
    List<Cart> findByOpenCartAndUser(Integer userId);
    void delete(Integer id);

}
