package ru.kelcuprum.simplystatus.config;

import net.minecraft.client.Minecraft;
import org.json.JSONObject;
import ru.kelcuprum.simplystatus.SimplyStatus;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class AssetsConfig {
    /**
     * Начальная иконка
     */
    public String logo = "";

    /**
     * День
     */
    public String day = "";
    /**
     * Ночь
     */
    public String night = "";
    /**
     * Не ебу
     */
    public String morning = "";
    /**
     * Не ебу 2
     */
    public String evening = "";

    /**
     * Верхний мир
     */
    public String world = "";
    /**
     * Нижний мир
     */
    public String world_nether = "";
    /**
     * The End
     */
    public String world_the_end = "";
    /**
     * The End
     */
    public String world_moon = "";

    /**
     * Music
     */
    public String music = "";
    /**
     * ReplayMod
     */
    public String replaymod = "";
    /**
     * PlasmoVoice or Simple Voice Chat
     */
    public String voice = "";
    /**
     * Неизвестный мир
     */
    public String unknown_world = "";
    /**
     * Неизвестное значение
     */
    public String unknown = "";
    public void loadUserAssets(){
        Minecraft mc = Minecraft.getInstance();
        final Path assetsFile = mc.gameDirectory.toPath().resolve("config/SimplyStatus/assets.json");
        try {
            JSONObject jsonConfig = new JSONObject();
            if(assetsFile.toFile().exists()) jsonConfig = new JSONObject(Files.readString(assetsFile));
            AssetsConfig assets = new AssetsConfig(jsonConfig);
            logo = assets.logo;
            day = assets.day;
            night = assets.night;
            morning = assets.morning;
            evening = assets.evening;

            world = assets.world;
            world_nether = assets.world_nether;
            world_the_end = assets.world_the_end;

            replaymod = assets.replaymod;
            voice = assets.voice;
            music = assets.music;

            unknown_world = assets.unknown_world;
            unknown = assets.unknown;

        } catch (Exception e){
            e.printStackTrace();
            saveUserAssets();
        }
    }
    public void saveUserAssets(){
        Minecraft mc = Minecraft.getInstance();
        final Path assetsFile = mc.gameDirectory.toPath().resolve("config/SimplyStatus/assets.json");
        JSONObject jsonConfig = new JSONObject();
        jsonConfig.put("logo", logo)
                .put("day", day)
                .put("night", night)
                .put("morning", morning)
                .put("evening", evening)
                .put("world", world)
                .put("world_nether", world_nether)
                .put("world_the_end", world_the_end)
                .put("replaymod", replaymod)
                .put("music", music)
                .put("voice", voice)
                .put("unknown", unknown)
                .put("unknown_world", unknown_world);
        try {
            Files.createDirectories(assetsFile.getParent());
            Files.writeString(assetsFile, jsonConfig.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public AssetsConfig() {
        AssetsConfig assets = new AssetsConfig(ModConfig.assets.getJSONObject(SimplyStatus.userConfig.getString("USE_ASSETS", "Default").toLowerCase()));
            logo = assets.logo;
            day = assets.day;
            night = assets.night;
            morning = assets.morning;
            evening = assets.evening;

            world = assets.world;
            world_nether = assets.world_nether;
            world_the_end = assets.world_the_end;
            world_moon = assets.world_moon;

            replaymod = assets.replaymod;
            voice = assets.voice;
            music = assets.music;

            unknown_world = assets.unknown_world;
            unknown = assets.unknown;
    }
    public AssetsConfig(JSONObject jsonAssets){
        for (String key : jsonAssets.keySet()) {
            switch (key) {
                case "logo" -> logo = jsonAssets.getString(key);
                case "day" -> day = jsonAssets.getString(key);
                case "night" -> night = jsonAssets.getString(key);
                case "morning" -> morning = jsonAssets.getString(key);
                case "evening" -> evening = jsonAssets.getString(key);
                case "world" -> world = jsonAssets.getString(key);
                case "world_nether" -> world_nether = jsonAssets.getString(key);
                case "world_the_end" -> world_the_end = jsonAssets.getString(key);
                case "world_moon" -> world_moon = jsonAssets.getString(key);
                case "replaymod" -> replaymod = jsonAssets.getString(key);
                case "music" -> music = jsonAssets.getString(key);
                case "voice" -> voice = jsonAssets.getString(key);
                case "unknown_world" -> unknown_world = jsonAssets.getString(key);
                case "unknown" -> unknown = jsonAssets.getString(key);
            }
        }
    }
}
