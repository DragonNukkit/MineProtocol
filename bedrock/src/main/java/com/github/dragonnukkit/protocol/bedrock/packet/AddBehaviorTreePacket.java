package com.github.dragonnukkit.protocol.bedrock.packet;

import com.github.dragonnukkit.protocol.bedrock.BedrockPacket;
import com.github.dragonnukkit.protocol.bedrock.BedrockPacketHandler;
import lombok.Data;

@Data
public class AddBehaviorTreePacket extends BedrockPacket {
    private String json;

    @Override
    public void encode() {
        writeString(json);
    }

    @Override
    public void decode() {
        json = readString();
    }

    @Override
    public void handle(BedrockPacketHandler handler) {

    }
}
