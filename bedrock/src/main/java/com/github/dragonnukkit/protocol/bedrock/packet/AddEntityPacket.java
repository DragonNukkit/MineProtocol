package com.github.dragonnukkit.protocol.bedrock.packet;

import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3f;
import com.github.dragonnukkit.protocol.util.VarIntBufferUtils;
import com.github.dragonnukkit.protocol.api.type.math.Rotation;
import com.github.dragonnukkit.protocol.bedrock.BedrockPacket;
import com.github.dragonnukkit.protocol.bedrock.BedrockPacketHandler;
import com.github.dragonnukkit.protocol.bedrock.util.BedrockBufferUtils;
import com.github.dragonnukkit.protocol.common.entity.Attribute;
import com.github.dragonnukkit.protocol.common.entity.EntityLink;
import io.netty.buffer.ByteBuf;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AddEntityPacket implements BedrockPacket {
    private long uniqueEntityId;
    private long runtimeEntityId;
    private int entityType;
    private Vector3d position;
    private Vector3f motion;
    private Rotation rotation;
    private final List<Attribute> attributes = new ArrayList<>();
    // metadata
    private final List<EntityLink> entityLinks = new ArrayList<>();

    @Override
    public void encode(ByteBuf buffer) {
        VarIntBufferUtils.writeLong(buffer, uniqueEntityId);
        VarIntBufferUtils.writeUnsignedLong(buffer, runtimeEntityId);
        VarIntBufferUtils.writeUnsignedInt(buffer, entityType);
        BedrockBufferUtils.writeVector3f(buffer, position);
        BedrockBufferUtils.writeVector3f(buffer, motion);
        BedrockBufferUtils.writeBodyRotation(buffer, rotation);
        BedrockBufferUtils.writeEntityAttributes(buffer, attributes);
        // Metadata
        BedrockBufferUtils.writeEntityLinks(buffer, entityLinks);
    }

    @Override
    public void decode(ByteBuf buffer) {
        uniqueEntityId = VarIntBufferUtils.readLong(buffer);
        runtimeEntityId = VarIntBufferUtils.readUnsignedLong(buffer);
        entityType = (int) VarIntBufferUtils.readUnsignedInt(buffer);
        position = BedrockBufferUtils.readVector3fAs3d(buffer);
        motion = BedrockBufferUtils.readVector3f(buffer);
        rotation = BedrockBufferUtils.readBodyRotation(buffer);
        attributes.addAll(BedrockBufferUtils.readEntityAttributes(buffer));
        // Metadata
        entityLinks.addAll(BedrockBufferUtils.readEntityLinks(buffer));
    }

    @Override
    public void handle(BedrockPacketHandler handler) {

    }
}
