package ru.kelcuprum.sailstatus.presence.multiplayer;

import com.jagrosh.discordipc.entities.*;
import ru.kelcuprum.sailstatus.SailStatus;
import ru.kelcuprum.sailstatus.config.Assets;

public class Connect {
    /**
     * Состояние: Игрок подключается<br>
     * Причины: Идёт подключение к серверу
     */
    public Connect(){
        RichPresence.Builder presence = new RichPresence.Builder().setActivityType(ActivityType.Playing);
        presence.setState(SailStatus.localization.getLocalization("server.connecting", true));
        presence.setLargeImage(Assets.getSelected().getIcon("unknown_world"));
        SailStatus.sendPresence(presence.build());
    }
}
