package com.github.dragonnukkit.protocol.common.entity;

import lombok.Value;

@Value
public class EntityLink {
    private final long fromUniqueEntityId;
    private final long toUniqueEntityId;
    private final Type type;
    private final boolean immediate;

    @Override
    public String toString() {
        return "EntityLink(" +
            "fromUniqueEntityId=" + fromUniqueEntityId +
            ", toUniqueEntityId=" + toUniqueEntityId +
            ", type=" + type +
            ", immediate=" + immediate +
            ')';
    }

    public enum Type {
        REMOVE,
        RIDER,
        PASSENGER
    }
}
