package com.github.dragonnukkit.protocol;

import io.netty.buffer.ByteBuf;

public interface MinecraftPacket {
    void encode(ByteBuf buf);

    void decode(ByteBuf buf);
}
