package com.mainacad.dao;

import com.mainacad.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItemDAO extends JpaRepository<Item, Integer> {

    Optional<Item> findById(Integer id);

}
