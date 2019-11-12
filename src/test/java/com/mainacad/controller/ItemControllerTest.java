package com.mainacad.controller;

import com.mainacad.ApplicationRunner;
import com.mainacad.entity.Item;
import com.mainacad.service.interfaces.ItemService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.net.URI;
import java.net.URISyntaxException;

@SpringJUnitConfig(ApplicationRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles({"json", "test"})
class ItemControllerTest {

    @Autowired
    TestRestTemplate testRestTemplate;

    @MockBean
    ItemService itemService;

    @Test
    void createItem() throws URISyntaxException {
        Item item = new Item();
        item.setPrice(10000);
        item.setName("Machina");
        item.setInitPrice(2222);
        item.setArticle("Perf");

        Mockito.when(itemService.save(Mockito.any(Item.class))).thenReturn(item);

        RequestEntity<Item> request = new RequestEntity<>(item, HttpMethod.POST, new URI("/item"));

        ResponseEntity<Item> response = testRestTemplate.exchange(request, Item.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

        Mockito.verify(itemService, Mockito.times(1)).save(Mockito.any(Item.class));

    }

    @Test
    void updateItem() throws URISyntaxException {
        Item item = new Item();
        item.setPrice(10000);
        item.setName("Machina");
        item.setInitPrice(2222);
        item.setArticle("Perf");

        Mockito.when(itemService.update(Mockito.any(Item.class))).thenReturn(item);

        RequestEntity<Item> request = new RequestEntity<>(item, HttpMethod.PUT, new URI("/item"));

        ResponseEntity<Item> response = testRestTemplate.exchange(request, Item.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

        Mockito.verify(itemService, Mockito.times(1)).update(Mockito.any(Item.class));

    }

    @Test
    void getOneOrList() throws URISyntaxException {
        Item item = new Item();

        Mockito.when(itemService.findOne(1)).thenReturn(item);

        RequestEntity<Item> request = new RequestEntity<>(HttpMethod.GET, new URI("/item/1"));

        ResponseEntity<Item> response = testRestTemplate.exchange(request, Item.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

        Mockito.verify(itemService, Mockito.times(1)).findOne(Mockito.anyInt());


    }

    @Test
    void delete() throws URISyntaxException {
        Mockito.doNothing().when(itemService).delete(Mockito.anyInt());

        RequestEntity<Item> request = new RequestEntity<>(HttpMethod.DELETE, new URI("/item/222"));

        ResponseEntity response = testRestTemplate.exchange(request, Object.class);

        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

        Mockito.verify(itemService, Mockito.times(1)).delete(Mockito.anyInt());
    }
}