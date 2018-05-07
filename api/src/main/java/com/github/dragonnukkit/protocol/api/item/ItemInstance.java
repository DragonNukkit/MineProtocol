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
        Metadata metadata = getItemData().orElseThrow(() ->
            new IllegalStateException("Requested metadata for " + getClass().getSimpleName() + " is undefined."));
        if (!metadata.getClass().isAssignableFrom(clazz)) {
            throw new IllegalStateException("Unexpected metadata type for " + getClass().getSimpleName()
                + " (Expected: " + clazz.getSimpleName() + "; Found: " + metadata.getClass().getSimpleName() + ")");
        }
        return clazz.cast(metadata);
    }

    ItemInstanceBuilder toBuilder();
}
