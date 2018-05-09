package com.github.dragonnukkit.protocol.bedrock.util;

import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3f;
import com.flowpowered.math.vector.Vector3i;
import com.github.dragonnukkit.protocol.util.VarIntBufferUtils;
import com.github.dragonnukkit.protocol.api.type.math.Rotation;
import com.github.dragonnukkit.protocol.common.entity.Attribute;
import com.github.dragonnukkit.protocol.common.entity.EntityLink;
import io.netty.buffer.ByteBuf;
import io.netty.util.AsciiString;
import lombok.experimental.UtilityClass;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@UtilityClass
public class BedrockBufferUtils {

    /*
    public static AsciiString readLEAsciiString(ByteBuf buffer) {
        int length = buffer.readIntLE();
        byte[] bytes = new byte[length];
        buffer.readBytes(bytes);
        return new AsciiString(bytes);
    }

    public static void writeLEAsciiString(ByteBuf buffer, AsciiString string) {
        byte[] bytes = string.array();
        buffer.writeIntLE(bytes.length);
        buffer.writeBytes(bytes);
    }
    */

    public static String readString(ByteBuf buffer) {
        int length = (int) VarIntBufferUtils.readUnsignedInt(buffer);
        byte[] bytes = new byte[length];
        buffer.readBytes(bytes);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static void writeString(ByteBuf buffer, String string) {
        byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
        VarIntBufferUtils.writeUnsignedInt(buffer, bytes.length);
        buffer.writeBytes(bytes);
    }

    public static Rotation readBodyRotation(ByteBuf buffer) {
        float pitch = buffer.readFloatLE();
        float yaw = buffer.readFloatLE();
        return Rotation.fromBodyRotation(pitch, yaw);
    }

    public static void writeBodyRotation(ByteBuf buffer, Rotation rotation) {
        buffer.writeFloatLE(rotation.getPitch());
        buffer.writeFloatLE(rotation.getYaw());
    }

    public static List<Attribute> readEntityAttributes(ByteBuf buffer) {
        List<Attribute> attributes = new ArrayList<>();
        int length = (int) VarIntBufferUtils.readUnsignedInt(buffer);

        for (int i = 0; i < length; i++) {
            String name = readString(buffer);
            float min = buffer.readFloatLE();
            float max = buffer.readFloatLE();
            float val = buffer.readFloatLE();
            attributes.add(new Attribute(name, min, max, val));
        }
        return attributes;
    }

    public static void writeEntityAttributes(ByteBuf buffer, Collection<Attribute> attributes) {
        VarIntBufferUtils.writeUnsignedInt(buffer, attributes.size());
        for (Attribute attribute : attributes) {
            writeString(buffer, attribute.getName());
            buffer.writeFloatLE(attribute.getMinimumValue());
            buffer.writeFloatLE(attribute.getMaximumValue());
            buffer.writeFloatLE(attribute.getValue());
        }
    }

    public static Collection<EntityLink> readEntityLinks(ByteBuf buffer) {
        List<EntityLink> entityLinkList = new ArrayList<>();
        int size = (int) VarIntBufferUtils.readUnsignedInt(buffer);

        for (int i = 0; i < size; i++) {
            long from = VarIntBufferUtils.readLong(buffer);
            long to = VarIntBufferUtils.readLong(buffer);
            byte type = buffer.readByte();
            boolean immediate = buffer.readBoolean();
            entityLinkList.add(new EntityLink(from, to, EntityLink.Type.values()[type], immediate));
        }
        return entityLinkList;
    }

    public static void writeEntityLinks(ByteBuf buffer, Collection<EntityLink> entityLinkList) {
        VarIntBufferUtils.writeUnsignedInt(buffer, entityLinkList.size());
        for (EntityLink entityLink: entityLinkList) {
            VarIntBufferUtils.writeLong(buffer, entityLink.getFromUniqueEntityId());
            VarIntBufferUtils.writeLong(buffer, entityLink.getToUniqueEntityId());
            buffer.writeByte(entityLink.getType().ordinal());
            buffer.writeBoolean(entityLink.isImmediate());
        }
    }

    public static Vector3i readVector3i(ByteBuf buffer) {
        int x = VarIntBufferUtils.readInt(buffer);
        int y = (int) VarIntBufferUtils.readUnsignedInt(buffer);
        int z = VarIntBufferUtils.readInt(buffer);
        return new Vector3i(x, y, z);
    }

    public static void writeVector3i(ByteBuf buffer, Vector3i blockPosition) {
        VarIntBufferUtils.writeInt(buffer, blockPosition.getX());
        VarIntBufferUtils.writeUnsignedInt(buffer, blockPosition.getY());
        VarIntBufferUtils.writeInt(buffer, blockPosition.getZ());
    }

    public static Vector3d readVector3fAs3d(ByteBuf buffer) {
        double x = buffer.readFloatLE();
        double y = buffer.readFloatLE();
        double z = buffer.readFloatLE();
        return Vector3d.from(x, y, z);
    }

    public static void writeVector3f(ByteBuf buffer, Vector3d vector3d) {
        Vector3f vector3f = vector3d.toFloat();
        buffer.writeFloatLE(vector3f.getX());
        buffer.writeFloatLE(vector3f.getY());
        buffer.writeFloatLE(vector3f.getZ());
    }

    public static Vector3f readVector3f(ByteBuf buffer) {
        float x = buffer.readFloatLE();
        float y = buffer.readFloatLE();
        float z = buffer.readFloatLE();
        return Vector3f.from(x, y, z);
    }

    public static void writeVector3f(ByteBuf buffer, Vector3f vector3f) {
        buffer.writeFloatLE(vector3f.getX());
        buffer.writeFloatLE(vector3f.getY());
        buffer.writeFloatLE(vector3f.getZ());
    }
}
