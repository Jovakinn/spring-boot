package com.mainacad.dao;

import com.mainacad.entity.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderDAO extends JpaRepository<Order, Integer> {

    List<Order> findAllByAmount(Integer amount);

    Optional<Order> findById(Integer id);

    @Query(nativeQuery = true, value = "SELECT * FROM orders WHERE cart_id=?")
    List<Order> findByCart(Integer cartId);
}
