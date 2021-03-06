package com.github.dragonnukkit.protocol.java.packet;

import com.flowpowered.math.vector.Vector3d;
import com.github.dragonnukkit.protocol.MinecraftPacketMeta;
import com.github.dragonnukkit.protocol.java.JavaPacket;
import com.github.dragonnukkit.protocol.java.JavaPacketHandler;
import com.github.dragonnukkit.protocol.java.type.GlobalEntityType;
import com.github.dragonnukkit.protocol.java.util.JavaBufferUtils;
import com.github.dragonnukkit.protocol.util.VarIntBufferUtils;
import io.netty.buffer.ByteBuf;
import lombok.Data;

@Data
@MinecraftPacketMeta(id = 0x02)
public class SpawnGlobalEntityPacket implements JavaPacket {
    private int entityId;
    private GlobalEntityType type;
    private Vector3d position;

    @Override
    public void decode(ByteBuf buffer) {
        entityId = VarIntBufferUtils.readInt(buffer);
        type = JavaBufferUtils.readGlobalEntityType(buffer);
        position = JavaBufferUtils.readDoublePosition(buffer);
    }

    @Override
    public void encode(ByteBuf buffer) {
        VarIntBufferUtils.writeInt(buffer, entityId);
        JavaBufferUtils.writeGlobalEntityType(buffer, type);
        JavaBufferUtils.writeDoublePosition(buffer, position);
    }

    @Override
    public void handle(JavaPacketHandler handler) {

    }
}
