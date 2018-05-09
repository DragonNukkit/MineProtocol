package com.github.dragonnukkit.protocol.bedrock.packet;

import com.flowpowered.math.vector.Vector3i;
import com.github.dragonnukkit.protocol.bedrock.util.BedrockBufferUtils;
import com.github.dragonnukkit.protocol.util.VarIntBufferUtils;
import com.github.dragonnukkit.protocol.bedrock.BedrockPacket;
import com.github.dragonnukkit.protocol.bedrock.BedrockPacketHandler;
import io.netty.buffer.ByteBuf;
import lombok.Data;

@Data
public class AddHangingEntityPacket implements BedrockPacket {
    private long uniqueEntityId;
    private long runtimeEntityId;
    private Vector3i blockPosition;
    private int rotation;

    @Override
    public void encode(ByteBuf buffer) {
        VarIntBufferUtils.writeLong(buffer, uniqueEntityId);
        VarIntBufferUtils.writeUnsignedLong(buffer, runtimeEntityId);
        BedrockBufferUtils.writeVector3i(buffer, blockPosition);
        VarIntBufferUtils.writeInt(buffer, rotation);
    }

    @Override
    public void decode(ByteBuf buffer) {
        uniqueEntityId = VarIntBufferUtils.readLong(buffer);
        runtimeEntityId = VarIntBufferUtils.readUnsignedLong(buffer);
        blockPosition = BedrockBufferUtils.readVector3i(buffer);
        rotation = VarIntBufferUtils.readInt(buffer);
    }

    @Override
    public void handle(BedrockPacketHandler handler) {

    }
}
