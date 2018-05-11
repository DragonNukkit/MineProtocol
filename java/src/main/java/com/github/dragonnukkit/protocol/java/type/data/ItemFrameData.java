package com.github.dragonnukkit.protocol.java.type.data;

import com.github.dragonnukkit.protocol.java.type.Direction;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemFrameData implements ObjectData {

    @Getter
    private Direction direction;

    @Override
    public int getRawData() {
        return direction.getDirectionId();
    }

    public static ItemFrameData fromDirection(@NonNull Direction direction) {
        return new ItemFrameData(direction);
    }
}
