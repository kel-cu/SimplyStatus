package og.__kel_.simplystatus.configs;

import net.minecraft.client.MinecraftClient;
import og.__kel_.simplystatus.client.HotKeys;
import og.__kel_.simplystatus.configs.entity.AssetsEntity;
import og.__kel_.simplystatus.configs.entity.Client;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AssetsConfig {
    //
    public static String music = "music";
    public static String day = "day_update_2";
    public static String night = "nigth_update_2";
    public static String morning = "morning_update_2";
    public static String evening = "evening_update_2";
    public static String logo = "logo";
    public static String Unknown = "unknown_world";
    public static String replay = "replaymod_logo";
    public static String overworld = "overworld";
    public static String nether = "nether";
    public static String end = "end";
    public void load() {
        MinecraftClient mc = MinecraftClient.getInstance();
        try {
            String jsonContent = Files.readString(new File(mc.runDirectory + "/SimplyStatus/assets.json").toPath());
            AssetsEntity assetsConfig = new AssetsEntity(jsonContent);
            music = assetsConfig.music;
            day = assetsConfig.day;
            night = assetsConfig.night;
            morning = assetsConfig.morning;
            evening = assetsConfig.evening;
            logo = assetsConfig.logo;
            Unknown = assetsConfig.Unknown;
            replay = assetsConfig.replay;
            overworld = assetsConfig.overworld;
            nether = assetsConfig.nether;
            end = assetsConfig.end;
        } catch (Exception e) {
            save();
        }
    }
    public void save() {
        MinecraftClient mc = MinecraftClient.getInstance();
        final Path configFile = mc.runDirectory.toPath().resolve("SimplyStatus/assets.json");
        JSONObject jsonConfig = new JSONObject();
        try{
            jsonConfig.put("music", this.music)
                    .put("day", this.day)
                    .put("night", this.night)
                    .put("morning", this.morning)
                    .put("evening", this.evening)
                    .put("logo", this.logo)
                    .put("Unknown", this.Unknown)
                    .put("replay", this.replay)
                    .put("overworld", this.overworld)
                    .put("nether", this.nether)
                    .put("end", this.end);
        } catch (JSONException ej){
            System.out.println(ej.getMessage());
        }
        try {
            Files.createDirectories(configFile.getParent());
            Files.writeString(configFile, jsonConfig.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
