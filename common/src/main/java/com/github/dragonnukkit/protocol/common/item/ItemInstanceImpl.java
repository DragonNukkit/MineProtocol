package com.github.dragonnukkit.protocol.common.item;

import com.github.dragonnukkit.protocol.api.item.ItemInstance;
import com.github.dragonnukkit.protocol.api.item.ItemInstanceBuilder;
import com.github.dragonnukkit.protocol.api.item.ItemType;
import com.github.dragonnukkit.protocol.api.metadata.Metadata;
import com.google.common.base.Preconditions;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import java.util.Optional;

@Immutable
public class ItemInstanceImpl implements ItemInstance {
    private final ItemType itemType;
    private final int amount;
    private final Metadata metadata;

    public ItemInstanceImpl(@Nonnull ItemType itemType, @Nonnegative int amount, @Nullable Metadata metadata) {
        this.itemType = Preconditions.checkNotNull(itemType, "itemType");
        Preconditions.checkArgument(amount > 0);
        this.amount = amount;
        this.metadata = metadata;
    }

    @Override
    public ItemType getItemType() {
        return itemType;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public Optional<Metadata> getItemData() {
        return Optional.ofNullable(metadata);
    }

    @Override
    public ItemInstanceBuilder toBuilder() {
        return new ItemInstanceBuilderImpl();
    }
}
