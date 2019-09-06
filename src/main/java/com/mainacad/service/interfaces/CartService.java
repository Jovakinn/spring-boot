package com.mainacad.service.interfaces;

import com.mainacad.entity.Cart;

import java.util.List;

public interface CartService {

    Cart save(Cart cart);
    Cart update(Cart cart);
    Cart findOne(Integer id);
    List<Cart> findAll();
    void delete(Integer id);

}
