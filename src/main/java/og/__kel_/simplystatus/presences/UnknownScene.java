package og.__kel_.simplystatus.presences;

import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import com.replaymod.core.ReplayMod;
import com.replaymod.replay.ReplayModReplay;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import og.__kel_.simplystatus.client.HotKeys;
import og.__kel_.simplystatus.client.MainClient;
import og.__kel_.simplystatus.Translate;
import og.__kel_.simplystatus.info.Client;
import og.__kel_.simplystatus.info.Assets;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class UnknownScene {
    public UnknownScene(DiscordRPC lib, MinecraftClient mc, Translate Translate, Long start_time, NullPointerException err) {
        Client client = new Client();
        Assets assets = new Assets(HotKeys.bedrock, HotKeys.cringeIcons);
        Translate.selectedLang();
        DiscordRichPresence presence = new DiscordRichPresence();
        if(HotKeys.showTime){
            presence.startTimestamp = start_time;
        }
        presence.state = client.getVersion(mc);
        presence.largeImageKey = assets.Unknown;
        boolean IsThereAReplayMod = FabricLoader.getInstance().getModContainer("replaymod").isPresent();
        if(IsThereAReplayMod){
            if(ReplayModReplay.instance.getReplayHandler().getReplayFile() != null){
                try{
                    presence.state =ReplayModReplay.instance.getReplayHandler().getReplayFile().getMetaData().getCustomServerName();
                    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy, hh:mm");
                    String strDate = dateFormat.format(ReplayModReplay.instance.getReplayHandler().getReplayFile().getMetaData().getDate());
                    presence.details = "Replay from: " + strDate+"(am/pm)";
                }catch (Exception error){
                    error.printStackTrace();
                }
                presence.largeImageKey = assets.replay;
                presence.largeImageText = "ReplayMod v"+ReplayMod.instance.getVersion();
            } else{
                MainClient.log.error("Ooh, there's been an error\n"+err.toString());
            }
        } else {
            MainClient.log.error("Ooh, there's been an error\n"+err.toString());
        }
        lib.Discord_UpdatePresence(presence);
    }
}
