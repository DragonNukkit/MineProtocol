package com.github.dragonnukkit.protocol.bedrock;

import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3f;
import com.flowpowered.math.vector.Vector3i;
import com.github.dragonnukkit.protocol.MinecraftPacket;
import com.github.dragonnukkit.protocol.api.type.math.Rotation;
import com.github.dragonnukkit.protocol.common.entity.Attribute;
import com.github.dragonnukkit.protocol.common.entity.EntityLink;
import com.github.dragonnukkit.protocol.util.VarInt;
import com.whirvis.jraknet.RakNetPacket;
import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public abstract class BedrockPacket extends RakNetPacket implements MinecraftPacket {

    public BedrockPacket(ByteBuf buffer) {
        super(buffer);
    }

    abstract public void handle(BedrockPacketHandler handler);

    @Override
    abstract public void encode();

    @Override
    abstract public void decode();

    @Override
    public final void encode(ByteBuf buf) {
        encode();
    }

    @Override
    public final void decode(ByteBuf buf) {
        decode();
    }

    protected int readVarInt() {
        return VarInt.readInt(buffer());
    }

    protected void writeVarInt(int value) {
        VarInt.writeInt(buffer(), value);
    }

    protected long readUnsignedVarInt() {
        return VarInt.readUnsignedInt(buffer());
    }

    protected void writeUnsignedVarInt(long value) {
        VarInt.writeUnsignedInt(buffer(), value);
    }

    protected long readVarLong() {
        return VarInt.readLong(buffer());
    }

    protected void writeVarLong(long value) {
        VarInt.writeLong(buffer(), value);
    }

    protected long readUnsignedVarLong() {
        return VarInt.readUnsignedLong(buffer());
    }

    protected void writeUnsignedVarLong(long value) {
        VarInt.writeUnsignedLong(buffer(), value);
    }

    protected Vector3i readVector3i() {
        int x = readVarInt();
        int y = (int) readUnsignedVarInt();
        int z = readVarInt();
        return new Vector3i(x, y, z);
    }

    protected void writeVector3i(Vector3i blockPosition) {
        writeVarInt(blockPosition.getX());
        writeUnsignedVarInt(blockPosition.getY());
        writeVarInt(blockPosition.getZ());
    }

    protected Vector3d readVector3fAs3d() {
        double x = buffer().readFloatLE();
        double y = buffer().readFloatLE();
        double z = buffer().readFloatLE();
        return Vector3d.from(x, y, z);
    }

    protected void writeVector3f(Vector3d vector3d) {
        Vector3f vector3f = vector3d.toFloat();
        buffer().writeFloatLE(vector3f.getX());
        buffer().writeFloatLE(vector3f.getY());
        buffer().writeFloatLE(vector3f.getZ());
    }

    protected Vector3f readVector3f() {
        float x = buffer().readFloatLE();
        float y = buffer().readFloatLE();
        float z = buffer().readFloatLE();
        return Vector3f.from(x, y, z);
    }

    protected void writeVector3f(Vector3f vector3f) {
        buffer().writeFloatLE(vector3f.getX());
        buffer().writeFloatLE(vector3f.getY());
        buffer().writeFloatLE(vector3f.getZ());
    }

    protected Rotation readBodyRotation() {
        float pitch = buffer().readFloatLE();
        float yaw = buffer().readFloatLE();
        return Rotation.fromBodyRotation(pitch, yaw);
    }

    protected void writeBodyRotation(Rotation rotation) {
        buffer().writeFloatLE(rotation.getPitch());
        buffer().writeFloatLE(rotation.getYaw());
    }

    protected List<Attribute> readEntityAttributes() {
        List<Attribute> attributes = new ArrayList<>();
        int length = (int) readUnsignedVarInt();

        for (int i = 0; i < length; i++) {
            String name = readString();
            float min = buffer().readFloatLE();
            float max = buffer().readFloatLE();
            float val = buffer().readFloatLE();
            attributes.add(new Attribute(name, min, max, val));
        }
        return attributes;
    }

    protected void writeEntityAttributes(Collection<Attribute> attributes) {
        writeUnsignedVarInt(attributes.size());
        for (Attribute attribute : attributes) {
            writeString(attribute.getName());
            buffer().writeFloatLE(attribute.getMinimumValue());
            buffer().writeFloatLE(attribute.getMaximumValue());
            buffer().writeFloatLE(attribute.getValue());
        }
    }

    protected Collection<EntityLink> readEntityLinks() {
        List<EntityLink> entityLinkList = new ArrayList<>();
        int size = (int) readUnsignedVarInt();

        for (int i = 0; i < size; i++) {
            long from = readVarLong();
            long to = readVarLong();
            byte type = buffer().readByte();
            boolean immediate = buffer().readBoolean();
            entityLinkList.add(new EntityLink(from, to, EntityLink.Type.values()[type], immediate));
        }
        return entityLinkList;
    }

    protected void writeEntityLinks(Collection<EntityLink> entityLinkList) {
        writeUnsignedVarInt(entityLinkList.size());
        for (EntityLink entityLink : entityLinkList) {
            writeVarLong(entityLink.getFromUniqueEntityId());
            writeVarLong(entityLink.getToUniqueEntityId());
            buffer().writeByte(entityLink.getType().ordinal());
            buffer().writeBoolean(entityLink.isImmediate());
        }
    }
}
