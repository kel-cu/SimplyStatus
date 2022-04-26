package og.__kel_.simplystatus.presences;

import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.TranslatableText;
import og.__kel_.simplystatus.SimplyStatusClient;
import og.__kel_.simplystatus.SimplyStatusTranslate;
import og.__kel_.simplystatus.info.Game;
import og.__kel_.simplystatus.info.assets_rpc;

public class UnknownScene {
    public UnknownScene(DiscordRPC lib, MinecraftClient mc, SimplyStatusTranslate Translate, Long start_time){
        Game game = new Game();
        assets_rpc assets = new assets_rpc(SimplyStatusClient.Bedrock, SimplyStatusClient.CringeIcons);
        Translate.selectedLang();
        DiscordRichPresence MainPresence = new DiscordRichPresence();
        MainPresence.startTimestamp = start_time;
        MainPresence.state = game.getVersion(mc);
        MainPresence.largeImageKey = assets.Unknown;
        boolean IsThereAReplayMod = FabricLoader.getInstance().getModContainer("replaymod").isEmpty();
        if(!IsThereAReplayMod){
            MainPresence.smallImageKey = assets.replay;
            MainPresence.smallImageText = new TranslatableText("status.simplystatus.replaymod").getString();
        }
        lib.Discord_UpdatePresence(MainPresence);
    }
}
