package og.__kel_.simplystatus.presences;

import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableTextContent;
import og.__kel_.simplystatus.Client;
import og.__kel_.simplystatus.Config;
import og.__kel_.simplystatus.Translate;
import og.__kel_.simplystatus.info.Game;
import og.__kel_.simplystatus.info.assets_rpc;

public class MultiPlayer {
    public MultiPlayer(DiscordRPC lib, MinecraftClient mc, Translate Translate, Long start_time, Config cfg){
        Game game = new Game();
        assets_rpc assets = new assets_rpc(Client.Bedrock, Client.CringeIcons);
        DiscordRichPresence presence = new DiscordRichPresence();
        if(mc.player!=null){
            if(mc.player.isSleeping()){
                presence.details = Translate.replaceText(Translate.text_isSleep, false, false,true, game);
            }else if(mc.player.isSneaking()){
                presence.details = Translate.replaceText(Translate.text_isSneaking, false, false,true, game);
            }else {
                game.info(mc, presence,Translate);
            }
            //time
            game.time(mc, presence, Translate, assets);
        }
        String server_string = "";
        cfg.load(mc.getCurrentServerEntry().address);
         if (Client.ViewName) {
            if(mc.getCurrentServerEntry().name == null){
                server_string = MutableText.of(new TranslatableTextContent("status.simplystatus.unknownNameServer")).getString();
            } else {
                server_string = mc.getCurrentServerEntry().name;
            }

        } else {
            server_string = mc.getCurrentServerEntry().address;
        }

        if (server_string.equals("")) {
            presence.state = Translate.replaceText(Translate.text_UnknownServer, true, false,false, game);
        } else {
            presence.state = Translate.replaceText(Translate.text_information, false, false,true, game);
        }
        if(Client.showTime){
            presence.startTimestamp = start_time;
        }

        lib.Discord_UpdatePresence(presence);
    }
}
