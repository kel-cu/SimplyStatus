package ru.simplykel.simplystatus.info;

import club.minnced.discord.rpc.DiscordRichPresence;
import net.minecraft.client.MinecraftClient;
import ru.simplykel.simplystatus.Client;
import ru.simplykel.simplystatus.config.Localization;

public class World {
    public static void getTime(DiscordRichPresence presence){
        MinecraftClient CLIENT = MinecraftClient.getInstance();
        long currentTime = CLIENT.world.getLunarTime() % 24000;
        if (currentTime < 6000 && currentTime > 0) {
            presence.largeImageKey = Client.ASSETS.morning;
            presence.largeImageText = Localization.getLocalization("time.morning", true);
        } else if (currentTime < 12000 && currentTime > 6000) {
            presence.largeImageKey = Client.ASSETS.day;
            presence.largeImageText = Localization.getLocalization("time.day", true);
        } else if (currentTime < 16500 && currentTime > 12000) {
            presence.largeImageKey = Client.ASSETS.evening;
            presence.largeImageText = Localization.getLocalization("time.evening", true);
        } else if (currentTime < 24000 && currentTime > 16500) {
            presence.largeImageKey = Client.ASSETS.night;
            presence.largeImageText = Localization.getLocalization("time.night", true);
        } else {
            presence.largeImageKey = Client.ASSETS.world;
            presence.largeImageText = Localization.getLocalization("world.overworld", true);
            presence.smallImageKey = null;
            presence.smallImageText = null;
        }
    }
    public static String getCodeName(){
        MinecraftClient CLIENT = MinecraftClient.getInstance();
        return CLIENT.player.getWorld().getRegistryKey().getValue().toString();
    }
    public static String getAssets(){
        MinecraftClient CLIENT = MinecraftClient.getInstance();
        String world = CLIENT.player.getWorld().getRegistryKey().getValue().toString();
        if(world.equals("minecraft:the_moon")) return Client.ASSETS.world_moon;
        if(world.equals("minecraft:the_end")) return Client.ASSETS.world_the_end;
        if(world.equals("minecraft:the_nether")) return Client.ASSETS.world_nether;
        if(world.equals("minecraft:overworld")) return Client.ASSETS.world;
        return Client.ASSETS.unknown_world;
    }
    public static String getName(){
        MinecraftClient CLIENT = MinecraftClient.getInstance();
        String world = CLIENT.player.getWorld().getRegistryKey().getValue().toString();
        if(world.equals("minecraft:the_moon")) return Localization.getLocalization("world.moon", true);
        if(world.equals("minecraft:the_end")) return Localization.getLocalization("world.the_end", true);
        if(world.equals("minecraft:the_nether")) return Localization.getLocalization("world.nether", true);
        if(world.equals("minecraft:overworld")) return Localization.getLocalization("world.overworld", true);
        return Localization.getLocalization("unknown.world", true);
    }
}
