package com.mainacad.service.impl;

import com.mainacad.dao.ItemDAO;
import com.mainacad.entity.Item;
import com.mainacad.service.interfaces.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        if (item.getId() != null && findOne(item.getId()) != null) {
            return itemDAO.saveAndFlush(item);
        }
        return null;
    }

    @Override
    public Item findOne(Integer id) {
        Optional<Item> itemWrapper = itemDAO.findById(id);
        return itemWrapper.orElse(null);
    }

    @Override
    public Optional<Item> findById(Integer id) {
        return itemDAO.findById(id);
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
