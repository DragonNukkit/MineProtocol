package com.github.dragonnukkit.protocol.java;

import com.github.dragonnukkit.protocol.MinecraftPacket;

public interface JavaPacket extends MinecraftPacket {

    void handle(JavaPacketHandler handler);
}
