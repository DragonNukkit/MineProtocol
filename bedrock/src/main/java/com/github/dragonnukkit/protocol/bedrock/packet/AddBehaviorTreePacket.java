package com.github.dragonnukkit.protocol.bedrock.packet;

import com.github.dragonnukkit.protocol.bedrock.BedrockPacket;
import com.github.dragonnukkit.protocol.bedrock.BedrockPacketHandler;
import com.github.dragonnukkit.protocol.bedrock.BedrockUtil;
import io.netty.buffer.ByteBuf;
import lombok.Data;

@Data
public class AddBehaviorTreePacket implements BedrockPacket {
    private String json;

    @Override
    public void encode(ByteBuf buf) {
        BedrockUtil.writeString(buf, json);
    }

    @Override
    public void decode(ByteBuf buf) {
        json = BedrockUtil.readString(buf);
    }

    @Override
    public void handle(BedrockPacketHandler handler) {

    }
}
