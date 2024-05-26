package ru.kelcuprum.simplystatus.presence.multiplayer;

import com.jagrosh.discordipc.entities.RichPresence;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.config.Assets;
import ru.kelcuprum.simplystatus.info.Player;
import ru.kelcuprum.simplystatus.info.World;
import static ru.kelcuprum.simplystatus.SimplyStatus.MINECRAFT;

public class MultiPlayer {
    public MultiPlayer(){
        RichPresence.Builder presence = new RichPresence.Builder();
        SimplyStatus.serverConfig = new Config(String.format("config/SimplyStatus/servers/%s.json", MINECRAFT.getCurrentServer().ip.replace(":", "_")));
        SimplyStatus.serverConfig.load();
        SimplyStatus.updateContentPresenceByConfigs(presence, true);
        presence.setDetails(Player.getState());
        presence.setState(SimplyStatus.localization.getLocalization("player.world.state", true));
        String world = World.getCodeName();
        if(SimplyStatus.userConfig.getBoolean("ENABLE_WORLD", true)){
            if(world.endsWith("overworld") && SimplyStatus.userConfig.getBoolean("ENABLE_TIME_CYCLE", true)) World.getTime(presence);
            else {
                presence.setLargeImage(World.getAssets(), World.getName());
            }
        } else presence.setLargeImage(Assets.getSelected().getIcon("logo"));
        SimplyStatus.updateDiscordPresence(presence.build());
    }
}
