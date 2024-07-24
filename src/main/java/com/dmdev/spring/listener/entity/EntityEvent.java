package com.dmdev.spring.listener.entity;

import lombok.Getter;

import java.util.EventObject;

//extends EventObject - если хотим создать свой ивент
public class EntityEvent extends EventObject {

    @Getter
    private final AccessType accessType;

    public EntityEvent(Object entity, AccessType accessType) {
        super(entity);
        this.accessType = accessType;
    }

    @Override
    public String toString() {
        return "EntityEvent{" +
               "accessType=" + accessType +
               '}';
    }
}
