package og.__kel_.simplystatus.presences;

import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import net.minecraft.client.MinecraftClient;
import og.__kel_.simplystatus.client.HotKeys;
import og.__kel_.simplystatus.configs.Config;
import og.__kel_.simplystatus.Translate;
import og.__kel_.simplystatus.info.Client;
import og.__kel_.simplystatus.info.Assets;

public class SinglePlayer {
    public SinglePlayer(DiscordRPC lib, MinecraftClient mc, Translate Translate, Long start_time, Config cfg){
        Client client = new Client();
        Assets assets = new Assets(HotKeys.bedrock, HotKeys.cringeIcons);
        DiscordRichPresence presence = new DiscordRichPresence();
        if(mc.player!=null){
            if(mc.player.isSleeping()){
                presence.details = Translate.replaceText(Translate.sleep, false, false,false, client);
            }else if(mc.player.isSneaking()){
                presence.details = Translate.replaceText(Translate.sneaking, false, false,false, client);
            }else {
                client.info(mc, presence,Translate);
            }
            //time
            client.time(mc, presence, Translate, assets);
        }

        presence.state = Translate.replaceText(Translate.information, false, false,false, client);
        if(HotKeys.showTime){
            presence.startTimestamp = start_time;
        }
        lib.Discord_UpdatePresence(presence);
    }
}
