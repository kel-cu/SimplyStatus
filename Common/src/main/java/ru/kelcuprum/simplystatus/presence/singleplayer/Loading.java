package ru.kelcuprum.simplystatus.presence.singleplayer;

import com.jagrosh.discordipc.entities.RichPresence;
import ru.kelcuprum.simplystatus.SimplyStatus;

public class Loading {
    /**
     * Состояние: <br>
     * Причины:
     */
    public Loading(){
        RichPresence.Builder presence = new RichPresence.Builder();
        presence.setState(SimplyStatus.localization.getLocalization("world.loading", true));
        presence.setSmallImage(SimplyStatus.ASSETS.logo);
        SimplyStatus.updateDiscordPresence(presence.build());
    }
}
