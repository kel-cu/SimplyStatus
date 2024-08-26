package ru.kelcuprum.simplystatus.presence.multiplayer;

import com.jagrosh.discordipc.entities.*;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.config.Assets;

public class Disconnect {
    /**
     * Состояние: Игрок отключён<br>
     * Причины: Игрок был отключён от сервера без его помощи
     */
    public Disconnect(){
        RichPresence.Builder presence = new RichPresence.Builder().setActivityType(ActivityType.Playing);
        presence.setState(SimplyStatus.localization.getLocalization("server.disconnected", true));
        presence.setLargeImage(Assets.getSelected().getIcon("logo"));
        SimplyStatus.sendPresence(presence.build());
    }
}
