package com.github.dragonnukkit.protocol.util;

import io.netty.buffer.ByteBuf;
import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class CommonBufferUtils {

    public static UUID readUniqueId(ByteBuf buffer) {
        return new UUID(buffer.readLong(), buffer.readLong());
    }

    public static void writeUuid(ByteBuf buffer, UUID uuid) {
        buffer.writeLong(uuid.getMostSignificantBits());
        buffer.writeLong(uuid.getLeastSignificantBits());
    }
}
