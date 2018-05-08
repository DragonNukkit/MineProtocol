package com.github.dragonnukkit.protocol.bedrock.packet;

import com.flowpowered.math.vector.Vector3i;
import com.github.dragonnukkit.protocol.bedrock.BedrockPacket;
import com.github.dragonnukkit.protocol.bedrock.BedrockPacketHandler;
import io.netty.buffer.ByteBuf;
import lombok.Data;

@Data
public class AddHangingEntityPacket extends BedrockPacket {
    private long uniqueEntityId;
    private long runtimeEntityId;
    private Vector3i blockPosition;
    private int rotation;

    public AddHangingEntityPacket(ByteBuf buffer) {
        super(buffer);
    }

    @Override
    public void encode() {
        writeVarLong(uniqueEntityId);
        writeUnsignedVarLong(runtimeEntityId);
        writeVector3i(blockPosition);
        writeVarInt(rotation);
    }

    @Override
    public void decode() {
        uniqueEntityId = readVarLong();
        runtimeEntityId = readUnsignedVarLong();
        blockPosition = readVector3i();
        rotation = readVarInt();
    }

    @Override
    public void handle(BedrockPacketHandler handler) {

    }
}
