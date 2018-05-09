package com.github.dragonnukkit.protocol.java.packet;

import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3f;
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
@MinecraftPacketMeta(id = 0x00)
public class SpawnObjectPacket implements JavaPacket {
    private int entityId;
    private UUID entityUniqueId;
    private byte entityType; // TODO: enum?
    private Vector3d position;
    private Rotation rotation;
    private int data;
    private Vector3f motion;

    @Override
    public void encode(ByteBuf buffer) {
        entityId = VarIntBufferUtils.readInt(buffer);
        entityUniqueId = CommonBufferUtils.readUniqueId(buffer);
        entityType = buffer.readByte();
        position = JavaBufferUtils.readPosition(buffer);
        rotation = JavaBufferUtils.readBodyRotation(buffer);
        data = buffer.readInt();
        motion = JavaBufferUtils.readMotion(buffer);
    }

    @Override
    public void decode(ByteBuf buffer) {
        VarIntBufferUtils.writeInt(buffer, entityId);
        CommonBufferUtils.writeUuid(buffer, entityUniqueId);
        buffer.writeByte(entityType);
        JavaBufferUtils.writePosition(buffer, position);
        JavaBufferUtils.writeBodyRotation(buffer, rotation);
        buffer.writeInt(data);
        JavaBufferUtils.writeMotion(buffer, motion);
    }

    @Override
    public void handle(JavaPacketHandler handler) {

    }
}
