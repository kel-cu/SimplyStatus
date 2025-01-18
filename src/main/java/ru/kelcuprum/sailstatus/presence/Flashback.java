package ru.kelcuprum.sailstatus.presence;

import com.jagrosh.discordipc.entities.ActivityType;
import com.jagrosh.discordipc.entities.RichPresence;
import ru.kelcuprum.sailstatus.SailStatus;
import ru.kelcuprum.sailstatus.config.Assets;

public class Flashback {
    /**
     * Состояние: В главном меню<br>
     * Причины: Игрок находится в главном меню; Игрок в ReplayMod сцене и параметр скрыт
     */
    public Flashback() {
        RichPresence.Builder presence = new RichPresence.Builder()
                .setActivityType(ActivityType.Watching)
                .setDetails(SailStatus.localization.getLocalization("mod.flashback", true))
                .setState(SailStatus.localization.getLocalization("mod.flashback.state", true))
                .setLargeImage(Assets.getSelected().getIcon("flashback"));
        SailStatus.updateContentPresenceByConfigs(presence);
        SailStatus.sendPresence(presence.build());
    }
}
