package com.mainacad.service;

import com.mainacad.ApplicationRunner;
import com.mainacad.entity.Item;
import com.mainacad.service.interfaces.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringJUnitConfig(ApplicationRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Test
    public void testGetAndUpdateAndDelete() {
        Item item = new Item();
        item.setPrice(10000);
        item.setName("Machina");
        item.setInitPrice(2222);
        item.setArticle("Perf");

        itemService.save(item);

        Item savedItem = itemService.findOne(item.getId());

        assertEquals(savedItem.getName(), "Machina");
        savedItem.setArticle("new_article");

        Item updatedItem = itemService.update(savedItem);

        assertEquals(updatedItem.getArticle(), "new_article");

        itemService.delete(updatedItem.getId());
        Optional<Item> deletedItem = itemService.findById(savedItem.getId());

        assertEquals(deletedItem, Optional.empty());
    }
}