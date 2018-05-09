package com.github.dragonnukkit.protocol.java.util;

import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3f;
import com.github.dragonnukkit.protocol.api.type.math.Rotation;
import io.netty.buffer.ByteBuf;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JavaBufferUtils {

    public static Rotation readBodyRotation(ByteBuf buffer) {
        float pitch = buffer.readByte() / 256;
        float yaw = buffer.readByte() / 256;
        return Rotation.fromBodyRotation(pitch, yaw);
    }

    public static void writeBodyRotation(ByteBuf buffer, Rotation rotation) {
        buffer.writeByte((byte) (rotation.getPitch() * 256));
        buffer.writeByte((byte) (rotation.getYaw() * 256));
    }

    public static Rotation readHeadRotation(ByteBuf buffer) {
        float pitch = buffer.readByte() / 256;
        float yaw = buffer.readByte() / 256;
        float headYaw = buffer.readByte() / 256;
        return new Rotation(pitch, yaw, headYaw);
    }

    public static void writeHeadRotation(ByteBuf buffer, Rotation rotation) {
        buffer.writeByte((byte) (rotation.getPitch() * 256));
        buffer.writeByte((byte) (rotation.getYaw() * 256));
        buffer.writeByte((byte) (rotation.getHeadYaw() * 256));
    }

    public static Vector3d readPosition(ByteBuf buffer) {
        double x = buffer.readDouble();
        double y = buffer.readDouble();
        double z = buffer.readDouble();
        return new Vector3d(x, y, z);
    }

    public static void writePosition(ByteBuf buffer, Vector3d position) {
        buffer.writeDouble(position.getX());
        buffer.writeDouble(position.getY());
        buffer.writeDouble(position.getZ());
    }

    public static Vector3f readMotion(ByteBuf buffer) {
        double x = buffer.readShort() / 8000;
        double y = buffer.readShort() / 8000;
        double z = buffer.readShort() / 8000;
        return new Vector3f(x, y, z);
    }

    public static void writeMotion(ByteBuf buffer, Vector3f velocity) {
        buffer.writeByte((short) velocity.getX() * 8000);
        buffer.writeByte((short) velocity.getY() * 8000);
        buffer.writeByte((short) velocity.getZ() * 8000);
    }
}
