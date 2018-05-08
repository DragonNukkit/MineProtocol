package com.github.dragonnukkit.protocol.bedrock.packet;

import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3f;
import com.github.dragonnukkit.protocol.api.type.math.Rotation;
import com.github.dragonnukkit.protocol.bedrock.BedrockPacket;
import com.github.dragonnukkit.protocol.bedrock.BedrockPacketHandler;
import com.github.dragonnukkit.protocol.common.entity.Attribute;
import com.github.dragonnukkit.protocol.common.entity.EntityLink;
import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class AddEntityPacket extends BedrockPacket {
    private long uniqueEntityId;
    private long runtimeEntityId;
    private int entityType;
    private Vector3d position;
    private Vector3f motion;
    private Rotation rotation;
    private final List<Attribute> attributes = new ArrayList<>();
    // metadata
    private final List<EntityLink> entityLinks = new ArrayList<>();

    public AddEntityPacket(ByteBuf buffer) {
        super(buffer);
    }

    @Override
    public void encode() {
        writeVarLong(uniqueEntityId);
        writeUnsignedVarLong(runtimeEntityId);
        writeUnsignedVarInt(entityType);
        writeVector3f(position);
        writeVector3f(motion);
        writeBodyRotation(rotation);
        writeEntityAttributes(attributes);
        // Metadata
        writeEntityLinks(entityLinks);
    }

    @Override
    public void decode() {
        uniqueEntityId = readVarLong();
        runtimeEntityId = readUnsignedVarLong();
        entityType = (int) readUnsignedVarInt();
        position = readVector3fAs3d();
        motion = readVector3f();
        rotation = readBodyRotation();
        attributes.addAll(readEntityAttributes());
        // Metadata
        entityLinks.addAll(readEntityLinks());
    }

    @Override
    public void handle(BedrockPacketHandler handler) {

    }
}
