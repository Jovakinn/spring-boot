package com.mainacad.service.interfaces;

import com.mainacad.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User save(User user);
    User update(User user);
    User findOne(Integer id);
    User findOneByLoginAndPassword(String login, String password);
    User findOneByEmail(String email);
    List<User> findAllByEmailQuery(String email);
    Optional<User> findById(Integer id);
    List<User> findAll();
    void delete(Integer id);

}