package com.github.dragonnukkit.protocol.api.type.math;

import com.flowpowered.math.vector.Vector2f;
import com.flowpowered.math.vector.Vector3f;
import com.github.dragonnukkit.protocol.api.util.Lazy;
import lombok.ToString;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import java.util.Objects;

import static com.github.dragonnukkit.protocol.api.util.ArgumentUtils.requireFinite;

@Immutable
@ToString(of = {"pitch", "yaw", "headYaw"})
public final class Rotation {
    public static final Rotation ZERO = new Rotation(0f, 0f, 0f);

    private volatile Lazy<Integer> hashCode;
    private final float pitch;
    private final float yaw;
    private final float headYaw;

    public Rotation(float pitch, float yaw, float headYaw) {
        hashCode = new Lazy<>();
        this.pitch = requireFinite(pitch, "pitch");
        this.yaw = requireFinite(yaw, "yaw");
        this.headYaw = requireFinite(headYaw, "headYaw");
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

    public static Rotation fromVector3f(@Nonnull Vector3f vector3f) {
        return new Rotation(vector3f.getX(), vector3f.getY(), vector3f.getZ());
    }

    public static Rotation fromVector2f(@Nonnull Vector2f vector2f) {
        return new Rotation(vector2f.getX(), vector2f.getY(), 0f);
    }

    public static Rotation fromBodyRotation(float pitch, float yaw) {
        return new Rotation(pitch, yaw, 0f);
    }

    public Vector3f toVector3f() {
        return new Vector3f(pitch, yaw, headYaw);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Rotation)) return false;
        Rotation otherRotation = (Rotation) other;
        return otherRotation.pitch == pitch && otherRotation.yaw == yaw && otherRotation.headYaw == headYaw;
    }

    @Override
    public int hashCode() {
        return hashCode.get(() -> Objects.hash(pitch, yaw, headYaw));
    }
}
