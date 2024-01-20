package ru.kelcuprum.simplystatus.presence;

import com.jagrosh.discordipc.entities.RichPresence;
import ru.kelcuprum.simplystatus.SimplyStatus;

public class ReplayMod {
    /**
     * Состояние: В главном меню<br>
     * Причины: Игрок находится в главном меню; Игрок в ReplayMod сцене и параметр скрыт
     */
    public ReplayMod(){
        RichPresence.Builder presence = new RichPresence.Builder()
            .setDetails(SimplyStatus.localization.getLocalization("mod.replaymod", true))
            .setState(SimplyStatus.localization.getLocalization("mod.replaymod.state", true))
            .setLargeImage(SimplyStatus.ASSETS.replaymod);
        SimplyStatus.updateContentPresenceByConfigs(presence);
        SimplyStatus.updateDiscordPresence(presence.build());
    }
}
