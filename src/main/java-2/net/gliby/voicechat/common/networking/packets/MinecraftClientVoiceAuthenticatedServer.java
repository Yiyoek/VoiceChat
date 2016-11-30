/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  io.netty.buffer.ByteBuf
 *  net.minecraftforge.fml.common.network.ByteBufUtils
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessage
 *  net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler
 *  net.minecraftforge.fml.common.network.simpleimpl.MessageContext
 */
package net.gliby.voicechat.common.networking.packets;

import io.netty.buffer.ByteBuf;
import net.gliby.voicechat.VoiceChat;
import net.gliby.voicechat.client.networking.ClientNetwork;
import net.gliby.voicechat.common.networking.MinecraftPacket;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MinecraftClientVoiceAuthenticatedServer
extends MinecraftPacket
implements IMessageHandler<MinecraftClientVoiceAuthenticatedServer, IMessage> {
    boolean showVoicePlates;
    boolean showVoiceIcons;
    int minQuality;
    int maxQuality;
    int bufferSize;
    int soundDistance;
    int voiceServerType;
    int udpPort;
    String hash;
    String ip;

    public MinecraftClientVoiceAuthenticatedServer() {
    }

    public MinecraftClientVoiceAuthenticatedServer(boolean canShowVoicePlates, boolean canShowVoiceIcons, int minQuality, int maxQuality, int bufferSize, int soundDistance, int voiceServerType, int udpPort, String hash, String ip) {
        this.showVoicePlates = canShowVoicePlates;
        this.showVoiceIcons = canShowVoiceIcons;
        this.minQuality = minQuality;
        this.maxQuality = maxQuality;
        this.bufferSize = bufferSize;
        this.soundDistance = soundDistance;
        this.voiceServerType = voiceServerType;
        this.udpPort = udpPort;
        this.hash = hash;
        this.ip = ip;
    }

    public void fromBytes(ByteBuf buf) {
        this.showVoicePlates = buf.readBoolean();
        this.showVoiceIcons = buf.readBoolean();
        this.minQuality = buf.readInt();
        this.maxQuality = buf.readInt();
        this.bufferSize = buf.readInt();
        this.soundDistance = buf.readInt();
        this.voiceServerType = buf.readInt();
        this.udpPort = buf.readInt();
        this.hash = ByteBufUtils.readUTF8String((ByteBuf)buf);
        this.ip = ByteBufUtils.readUTF8String((ByteBuf)buf);
    }

    public IMessage onMessage(MinecraftClientVoiceAuthenticatedServer packet, MessageContext ctx) {
        VoiceChat.getProxyInstance().getClientNetwork().handleVoiceAuthenticatedServer(packet.showVoicePlates, packet.showVoiceIcons, packet.minQuality, packet.maxQuality, packet.bufferSize, packet.soundDistance, packet.voiceServerType, packet.udpPort, packet.hash, packet.ip);
        return null;
    }

    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(this.showVoicePlates);
        buf.writeBoolean(this.showVoiceIcons);
        buf.writeInt(this.minQuality);
        buf.writeInt(this.maxQuality);
        buf.writeInt(this.bufferSize);
        buf.writeInt(this.soundDistance);
        buf.writeInt(this.voiceServerType);
        buf.writeInt(this.udpPort);
        ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)this.hash);
        ByteBufUtils.writeUTF8String((ByteBuf)buf, (String)this.ip);
    }
}
