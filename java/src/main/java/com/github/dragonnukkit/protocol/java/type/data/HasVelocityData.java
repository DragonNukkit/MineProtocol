package com.github.dragonnukkit.protocol.java.type.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class HasVelocityData implements ObjectData {

    @Getter
    private boolean hasVelocity;

    @Override
    public int getRawData() {
        return hasVelocity ? 1 : 0;
    }

    public static HasVelocityData fromHasVelocity(boolean hasVelocity) {
        return new HasVelocityData(hasVelocity);
    }
}
