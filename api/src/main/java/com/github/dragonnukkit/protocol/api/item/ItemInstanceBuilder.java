package com.github.dragonnukkit.protocol.api.item;

import com.github.dragonnukkit.protocol.api.metadata.Metadata;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

public interface ItemInstanceBuilder {
    ItemInstanceBuilder itemType(@Nonnull ItemType itemType);

    ItemInstanceBuilder amount(@Nonnegative int amount);

    ItemInstanceBuilder itemData(Metadata metadata);

    ItemInstance build();
}
