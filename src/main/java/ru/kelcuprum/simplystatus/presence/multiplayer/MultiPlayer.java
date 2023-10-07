package ru.kelcuprum.simplystatus.presence.multiplayer;

import club.minnced.discord.rpc.DiscordRichPresence;
import net.minecraft.client.Minecraft;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.config.Config;
import ru.kelcuprum.simplystatus.info.Player;
import ru.kelcuprum.simplystatus.info.World;
import ru.kelcuprum.simplystatus.localization.Localization;

public class MultiPlayer {
    public MultiPlayer(){
        DiscordRichPresence presence = new DiscordRichPresence();
        SimplyStatus.serverConfig = new Config(String.format("config/SimplyStatus/servers/%s.json", Minecraft.getInstance().getCurrentServer().ip.replace(":", "_")));
        SimplyStatus.serverConfig.load();
        SimplyStatus.updateContentPresenceByConfigs(presence, true);
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
