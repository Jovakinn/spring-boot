package com.mainacad.service;

import com.mainacad.ApplicationRunner;
import com.mainacad.entity.Profile;
import com.mainacad.entity.User;
import com.mainacad.service.interfaces.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(ApplicationRunner.class)
@ActiveProfiles("dev")
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Test
    public void testGetAndUpdate() {
        User user = new User();
        user.setEmail("ignatenko2207@gmail.com");
        user.setFirstName("Alex");
        user.setLastName("Ignatenko");
        user.setLogin("ignatenko2207");
        user.setPassword("12345");
        user.setProfile(Profile.ADMIN);

        userService.save(user);

        User savedUser = userService.findOne(user.getId());

        assertEquals(savedUser.getLogin(), "ignatenko2207");
        savedUser.setLogin("new_login");

        User updatedUser = userService.update(savedUser);

        assertEquals(updatedUser.getLogin(), "new_login");
    }

}