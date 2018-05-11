package com.github.dragonnukkit.protocol.java.type;

public enum GlobalEntityType {
    THUNDERBOLT;

    public byte getTypeId() {
        return (byte) (ordinal() + 1);
    }

    public static GlobalEntityType fromTypeId(byte id) {
        return values()[id - 1];
    }
}
