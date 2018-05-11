package com.github.dragonnukkit.protocol.java.packet;

import com.flowpowered.math.vector.Vector3i;
import com.github.dragonnukkit.protocol.MinecraftPacketMeta;
import com.github.dragonnukkit.protocol.java.JavaPacket;
import com.github.dragonnukkit.protocol.java.JavaPacketHandler;
import com.github.dragonnukkit.protocol.java.type.Direction;
import com.github.dragonnukkit.protocol.java.type.PaintingType;
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
    private PaintingType type;
    private Vector3i position;
    private Direction direction;

    @Override
    public void decode(ByteBuf buffer) {
        entityId = VarIntBufferUtils.readInt(buffer);
        entityUniqueId = CommonBufferUtils.readUniqueId(buffer);
        type = JavaBufferUtils.readPaintingType(buffer);
        position = JavaBufferUtils.readIntPosition(buffer);
        direction = JavaBufferUtils.readDirection(buffer);
    }

    @Override
    public void encode(ByteBuf buffer) {
        VarIntBufferUtils.writeInt(buffer, entityId);
        CommonBufferUtils.writeUuid(buffer, entityUniqueId);
        JavaBufferUtils.writePaintingType(buffer, type);
        JavaBufferUtils.writeIntPosition(buffer, position);
        JavaBufferUtils.writeDirection(buffer, direction);
    }

    @Override
    public void handle(JavaPacketHandler handler) {

    }
}
