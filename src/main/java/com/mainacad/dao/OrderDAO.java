package com.mainacad.dao;

import com.mainacad.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDAO extends JpaRepository<Order, Integer> {

    List<Order> findAllByAmount(Integer amount);
}
