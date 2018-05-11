package com.github.dragonnukkit.protocol.java.type.data;

import com.github.dragonnukkit.protocol.java.type.MinecartType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MinecartData implements ObjectData {

    @Getter
    private MinecartType minecartType;

    @Override
    public int getRawData() {
        return minecartType.getTypeId();
    }

    public static MinecartData fromMinecartType(@NonNull MinecartType minecartType) {
        return new MinecartData(minecartType);
    }
}
