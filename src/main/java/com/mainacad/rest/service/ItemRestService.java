package com.mainacad.rest.service;

import com.mainacad.entity.Item;
import com.mainacad.service.interfaces.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ItemRestService {

    @Autowired
    ItemService itemService;

    RestTemplate restTemplate = new RestTemplate();

    public static final String URL = "localhost:8082/items?present=true";

    @Scheduled(cron = "0 0 5 * * *")
    public List<Item> getItem() throws URISyntaxException {
        List<Item> items = new ArrayList<>();

        Map headers = new HttpHeaders();
        headers.put(HttpHeaders.AUTHORIZATION, "token");

        RequestEntity requestEntity = new RequestEntity<>(headers, HttpMethod.GET, new URI(URL));

        ResponseEntity<List> responseEntity = restTemplate.exchange(requestEntity, List.class);


        return items;
    }

}
