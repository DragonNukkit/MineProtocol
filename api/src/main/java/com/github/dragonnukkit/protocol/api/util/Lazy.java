package com.github.dragonnukkit.protocol.api.util;

import java.util.function.Supplier;

public class Lazy<T> {

    private T value;

    public Lazy() {
        value = null;
    }

    public T get(Supplier<T> supplier) {
        if(value != null) {
            return value;
        }
        value = supplier.get();
        return value;
    }

}
