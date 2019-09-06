package com.mainacad.service.interfaces;

import com.mainacad.entity.Item;

import java.util.List;

public interface ItemService {

    Item save(Item item);
    Item update(Item item);
    Item findOne(Integer id);
    List<Item> findAll();
    void delete(Integer id);

}
