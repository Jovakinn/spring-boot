package com.mainacad.dao;

import com.mainacad.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDAO extends JpaRepository<Order, Integer> {


}
