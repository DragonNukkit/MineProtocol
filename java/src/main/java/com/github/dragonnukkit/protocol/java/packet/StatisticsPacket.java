package com.github.dragonnukkit.protocol.java.packet;

import com.github.dragonnukkit.protocol.MinecraftPacketMeta;
import com.github.dragonnukkit.protocol.java.JavaPacket;
import com.github.dragonnukkit.protocol.java.JavaPacketHandler;
import com.github.dragonnukkit.protocol.java.type.Statistic;
import com.github.dragonnukkit.protocol.java.util.JavaBufferUtils;
import com.github.dragonnukkit.protocol.util.VarIntBufferUtils;
import io.netty.buffer.ByteBuf;
import lombok.Data;

@Data
@MinecraftPacketMeta(id = 0x07)
public class StatisticsPacket implements JavaPacket {
    private int entityId;
    private Statistic[] statistics;

    @Override
    public void encode(ByteBuf buffer) {
        entityId = VarIntBufferUtils.readInt(buffer);
        statistics = JavaBufferUtils.readStatistics(buffer);
    }

    @Override
    public void decode(ByteBuf buffer) {
        VarIntBufferUtils.writeInt(buffer, entityId);
        JavaBufferUtils.writeStatistics(buffer, statistics);
    }

    @Override
    public void handle(JavaPacketHandler handler) {

    }
}
