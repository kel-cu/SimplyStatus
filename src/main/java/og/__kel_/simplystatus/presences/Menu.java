package og.__kel_.simplystatus.presences;

import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import net.minecraft.client.MinecraftClient;
import og.__kel_.simplystatus.Main;
import og.__kel_.simplystatus.client.HotKeys;
import og.__kel_.simplystatus.Translate;
import og.__kel_.simplystatus.client.MainClient;
import og.__kel_.simplystatus.info.Client;
import og.__kel_.simplystatus.info.Assets;
import og.__kel_.simplystatus.info.Music;

import java.util.Objects;

public class Menu {
    public Menu(DiscordRPC lib, MinecraftClient mc, Translate Translate, Long start_time){
        Client client = new Client();
        Translate.selectedLang();
        DiscordRichPresence MainPresence = new DiscordRichPresence();
        MainPresence.details = Translate.replaceText(Translate.mainMenu, true, false,false, client);
        MainPresence.largeImageText = Translate.replaceText(Translate.maybeGoodPlayer, true, false,false, client);
        MainPresence.state = Translate.replaceText(Translate.mainMenu_state, true, false,false, client);
        Assets assets = new Assets(Main.useCustomAssets, Main.bedrock, false);
        if(Objects.equals(MainPresence.details, "status.simplystatus.text_MainMenu")) {
            MainPresence.state = null;
            MainPresence.largeImageText = null;
            MainPresence.details = "Game loading...";
        };
        MainPresence.largeImageKey = assets.logo;
        if(Main.showTime){
            MainPresence.startTimestamp = start_time;
        }
        if(Main.showAvatar){
            client.avatar(mc, MainPresence);
        }
        if(Main.viewMusicListening && MainClient.musicPlayer){

            Music music = new Music();
            if(!music.isPaused()) {
//                MainPresence.smallImageKey = assets.music;
//                if (music.isAuthorEnable()) {
//                    MainPresence.smallImageText = music.getAuthor() + " - " + music.getTrack();
//                } else {
//                    MainPresence.smallImageText = music.getTrack();
//                }
//                music.getManager().
                long startListening = (System.currentTimeMillis() / 1000) - (music.getManager().getCurrentTrack().getPosition() / 1000);
                MainPresence.startTimestamp = startListening;
                if(!music.getManager().getCurrentTrack().getInfo().isStream()){
                    MainPresence.endTimestamp = startListening + (music.getManager().getCurrentTrack().getDuration() / 1000);
                }
                MainPresence.state = Translate.replaceText(Translate.mainMenu_state_music, true, false,false, client);
            }
        }

        lib.Discord_UpdatePresence(MainPresence);
    }
}
