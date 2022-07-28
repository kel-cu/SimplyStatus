package og.__kel_.simplystatus.presences;

import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import net.minecraft.client.MinecraftClient;
import og.__kel_.simplystatus.client.HotKeys;
import og.__kel_.simplystatus.Translate;
import og.__kel_.simplystatus.info.Client;
import og.__kel_.simplystatus.info.Assets;

import java.util.Objects;

public class Menu {
    public Menu(DiscordRPC lib, MinecraftClient mc, Translate Translate, Long start_time){
        Client client = new Client();
        Translate.selectedLang();
        DiscordRichPresence MainPresence = new DiscordRichPresence();
        MainPresence.details = Translate.replaceText(Translate.mainMenu, true, false,false, client);
        MainPresence.largeImageText = Translate.replaceText(Translate.maybeGoodPlayer, true, false,false, client);
        MainPresence.state = client.getVersion(mc);
        Assets assets = new Assets(HotKeys.bedrock, false);
        if(Objects.equals(MainPresence.details, "status.simplystatus.text_MainMenu")) {
            MainPresence.largeImageText = null;
            MainPresence.details = "Game loading...";
        };
        MainPresence.largeImageKey = assets.logo;
        if(HotKeys.showTime){
            MainPresence.startTimestamp = start_time;
        }
        lib.Discord_UpdatePresence(MainPresence);
    }
}
