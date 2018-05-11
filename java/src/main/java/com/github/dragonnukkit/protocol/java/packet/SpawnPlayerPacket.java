package com.github.dragonnukkit.protocol.java.packet;

import com.flowpowered.math.vector.Vector3d;
import com.github.dragonnukkit.protocol.MinecraftPacketMeta;
import com.github.dragonnukkit.protocol.api.type.math.Rotation;
import com.github.dragonnukkit.protocol.java.JavaPacket;
import com.github.dragonnukkit.protocol.java.JavaPacketHandler;
import com.github.dragonnukkit.protocol.java.util.JavaBufferUtils;
import com.github.dragonnukkit.protocol.util.CommonBufferUtils;
import com.github.dragonnukkit.protocol.util.VarIntBufferUtils;
import io.netty.buffer.ByteBuf;
import lombok.Data;

import java.util.UUID;

@Data
@MinecraftPacketMeta(id = 0x05)
public class SpawnPlayerPacket implements JavaPacket {
    private int entityId;
    private UUID playerUniqueId;
    private Vector3d position;
    private Rotation rotation;
    // TODO: Metadata

    @Override
    public void encode(ByteBuf buffer) {
        entityId = VarIntBufferUtils.readInt(buffer);
        playerUniqueId = CommonBufferUtils.readUniqueId(buffer);
        position = JavaBufferUtils.readDoublePosition(buffer);
        rotation = JavaBufferUtils.readBodyRotation(buffer);
    }

    @Override
    public void decode(ByteBuf buffer) {
        VarIntBufferUtils.writeInt(buffer, entityId);
        CommonBufferUtils.writeUuid(buffer, playerUniqueId);
        JavaBufferUtils.writeDoublePosition(buffer, position);
        JavaBufferUtils.writeBodyRotation(buffer, rotation);
    }

    @Override
    public void handle(JavaPacketHandler handler) {

    }
}
