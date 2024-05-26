package ru.kelcuprum.simplystatus.presence.multiplayer;

import com.jagrosh.discordipc.entities.RichPresence;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.config.Assets;

public class Connect {
    /**
     * Состояние: Игрок подключается<br>
     * Причины: Идёт подключение к серверу
     */
    public Connect(){
        RichPresence.Builder presence = new RichPresence.Builder();
        presence.setState(SimplyStatus.localization.getLocalization("server.connecting", true));
        presence.setLargeImage(Assets.getSelected().getIcon("unknown_world"));
        SimplyStatus.updateDiscordPresence(presence.build());
    }
}
