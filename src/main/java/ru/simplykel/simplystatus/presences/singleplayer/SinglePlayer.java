package ru.simplykel.simplystatus.presences.singleplayer;

import club.minnced.discord.rpc.DiscordRichPresence;
import net.minecraft.client.MinecraftClient;
import ru.simplykel.simplystatus.Client;
import ru.simplykel.simplystatus.config.Localization;
import ru.simplykel.simplystatus.config.ServerConfig;
import ru.simplykel.simplystatus.config.UserConfig;
import ru.simplykel.simplystatus.info.Player;
import ru.simplykel.simplystatus.info.World;
import ru.simplykel.simplystatus.mods.MusicPlayer;

public class SinglePlayer {
    public SinglePlayer(){
        DiscordRichPresence presence = new DiscordRichPresence();
        String world = World.getCodeName();
        if(world.endsWith("overworld")) World.getTime(presence);
        else {
            presence.largeImageKey = World.getAssets();
            presence.largeImageText = World.getName();
        }
        if(UserConfig.VIEW_MUSIC_LISTENER && !new MusicPlayer().paused) {
            presence.smallImageKey = Client.ASSETS.music;
            presence.smallImageText = Localization.getLocalization("mod.music", true);
        } else if(UserConfig.SHOW_AVATAR_PLAYER) {
            presence.smallImageKey = Player.getURLAvatar();
            presence.smallImageText = Player.getName();
        }
        if(UserConfig.SHOW_GAME_STARTED) presence.startTimestamp = Client.STARTED_TIME_GAME;
        presence.details = Player.getState();
        presence.state = Localization.getLocalization("player.world.state", true);
        Client.LIB.Discord_UpdatePresence(presence);
    }
}
