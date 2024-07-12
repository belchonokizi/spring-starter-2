package com.dmdev.spring.listener.entity;

import java.util.EventObject;

//extends EventObject - если хотим создать свой ивент
public class EntityEvent extends EventObject {

    private final AccessType accessType;

    public EntityEvent(Object entity, AccessType accessType) {
        super(entity);
        this.accessType = accessType;
    }

    public AccessType getAccessType() {
        return accessType;
    }

    @Override
    public String toString() {
        return "EntityEvent{" +
               "accessType=" + accessType +
               '}';
    }
}
