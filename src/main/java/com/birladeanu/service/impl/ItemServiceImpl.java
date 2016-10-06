package com.birladeanu.service.impl;

import com.birladeanu.dal.model.Item;
import com.birladeanu.dal.repository.ItemRepository;
import com.birladeanu.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by pavel on 10/6/16.
 */
@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public void save(Item item) {
        itemRepository.save(item);
    }

    @Override
    @Transactional(readOnly = true)
    public Item find(Long id) {
        return itemRepository.findOne(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Item> findAll() {
        return (List<Item>) itemRepository.findAll();
    }

    @Override
    public void delete(Item item) {
        itemRepository.delete(item);
    }
}
