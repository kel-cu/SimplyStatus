package ru.kelcuprum.simplystatus.presence.multiplayer;

import com.jagrosh.discordipc.entities.RichPresence;
import net.minecraft.client.Minecraft;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.info.Player;
import ru.kelcuprum.simplystatus.info.World;

import java.util.Objects;

public class MultiPlayer {
    public MultiPlayer(){
        RichPresence.Builder presence = new RichPresence.Builder();
        SimplyStatus.serverConfig = new Config(String.format("config/SimplyStatus/servers/%s.json", Objects.requireNonNull(Minecraft.getInstance().getCurrentServer()).ip.replace(":", "_")));
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
        } else presence.setLargeImage(SimplyStatus.ASSETS.logo);
        SimplyStatus.updateDiscordPresence(presence.build());
    }
}
