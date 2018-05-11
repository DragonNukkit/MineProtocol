package com.github.dragonnukkit.protocol.java.packet;

import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3f;
import com.github.dragonnukkit.protocol.MinecraftPacketMeta;
import com.github.dragonnukkit.protocol.api.type.math.Rotation;
import com.github.dragonnukkit.protocol.java.JavaPacket;
import com.github.dragonnukkit.protocol.java.JavaPacketHandler;
import com.github.dragonnukkit.protocol.java.type.Direction;
import com.github.dragonnukkit.protocol.java.type.MinecartType;
import com.github.dragonnukkit.protocol.java.type.data.*;
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
    private byte type; // TODO: enum?
    private Vector3d position;
    private Rotation rotation;
    private ObjectData data;
    private Vector3f motion;

    @Override
    public void decode(ByteBuf buffer) {
        entityId = VarIntBufferUtils.readInt(buffer);
        entityUniqueId = CommonBufferUtils.readUniqueId(buffer);
        type = buffer.readByte();
        position = JavaBufferUtils.readDoublePosition(buffer);
        rotation = JavaBufferUtils.readBodyRotation(buffer);
        int rawData = buffer.readInt();
        // TODO: replace with EntityType.readData() when the EntityType  enumerator will be created.
        switch (type) {
            case 10: // Minecart
                data = MinecartData.fromMinecartType(MinecartType.fromTypeId(rawData));
                break;
            case 71: // Item frame
                data = ItemFrameData.fromDirection(Direction.fromDirectionId((byte) rawData));
                break;
            case 70: // Falling block
                data = FallingBlockData.fromBlockType(rawData);
                break;
            case 90: // Fishing float
                data = FishingFloatData.fromOwnerEntityId(rawData);
                break;
            case 60: // Arrow
            case 91: // Spectral arrow
            case 63: // Fireball
            case 64: // Small fireball
            case 93: // Dragon fireball
            case 66: // Wither skull
                data = ProjectileData.fromShooterEntityId(rawData);
                break;
            default:
                data = HasVelocityData.fromHasVelocity(rawData != 0);
        }
        motion = JavaBufferUtils.readMotion(buffer);
    }

    @Override
    public void encode(ByteBuf buffer) {
        VarIntBufferUtils.writeInt(buffer, entityId);
        CommonBufferUtils.writeUuid(buffer, entityUniqueId);
        buffer.writeByte(type);
        JavaBufferUtils.writeDoublePosition(buffer, position);
        JavaBufferUtils.writeBodyRotation(buffer, rotation);
        buffer.writeInt(data.getRawData());
        JavaBufferUtils.writeMotion(buffer, motion);
    }

    @Override
    public void handle(JavaPacketHandler handler) {

    }
}
