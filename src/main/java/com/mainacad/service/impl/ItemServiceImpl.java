package com.mainacad.service.impl;

import com.mainacad.dao.ItemDAO;
import com.mainacad.entity.Item;
import com.mainacad.service.interfaces.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemDAO itemDAO;

    @Override
    public Item save(Item item) {
        return itemDAO.save(item);
    }

    @Override
    public Item update(Item item) {
        if (item.getId() != null && itemDAO.findOne(item.getId()) != null) {
            return itemDAO.update(item);
        }
        return null;
    }

    @Override
    public Item findOne(Integer id) {
        if (id != null) {
            return itemDAO.findOne(id);
        }
        return null;
    }

    @Override
    public List<Item> findAll() {
        return itemDAO.findAll();
    }

    @Override
    public void delete(Integer id) {
        Item item = findOne(id);
        if (item != null) {
            itemDAO.delete(item);
        }
    }
}
