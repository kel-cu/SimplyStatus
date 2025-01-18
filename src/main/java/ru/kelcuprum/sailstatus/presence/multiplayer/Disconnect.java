package ru.kelcuprum.sailstatus.presence.multiplayer;

import com.jagrosh.discordipc.entities.*;
import ru.kelcuprum.sailstatus.SailStatus;
import ru.kelcuprum.sailstatus.config.Assets;

public class Disconnect {
    /**
     * Состояние: Игрок отключён<br>
     * Причины: Игрок был отключён от сервера без его помощи
     */
    public Disconnect(){
        RichPresence.Builder presence = new RichPresence.Builder().setActivityType(ActivityType.Playing);
        presence.setState(SailStatus.localization.getLocalization("server.disconnected", true));
        presence.setLargeImage(Assets.getSelected().getIcon("logo"));
        SailStatus.sendPresence(presence.build());
    }
}
