package ru.kelcuprum.sailstatus.presence.singleplayer;

import com.jagrosh.discordipc.entities.*;
import ru.kelcuprum.sailstatus.SailStatus;
import ru.kelcuprum.sailstatus.config.Assets;

public class Loading {
    /**
     * Состояние: <br>
     * Причины:
     */
    public Loading(){
        RichPresence.Builder presence = new RichPresence.Builder().setActivityType(ActivityType.Playing);
        presence.setState(SailStatus.localization.getLocalization("world.loading", true));
        presence.setLargeImage(Assets.getSelected().getIcon("logo"));
        SailStatus.sendPresence(presence.build());
    }
}
