package ru.simplykel.simplystatus.presences.singleplayer;

import club.minnced.discord.rpc.DiscordRichPresence;
import ru.simplykel.simplystatus.Client;
import ru.simplykel.simplystatus.config.Localization;

public class Loading {
    /**
     * Состояние: <br>
     * Причины:
     */
    public Loading(){
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.state = Localization.getLocalization("world.loading", true);
        presence.smallImageKey = Client.ASSETS.logo;
        Client.LIB.Discord_UpdatePresence(presence);
    }
}
