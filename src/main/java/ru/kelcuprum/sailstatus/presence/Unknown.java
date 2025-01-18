package ru.kelcuprum.sailstatus.presence;

import com.jagrosh.discordipc.entities.*;
import ru.kelcuprum.sailstatus.SailStatus;
import ru.kelcuprum.sailstatus.config.Assets;

public class Unknown {
    /**
     * Состояние: what?<br>
     * Причины: Ни одна проверка не нашла подходящий вариант
     */
    public Unknown(){
        RichPresence.Builder presence = new RichPresence.Builder().setActivityType(ActivityType.Playing);
        presence.setState(SailStatus.localization.getLocalization("unknown", true));
        presence.setLargeImage(Assets.getSelected().getIcon("unknown"));
        SailStatus.sendPresence(presence.build());
    }
}
