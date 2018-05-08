package com.github.dragonnukkit.protocol.util;

import io.netty.buffer.ByteBuf;
import lombok.experimental.UtilityClass;

@UtilityClass
public class VarInt {

    public static int readInt(ByteBuf buf) {
        int n = (int) readVarInt(buf);
        return (n >>> 1) ^ -(n & 1);
    }

    public static void writeInt(ByteBuf buf, int value) {
        writeVarInt(buf, (value << 1) ^ (value >> 31));
    }

    public static long readUnsignedInt(ByteBuf buf) {
        return readVarInt(buf);
    }

    public static void writeUnsignedInt(ByteBuf buf, long value) {
        writeVarInt(buf, value);
    }

    public static long readLong(ByteBuf buf) {
        long n = readVarInt(buf);
        return  (n >>> 1) ^ -(n & 1);
    }

    public static void writeLong(ByteBuf buf, long value) {
        writeVarInt(buf, (value << 1) ^ (value >> 63));
    }

    public static long readUnsignedLong(ByteBuf buf) {
        return readVarInt(buf);
    }

    public static void writeUnsignedLong(ByteBuf buf, long value) {
        writeVarInt(buf, value);
    }

    private static long readVarInt(ByteBuf buf) {
        long result = 0;
        for (int shift = 0; shift < 64; shift += 7) {
            final byte b = buf.readByte();
            result |= (long) (b & 0x7F) << shift;
            if ((b & 0x80) == 0) {
                return result;
            }
        }
        throw new ArithmeticException("The VarInt value is too large!");
    }

    private static void writeVarInt(ByteBuf buf, long value) {
        while ((value & ~0x7FL) != 0) {
            buf.writeByte((byte) (((int) value & 0x7F) | 0x80));
            value >>>= 7;
        }
        buf.writeByte((int) value);
    }
}
