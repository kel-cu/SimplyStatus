package ru.simplykel.simplystatus.mods;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import su.plo.voice.client.ModVoiceClient;
/**
 * PlasmoVoice support
 */
public class PlasmoVoice {
    public boolean isSpeak = false;
    public boolean isSelfTalk = false;
    public boolean isOnePlayer = false;
    public String listener = "";
    public PlasmoVoice(){

        if (ModVoiceClient.INSTANCE.getUdpClientManager().isConnected()) {
            MinecraftClient CLIENT = MinecraftClient.getInstance();
            if (ModVoiceClient.INSTANCE.getActivationManager().getParentActivation().get().isActive()) {
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
