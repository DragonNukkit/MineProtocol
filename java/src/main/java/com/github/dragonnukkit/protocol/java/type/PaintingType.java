package com.github.dragonnukkit.protocol.java.type;

import lombok.Getter;
import lombok.NonNull;

public enum PaintingType {
    KEBAB("Kebab"),
    AZTEC("Aztec"),
    ALBAN("Alban"),
    AZTEC2("Aztec2"),
    BOMB("Bomb"),
    PLANT("Plant"),
    WASTELAND("Wasteland"),
    POOL("Pool"),
    COURBET("Courbet"),
    SEA("Sea"),
    SUNSET("Sunset"),
    CREEBET("Creebet"),
    WANDERER("Wanderer"),
    GRAHAM("Graham"),
    MATCH("Match"),
    BUST("Bust"),
    STAGE("Stage"),
    VOID("Void"),
    SKULL_AND_ROSES("SkullAndRoses"),
    WITHER("Wither"),
    FIGHTERS("Fighters"),
    POINTER("Pointer"),
    PIGSCENE("Pigscene"),
    BURNING_SKULL("BurningSkull"),
    SKELETON("Skeleton"),
    DONKEY_KONG("DonkeyKong");

    @Getter
    private String title;

    PaintingType(String title) {
        this.title = title;
    }

    public static PaintingType fromTitle(@NonNull String title) {
        for (PaintingType type : values()) {
            if (type.getTitle().equals(title)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No painting with name " + title);
    }
}
