package com.mainacad.controller;

import com.mainacad.entity.User;
import com.mainacad.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Profile("json")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.save(user);

        if (savedUser != null) {
            return new ResponseEntity<User>(savedUser, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping()
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        User updatedUser = userService.update(user);

        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping({"/{id}","/",""})
    public ResponseEntity getOneOrList(@PathVariable(required = false) Integer id) { // check without id
        if (id != null) {
            User userFromDB = userService.findOne(id);
            if (userFromDB != null){
                return new ResponseEntity<>(userFromDB, HttpStatus.OK);
            }
        } else {
            List<User> users = userService.findAll();
            return new ResponseEntity<>(users, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        userService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}