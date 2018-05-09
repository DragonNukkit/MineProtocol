package com.github.dragonnukkit.protocol.java.packet;

import com.flowpowered.math.vector.Vector3d;
import com.github.dragonnukkit.protocol.MinecraftPacketMeta;
import com.github.dragonnukkit.protocol.java.JavaPacket;
import com.github.dragonnukkit.protocol.java.JavaPacketHandler;
import com.github.dragonnukkit.protocol.java.util.JavaBufferUtils;
import com.github.dragonnukkit.protocol.util.VarIntBufferUtils;
import io.netty.buffer.ByteBuf;
import lombok.Data;

@Data
@MinecraftPacketMeta(id = 0x01)
public class SpawnExperienceOrbPacket implements JavaPacket {
    private int entityId;
    private Vector3d position;
    private short count;

    @Override
    public void encode(ByteBuf buffer) {
        entityId = VarIntBufferUtils.readInt(buffer);
        position = JavaBufferUtils.readPosition(buffer);
        count = buffer.readShort();
    }

    @Override
    public void decode(ByteBuf buffer) {
        VarIntBufferUtils.writeInt(buffer, entityId);
        JavaBufferUtils.writePosition(buffer, position);
        buffer.writeShort(count);
    }

    @Override
    public void handle(JavaPacketHandler handler) {

    }
}
