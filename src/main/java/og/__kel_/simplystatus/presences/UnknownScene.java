package og.__kel_.simplystatus.presences;

import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableTextContent;
import og.__kel_.simplystatus.Client;
import og.__kel_.simplystatus.Main;
import og.__kel_.simplystatus.Translate;
import og.__kel_.simplystatus.info.Game;
import og.__kel_.simplystatus.info.assets_rpc;

public class UnknownScene {
    public UnknownScene(DiscordRPC lib, MinecraftClient mc, Translate Translate, Long start_time, NullPointerException err){
        Game game = new Game();
        assets_rpc assets = new assets_rpc(Client.Bedrock, Client.CringeIcons);
        Translate.selectedLang();
        DiscordRichPresence MainPresence = new DiscordRichPresence();
        if(Client.showTime){
            MainPresence.startTimestamp = start_time;
        }
        MainPresence.state = game.getVersion(mc);
        MainPresence.largeImageKey = assets.Unknown;
        boolean IsThereAReplayMod = FabricLoader.getInstance().getModContainer("replaymod").isEmpty();
        if(!IsThereAReplayMod){
            MainPresence.smallImageKey = assets.replay;
            MainPresence.smallImageText = MutableText.of(new TranslatableTextContent("status.simplystatus.replaymod")).getString();
        } else {
            Main.log.error("Ooh, there's been an error\n"+err.toString());
        }
        lib.Discord_UpdatePresence(MainPresence);
    }
}
