package com.github.dragonnukkit.protocol.java.type.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FallingBlockData implements ObjectData {

    @Getter
    private int blockType; // TODO: enum

    @Override
    public int getRawData() {
        return blockType;
    }

    public static FallingBlockData fromBlockType(int blockType) {
        return new FallingBlockData(blockType);
    }
}
