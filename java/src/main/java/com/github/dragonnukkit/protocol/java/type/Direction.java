package com.github.dragonnukkit.protocol.java.type;

public enum Direction {
    SOUTH,
    WEST,
    NORTH,
    EAST;

    public byte getDirectionId() {
        return (byte) ordinal();
    }

    public static Direction fromDirectionId(byte directionId) {
        return values()[directionId];
    }
}
