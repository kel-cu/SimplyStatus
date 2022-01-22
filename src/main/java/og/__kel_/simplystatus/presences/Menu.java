package og.__kel_.simplystatus.presences;

import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import net.minecraft.client.MinecraftClient;
import og.__kel_.simplystatus.SimplyStatusMain;
import og.__kel_.simplystatus.SimplyStatusTranslate;
import og.__kel_.simplystatus.info.Game;

public class Menu {
    public Menu(DiscordRPC lib, MinecraftClient mc, SimplyStatusTranslate Translate, Long start_time){
        Game game = new Game();
        Translate.selectedLang();
        DiscordRichPresence MainPresence = new DiscordRichPresence();
        MainPresence.startTimestamp = start_time;
        MainPresence.details = Translate.text_MainMenu.replace("%username%", SimplyStatusMain.DiscordName);
        MainPresence.state = game.getVersion(mc);
        MainPresence.largeImageKey = "logo";
        MainPresence.largeImageText = Translate.text_goodPlayer.replace("%username%", SimplyStatusMain.DiscordName);;
        lib.Discord_UpdatePresence(MainPresence);
    }
}
