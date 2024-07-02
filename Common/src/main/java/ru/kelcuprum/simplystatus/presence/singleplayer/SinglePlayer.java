package ru.kelcuprum.simplystatus.presence.singleplayer;

import com.jagrosh.discordipc.entities.RichPresence;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.config.Assets;
import ru.kelcuprum.simplystatus.info.SimplyPlayer;
import ru.kelcuprum.simplystatus.info.World;

public class SinglePlayer {
    public SinglePlayer(){
        RichPresence.Builder presence = new RichPresence.Builder();
        SimplyStatus.updateContentPresenceByConfigs(presence);
        presence.setDetails(SimplyPlayer.getState());
        presence.setState(SimplyStatus.localization.getLocalization("player.world.state", true));
        if(SimplyStatus.userConfig.getBoolean("ENABLE_WORLD", true)){
            if(World.getCodeName().endsWith("overworld") && SimplyStatus.userConfig.getBoolean("ENABLE_TIME_CYCLE", true)) World.getTime(presence);
            else {
                presence.setLargeImage(World.getAssets(), World.getName());
            }
        } else presence.setLargeImage(Assets.getSelected().getIcon("logo"));
        SimplyStatus.updateDiscordPresence(presence.build());
    }
}
