package ru.kelcuprum.sailstatus.info;

import com.jagrosh.discordipc.entities.RichPresence;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.info.World;
import ru.kelcuprum.sailstatus.SailStatus;
import ru.kelcuprum.sailstatus.config.Assets;

public class PresenceWorld {
    public static void getTime(RichPresence.Builder presence){
        if(AlinLib.MINECRAFT.level == null) return;
        long currentTime = AlinLib.MINECRAFT.level.getDayTime() % 24000;
        if (currentTime < 6000 && currentTime > 0) {
            presence.setLargeImage(Assets.getSelected().getIcon("morning"), SailStatus.localization.getLocalization("time.morning", true));
        } else if (currentTime < 12000 && currentTime > 6000) {
            presence.setLargeImage(Assets.getSelected().getIcon("day"), SailStatus.localization.getLocalization("time.day", true));
        } else if (currentTime < 16500 && currentTime > 12000) {
            presence.setLargeImage(Assets.getSelected().getIcon("evening"), SailStatus.localization.getLocalization("time.evening", true));
        } else if (currentTime > 16500) {
            presence.setLargeImage(Assets.getSelected().getIcon("night"), SailStatus.localization.getLocalization("time.night", true));
        } else {
            presence.setLargeImage(Assets.getSelected().getIcon("world"), SailStatus.localization.getLocalization("world.overworld", true));
            presence.setSmallImage("", "");
        }
    }
    public static String getAssets(){
        return switch (World.getCodeName()){
            case "minecraft:the_moon" -> Assets.getSelected().getIcon("world_moon");
            case "minecraft:the_end" -> Assets.getSelected().getIcon("world_the_end");
            case "minecraft:the_nether" -> Assets.getSelected().getIcon("world_nether");
            case "minecraft:overworld" -> Assets.getSelected().getIcon("world");
            default -> Assets.getSelected().getIcon("unknown_world");
        };
    }
    public static String getScene(){
        if(AlinLib.MINECRAFT.getCurrentServer() != null && !AlinLib.MINECRAFT.isSingleplayer())
            return SailStatus.serverConfig.getBoolean("USE_CUSTOM_NAME", false) ? SailStatus.serverConfig.getString("CUSTOM_NAME", "Custom name") :
                    SailStatus.serverConfig.getBoolean("SHOW_NAME", true) ? AlinLib.MINECRAFT.getCurrentServer().name :
                        SailStatus.serverConfig.getBoolean("SHOW_ADDRESS", false) ? AlinLib.MINECRAFT.getCurrentServer().ip :
                                SailStatus.localization.getLocalization("address.hidden", false);
        else return SailStatus.userConfig.getBoolean("SINGLEPLAYER.WORLD_NAME", false) ? AlinLib.MINECRAFT.getSingleplayerServer().getWorldData().getLevelName() : SailStatus.localization.getLocalization("singleplayer", false);
    }
}
