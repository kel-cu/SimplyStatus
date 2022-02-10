package og.__kel_.simplystatus.presences;

import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.TranslatableText;
import og.__kel_.simplystatus.SimplyStatusTranslate;
import og.__kel_.simplystatus.info.Game;

import java.lang.reflect.Array;

public class UnknownScene {
    public UnknownScene(DiscordRPC lib, MinecraftClient mc, SimplyStatusTranslate Translate, Long start_time){
        Translate.selectedLang();
        DiscordRichPresence MainPresence = new DiscordRichPresence();
        MainPresence.startTimestamp = start_time;
        MainPresence.state = Game.getVersion(mc);
        MainPresence.largeImageKey = "logo";
        boolean IsThereAReplayMod = FabricLoader.getInstance().getModContainer("replaymod").isEmpty();
        if(!IsThereAReplayMod){
            MainPresence.details = new TranslatableText("status.simplystatus.replaymod").getString();
        }
        lib.Discord_UpdatePresence(MainPresence);
    }
}
