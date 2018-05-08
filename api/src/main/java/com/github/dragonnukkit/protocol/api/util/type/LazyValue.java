package com.github.dragonnukkit.protocol.api.util.type;

import java.util.function.Supplier;

public class LazyValue<T> {

    private T value;

    public LazyValue() {
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
