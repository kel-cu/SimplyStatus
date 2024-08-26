package ru.kelcuprum.simplystatus.presence.singleplayer;

import com.jagrosh.discordipc.entities.*;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.config.Assets;

public class Loading {
    /**
     * Состояние: <br>
     * Причины:
     */
    public Loading(){
        RichPresence.Builder presence = new RichPresence.Builder().setActivityType(ActivityType.Playing);
        presence.setState(SimplyStatus.localization.getLocalization("world.loading", true));
        presence.setLargeImage(Assets.getSelected().getIcon("logo"));
        SimplyStatus.sendPresence(presence.build());
    }
}
