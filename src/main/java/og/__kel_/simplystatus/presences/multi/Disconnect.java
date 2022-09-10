package og.__kel_.simplystatus.presences.multi;

import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import net.minecraft.client.MinecraftClient;
import og.__kel_.simplystatus.Translate;
import og.__kel_.simplystatus.client.HotKeys;
import og.__kel_.simplystatus.client.MainClient;
import og.__kel_.simplystatus.configs.Config;
import og.__kel_.simplystatus.info.Assets;
import og.__kel_.simplystatus.info.Client;
import og.__kel_.simplystatus.info.Music;

public class Disconnect {
    public Disconnect(DiscordRPC lib, MinecraftClient mc, Translate Translate, Long start_time, Config cfg){
        Client client = new Client();
        Assets assets = new Assets(HotKeys.bedrock, HotKeys.cringeIcons);
        DiscordRichPresence presence = new DiscordRichPresence();

        presence.details = Translate.replaceText(Translate.disconnect, true, false, false, client);
        presence.largeImageKey = assets.Unknown;
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
