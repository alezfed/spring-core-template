package com.epam.edu.spring.core.template.configuration;

import lombok.SneakyThrows;
import org.reflections.ReflectionUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Method;
import java.util.Random;
import java.util.Set;

public class InjectRandomIntAnnotationBeanPostProcessor implements BeanPostProcessor {
    @SneakyThrows
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Set<Method> methods = ReflectionUtils.getAllMethods(bean.getClass(), method -> method.isAnnotationPresent(InjectRandomInt.class));
        for (Method method : methods) {
            InjectRandomInt annotation = method.getAnnotation(InjectRandomInt.class);
            int min = annotation.min();
            int max = annotation.max();
            method.setAccessible(true);
            method.invoke(bean, new Random().nextInt(max - min) + min);
        }
        return bean;
    }
}