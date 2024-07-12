package com.dmdev.spring.listener.entity;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EntityListener {

    //c фильтрацией
    @EventListener(condition = "#root.args[0].accessType.name() == 'READ'")
    public void acceptEntity(EntityEvent entityEvent) {
        System.out.println("Entity: " + entityEvent);
    }
}
