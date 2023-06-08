package ru.simplykel.simplystatus.presences;

import club.minnced.discord.rpc.DiscordRichPresence;
import ru.simplykel.simplystatus.Client;
import ru.simplykel.simplystatus.config.Localization;

public class Unknown {
    /**
     * Состояние: what?<br>
     * Причины: Ни одна проверка не нашла подходящий вариант
     */
    public Unknown(){
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.state = Localization.getLocalization("unknown", true);
        presence.largeImageKey = Client.ASSETS.unknown;
        Client.updateDiscordPresence(presence);
    }
}
