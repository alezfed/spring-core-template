package com.epam.edu.spring.core.template.repository;

import com.epam.edu.spring.core.template.configuration.InjectRandomInt;
import com.epam.edu.spring.core.template.entity.Item;
import lombok.ToString;

import javax.annotation.PostConstruct;
import java.util.LinkedList;

@ToString
public class LinkedListItemRepository extends AbstractRepository<Item> implements ItemRepository {
    private LinkedList<Item> items;

    @Override
    public Item getById(long id) {
        return items.stream().filter(item -> item.getId() == id).findAny().orElse(null);
    }

    @Override
    public boolean createItem(Item item) {
        item.setId(initialSequence);
        items.add(item);
        initialSequence++;
        return true;
    }

    @Override
    @InjectRandomInt(min = 1, max = 100)
    void setInitialSequence(int val) {
        this.initialSequence = val;
    }

    @PostConstruct
    void setHolder() {
        items = new LinkedList<>();
    }
}