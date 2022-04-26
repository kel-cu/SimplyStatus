package og.__kel_.simplystatus.presences;

import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import net.minecraft.client.MinecraftClient;
import og.__kel_.simplystatus.SimplyStatusClient;
import og.__kel_.simplystatus.SimplyStatusMain;
import og.__kel_.simplystatus.SimplyStatusTranslate;
import og.__kel_.simplystatus.info.Game;
import og.__kel_.simplystatus.info.assets_rpc;

public class Menu {
    public Menu(DiscordRPC lib, MinecraftClient mc, SimplyStatusTranslate Translate, Long start_time){
        Game game = new Game();
        Translate.selectedLang();
        DiscordRichPresence MainPresence = new DiscordRichPresence();
        MainPresence.startTimestamp = start_time;
        if(SimplyStatusClient.ViewUsername){
            MainPresence.details = Translate.text_MainMenu.replace("%username%", mc.getSession().getUsername());
        } else {
            MainPresence.details = Translate.text_MainMenu.replace("%username%", SimplyStatusMain.player.username);
        }
        MainPresence.state = game.getVersion(mc);
        assets_rpc assets = new assets_rpc(SimplyStatusClient.Bedrock, SimplyStatusClient.CringeIcons);
        MainPresence.largeImageKey = assets.logo;
        MainPresence.largeImageText = Translate.text_goodPlayer.replace("%username%", SimplyStatusMain.DiscordName);;
        lib.Discord_UpdatePresence(MainPresence);
    }
}
