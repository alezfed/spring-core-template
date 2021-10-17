package com.epam.edu.spring.core.template.repository;

import com.epam.edu.spring.core.template.entity.Item;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.ArrayList;

@ToString
public class ArrayListItemRepository extends AbstractRepository<Item> implements ItemRepository {
    private ArrayList<Item> items;

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
    @Value("${initial.sequence}")
    void setInitialSequence(int val) {
        this.initialSequence = val;
    }

    @PostConstruct
    void setHolder() {
        items = new ArrayList<>();
    }
}