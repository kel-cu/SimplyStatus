package ru.kelcuprum.simplystatus.mods;

import de.maxhenkel.voicechat.plugins.impl.VoicechatClientApiImpl;
import de.maxhenkel.voicechat.voice.client.ClientManager;
import net.minecraft.client.player.AbstractClientPlayer;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.simplystatus.SimplyStatus;
import su.plo.voice.client.ModVoiceClient;

import java.util.Objects;

public class Voice {
    public boolean isSpeak = false;
    public boolean isSelfTalk = false;
    public boolean isOnePlayer = false;
    public String listener = "";
    public Voice(){
        if(SimplyStatus.plasmo) usePlasmo();
        else if(SimplyStatus.svc) useSVC();
    }
    private void usePlasmo(){
        if (ModVoiceClient.INSTANCE.getUdpClientManager().isConnected() && ModVoiceClient.INSTANCE.getActivationManager().getParentActivation().isPresent()) {
            if (ModVoiceClient.INSTANCE.getActivationManager().getParentActivation().get().isActive()) {
                isSpeak = true;
                assert AlinLib.MINECRAFT.level != null;
                assert AlinLib.MINECRAFT.player != null;
                if (AlinLib.MINECRAFT.level.players().size() == 1) {
                    isSelfTalk = true;
                } else if(AlinLib.MINECRAFT.level.players().size() == 2){
                    if (AlinLib.MINECRAFT.level.players().get(0).getName().equals(AlinLib.MINECRAFT.level.players().get(1).getName())) {
                        isSelfTalk = true;
                    } else {
                        AbstractClientPlayer player = AlinLib.MINECRAFT.level.players().get(0);
                        if (player.getName().equals(AlinLib.MINECRAFT.player.getName())) player = AlinLib.MINECRAFT.level.players().get(1);
                        listener = player.getName().getString();
                        isOnePlayer = true;
                        isSelfTalk = false;
                    }
                } else {
                    isOnePlayer = false;
                    isSelfTalk = false;
                    listener = AlinLib.MINECRAFT.getUser().getName();
                }
            } else toDisable();
        } else toDisable();
    }
    private void useSVC(){
        if(!VoicechatClientApiImpl.instance().isDisconnected()){
            if(Objects.requireNonNull(Objects.requireNonNull(ClientManager.getClient()).getMicThread()).isTalking()) {
                isSpeak = true;
                assert AlinLib.MINECRAFT.level != null;
                assert AlinLib.MINECRAFT.player != null;
                if (AlinLib.MINECRAFT.level.players().size() == 1) {
                    isSelfTalk = true;
                } else if(AlinLib.MINECRAFT.level.players().size() == 2){
                    if (AlinLib.MINECRAFT.level.players().get(0).getName().equals(AlinLib.MINECRAFT.level.players().get(1).getName())) {
                        isSelfTalk = true;
                    } else {
                        AbstractClientPlayer player = AlinLib.MINECRAFT.level.players().get(0);
                        if (player.getName().equals(AlinLib.MINECRAFT.player.getName())) player = AlinLib.MINECRAFT.level.players().get(1);
                        listener = player.getName().getString();
                        isOnePlayer = true;
                        isSelfTalk = false;
                    }
                } else {
                    isOnePlayer = false;
                    isSelfTalk = false;
                    listener = AlinLib.MINECRAFT.getUser().getName();
                }
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
