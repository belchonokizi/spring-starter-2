package com.dmdev.spring.bfpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.core.Ordered;

import java.util.List;

public class LogBeanFactoryPostProcessor implements BeanFactoryPostProcessor, Ordered {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        //можно самим подкрутить Bean Definitions
        for (String beanDefinitionName : beanFactory.getBeanDefinitionNames()) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
            List<ConstructorArgumentValues.ValueHolder> genericArgumentValues = beanDefinition.getConstructorArgumentValues().getGenericArgumentValues();
            for (ConstructorArgumentValues.ValueHolder genericArgumentValue : genericArgumentValues) {
                //установить значения

            }
        }
    }

    //установка порядка, чем меньше число - тем выше приоритет
    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
