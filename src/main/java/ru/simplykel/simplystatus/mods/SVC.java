package ru.simplykel.simplystatus.mods;

import de.maxhenkel.voicechat.plugins.impl.VoicechatClientApiImpl;
import de.maxhenkel.voicechat.voice.client.ClientManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;

/**
 * Simple Voice Chat support
 */
public class SVC {
    public boolean isSpeak = false;
    public boolean isSelfTalk = false;
    public boolean isOnePlayer = false;
    public String listener = "";
    public SVC(){
        if(!VoicechatClientApiImpl.instance().isDisconnected()){
            MinecraftClient CLIENT = MinecraftClient.getInstance();
            if(ClientManager.getClient().getMicThread().isTalking()) {
                isSpeak = true;
                if (CLIENT.world.getPlayers().size() == 1) {
                    isSelfTalk = true;
                } else if(CLIENT.world.getPlayers().size() == 2){
                    if (CLIENT.world.getPlayers().get(0).getName().equals(CLIENT.world.getPlayers().get(1).getName())) {
                        isSelfTalk = true;
                    } else {
                        PlayerEntity player = CLIENT.world.getPlayers().get(0);
                        if (player.getName().equals(CLIENT.player.getName())) {
                            player = CLIENT.world.getPlayers().get(1);
                        }
                        listener = player.getName().getString();
                        isOnePlayer = true;
                        isSelfTalk = false;
                    }
                } else toDisable();
            } else toDisable();
        } else toDisable();
    }
    private void toDisable(){
        isSpeak =  false;
        isOnePlayer = false;
        isSelfTalk = false;
        listener = "";

    }
}
