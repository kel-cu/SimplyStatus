package ru.simplykel.simplystatus.presences.multiplayer;

import club.minnced.discord.rpc.DiscordRichPresence;
import ru.simplykel.simplystatus.Client;
import ru.simplykel.simplystatus.config.Localization;

public class Disconnect {
    /**
     * Состояние: Игрок отключён<br>
     * Причины: Игрок был отключён от сервера без его помощи
     */
    public Disconnect(){
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.state = Localization.getLocalization("server.disconnected", true);
        presence.smallImageKey = Client.ASSETS.logo;
        Client.updateDiscordPresence(presence);
    }
}
