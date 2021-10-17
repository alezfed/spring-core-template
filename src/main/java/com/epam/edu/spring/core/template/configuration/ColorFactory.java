package com.epam.edu.spring.core.template.configuration;

import com.epam.edu.spring.core.template.entity.Color;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.FactoryBean;

import java.util.Random;

@NoArgsConstructor
public class ColorFactory implements FactoryBean<Color> {
    @Override
    public Color getObject() throws Exception {
        Color[] colors = Color.values();
        return colors[new Random().nextInt(colors.length)];
    }

    @Override
    public Class<?> getObjectType() {
        return Color.class;
    }
}