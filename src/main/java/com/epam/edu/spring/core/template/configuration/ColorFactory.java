package com.epam.edu.spring.core.template.configuration;

import com.epam.edu.spring.core.template.entity.Color;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
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