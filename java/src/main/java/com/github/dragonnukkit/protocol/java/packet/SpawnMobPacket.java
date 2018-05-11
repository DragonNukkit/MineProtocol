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
@MinecraftPacketMeta(id = 0x03)
public class SpawnMobPacket implements JavaPacket {
    private int entityId;
    private UUID entityUniqueId;
    private int type; // TODO: enum?
    private Vector3d position;
    private Rotation rotation;
    private Vector3f motion;
    // TODO: Metadata

    @Override
    public void encode(ByteBuf buffer) {
        entityId = VarIntBufferUtils.readInt(buffer);
        entityUniqueId = CommonBufferUtils.readUniqueId(buffer);
        type = buffer.readByte();
        position = JavaBufferUtils.readDoublePosition(buffer);
        rotation = JavaBufferUtils.readHeadRotation(buffer);
        motion = JavaBufferUtils.readMotion(buffer);
    }

    @Override
    public void decode(ByteBuf buffer) {
        VarIntBufferUtils.writeInt(buffer, entityId);
        CommonBufferUtils.writeUuid(buffer, entityUniqueId);
        buffer.writeByte(type);
        JavaBufferUtils.writeDoublePosition(buffer, position);
        JavaBufferUtils.writeHeadRotation(buffer, rotation);
        JavaBufferUtils.writeMotion(buffer, motion);
    }

    @Override
    public void handle(JavaPacketHandler handler) {

    }
}
