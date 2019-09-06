package com.mainacad.service;

import com.mainacad.entity.User;

import java.util.List;

public interface UserService {

    User save(User user);
    User update(User user);
    User findOne(Integer id);
    List<User> findAll();
    void delete(Integer id);

}
