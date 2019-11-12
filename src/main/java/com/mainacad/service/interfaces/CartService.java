package com.mainacad.service.interfaces;

import com.mainacad.entity.Cart;
import com.mainacad.entity.User;

import java.util.List;
import java.util.Optional;

public interface CartService {

    Cart save(Cart cart);
    Cart update(Cart cart);
    Cart findOne(Integer id);
    List<Cart> findAll();
    List<Cart> findByUser(Integer userID);
    List<Cart> findByOpenCartAndUser(Integer userId);
    void delete(Integer id);
    Optional<Cart> findById(Integer id);

}
