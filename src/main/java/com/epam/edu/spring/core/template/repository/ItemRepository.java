package com.epam.edu.spring.core.template.repository;

import com.epam.edu.spring.core.template.entity.Item;

public interface ItemRepository {

    Item getById(long id);

    boolean createItem(Item item);
}