package com.github.dragonnukkit.protocol.util;

import io.netty.buffer.ByteBuf;
import lombok.experimental.UtilityClass;

@UtilityClass
public class VarIntBufferUtils {

    public static int readInt(ByteBuf buffer) {
        int n = (int) readVarInt(buffer);
        return (n >>> 1) ^ -(n & 1);
    }

    public static void writeInt(ByteBuf buffer, int value) {
        writeVarInt(buffer, (value << 1) ^ (value >> 31));
    }

    public static long readUnsignedInt(ByteBuf buffer) {
        return readVarInt(buffer);
    }

    public static void writeUnsignedInt(ByteBuf buffer, long value) {
        writeVarInt(buffer, value);
    }

    public static long readLong(ByteBuf buffer) {
        long n = readVarInt(buffer);
        return  (n >>> 1) ^ -(n & 1);
    }

    public static void writeLong(ByteBuf buffer, long value) {
        writeVarInt(buffer, (value << 1) ^ (value >> 63));
    }

    public static long readUnsignedLong(ByteBuf buffer) {
        return readVarInt(buffer);
    }

    public static void writeUnsignedLong(ByteBuf buffer, long value) {
        writeVarInt(buffer, value);
    }

    private static long readVarInt(ByteBuf buffer) {
        long result = 0;
        for (int shift = 0; shift < 64; shift += 7) {
            final byte b = buffer.readByte();
            result |= (long) (b & 0x7F) << shift;
            if ((b & 0x80) == 0) {
                return result;
            }
        }
        throw new ArithmeticException("The VarInt value is too large!");
    }

    private static void writeVarInt(ByteBuf buffer, long value) {
        while ((value & ~0x7FL) != 0) {
            buffer.writeByte((byte) (((int) value & 0x7F) | 0x80));
            value >>>= 7;
        }
        buffer.writeByte((int) value);
    }
}
