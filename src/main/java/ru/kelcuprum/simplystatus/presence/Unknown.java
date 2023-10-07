package ru.kelcuprum.simplystatus.presence;

import club.minnced.discord.rpc.DiscordRichPresence;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.localization.Localization;

public class Unknown {
    /**
     * Состояние: what?<br>
     * Причины: Ни одна проверка не нашла подходящий вариант
     */
    public Unknown(){
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.state = Localization.getLocalization("unknown", true);
        presence.largeImageKey = SimplyStatus.ASSETS.unknown;
        SimplyStatus.updateDiscordPresence(presence);
    }
}
