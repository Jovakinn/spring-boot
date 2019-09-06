package com.mainacad.controller;

import com.mainacad.entity.Item;
import com.mainacad.service.interfaces.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping()
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        Item savedItem = itemService.save(item);

        if (savedItem != null) {
            return new ResponseEntity<Item>(savedItem, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PutMapping()
    public ResponseEntity<Item> updateItem(@RequestBody Item item) {
        Item updatedItem = itemService.update(item);

        if (updatedItem != null) {
            return new ResponseEntity(updatedItem, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path="/get-one/{id}")
    public ResponseEntity<Item> getOne(@PathVariable Integer id) {
        Item itemFromDB = itemService.findOne(id);

        if (itemFromDB != null){
            return new ResponseEntity(itemFromDB, HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path="/get-all")
    public ResponseEntity<List> getAll() {
        List<Item> items = itemService.findAll();
        return new ResponseEntity(items, HttpStatus.OK);
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        itemService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
