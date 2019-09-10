package com.mainacad.dao;

import com.mainacad.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDAO extends JpaRepository<User, Integer> {

    List<User> findAllByLoginAndPassword(String Login, String password);

    @Query(nativeQuery = true, value = "SELECT * FROM users WHERE emai=?1")
    List<User> findAllBySQLQuery(String email);


}
