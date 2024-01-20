package ru.kelcuprum.simplystatus.presence;

import com.jagrosh.discordipc.entities.RichPresence;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.localization.StarScript;

public class Unknown {
    /**
     * Состояние: what?<br>
     * Причины: Ни одна проверка не нашла подходящий вариант
     */
    public Unknown(){
        RichPresence.Builder presence = new RichPresence.Builder();
        presence.setState(SimplyStatus.localization.getLocalization("unknown", true));
        presence.setLargeImage(SimplyStatus.ASSETS.unknown);
        SimplyStatus.updateDiscordPresence(presence.build());
    }
}
