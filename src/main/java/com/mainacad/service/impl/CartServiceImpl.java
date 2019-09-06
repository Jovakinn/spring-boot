package com.mainacad.service.impl;

import com.mainacad.dao.CartDAO;
import com.mainacad.entity.Cart;
import com.mainacad.service.interfaces.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    CartDAO cartDAO;

    @Override
    public Cart save(Cart cart) {
        return cartDAO.save(cart);
    }

    @Override
    public Cart update(Cart cart) {
        if (cart.getId() != null && cartDAO.findOne(cart.getId()) != null) {
            return cartDAO.update(cart);
        }
        return null;
    }

    @Override
    public Cart findOne(Integer id) {
        if (id != null) {
            return cartDAO.findOne(id);
        }
        return null;
    }

    @Override
    public List<Cart> findAll() {
        return cartDAO.findAll();
    }

    @Override
    public void delete(Integer id) {
        Cart cart = findOne(id);
        if (cart != null) {
            cartDAO.delete(cart);
        }
    }
}
