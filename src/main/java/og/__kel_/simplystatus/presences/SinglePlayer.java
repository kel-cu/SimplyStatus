package og.__kel_.simplystatus.presences;

import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import net.minecraft.client.MinecraftClient;
import og.__kel_.simplystatus.client.HotKeys;
import og.__kel_.simplystatus.Config;
import og.__kel_.simplystatus.Translate;
import og.__kel_.simplystatus.info.Game;
import og.__kel_.simplystatus.info.assets_rpc;

public class SinglePlayer {
    public SinglePlayer(DiscordRPC lib, MinecraftClient mc, Translate Translate, Long start_time, Config cfg){
        Game game = new Game();
        assets_rpc assets = new assets_rpc(HotKeys.bedrock, HotKeys.cringeIcons);
        DiscordRichPresence presence = new DiscordRichPresence();
        if(mc.player!=null){
            if(mc.player.isSleeping()){
                presence.details = Translate.replaceText(Translate.sleep, false, false,false, game);
            }else if(mc.player.isSneaking()){
                presence.details = Translate.replaceText(Translate.sneaking, false, false,false, game);
            }else {
                game.info(mc, presence,Translate);
            }
            //time
            game.time(mc, presence, Translate, assets);
        }

        presence.state = Translate.replaceText(Translate.information, false, false,false, game);
        if(HotKeys.showTime){
            presence.startTimestamp = start_time;
        }
        lib.Discord_UpdatePresence(presence);
    }
}
