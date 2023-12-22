package ru.kelcuprum.simplystatus.presence.multiplayer;

import ru.kelcuprum.simplystatus.info.DiscordRichPresence;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.localization.Localization;

public class Connect {
    /**
     * Состояние: Игрок подключается<br>
     * Причины: Идёт подключение к серверу
     */
    public Connect(){
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.state = Localization.getLocalization("server.connecting", true);
        presence.smallImageKey = SimplyStatus.ASSETS.unknown_world;
        SimplyStatus.updateDiscordPresence(presence);
    }
}
