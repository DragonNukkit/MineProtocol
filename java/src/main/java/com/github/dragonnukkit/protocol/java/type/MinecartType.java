package com.github.dragonnukkit.protocol.java.type;

public enum MinecartType {
    RIDEABLE_MINECART,
    CHEST_MINECART,
    FURNACE_MINECART,
    TNT_MINECART,
    SPAWNER_MINECART,
    HOPPER_MINECART,
    COMMAND_BLOCK_MINECART;

    public int getTypeId() {
        return ordinal();
    }

    public static MinecartType fromTypeId(int typeId) {
        return values()[typeId];
    }
}
