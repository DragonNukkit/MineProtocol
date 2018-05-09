package com.github.dragonnukkit.protocol;

import io.netty.buffer.ByteBuf;

public interface MinecraftPacket {
    void encode(ByteBuf buffer);

    void decode(ByteBuf buffer);
}
