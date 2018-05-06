package com.github.dragonnukkit.protocol.bedrock;

import com.github.dragonnukkit.protocol.MinecraftPacket;

public interface BedrockPacket extends MinecraftPacket {
    void handle(BedrockPacketHandler handler);
}
