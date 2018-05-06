package com.github.dragonnukkit.protocol.api.item;

import com.github.dragonnukkit.protocol.api.metadata.Metadata;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import java.util.Optional;

@Nonnull
@Immutable
public interface ItemInstance {

    ItemType getItemType();

    int getAmount();

    Optional<Metadata> getItemData();

    default <T extends Metadata> T ensureItemData(Class<T> clazz) {
        return (T) getItemData().get();
    }

    ItemInstanceBuilder toBuilder();
}
