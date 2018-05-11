package com.github.dragonnukkit.protocol.java.util;

import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3f;
import com.flowpowered.math.vector.Vector3i;
import com.github.dragonnukkit.protocol.api.type.math.Rotation;
import com.github.dragonnukkit.protocol.java.type.*;
import com.github.dragonnukkit.protocol.java.type.Direction;
import com.github.dragonnukkit.protocol.util.VarIntBufferUtils;
import io.netty.buffer.ByteBuf;
import lombok.experimental.UtilityClass;

import java.nio.charset.StandardCharsets;
import java.util.function.BiConsumer;
import java.util.function.Function;

@UtilityClass
public class JavaBufferUtils {

    public static Direction readDirection(ByteBuf buffer) {
        return Direction.fromDirectionId(buffer.readByte());
    }

    public static void writeDirection(ByteBuf buffer, Direction direction) {
        buffer.writeByte(direction.getDirectionId());
    }

    public static AnimationType readAnimationType(ByteBuf buffer) {
        return AnimationType.fromTypeId(buffer.readByte());
    }

    public static void writeAnimationType(ByteBuf buffer, AnimationType animation) {
        buffer.writeByte(animation.getTypeId());
    }

    public static GlobalEntityType readGlobalEntityType(ByteBuf buffer) {
        return GlobalEntityType.fromTypeId(buffer.readByte());
    }

    public static void writeGlobalEntityType(ByteBuf buffer, GlobalEntityType type) {
        buffer.writeByte(type.getTypeId());
    }

    public static PaintingType readPaintingType(ByteBuf buffer) {
        return PaintingType.fromTitle(readString(buffer));
    }

    public static void writePaintingType(ByteBuf buffer, PaintingType type) {
        writeString(buffer, type.getTitle());
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] readArray(ByteBuf buffer, Function<ByteBuf, T> elementSupplier) {
        int count = VarIntBufferUtils.readInt(buffer);
        T[] array = (T[]) new Object[count];
        for (int i = 0; i < count; i++) {
            array[i] = elementSupplier.apply(buffer);
        }
        return array;
    }

    public static <T> void writeArray(ByteBuf buffer, T[] array, BiConsumer<ByteBuf, T> elementConsumer) {
        VarIntBufferUtils.writeInt(buffer, array.length);
        for (T element : array) {
            elementConsumer.accept(buffer, element);
        }
    }

    public static Statistic[] readStatistics(ByteBuf buffer) {
        return readArray(buffer, (current) -> {
            String name = JavaBufferUtils.readString(current);
            int value = VarIntBufferUtils.readInt(buffer);
            return new Statistic(name, value);
        });
    }

    public static void writeStatistics(ByteBuf buffer, Statistic[] statistics) {
        writeArray(buffer, statistics, (current, statistic) -> {
            JavaBufferUtils.writeString(current, statistic.getName());
            VarIntBufferUtils.writeInt(current, statistic.getValue());
        });
    }

    public static String readString(ByteBuf buffer) {
        int length = VarIntBufferUtils.readInt(buffer);
        byte[] bytes = new byte[length];
        buffer.readBytes(bytes);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static void writeString(ByteBuf buffer, String string) {
        byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
        VarIntBufferUtils.writeInt(buffer, bytes.length);
        buffer.writeBytes(bytes);
    }

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

    public static Vector3d readDoublePosition(ByteBuf buffer) {
        double x = buffer.readDouble();
        double y = buffer.readDouble();
        double z = buffer.readDouble();
        return new Vector3d(x, y, z);
    }

    public static void writeDoublePosition(ByteBuf buffer, Vector3d position) {
        buffer.writeDouble(position.getX());
        buffer.writeDouble(position.getY());
        buffer.writeDouble(position.getZ());
    }

    public static Vector3i readIntPosition(ByteBuf buffer) {
        long value = buffer.readLong();
        int x = (int) (value >> 38);
        int y = (int) ((value >> 26) & 0xFFF);
        int z = (int) (value << 38 >> 38);
        return new Vector3i(x, y, z);
    }

    public static void writeIntPosition(ByteBuf buffer, Vector3i position) {
        buffer.writeDouble((((long) (position.getX()) & 0x3FFFFFF) << 38)
            | ((((long) position.getY()) & 0xFFF) << 26)
            | (((long) position.getZ()) & 0x3FFFFFF));
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
