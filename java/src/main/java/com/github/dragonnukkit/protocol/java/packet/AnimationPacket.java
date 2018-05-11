package com.github.dragonnukkit.protocol.java.packet;

import com.github.dragonnukkit.protocol.MinecraftPacketMeta;
import com.github.dragonnukkit.protocol.java.JavaPacket;
import com.github.dragonnukkit.protocol.java.JavaPacketHandler;
import com.github.dragonnukkit.protocol.java.type.AnimationType;
import com.github.dragonnukkit.protocol.java.util.JavaBufferUtils;
import com.github.dragonnukkit.protocol.util.VarIntBufferUtils;
import io.netty.buffer.ByteBuf;
import lombok.Data;

@Data
@MinecraftPacketMeta(id = 0x06)
public class AnimationPacket implements JavaPacket {
    private int entityId;
    private AnimationType animation;

    @Override
    public void encode(ByteBuf buffer) {
        entityId = VarIntBufferUtils.readInt(buffer);
        animation = JavaBufferUtils.readAnimationType(buffer);
    }

    @Override
    public void decode(ByteBuf buffer) {
        VarIntBufferUtils.writeInt(buffer, entityId);
        JavaBufferUtils.writeAnimationType(buffer, animation);
    }

    @Override
    public void handle(JavaPacketHandler handler) {

    }
}
