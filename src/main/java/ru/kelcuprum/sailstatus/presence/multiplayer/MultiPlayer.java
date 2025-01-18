package ru.kelcuprum.sailstatus.presence.multiplayer;

import com.jagrosh.discordipc.entities.*;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.info.World;
import ru.kelcuprum.sailstatus.SailStatus;
import ru.kelcuprum.sailstatus.config.Assets;
import ru.kelcuprum.sailstatus.info.*;

public class MultiPlayer {
    public MultiPlayer(){
        RichPresence.Builder presence = new RichPresence.Builder().setActivityType(ActivityType.Playing);
        SailStatus.serverConfig = new Config(String.format("config/SimplyStatus/servers/%s.json", AlinLib.MINECRAFT.getCurrentServer().ip.replace(":", "_")));
        SailStatus.serverConfig.load();
        SailStatus.updateContentPresenceByConfigs(presence, true);
        presence.setDetails(PresencePlayer.getState());
        presence.setState(SailStatus.localization.getLocalization("player.world.state", true));
        if(SailStatus.userConfig.getBoolean("ENABLE_WORLD", true)){
            if(World.getCodeName().endsWith("overworld") && SailStatus.userConfig.getBoolean("ENABLE_TIME_CYCLE", true)) PresenceWorld.getTime(presence);
            else {
                presence.setLargeImage(PresenceWorld.getAssets(), World.getName());
            }
        } else presence.setLargeImage(Assets.getSelected().getIcon("logo"));
        SailStatus.sendPresence(presence.build());
    }
}
