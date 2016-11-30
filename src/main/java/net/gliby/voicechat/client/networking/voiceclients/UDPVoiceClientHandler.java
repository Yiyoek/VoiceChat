package net.gliby.voicechat.client.networking.voiceclients;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;
import java.util.concurrent.LinkedBlockingQueue;
import net.gliby.voicechat.client.networking.voiceclients.UDPVoiceClient;
import net.gliby.voicechat.common.networking.voiceservers.udp.UDPByteUtilities;

public class UDPVoiceClientHandler implements Runnable {

   public LinkedBlockingQueue packetQueue;
   private final UDPVoiceClient client;


   public UDPVoiceClientHandler(UDPVoiceClient client) {
      this.client = client;
      this.packetQueue = new LinkedBlockingQueue();
   }

   private void handleAuthComplete() {
      this.client.handleAuth();
   }

   private void handleChunkVoiceData(ByteArrayDataInput in) {
      int entityId = in.readInt();
      byte chunkSize = in.readByte();
      boolean direct = in.readBoolean();
      byte[] data = UDPByteUtilities.readBytes(in);
      this.client.handlePacket(entityId, data, chunkSize, direct);
   }

   private void handleEntityPosition(ByteArrayDataInput in) {
      int entityId = in.readInt();
      double x = in.readDouble();
      double y = in.readDouble();
      double z = in.readDouble();
      this.client.handleEntityPosition(entityId, x, y, z);
   }

   private void handleVoiceData(ByteArrayDataInput in) {
      int entityId = in.readInt();
      boolean direct = in.readBoolean();
      byte[] data = UDPByteUtilities.readBytes(in);
      this.client.handlePacket(entityId, data, data.length, direct);
   }

   private void handleVoiceEnd(ByteArrayDataInput in) {
      int entityId = in.readInt();
      this.client.handleEnd(entityId);
   }

   public void read(byte[] data) {
      ByteArrayDataInput in = ByteStreams.newDataInput(data);
      byte id = in.readByte();
      switch(id) {
      case 0:
         this.handleAuthComplete();
         break;
      case 1:
         this.handleVoiceData(in);
         break;
      case 2:
         this.handleVoiceEnd(in);
      case 3:
      default:
         break;
      case 4:
         this.handleEntityPosition(in);
         break;
      case 5:
         this.handleChunkVoiceData(in);
      }

   }

   public void run() {
      while(UDPVoiceClient.running) {
         if(!this.packetQueue.isEmpty()) {
            this.read((byte[])this.packetQueue.poll());
         } else {
            synchronized(this) {
               try {
                  this.wait();
               } catch (InterruptedException var4) {
                  var4.printStackTrace();
               }
            }
         }
      }

   }
}