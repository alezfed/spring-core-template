package com.epam.edu.spring.core.template.service;

import com.epam.edu.spring.core.template.configuration.ColorFactory;
import com.epam.edu.spring.core.template.entity.Color;
import com.epam.edu.spring.core.template.entity.Item;
import com.epam.edu.spring.core.template.repository.ItemRepository;
import com.epam.edu.spring.core.template.validator.ItemValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service(value = "service")
public class SimpleItemService implements ItemService {
    private final Random random = new Random();
    private ItemRepository itemRepository;
    private ItemValidator itemValidator;
    private ColorFactory colorFactory;

    @Autowired
    public SimpleItemService(@Qualifier("repository") ItemRepository itemRepository, ItemValidator itemValidator, ColorFactory colorFactory) {
        this.itemRepository = itemRepository;
        this.itemValidator = itemValidator;
        this.colorFactory = colorFactory;
    }

    @Override
    public Item getById(long id) {
        return itemRepository.getById(id);
    }

    @Override
    public boolean createItem(Item item) {
        if (itemValidator.isItemValid(item)) {
            try {
                Color color = colorFactory.getObject();
                item.setColor(color);
                item.setName("It is name of " + color.toString());
                item.setPrice(random.nextInt(500) + 200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return itemRepository.createItem(item);
        }
        return false;
    }

    @Autowired
    public void setItemRepository(@Qualifier("repository") ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Autowired
    public void setItemValidator(ItemValidator itemValidator) {
        this.itemValidator = itemValidator;
    }

    @Autowired
    public void setColorFactory(ColorFactory colorFactory) {
        this.colorFactory = colorFactory;
    }
}