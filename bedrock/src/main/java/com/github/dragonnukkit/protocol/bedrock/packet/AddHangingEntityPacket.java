package com.github.dragonnukkit.protocol.bedrock.packet;

import com.flowpowered.math.vector.Vector3i;
import com.github.dragonnukkit.protocol.bedrock.BedrockPacket;
import com.github.dragonnukkit.protocol.bedrock.BedrockPacketHandler;

public class AddHangingEntityPacket extends BedrockPacket {
    private long uniqueEntityId;
    private long runtimeEntityId;
    private Vector3i blockPosition;
    private int rotation;

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
