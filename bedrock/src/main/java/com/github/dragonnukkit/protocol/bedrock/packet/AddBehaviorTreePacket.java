package com.github.dragonnukkit.protocol.bedrock.packet;

import com.github.dragonnukkit.protocol.bedrock.BedrockPacket;
import com.github.dragonnukkit.protocol.bedrock.BedrockPacketHandler;
import com.github.dragonnukkit.protocol.bedrock.util.BedrockBufferUtils;
import io.netty.buffer.ByteBuf;
import lombok.Data;

@Data
public class AddBehaviorTreePacket implements BedrockPacket {
    private String json;

    @Override
    public void encode(ByteBuf buffer) {
        BedrockBufferUtils.writeString(buffer, json);
    }

    @Override
    public void decode(ByteBuf buffer) {
        json = BedrockBufferUtils.readString(buffer);
    }

    @Override
    public void handle(BedrockPacketHandler handler) {

    }
}
