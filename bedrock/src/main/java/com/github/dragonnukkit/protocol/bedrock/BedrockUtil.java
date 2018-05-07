package com.github.dragonnukkit.protocol.bedrock;

import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3f;
import com.flowpowered.math.vector.Vector3i;
import com.github.dragonnukkit.protocol.VarInt;
import com.github.dragonnukkit.protocol.api.type.math.Rotation;
import com.github.dragonnukkit.protocol.common.entity.Attribute;
import com.github.dragonnukkit.protocol.common.entity.EntityLink;
import com.google.common.base.Preconditions;
import io.netty.buffer.ByteBuf;
import io.netty.util.AsciiString;
import lombok.experimental.UtilityClass;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@UtilityClass
public class BedrockUtil {

    public static AsciiString readLEAsciiString(ByteBuf buf) {
        int length = buf.readIntLE();
        byte[] bytes = new byte[length];
        buf.readBytes(bytes);
        return new AsciiString(bytes);
    }

    public static void writeLEAsciiString(ByteBuf buf, AsciiString string) {
        byte[] bytes = string.array();
        buf.writeIntLE(bytes.length);
        buf.writeBytes(bytes);
    }

    public static String readString(ByteBuf buf) {
        int length = (int) VarInt.readUnsignedInt(buf);
        byte[] bytes = new byte[length];
        buf.readBytes(bytes);
        return new String(bytes, StandardCharsets.UTF_8);
    }

    public static void writeString(ByteBuf buf, String string) {
        byte[] bytes = string.getBytes(StandardCharsets.UTF_8);
        VarInt.writeUnsignedInt(buf, bytes.length);
        buf.writeBytes(bytes);
    }

    public static UUID readUuid(ByteBuf buffer) {
        Preconditions.checkNotNull(buffer, "buffer");
        return new UUID(buffer.readLong(), buffer.readLong());
    }

    public static void writeUuid(ByteBuf buffer, UUID uuid) {
        buffer.writeLong(uuid.getMostSignificantBits());
        buffer.writeLong(uuid.getLeastSignificantBits());
    }

    public static Vector3i readVector3i(ByteBuf buf) {
        int x = VarInt.readInt(buf);
        int y = (int) VarInt.readUnsignedInt(buf);
        int z = VarInt.readInt(buf);
        return new Vector3i(x, y, z);
    }

    public static void writeVector3i(ByteBuf buf, Vector3i blockPosition) {
        VarInt.writeInt(buf, blockPosition.getX());
        VarInt.writeUnsignedInt(buf, blockPosition.getY());
        VarInt.writeInt(buf, blockPosition.getZ());
    }

    public static Vector3d readVector3fAs3d(ByteBuf buf) {
        double x = buf.readFloatLE();
        double y = buf.readFloatLE();
        double z = buf.readFloatLE();
        return Vector3d.from(x, y, z);
    }

    public static void writeVector3f(ByteBuf buf, Vector3d vector3d) {
        Vector3f vector3f = vector3d.toFloat();
        buf.writeFloatLE(vector3f.getX());
        buf.writeFloatLE(vector3f.getY());
        buf.writeFloatLE(vector3f.getZ());
    }

    public static Vector3f readVector3f(ByteBuf buf) {
        float x = buf.readFloatLE();
        float y = buf.readFloatLE();
        float z = buf.readFloatLE();
        return Vector3f.from(x, y, z);
    }

    public static void writeVector3f(ByteBuf buf, Vector3f vector3f) {
        buf.writeFloatLE(vector3f.getX());
        buf.writeFloatLE(vector3f.getY());
        buf.writeFloatLE(vector3f.getZ());
    }

    public static Rotation readBodyRotation(ByteBuf buf) {
        float pitch = buf.readFloatLE();
        float yaw = buf.readFloatLE();
        return Rotation.fromBodyRotation(pitch, yaw);
    }

    public static void writeBodyRotation(ByteBuf buf, Rotation rotation) {
        buf.writeFloatLE(rotation.getPitch());
        buf.writeFloatLE(rotation.getYaw());
    }

    public static List<Attribute> readEntityAttributes(ByteBuf buf) {
        List<Attribute> attributes = new ArrayList<>();
        int length = (int) VarInt.readUnsignedInt(buf);

        for (int i = 0; i < length; i++) {
            String name = readString(buf);
            float min = buf.readFloatLE();
            float max = buf.readFloatLE();
            float val = buf.readFloatLE();
            attributes.add(new Attribute(name, min, max, val));
        }
        return attributes;
    }

    public static void writeEntityAttributes(ByteBuf buf, Collection<Attribute> attributes) {
        VarInt.writeUnsignedInt(buf, attributes.size());
        for (Attribute attribute : attributes) {
            writeString(buf, attribute.getName());
            buf.writeFloatLE(attribute.getMinimumValue());
            buf.writeFloatLE(attribute.getMaximumValue());
            buf.writeFloatLE(attribute.getValue());
        }
    }

    public static Collection<EntityLink> readEntityLinks(ByteBuf buf) {
        List<EntityLink> entityLinkList = new ArrayList<>();
        int size = (int) VarInt.readUnsignedInt(buf);

        for (int i = 0; i < size; i++) {
            long from = VarInt.readLong(buf);
            long to = VarInt.readLong(buf);
            byte type = buf.readByte();
            boolean immediate = buf.readBoolean();
            entityLinkList.add(new EntityLink(from, to, EntityLink.Type.values()[type], immediate));
        }
        return entityLinkList;
    }

    public static void writeEntityLinks(ByteBuf buf, Collection<EntityLink> entityLinkList) {
        VarInt.writeUnsignedInt(buf, entityLinkList.size());
        for (EntityLink entityLink: entityLinkList) {
            VarInt.writeLong(buf, entityLink.getFromUniqueEntityId());
            VarInt.writeLong(buf, entityLink.getToUniqueEntityId());
            buf.writeByte(entityLink.getType().ordinal());
            buf.writeBoolean(entityLink.isImmediate());
        }
    }
}
