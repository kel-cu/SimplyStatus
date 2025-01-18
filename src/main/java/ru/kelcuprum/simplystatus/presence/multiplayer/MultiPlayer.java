package ru.kelcuprum.simplystatus.presence.multiplayer;

import com.jagrosh.discordipc.entities.*;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.info.World;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.config.Assets;
import ru.kelcuprum.simplystatus.info.*;

public class MultiPlayer {
    public MultiPlayer(){
        RichPresence.Builder presence = new RichPresence.Builder().setActivityType(ActivityType.Playing);
        SimplyStatus.serverConfig = new Config(String.format("config/SimplyStatus/servers/%s.json", AlinLib.MINECRAFT.getCurrentServer().ip.replace(":", "_")));
        SimplyStatus.serverConfig.load();
        SimplyStatus.updateContentPresenceByConfigs(presence, true);
        presence.setDetails(PresencePlayer.getState());
        presence.setState(SimplyStatus.localization.getLocalization("player.world.state", true));
        if(SimplyStatus.userConfig.getBoolean("ENABLE_WORLD", true)){
            if(World.getCodeName().endsWith("overworld") && SimplyStatus.userConfig.getBoolean("ENABLE_TIME_CYCLE", true)) PresenceWorld.getTime(presence);
            else {
                presence.setLargeImage(PresenceWorld.getAssets(), World.getName());
            }
        } else presence.setLargeImage(Assets.getSelected().getIcon("logo"));
        SimplyStatus.sendPresence(presence.build());
    }
}
