package ru.kelcuprum.simplystatus.info;

import com.jagrosh.discordipc.entities.RichPresence;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.config.Assets;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import static ru.kelcuprum.simplystatus.SimplyStatus.MINECRAFT;

public class World {
    public static String getTimeType(){
        if(MINECRAFT.level == null) return "";
        long currentTime = MINECRAFT.level.getDayTime() % 24000;
        if (currentTime < 6000 && currentTime > 0) {
            return SimplyStatus.localization.getLocalization("time.morning", false);
        } else if (currentTime < 12000 && currentTime > 6000) {
            return SimplyStatus.localization.getLocalization("time.day", false);
        } else if (currentTime < 16500 && currentTime > 12000) {
            return SimplyStatus.localization.getLocalization("time.evening", false);
        } else if (currentTime > 16500) {
            return SimplyStatus.localization.getLocalization("time.night", false);
        } else {
            return "";
        }
    }
    public static String getTime(){
        if(MINECRAFT.level == null) return "";
        long daytime = MINECRAFT.level.getDayTime()+6000;

        int hours=(int) (daytime / 1000)%24;
        int minutes = (int) ((daytime % 1000)*60/1000);
        int day = (int) daytime / 1000 / 24;
        String clock;
        try {
            String strDateFormat = SimplyStatus.localization.getLocalization("date.time", false);
            DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
            Calendar calendar = new GregorianCalendar();
            calendar.set(2000, Calendar.JANUARY, day+1, hours, minutes, 0);

            clock = dateFormat.format(calendar.getTimeInMillis());
        } catch (IllegalArgumentException ex) {
            clock = "illegal clock format; google for Java SimpleDateFormat";
        }
        return clock;

    }
    public static void getTime(RichPresence.Builder presence){
        if(MINECRAFT.level == null) return;
        long currentTime = MINECRAFT.level.getDayTime() % 24000;
        if (currentTime < 6000 && currentTime > 0) {
            presence.setLargeImage(Assets.getSelected().getIcon("morning"), SimplyStatus.localization.getLocalization("time.morning", true));
        } else if (currentTime < 12000 && currentTime > 6000) {
            presence.setLargeImage(Assets.getSelected().getIcon("day"), SimplyStatus.localization.getLocalization("time.day", true));
        } else if (currentTime < 16500 && currentTime > 12000) {
            presence.setLargeImage(Assets.getSelected().getIcon("evening"), SimplyStatus.localization.getLocalization("time.evening", true));
        } else if (currentTime > 16500) {
            presence.setLargeImage(Assets.getSelected().getIcon("night"), SimplyStatus.localization.getLocalization("time.night", true));
        } else {
            presence.setLargeImage(Assets.getSelected().getIcon("world"), SimplyStatus.localization.getLocalization("world.overworld", true));
            presence.setSmallImage("", "");
        }
    }
    public static String getCodeName(){
        if(MINECRAFT.level == null) return "";
        return MINECRAFT.level.dimension().location().toString();
    }
    public static String getAssets(){
        return switch (getCodeName()){
            case "minecraft:the_moon" -> Assets.getSelected().getIcon("world_moon");
            case "minecraft:the_end" -> Assets.getSelected().getIcon("world_the_end");
            case "minecraft:the_nether" -> Assets.getSelected().getIcon("world_nether");
            case "minecraft:overworld" -> Assets.getSelected().getIcon("world");
            default -> Assets.getSelected().getIcon("unknown_world");
        };
    }
    public static String getName(){
        return switch (getCodeName()) {
            case "minecraft:the_moon" -> SimplyStatus.localization.getLocalization("world.moon", false);
            case "minecraft:the_end" -> SimplyStatus.localization.getLocalization("world.the_end", false);
            case "minecraft:the_nether" -> SimplyStatus.localization.getLocalization("world.nether", false);
            case "minecraft:overworld" -> SimplyStatus.localization.getLocalization("world.overworld", false);
            default -> SimplyStatus.localization.getLocalization("unknown.world", false);
        };
    }
    public static String getScene(){
        if(MINECRAFT.getCurrentServer() != null && !MINECRAFT.isSingleplayer())
            return SimplyStatus.serverConfig.getBoolean("USE_CUSTOM_NAME", false) ? SimplyStatus.serverConfig.getString("CUSTOM_NAME", "Custom name") :
                    SimplyStatus.serverConfig.getBoolean("SHOW_NAME", true) ? MINECRAFT.getCurrentServer().name :
                        SimplyStatus.serverConfig.getBoolean("SHOW_ADDRESS", false) ? MINECRAFT.getCurrentServer().ip :
                                SimplyStatus.localization.getLocalization("address.hidden", false);
        else return SimplyStatus.userConfig.getBoolean("SINGLEPLAYER.WORLD_NAME", false) ? MINECRAFT.getSingleplayerServer().getWorldData().getLevelName() : SimplyStatus.localization.getLocalization("singleplayer", false);
    }
}
