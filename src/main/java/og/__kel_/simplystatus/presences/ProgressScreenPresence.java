package og.__kel_.simplystatus.presences;

import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableTextContent;
import og.__kel_.simplystatus.Translate;
import og.__kel_.simplystatus.client.HotKeys;
import og.__kel_.simplystatus.info.Assets;
import og.__kel_.simplystatus.info.Client;

import java.util.Objects;

public class ProgressScreenPresence {
    public ProgressScreenPresence(DiscordRPC lib, MinecraftClient mc, Translate Translate, Long start_time){
        Client client = new Client();
        Translate.selectedLang();
        DiscordRichPresence MainPresence = new DiscordRichPresence();
        MainPresence.details = Translate.replaceText(MutableText.of(new TranslatableTextContent("status.simplystatus.progressScreen")).getString(), true, false,false, client);
        MainPresence.largeImageText = Translate.replaceText(Translate.maybeGoodPlayer, true, false,false, client);
        MainPresence.state = client.getVersion(mc);
        Assets assets = new Assets(HotKeys.bedrock, false);
        if(Objects.equals(MainPresence.details, "status.simplystatus.progressScreen")) {
            MainPresence.largeImageText = null;
            MainPresence.details = "The game is loading resources...";
        };
        MainPresence.largeImageKey = assets.logo;
        if(HotKeys.showTime){
            MainPresence.startTimestamp = start_time;
        }
        lib.Discord_UpdatePresence(MainPresence);
    }
}
