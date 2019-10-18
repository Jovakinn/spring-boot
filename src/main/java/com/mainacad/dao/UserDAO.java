package com.mainacad.dao;

import com.mainacad.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Integer> {

    List<User> findAllByLoginAndPassword(String Login, String password);

    @Query(nativeQuery = true, value = "SELECT * FROM users WHERE email=?1")
    List<User> findAllByEmailQuery(String email);

    Optional<User> findById(Integer id);

}
