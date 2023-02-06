package ru.simplykel.simplystatus.presences;

import club.minnced.discord.rpc.DiscordRichPresence;
import ru.simplykel.simplystatus.Client;
import ru.simplykel.simplystatus.Main;
import ru.simplykel.simplystatus.config.Localization;
import ru.simplykel.simplystatus.config.ModConfig;
import ru.simplykel.simplystatus.config.UserConfig;
import ru.simplykel.simplystatus.info.Player;

public class MainMenu {
    /**
     * Состояние: В главном меню<br>
     * Причины: Игрок находится в главном меню; Игрок в ReplayMod сцене и параметр скрыт
     */
    public MainMenu(){
        DiscordRichPresence presence = new DiscordRichPresence();
        if(Localization.getLocalization("mainmenu", false).equals("simplystatus.presence.mainmenu")){
            presence.details = "Resources unready...";
        } else  {
            presence.details = Localization.getLocalization("mainmenu", true);
            if(Localization.getLocalization("mainmenu.state", true).equals("simplystatus.presence.mainmenu.state"))  presence.details = "m,kjn ";
            else presence.state = Localization.getLocalization("mainmenu.state", true);
        }
        presence.largeImageKey = Client.ASSETS.logo;
        if(UserConfig.SHOW_GAME_STARTED) presence.startTimestamp = Client.STARTED_TIME_GAME;
        if(UserConfig.SHOW_AVATAR_PLAYER) {
            presence.smallImageKey = Player.getURLAvatar();
            presence.smallImageText = Player.getName();
        }
        Client.LIB.Discord_UpdatePresence(presence);
    }
}
