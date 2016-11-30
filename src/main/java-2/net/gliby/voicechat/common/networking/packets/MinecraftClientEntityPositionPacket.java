/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 */
package net.gliby.voicechat.common.networking.packets;

import io.netty.buffer.ByteBuf;
import net.gliby.voicechat.VoiceChat;
import net.gliby.voicechat.client.networking.ClientNetwork;
import net.gliby.voicechat.client.networking.voiceclients.VoiceClient;
import net.gliby.voicechat.common.networking.MinecraftPacket;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MinecraftClientEntityPositionPacket
extends MinecraftPacket
implements IMessageHandler<MinecraftClientEntityPositionPacket, IMessage> {
    private int entityID;
    private double x;
    private double y;
    private double z;

    public MinecraftClientEntityPositionPacket() {
    }

    public MinecraftClientEntityPositionPacket(int entityID, double x, double y, double z) {
        this.entityID = entityID;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void fromBytes(ByteBuf buf) {
        this.entityID = buf.readInt();
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
    }

    public IMessage onMessage(MinecraftClientEntityPositionPacket packet, MessageContext ctx) {
        if (VoiceChat.getProxyInstance().getClientNetwork().isConnected()) {
            VoiceChat.getProxyInstance().getClientNetwork().getVoiceClient().handleEntityPosition(packet.entityID, packet.x, packet.y, packet.z);
        }
        return null;
    }

    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.entityID);
        buf.writeDouble(this.x);
        buf.writeDouble(this.y);
        buf.writeDouble(this.z);
    }
}
