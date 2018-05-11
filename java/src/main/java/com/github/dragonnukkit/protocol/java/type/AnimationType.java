package com.github.dragonnukkit.protocol.java.type;

public enum AnimationType {
    SWING_MAIN_ARM,
    TAKE_DAMAGE,
    LEAVE_BED,
    SWING_OFFHAND,
    CRITICAL_EFFECT,
    MAGIC_CRITICAL_EFFECT;

    public byte getTypeId() {
        return (byte) ordinal();
    }

    public static AnimationType fromTypeId(byte typeId) {
        return values()[typeId];
    }
}
