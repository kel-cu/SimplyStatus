package og.__kel_.simplystatus.presences.single;

import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableTextContent;
import og.__kel_.simplystatus.Translate;
import og.__kel_.simplystatus.client.HotKeys;
import og.__kel_.simplystatus.client.MainClient;
import og.__kel_.simplystatus.info.Assets;
import og.__kel_.simplystatus.info.Client;
import og.__kel_.simplystatus.info.Music;

import java.util.Objects;

public class ProgressScreenPresence {
    public ProgressScreenPresence(DiscordRPC lib, MinecraftClient mc, Translate Translate, Long start_time){
        Client client = new Client();
        DiscordRichPresence presence = new DiscordRichPresence();
        Assets assets = new Assets(HotKeys.bedrock, false);

        presence.details = Translate.replaceText(Translate.worldLoading, true, false, false, client);
        presence.largeImageKey = assets.Unknown;
        if(HotKeys.showTime){
            presence.startTimestamp = start_time;
        }
        lib.Discord_UpdatePresence(presence);
    }
}
