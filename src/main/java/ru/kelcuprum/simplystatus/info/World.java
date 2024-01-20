package ru.kelcuprum.simplystatus.info;

import com.jagrosh.discordipc.entities.RichPresence;
import net.minecraft.client.Minecraft;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.localization.StarScript;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class World {
    static Minecraft CLIENT = Minecraft.getInstance();
    public static String getTimeType(){
        if(CLIENT.level == null) return "";
        long currentTime = CLIENT.level.getDayTime() % 24000;
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
        long daytime = CLIENT.level.getDayTime()+6000;

        int hours=(int) (daytime / 1000)%24;
        int minutes = (int) ((daytime % 1000)*60/1000);
        int day = (int) daytime / 1000 / 24;
        String clock;
        try {
            String strDateFormat = SimplyStatus.localization.getLocalization("date.time", false);
            DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
            Calendar calendar = new GregorianCalendar();
            calendar.set(2000, 0, day+1, hours, minutes, 0);

            clock = dateFormat.format(calendar.getTimeInMillis());
        } catch (IllegalArgumentException ex) {
            clock = "illegal clock format; google for Java SimpleDateFormat";
        }
        return clock;

    }
    public static void getTime(RichPresence.Builder presence){
        long currentTime = CLIENT.level.getDayTime() % 24000;
        if (currentTime < 6000 && currentTime > 0) {
            presence.setLargeImage(SimplyStatus.ASSETS.morning, SimplyStatus.localization.getLocalization("time.morning", true));
        } else if (currentTime < 12000 && currentTime > 6000) {
            presence.setLargeImage(SimplyStatus.ASSETS.day, SimplyStatus.localization.getLocalization("time.day", true));
        } else if (currentTime < 16500 && currentTime > 12000) {
            presence.setLargeImage(SimplyStatus.ASSETS.evening, SimplyStatus.localization.getLocalization("time.evening", true));
        } else if (currentTime < 24000 && currentTime > 16500) {
            presence.setLargeImage(SimplyStatus.ASSETS.night, SimplyStatus.localization.getLocalization("time.night", true));
        } else {
            presence.setLargeImage(SimplyStatus.ASSETS.world, SimplyStatus.localization.getLocalization("world.overworld", true));
            presence.setSmallImage("", "");
        }
    }
    public static String getCodeName(){
        return CLIENT.level.dimension().location().toString();// CLIENT.level.dimensionTypeRegistration().value().toString(); //getRegistryKey().getValue().toString();
    }
    public static String getAssets(){
        String world = getCodeName();
        if(world.equals("minecraft:the_moon")) return SimplyStatus.ASSETS.world_moon;
        if(world.equals("minecraft:the_end")) return SimplyStatus.ASSETS.world_the_end;
        if(world.equals("minecraft:the_nether")) return SimplyStatus.ASSETS.world_nether;
        if(world.equals("minecraft:overworld")) return SimplyStatus.ASSETS.world;
        return SimplyStatus.ASSETS.unknown_world;
    }
    public static String getName(){
        String world = getCodeName();
        if(world.equals("minecraft:the_moon")) return SimplyStatus.localization.getLocalization("world.moon", false);
        if(world.equals("minecraft:the_end")) return SimplyStatus.localization.getLocalization("world.the_end", false);
        if(world.equals("minecraft:the_nether")) return SimplyStatus.localization.getLocalization("world.nether", false);
        if(world.equals("minecraft:overworld")) return SimplyStatus.localization.getLocalization("world.overworld", false);
        return SimplyStatus.localization.getLocalization("world.unknown", false);
    }
    public static String getScene(){
        if(CLIENT.getCurrentServer() != null && !CLIENT.isSingleplayer())
            return SimplyStatus.serverConfig.getBoolean("USE_CUSTOM_NAME", false) ? SimplyStatus.serverConfig.getString("CUSTOM_NAME", "Custom name") :
                    SimplyStatus.serverConfig.getBoolean("SHOW_NAME", true) ? CLIENT.getCurrentServer().name :
                        SimplyStatus.serverConfig.getBoolean("SHOW_ADDRESS", false) ? CLIENT.getCurrentServer().ip :
                                SimplyStatus.localization.getLocalization("address.hidden", false);
        else return SimplyStatus.localization.getLocalization("singleplayer", false);
    }
}
