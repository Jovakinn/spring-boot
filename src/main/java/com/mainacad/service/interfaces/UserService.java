package com.mainacad.service.interfaces;

import com.mainacad.entity.User;

import java.util.List;

public interface UserService {

    User save(User user);
    User update(User user);
    User findOne(Integer id);
    User findOneByLoginAndPassword(String login, String password);
    User findOneByEmail(String email);
    List<User> findAll();
    void delete(Integer id);

}