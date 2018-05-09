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
@MinecraftPacketMeta(id = 0x06)
public class AnimationPacket implements JavaPacket {
    private int entityId;
    private byte animation; // TODO: enum?

    @Override
    public void encode(ByteBuf buffer) {
        entityId = VarIntBufferUtils.readInt(buffer);
        animation = buffer.readByte();
    }

    @Override
    public void decode(ByteBuf buffer) {
        VarIntBufferUtils.writeInt(buffer, entityId);
        buffer.writeByte(animation);
    }

    @Override
    public void handle(JavaPacketHandler handler) {

    }
}
