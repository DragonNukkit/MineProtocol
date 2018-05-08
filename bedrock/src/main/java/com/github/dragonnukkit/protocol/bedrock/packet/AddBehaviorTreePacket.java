package com.github.dragonnukkit.protocol.bedrock.packet;

import com.github.dragonnukkit.protocol.bedrock.BedrockPacket;
import com.github.dragonnukkit.protocol.bedrock.BedrockPacketHandler;
import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class AddBehaviorTreePacket extends BedrockPacket {
    private String json;

    public AddBehaviorTreePacket(ByteBuf buffer) {
        super(buffer);
    }

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
