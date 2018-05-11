package com.github.dragonnukkit.protocol.java.type;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Statistic {
    private final String name; // TODO: enum?
    private int value;
}
