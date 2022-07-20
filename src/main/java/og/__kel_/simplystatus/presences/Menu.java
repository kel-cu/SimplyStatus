package og.__kel_.simplystatus.presences;

import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import net.minecraft.client.MinecraftClient;
import og.__kel_.simplystatus.client.HotKeys;
import og.__kel_.simplystatus.Translate;
import og.__kel_.simplystatus.info.Game;
import og.__kel_.simplystatus.info.assets_rpc;

import java.util.Objects;

public class Menu {
    public Menu(DiscordRPC lib, MinecraftClient mc, Translate Translate, Long start_time){
        Game game = new Game();
        Translate.selectedLang();
        DiscordRichPresence MainPresence = new DiscordRichPresence();
        MainPresence.details = Translate.replaceText(Translate.mainMenu, true, false,false, game);
        MainPresence.largeImageText = Translate.replaceText(Translate.maybeGoodPlayer, true, false,false, game);
        MainPresence.state = game.getVersion(mc);
        assets_rpc assets = new assets_rpc(HotKeys.bedrock, false);
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
