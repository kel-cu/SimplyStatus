package ru.kelcuprum.simplystatus.presence.singleplayer;

import ru.kelcuprum.simplystatus.info.DiscordRichPresence;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.info.Player;
import ru.kelcuprum.simplystatus.info.World;
import ru.kelcuprum.simplystatus.localization.Localization;

public class SinglePlayer {
    public SinglePlayer(){
        DiscordRichPresence presence = new DiscordRichPresence();
        SimplyStatus.updateContentPresenceByConfigs(presence);
        presence.details = Player.getState();
        presence.state = Localization.getLocalization("player.world.state", true);
        String world = World.getCodeName();
        if(SimplyStatus.userConfig.getBoolean("ENABLE_WORLD", true)){
            if(world.endsWith("overworld") && SimplyStatus.userConfig.getBoolean("ENABLE_TIME_CYCLE", true)) World.getTime(presence);
            else {
                presence.largeImageKey = World.getAssets();
                presence.largeImageText = World.getName();
            }
        } else presence.largeImageKey = SimplyStatus.ASSETS.logo;
        SimplyStatus.updateDiscordPresence(presence);
    }
}
