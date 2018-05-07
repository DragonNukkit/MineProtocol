package com.github.dragonnukkit.protocol.bedrock.packet;

import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3f;
import com.github.dragonnukkit.protocol.VarInt;
import com.github.dragonnukkit.protocol.api.util.Rotation;
import com.github.dragonnukkit.protocol.bedrock.BedrockPacket;
import com.github.dragonnukkit.protocol.bedrock.BedrockPacketHandler;
import com.github.dragonnukkit.protocol.bedrock.BedrockUtil;
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
    public void encode(ByteBuf buf) {
        VarInt.writeLong(buf, uniqueEntityId);
        VarInt.writeUnsignedLong(buf, runtimeEntityId);
        VarInt.writeUnsignedInt(buf, entityType);
        BedrockUtil.writeVector3f(buf, position);
        BedrockUtil.writeVector3f(buf, motion);
        BedrockUtil.writeBodyRotation(buf, rotation);
        BedrockUtil.writeEntityAttributes(buf, attributes);
        // Metadata
        BedrockUtil.writeEntityLinks(buf, entityLinks);
    }

    @Override
    public void decode(ByteBuf buf) {
        uniqueEntityId = VarInt.readLong(buf);
        runtimeEntityId = VarInt.readUnsignedLong(buf);
        entityType = (int) VarInt.readUnsignedInt(buf);
        position = BedrockUtil.readVector3fAs3d(buf);
        motion = BedrockUtil.readVector3f(buf);
        rotation = BedrockUtil.readBodyRotation(buf);
        attributes.addAll(BedrockUtil.readEntityAttributes(buf));
        // Metadata
        entityLinks.addAll(BedrockUtil.readEntityLinks(buf));
    }

    @Override
    public void handle(BedrockPacketHandler handler) {

    }
}
