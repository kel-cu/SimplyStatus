package ru.simplykel.simplystatus.presences;

import club.minnced.discord.rpc.DiscordRichPresence;
import ru.simplykel.simplystatus.Client;
import ru.simplykel.simplystatus.config.Localization;
import ru.simplykel.simplystatus.config.UserConfig;
import ru.simplykel.simplystatus.info.Player;

public class Unknown {
    /**
     * Состояние: what?<br>
     * Причины: Ни одна проверка не нашла подходящий вариант
     */
    public Unknown(){
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.state = Localization.getLocalization("unknown", true);
        presence.largeImageKey = Client.ASSETS.unknown;
        Client.LIB.Discord_UpdatePresence(presence);
    }
}
