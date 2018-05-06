package com.github.dragonnukkit.protocol.api.util;

import com.flowpowered.math.vector.Vector2f;
import com.flowpowered.math.vector.Vector3f;
import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import java.util.Objects;

@Immutable
public final class Rotation {
    public static final Rotation ZERO = new Rotation(0f, 0f ,0f);

    private transient volatile boolean hashed;
    private transient volatile int hashCode;
    private final float pitch;
    private final float yaw;
    private final float headYaw;

    public Rotation(float pitch, float yaw, float headYaw) {
        hashed = false;
        hashCode = 0;
        this.pitch = validate(pitch, "pitch");
        this.yaw = validate(yaw, "yaw");
        this.headYaw = validate(headYaw, "headYaw");
    }

    public static Rotation fromVector3f(@Nonnull Vector3f vector3f) {
        Preconditions.checkNotNull(vector3f, "vector3f");
        return new Rotation(vector3f.getX(), vector3f.getY(), vector3f.getZ());
    }

    public static Rotation fromVector2f(@Nonnull Vector2f vector2f) {
        Preconditions.checkNotNull(vector2f, "vector2f");
        return new Rotation(vector2f.getX(), vector2f.getY(), 0f);
    }

    private static float validate(float val, String name) {
        Preconditions.checkArgument(Float.isFinite(val), "%s value (%s) is not finite", name, val);
        return val;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getHeadYaw() {
        return headYaw;
    }

    public Vector3f toVector3f() {
        return new Vector3f(pitch, yaw, headYaw);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rotation)) return false;
        Rotation rotation = (Rotation) o;

        return Float.compare(rotation.pitch, pitch) == 0 &&
            Float.compare(rotation.yaw, yaw) == 0 &&
            Float.compare(rotation.headYaw, headYaw) == 0;
    }

    @Override
    public int hashCode() {
        if (!hashed) {
            hashCode = Objects.hash(pitch, yaw, headYaw);
            this.hashed = true;
        }
        return hashCode;
    }

    @Override
    public String toString() {
        return "Rotation(" +
            "pitch=" + pitch +
            ", yaw=" + yaw +
            ", headYaw=" + headYaw +
            ')';
    }
}
