package ru.kelcuprum.simplystatus.presence;

import club.minnced.discord.rpc.DiscordRichPresence;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.localization.Localization;

public class ReplayMod {
    /**
     * Состояние: В главном меню<br>
     * Причины: Игрок находится в главном меню; Игрок в ReplayMod сцене и параметр скрыт
     */
    public ReplayMod(){
        ru.kelcuprum.simplystatus.mods.ReplayMod.update();
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.details = Localization.getLocalization("mod.replaymod", true);
        presence.state = Localization.getLocalization("mod.replaymod.state", true);
        presence.largeImageKey = SimplyStatus.ASSETS.replaymod;
        SimplyStatus.updateContentPresenceByConfigs(presence);
        SimplyStatus.updateDiscordPresence(presence);
    }
}
