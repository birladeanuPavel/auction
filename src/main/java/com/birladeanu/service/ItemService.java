package com.birladeanu.service;

import com.birladeanu.dal.model.Item;

import java.util.List;

/**
 * Created by pavel on 10/6/16.
 */
public interface ItemService {

    void save(Item item);

    Item find(Long id);

    List<Item> findAll();

    void delete(Item item);
}
