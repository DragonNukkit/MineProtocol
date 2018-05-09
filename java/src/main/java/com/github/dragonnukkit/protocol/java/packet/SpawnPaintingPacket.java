package com.github.dragonnukkit.protocol.java.packet;

import com.flowpowered.math.vector.Vector3d;
import com.github.dragonnukkit.protocol.MinecraftPacketMeta;
import com.github.dragonnukkit.protocol.java.JavaPacket;
import com.github.dragonnukkit.protocol.java.JavaPacketHandler;
import com.github.dragonnukkit.protocol.java.util.JavaBufferUtils;
import com.github.dragonnukkit.protocol.util.CommonBufferUtils;
import com.github.dragonnukkit.protocol.util.VarIntBufferUtils;
import io.netty.buffer.ByteBuf;
import lombok.Data;

import java.util.UUID;

@Data
@MinecraftPacketMeta(id = 0x04)
public class SpawnPaintingPacket implements JavaPacket {
    private int entityId;
    private UUID entityUniqueId;
    private String title; // TODO: enum?
    private Vector3d position;
    // TODO: Direction

    @Override
    public void encode(ByteBuf buffer) {
        entityId = VarIntBufferUtils.readInt(buffer);
        entityUniqueId = CommonBufferUtils.readUniqueId(buffer);
        title = JavaBufferUtils.readString(buffer);
        //position = JavaBufferUtils.readPosition(buffer); TODO: position (http://wiki.vg/Data_types#Position)
    }

    @Override
    public void decode(ByteBuf buffer) {
        VarIntBufferUtils.writeInt(buffer, entityId);
        CommonBufferUtils.writeUuid(buffer, entityUniqueId);
        JavaBufferUtils.writeString(buffer, title);
        //JavaBufferUtils.writePosition(buffer, position);
    }

    @Override
    public void handle(JavaPacketHandler handler) {

    }
}
