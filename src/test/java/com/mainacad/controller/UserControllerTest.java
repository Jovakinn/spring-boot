package com.mainacad.controller;

import com.mainacad.ApplicationRunner;
import com.mainacad.entity.Profile;
import com.mainacad.entity.User;
import com.mainacad.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@SpringJUnitConfig(ApplicationRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class UserControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @MockBean
    UserService userService;

    @Test
    public void createUser() throws URISyntaxException {

        User user = new User();
        user.setId(1);
        user.setEmail("ignatenko2207@gmail.com");
        user.setFirstName("Alex");
        user.setLastName("Ignatenko");
        user.setLogin("ignatenko2207");
        user.setPassword("12345");
        user.setProfile(Profile.ADMIN);

        Mockito.when(userService.save(Mockito.any(User.class))).thenReturn(user);

        RequestEntity<User> request = new RequestEntity<>(user, HttpMethod.POST, new URI("/user"));

        ResponseEntity<User> response = testRestTemplate.exchange(request, User.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

        Mockito.verify(userService, Mockito.times(1)).save(Mockito.any(User.class));
    }

    @Test
    public void updateUser() {
    }

    @Test
    public void getOne() {
    }

    @Test
    public void getAll() throws URISyntaxException {
        List<User> users = new ArrayList<>();
        Mockito.when(userService.findAll()).thenReturn(users);

        RequestEntity<User> request = new RequestEntity<>(HttpMethod.GET, new URI("/user/get-all"));

        ResponseEntity<List> response = testRestTemplate.exchange(request, ParameterizedTypeReference.forType(List.class));

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

        Mockito.verify(userService, Mockito.times(1)).findAll();

        Assertions.assertEquals(response.getBody().getClass().getSimpleName(), "ArrayList");
    }

    @Test
    public void delete() {
    }
}