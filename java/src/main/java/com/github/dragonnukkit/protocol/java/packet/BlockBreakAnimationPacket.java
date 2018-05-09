package com.github.dragonnukkit.protocol.java.packet;

import com.flowpowered.math.vector.Vector3i;
import com.github.dragonnukkit.protocol.MinecraftPacketMeta;
import com.github.dragonnukkit.protocol.java.JavaPacket;
import com.github.dragonnukkit.protocol.java.JavaPacketHandler;
import com.github.dragonnukkit.protocol.java.type.Direction;
import com.github.dragonnukkit.protocol.java.util.JavaBufferUtils;
import com.github.dragonnukkit.protocol.util.CommonBufferUtils;
import com.github.dragonnukkit.protocol.util.VarIntBufferUtils;
import io.netty.buffer.ByteBuf;
import lombok.Data;

import java.util.UUID;

@Data
@MinecraftPacketMeta(id = 0x08)
public class BlockBreakAnimationPacket implements JavaPacket {
    private int entityId;
    private Vector3i position;
    private byte destroyStage;

    @Override
    public void encode(ByteBuf buffer) {
        entityId = VarIntBufferUtils.readInt(buffer);
        position = JavaBufferUtils.readIntPosition(buffer);
        destroyStage = buffer.readByte();
    }

    @Override
    public void decode(ByteBuf buffer) {
        VarIntBufferUtils.writeInt(buffer, entityId);
        JavaBufferUtils.writeIntPosition(buffer, position);
        buffer.writeByte(destroyStage);
    }

    @Override
    public void handle(JavaPacketHandler handler) {

    }
}
