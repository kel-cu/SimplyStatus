package ru.kelcuprum.simplystatus.presence.singleplayer;

import club.minnced.discord.rpc.DiscordRichPresence;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.localization.Localization;

public class Loading {
    /**
     * Состояние: <br>
     * Причины:
     */
    public Loading(){
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.state = Localization.getLocalization("world.loading", true);
        presence.smallImageKey = SimplyStatus.ASSETS.logo;
        SimplyStatus.updateDiscordPresence(presence);
    }
}
