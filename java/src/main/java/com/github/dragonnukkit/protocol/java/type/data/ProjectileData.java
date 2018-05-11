package com.github.dragonnukkit.protocol.java.type.data;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProjectileData implements ObjectData {

    @Getter
    private int shooterEntityId;

    @Override
    public int getRawData() {
        return shooterEntityId + 1; // Note: as defined by documentation
    }

    public static ProjectileData fromShooterEntityId(int shooterEntityId) {
        return new ProjectileData(shooterEntityId);
    }
}
