package ru.kelcuprum.simplystatus.presence.multiplayer;

import ru.kelcuprum.simplystatus.info.DiscordRichPresence;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.localization.Localization;

public class Disconnect {
    /**
     * Состояние: Игрок отключён<br>
     * Причины: Игрок был отключён от сервера без его помощи
     */
    public Disconnect(){
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.state = Localization.getLocalization("server.disconnected", true);
        presence.smallImageKey = SimplyStatus.ASSETS.logo;
        SimplyStatus.updateDiscordPresence(presence);
    }
}
