package com.mainacad.dao;

import com.mainacad.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDAO extends JpaRepository<Cart, Integer> {
}
