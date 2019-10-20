package com.mainacad.service.impl;

import com.mainacad.dao.CartDAO;
import com.mainacad.entity.Cart;
import com.mainacad.entity.User;
import com.mainacad.service.interfaces.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        if (cart.getId() != null && findOne(cart.getId()) != null) {
            return cartDAO.saveAndFlush(cart);
        }
        return null;
    }

    @Override
    public Cart findOne(Integer id) {
        Optional<Cart> cartWrapper = cartDAO.findById(id);
        return cartWrapper.orElse(null);
    }

    @Override
    public List<Cart> findAll() {
        return cartDAO.findAll();
    }

    @Override
    public List<Cart> findByUser(User user) {
        List<Cart> carts = cartDAO.findByUser(user);
        if (!carts.isEmpty()){
            carts.get(0);
        }
        return null;
    }

    @Override
    public List<Cart> findByOpenCartAndUser(Integer userId) {
        List<Cart> carts = cartDAO.findByOpenCartAndUser(userId);
        if (!carts.isEmpty()){
            carts.get(0);
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        Cart cart = findOne(id);
        if (cart != null) {
            cartDAO.delete(cart);
        }
    }
}
