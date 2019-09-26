package com.mainacad.service.impl;

import com.mainacad.dao.UserDAO;
import com.mainacad.entity.User;
import com.mainacad.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            return userDAO.saveAndFlush(user);
        }
        return userDAO.saveAndFlush(user);
    }

    @Override
    public User update(User user) {
        if (user.getId() != null && findOne(user.getId()) != null) {
            return userDAO.saveAndFlush(user);
        }
        return null;
    }

    @Override
    public User findOne(Integer id) {
        Optional<User> userWrapper = userDAO.findById(id);
        if (userWrapper.isPresent()){
            return userWrapper.get();
        }
        return null;
    }

    @Override
    @Async
    public User findOneByLoginAndPassword(String login, String password) {
        List<User> users = userDAO.findAllByLoginAndPassword(login, password);
        if (!users.isEmpty()){
            users.get(0);
        }
        return null;
    }

    @Override
    public User findOneByEmail(String email) {
        List<User> users = userDAO.findAllByEmailQuery(email);
        if (!users.isEmpty()){
            users.get(0);
        }
        return null;
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public void delete(Integer id) {
        userDAO.deleteById(id);
    }
}