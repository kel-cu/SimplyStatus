package ru.simplykel.simplystatus.presences.multiplayer;

import club.minnced.discord.rpc.DiscordRichPresence;
import com.mojang.authlib.yggdrasil.response.User;
import org.apache.logging.log4j.core.jmx.Server;
import ru.simplykel.simplystatus.Client;
import ru.simplykel.simplystatus.config.Localization;
import ru.simplykel.simplystatus.config.ServerConfig;
import ru.simplykel.simplystatus.config.UserConfig;
import ru.simplykel.simplystatus.info.Player;
import ru.simplykel.simplystatus.info.World;

public class MultiPlayer {
    public MultiPlayer(){
        DiscordRichPresence presence = new DiscordRichPresence();
        ServerConfig.load();
        String world = World.getCodeName();
        if(world.endsWith("overworld")) World.getTime(presence);
        else {
            presence.largeImageKey = World.getAssets();
            presence.largeImageText = World.getName();
        }
        if(UserConfig.SHOW_AVATAR_PLAYER) {
            presence.smallImageKey = Player.getURLAvatar();
            presence.smallImageText = Player.getName();
        }
        if(ServerConfig.SHOW_ICON && (ServerConfig.ICON_URL.length() != 0)){
            presence.smallImageKey = ServerConfig.ICON_URL;
            presence.smallImageText = Localization.getParsedText("%address%");
        }
        if(UserConfig.SHOW_GAME_STARTED) presence.startTimestamp = Client.STARTED_TIME_GAME;
        presence.details = Player.getState();
        presence.state = Localization.getLocalization("player.world.state", true);
        Client.LIB.Discord_UpdatePresence(presence);
    }
}
