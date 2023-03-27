package ru.simplykel.simplystatus.presences.multiplayer;

import club.minnced.discord.rpc.DiscordRichPresence;
import ru.simplykel.simplystatus.Client;
import ru.simplykel.simplystatus.config.Localization;

public class Connect {
    /**
     * Состояние: Игрок подключается<br>
     * Причины: Идёт подключение к серверу
     */
    public Connect(){
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.state = Localization.getLocalization("server.connecting", true);
        presence.smallImageKey = Client.ASSETS.unknown_world;
        Client.LIB.Discord_UpdatePresence(presence);
    }
}
