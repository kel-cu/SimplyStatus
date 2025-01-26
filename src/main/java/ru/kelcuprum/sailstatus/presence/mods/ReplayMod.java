package ru.kelcuprum.sailstatus.presence.mods;

import com.jagrosh.discordipc.entities.*;
import ru.kelcuprum.sailstatus.SailStatus;
import ru.kelcuprum.sailstatus.config.Assets;

public class ReplayMod {
    /**
     * Состояние: В главном меню<br>
     * Причины: Игрок находится в главном меню; Игрок в ReplayMod сцене и параметр скрыт
     */
    public ReplayMod() {
        RichPresence.Builder presence = new RichPresence.Builder()
                .setActivityType(ActivityType.Watching)
                .setDetails(SailStatus.localization.getLocalization("mod.replaymod", true))
                .setState(SailStatus.localization.getLocalization("mod.replaymod.state", true))
                .setLargeImage(Assets.getSelected().getIcon("replaymod"));
        SailStatus.updateContentPresenceByConfigs(presence);
        SailStatus.sendPresence(presence.build());
    }
}
