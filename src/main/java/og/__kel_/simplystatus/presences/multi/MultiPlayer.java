package og.__kel_.simplystatus.presences.multi;

import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableTextContent;
import og.__kel_.simplystatus.client.HotKeys;
import og.__kel_.simplystatus.client.MainClient;
import og.__kel_.simplystatus.configs.Config;
import og.__kel_.simplystatus.Translate;
import og.__kel_.simplystatus.info.Client;
import og.__kel_.simplystatus.info.Assets;
import og.__kel_.simplystatus.info.Music;

public class MultiPlayer {
    public MultiPlayer(DiscordRPC lib, MinecraftClient mc, Translate Translate, Long start_time, Config cfg){
        Client client = new Client();
        Assets assets = new Assets(HotKeys.bedrock, HotKeys.cringeIcons);
        DiscordRichPresence presence = new DiscordRichPresence();
        if(mc.player!=null){
            if(mc.player.isSleeping()){
                presence.details = Translate.replaceText(Translate.sleep, false, false,true, client);
            } else if(mc.player.isSneaking()){
                presence.details = Translate.replaceText(Translate.sneaking, false, false,true, client);
            } else if(mc.player.isOnFire() && !mc.player.isFireImmune()){
                presence.details = Translate.replaceText(Translate.onFire, false, false,false, client);
            } else {
                client.info(mc, presence,Translate);
            }
            //time
            client.time(mc, presence, Translate, assets);
        }
        String server_string = "";
        cfg.load(mc.getCurrentServerEntry().address);
         if (HotKeys.viewName) {
            if(mc.getCurrentServerEntry().name == null){
                server_string = MutableText.of(new TranslatableTextContent("status.simplystatus.unknownNameServer")).getString();
            } else {
                server_string = mc.getCurrentServerEntry().name;
            }

        } else {
            server_string = mc.getCurrentServerEntry().address;
        }

        if (server_string.equals("")) {
            presence.state = Translate.replaceText(Translate.unknownServer, true, false,false, client);
        } else {
            presence.state = Translate.replaceText(Translate.information, false, false,true, client);
        }
        if(HotKeys.showTime){
            presence.startTimestamp = start_time;
        }
        if(HotKeys.viewMusicListening && MainClient.musicPlayer){
            Music music = new Music();
            if(!music.isPaused()) {
                presence.smallImageKey = assets.music;
                if (music.isAuthorEnable()) {
                    presence.smallImageText = music.getAuthor() + " - " + music.getTrack();
                } else {
                    presence.smallImageText = music.getTrack();
                }
            }
        }
        lib.Discord_UpdatePresence(presence);
    }
}
