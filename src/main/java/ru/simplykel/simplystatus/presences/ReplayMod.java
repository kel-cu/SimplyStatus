package ru.simplykel.simplystatus.presences;

import club.minnced.discord.rpc.DiscordRichPresence;
import ru.simplykel.simplystatus.Client;
import ru.simplykel.simplystatus.config.Localization;
import ru.simplykel.simplystatus.config.UserConfig;
import ru.simplykel.simplystatus.info.Player;
import ru.simplykel.simplystatus.mods.MusicPlayer;

public class ReplayMod {
    /**
     * Состояние: В главном меню<br>
     * Причины: Игрок находится в главном меню; Игрок в ReplayMod сцене и параметр скрыт
     */
    public ReplayMod(){
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.details = Localization.getLocalization("mod.replaymod", true);
        presence.state = Localization.getLocalization("mod.replaymod.state", true);
        presence.largeImageKey = Client.ASSETS.replaymod;
        if(UserConfig.SHOW_GAME_STARTED) presence.startTimestamp = Client.STARTED_TIME_GAME;
        if(UserConfig.VIEW_MUSIC_LISTENER && !new MusicPlayer().paused) {
            presence.smallImageKey = Client.ASSETS.music;
            presence.smallImageText = Localization.getLocalization("mod.music", true);
        } else if(UserConfig.SHOW_AVATAR_PLAYER) {
            presence.smallImageKey = Player.getURLAvatar();
            presence.smallImageText = Player.getName();
        }
        Client.LIB.Discord_UpdatePresence(presence);
    }
}
