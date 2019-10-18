package com.mainacad.dao;

import com.mainacad.entity.Cart;
import com.mainacad.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartDAO extends JpaRepository<Cart, Integer> {

    Optional<Cart> findById(Integer id);

    List<Cart> findByUser(User user);

    @Query(nativeQuery = true, value = "SELECT * FROM carts WHERE status=false AND user_id=?")
    List<Cart> findByOpenCartAndUser(Integer userid);

}
