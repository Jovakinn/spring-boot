package com.mainacad.dao;

import com.mainacad.ApplicationRunner;
import com.mainacad.entity.Profile;
import com.mainacad.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(ApplicationRunner.class)
public class UserDAOTest {

    @Autowired
    UserDAO userDAO;

    @Test
    void testFindUserByLoginAndPassword(){
        User user = new User();
        user.setEmail("ignatenko2207@gmail.com");
        user.setFirstName("Alex");
        user.setLastName("Ignatenko");
        user.setLogin("ignatenko2207");
        user.setPassword("12345");
        user.setProfile(Profile.ADMIN);

        User savedUser = userDAO.save(user);
        assertNotNull(savedUser);

        List<User> users = userDAO.findAllByLoginAndPassword(savedUser.getLogin(), savedUser.getPassword());
        assertNotNull(users);
        assertTrue(!users.isEmpty());
        assertEquals(users.get(0).getEmail(), savedUser.getEmail());

        userDAO.delete(savedUser);
    }

    @Test
    void testFindUserBySQLQuery(){
        User user = new User();
        user.setEmail("ignatenko@gmail.com");
        user.setFirstName("Alex");
        user.setLastName("Ignatenko");
        user.setLogin("ignatenko2207");
        user.setPassword("12345");
        user.setProfile(Profile.ADMIN);

        User savedUser = userDAO.save(user);
        assertNotNull(savedUser);

        List<User> users = userDAO.findAllBySQLQuery(savedUser.getEmail());
        assertNotNull(users);
        assertTrue(!users.isEmpty());
        assertEquals(users.get(0).getEmail(), savedUser.getEmail());

        userDAO.delete(savedUser);
    }


    @Test
    public void testSaveAndGetAndDelete(){
        User user = new User();
        user.setEmail("ignatenko2207@gmail.com");
        user.setFirstName("Alex");
        user.setLastName("Ignatenko");
        user.setLogin("ignatenko2207");
        user.setPassword("12345");
        user.setProfile(Profile.ADMIN);

        User testUser = userDAO.save(user);


        assertNotNull(testUser);
        assertNotNull(testUser.getId());

        userDAO.delete(testUser);
        Optional<User> userWrapper = userDAO.findById(testUser.getId());
        assertTrue(userWrapper.isEmpty());
    }


    @Test
    public void testUpdate(){
        User user = new User();
        user.setEmail("ignatenko2207@gmail.com");
        user.setFirstName("Alex");
        user.setLastName("Ignatenko");
        user.setLogin("ignatenko2207");
        user.setPassword("12345");
        user.setProfile(Profile.ADMIN);

        User savedUser = userDAO.saveAndFlush(user);

        assertNotNull(savedUser);

        User modifiedUser = new User();
        modifiedUser.setId(savedUser.getId());
        modifiedUser.setEmail("new_email@gmail.com");
        modifiedUser.setFirstName("Alex");
        modifiedUser.setLastName("Ignatenko");
        modifiedUser.setLogin("new_login");
        modifiedUser.setPassword("new_password");
        modifiedUser.setProfile(Profile.CLIENT);

        User testUser = userDAO.saveAndFlush(modifiedUser);

        assertNotNull(testUser);

        Optional<User> userWrapper = userDAO.findById(testUser.getId());

        User dbUser = userWrapper.get();

        assertEquals(dbUser.getEmail(), "new_email@gmail.com");
        assertEquals(dbUser.getLogin(), "new_login");
        assertEquals(dbUser.getPassword(), "new_password");
        assertEquals(dbUser.getProfile(), Profile.CLIENT);

        userDAO.delete(dbUser);
    }

    @Test
    public void testGetAll(){
        User user1 = new User();
        user1.setEmail("ignatenko2207@gmail.com");
        user1.setFirstName("Alex");
        user1.setLastName("Ignatenko");
        user1.setLogin("ignatenko2207");
        user1.setPassword("12345");
        user1.setProfile(Profile.ADMIN);

        User user2 = new User();
        user2.setEmail("ignatenko2207@gmail.com");
        user2.setFirstName("Alex");
        user2.setLastName("Ignatenko");
        user2.setLogin("ignatenko2207");
        user2.setPassword("12345");
        user2.setProfile(Profile.ADMIN);

        User savedUser1 = userDAO.saveAndFlush(user1);
        User savedUser2 = userDAO.saveAndFlush(user2);

        List<User> users = userDAO.findAll();

        assertNotNull(users);
        assertTrue(users.size() >= 2);

        userDAO.delete(savedUser1);
        userDAO.delete(savedUser2);
    }

}