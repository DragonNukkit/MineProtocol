package com.github.dragonnukkit.protocol.bedrock.packet;

import com.flowpowered.math.vector.Vector3i;
import com.github.dragonnukkit.protocol.VarInt;
import com.github.dragonnukkit.protocol.bedrock.BedrockPacket;
import com.github.dragonnukkit.protocol.bedrock.BedrockPacketHandler;
import com.github.dragonnukkit.protocol.bedrock.BedrockUtil;
import io.netty.buffer.ByteBuf;

public class AddHangingEntityPacket implements BedrockPacket {
    private long uniqueEntityId;
    private long runtimeEntityId;
    private Vector3i blockPosition;
    private int rotation;

    @Override
    public void encode(ByteBuf buf) {
        VarInt.writeLong(buf, uniqueEntityId);
        VarInt.writeUnsignedLong(buf, runtimeEntityId);
        BedrockUtil.writeVector3i(buf, blockPosition);
        VarInt.writeInt(buf, rotation);
    }

    @Override
    public void decode(ByteBuf buf) {
        uniqueEntityId = VarInt.readLong(buf);
        runtimeEntityId = VarInt.readUnsignedLong(buf);
        blockPosition = BedrockUtil.readVector3i(buf);
        rotation = VarInt.readInt(buf);
    }

    @Override
    public void handle(BedrockPacketHandler handler) {

    }
}
