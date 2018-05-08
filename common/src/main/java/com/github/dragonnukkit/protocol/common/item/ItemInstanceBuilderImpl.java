package com.github.dragonnukkit.protocol.common.item;

import com.github.dragonnukkit.protocol.api.item.ItemInstance;
import com.github.dragonnukkit.protocol.api.item.ItemInstanceBuilder;
import com.github.dragonnukkit.protocol.api.item.ItemType;
import com.github.dragonnukkit.protocol.api.metadata.Metadata;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.annotation.Nonnull;

@NoArgsConstructor
public class ItemInstanceBuilderImpl implements ItemInstanceBuilder {
    private ItemType itemType;
    private int amount;
    private Metadata metadata;

    public ItemInstanceBuilderImpl(ItemInstance item) {
        this.itemType = item.getItemType();
        this.amount = item.getAmount();
        this.metadata = item.getItemData().orElse(null);
    }

    @Override
    public ItemInstanceBuilder itemType(@Nonnull ItemType itemType) {
        this.itemType = itemType;
        return this;
    }

    @Override
    public ItemInstanceBuilder amount(int amount) {
        this.amount = amount;
        return this;
    }

    @Override
    public ItemInstanceBuilder itemData(Metadata metadata) {
        this.metadata = metadata;
        return this;
    }

    @Override
    public ItemInstance build() {
        return new ItemInstanceImpl(itemType, amount, metadata);
    }
}
