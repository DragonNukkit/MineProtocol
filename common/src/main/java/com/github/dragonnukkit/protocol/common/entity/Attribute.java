package com.github.dragonnukkit.protocol.common.entity;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public final class Attribute {
    @NonNull
    private final String name;
    private final float value;
    private final float minimumValue;
    private final float maximumValue;
    private final float defaultValue;

    public Attribute(@NonNull String name, float value, float minimumValue, float maximumValue) {
        this(name, value, minimumValue, maximumValue, maximumValue);
    }
}
