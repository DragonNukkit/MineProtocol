package com.github.dragonnukkit.protocol.api.item;

public interface ItemType {
    int getId();

    String getName();

    Class<? extends ItemType> getMetadataClass();

    int getMaximumStackSize();

    default boolean isStackable() {
        return getMaximumStackSize() > 1;
    }
}
