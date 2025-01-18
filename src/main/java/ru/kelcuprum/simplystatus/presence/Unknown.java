package ru.kelcuprum.simplystatus.presence;

import com.jagrosh.discordipc.entities.*;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.config.Assets;

public class Unknown {
    /**
     * Состояние: what?<br>
     * Причины: Ни одна проверка не нашла подходящий вариант
     */
    public Unknown(){
        RichPresence.Builder presence = new RichPresence.Builder().setActivityType(ActivityType.Playing);
        presence.setState(SimplyStatus.localization.getLocalization("unknown", true));
        presence.setLargeImage(Assets.getSelected().getIcon("unknown"));
        SimplyStatus.sendPresence(presence.build());
    }
}
