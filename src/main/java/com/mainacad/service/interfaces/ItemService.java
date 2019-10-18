package com.mainacad.service.interfaces;

import com.mainacad.entity.Item;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    Item save(Item item);
    Item update(Item item);
    Item findOne(Integer id);
    Optional<Item> findById(Integer id);
    List<Item> findAll();
    void delete(Integer id);

}
