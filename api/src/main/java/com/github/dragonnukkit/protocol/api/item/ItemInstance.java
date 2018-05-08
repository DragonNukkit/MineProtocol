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

    @SuppressWarnings("unchecked")
    default <T extends Metadata> T ensureItemData(Class<T> clazz) {
        try {
            return (T) getItemData().orElseThrow(() -> new IllegalStateException("Item does not have metadata"));
        } catch (ClassCastException e) {
            throw new RuntimeException("Item metadata cannot be casted to " + clazz, e);
        }
    }

    ItemInstanceBuilder toBuilder();
}
