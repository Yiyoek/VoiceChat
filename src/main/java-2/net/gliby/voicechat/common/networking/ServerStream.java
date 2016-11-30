/*
 * Decompiled with CFR 0_118.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.player.EntityPlayerMP
 */
package net.gliby.voicechat.common.networking;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;

public class ServerStream {
    final int id;
    long lastUpdated;
    int tick;
    public EntityPlayerMP player;
    public List<Integer> entities;
    public int chatMode;
    public boolean dirty;

    ServerStream(EntityPlayerMP player, int id, String identifier) {
        this.id = id;
        this.player = player;
        this.entities = new ArrayList<Integer>();
        this.lastUpdated = System.currentTimeMillis();
    }

    public final int getLastTimeUpdated() {
        return (int)(System.currentTimeMillis() - this.lastUpdated);
    }
}
