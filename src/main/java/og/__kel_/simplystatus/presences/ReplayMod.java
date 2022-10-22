package og.__kel_.simplystatus.presences;

import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import com.replaymod.replay.ReplayModReplay;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import og.__kel_.simplystatus.Translate;
import og.__kel_.simplystatus.client.HotKeys;
import og.__kel_.simplystatus.client.MainClient;
import og.__kel_.simplystatus.configs.Config;
import og.__kel_.simplystatus.info.Assets;
import og.__kel_.simplystatus.info.Client;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class ReplayMod {
    public ReplayMod(DiscordRPC lib, MinecraftClient mc, Translate Translate, Long start_time, Config cfg){
        Client client = new Client();
        Assets assets = new Assets(HotKeys.bedrock, HotKeys.cringeIcons);
        DiscordRichPresence presence = new DiscordRichPresence();
        if(HotKeys.viewReplayMod){
            if(ReplayModReplay.instance.getReplayHandler().getReplayFile() != null){
                try{
                    presence.state =ReplayModReplay.instance.getReplayHandler().getReplayFile().getMetaData().getCustomServerName();
                    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy, hh:mm");
                    String strDate = dateFormat.format(ReplayModReplay.instance.getReplayHandler().getReplayFile().getMetaData().getDate());
                    //MainClient.log.warn("[replay]: " + dateFormat.getNumberFormat().toString());
                    presence.details = "Replay from: " + strDate+"(am/pm)";
                }catch (Exception error){
                    error.printStackTrace();
                }
                presence.largeImageKey = assets.replay;
                presence.largeImageText = "ReplayMod v"+ com.replaymod.core.ReplayMod.instance.getVersion();
            } else {
                presence.largeImageKey = assets.replay;
                presence.largeImageText = "ReplayMod v"+ com.replaymod.core.ReplayMod.instance.getVersion();
            }
            if(HotKeys.showAvatar){
                client.avatar(mc, presence);
            }
            lib.Discord_UpdatePresence(presence);
        } else {
            new Menu(lib, mc, Translate, start_time);
        }
    }
}
