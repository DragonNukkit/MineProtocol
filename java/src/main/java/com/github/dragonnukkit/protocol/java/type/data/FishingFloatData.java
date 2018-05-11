package com.github.dragonnukkit.protocol.java.type.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FishingFloatData implements ObjectData {

    @Getter
    private int ownerEntityId;

    @Override
    public int getRawData() {
        return ownerEntityId;
    }

    public static FishingFloatData fromOwnerEntityId(int entityId) {
        return new FishingFloatData(entityId);
    }
}
