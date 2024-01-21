package ru.kelcuprum.simplystatus.config;

import com.google.gson.JsonObject;
import net.minecraft.client.Minecraft;
import net.minecraft.util.GsonHelper;
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
            JsonObject jsonConfig = new JsonObject();
            if(assetsFile.toFile().exists()) jsonConfig = GsonHelper.parse(Files.readString(assetsFile));
            AssetsConfig assets = new AssetsConfig(jsonConfig);
            logo = assets.logo.isEmpty() ? ModConfig.defaultUrlsAssets.logo : assets.logo;
            day = assets.day.isEmpty() ? ModConfig.defaultUrlsAssets.day : assets.day;
            night = assets.night.isEmpty() ? ModConfig.defaultUrlsAssets.night : assets.night;
            morning = assets.morning.isEmpty() ? ModConfig.defaultUrlsAssets.morning : assets.morning;
            evening = assets.evening.isEmpty() ? ModConfig.defaultUrlsAssets.evening : assets.evening;

            world = assets.world.isEmpty() ? ModConfig.defaultUrlsAssets.world : assets.world;
            world_nether = assets.world_nether.isEmpty() ? ModConfig.defaultUrlsAssets.world_nether : assets.world_nether;
            world_the_end = assets.world_the_end.isEmpty() ? ModConfig.defaultUrlsAssets.world_the_end : assets.world_the_end;
            world_moon = assets.world_moon.isEmpty() ? ModConfig.defaultUrlsAssets.world_moon : assets.world_moon;

            replaymod = assets.replaymod.isEmpty() ? ModConfig.defaultUrlsAssets.replaymod : assets.replaymod;
            voice = assets.voice.isEmpty() ? ModConfig.defaultUrlsAssets.voice : assets.voice;
            music = assets.music.isEmpty() ? ModConfig.defaultUrlsAssets.music : assets.music;

            unknown_world = assets.unknown_world.isEmpty() ? ModConfig.defaultUrlsAssets.unknown_world : assets.unknown_world;
            unknown = assets.unknown.isEmpty() ? ModConfig.defaultUrlsAssets.unknown : assets.unknown;

        } catch (Exception e){
            e.printStackTrace();
            saveUserAssets();
        }
    }
    public void setValue(String type, String value){
        switch (type.toLowerCase()) {
            case "logo" -> logo = value;
            case "day" -> day = value;
            case "night" -> night = value;
            case "morning" -> morning = value;
            case "evening" -> evening = value;
            case "world" -> world = value;
            case "world_nether" -> world_nether = value;
            case "world_the_end" -> world_the_end = value;
            case "world_moon" -> world_moon = value;
            case "replaymod" -> replaymod = value;
            case "music" -> music = value;
            case "voice" -> voice = value;
            case "unknown_world" -> unknown_world = value;
            case "unknown" -> unknown = value;
        }
        saveUserAssets();
    }
    public void saveUserAssets(){
        Minecraft mc = Minecraft.getInstance();
        final Path assetsFile = mc.gameDirectory.toPath().resolve("config/SimplyStatus/assets.json");
        JsonObject jsonConfig = new JsonObject();
        jsonConfig.addProperty("logo", logo);
        jsonConfig.addProperty("day", day);
        jsonConfig.addProperty("night", night);
        jsonConfig.addProperty("morning", morning);
        jsonConfig.addProperty("evening", evening);
        jsonConfig.addProperty("world", world);
        jsonConfig.addProperty("world_nether", world_nether);
        jsonConfig.addProperty("world_the_end", world_the_end);
        jsonConfig.addProperty("replaymod", replaymod);
        jsonConfig.addProperty("music", music);
        jsonConfig.addProperty("voice", voice);
        jsonConfig.addProperty("unknown", unknown);
        jsonConfig.addProperty("unknown_world", unknown_world);
        try {
            Files.createDirectories(assetsFile.getParent());
            Files.writeString(assetsFile, jsonConfig.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public AssetsConfig() {
        AssetsConfig assets = new AssetsConfig(ModConfig.assets.getAsJsonObject(SimplyStatus.userConfig.getString("USE_ASSETS", "Default").toLowerCase()));
            logo = assets.logo.isEmpty() ? ModConfig.defaultUrlsAssets.logo : assets.logo;
            day = assets.day.isEmpty() ? ModConfig.defaultUrlsAssets.day : assets.day;
            night = assets.night.isEmpty() ? ModConfig.defaultUrlsAssets.night : assets.night;
            morning = assets.morning.isEmpty() ? ModConfig.defaultUrlsAssets.morning : assets.morning;
            evening = assets.evening.isEmpty() ? ModConfig.defaultUrlsAssets.evening : assets.evening;

            world = assets.world.isEmpty() ? ModConfig.defaultUrlsAssets.world : assets.world;
            world_nether = assets.world_nether.isEmpty() ? ModConfig.defaultUrlsAssets.world_nether : assets.world_nether;
            world_the_end = assets.world_the_end.isEmpty() ? ModConfig.defaultUrlsAssets.world_the_end : assets.world_the_end;
            world_moon = assets.world_moon.isEmpty() ? ModConfig.defaultUrlsAssets.world_moon : assets.world_moon;

            replaymod = assets.replaymod.isEmpty() ? ModConfig.defaultUrlsAssets.replaymod : assets.replaymod;
            voice = assets.voice.isEmpty() ? ModConfig.defaultUrlsAssets.voice : assets.voice;
            music = assets.music.isEmpty() ? ModConfig.defaultUrlsAssets.music : assets.music;

            unknown_world = assets.unknown_world.isEmpty() ? ModConfig.defaultUrlsAssets.unknown_world : assets.unknown_world;
            unknown = assets.unknown.isEmpty() ? ModConfig.defaultUrlsAssets.unknown : assets.unknown;
    }
    public AssetsConfig(JsonObject jsonAssets){
        for (String key : jsonAssets.keySet()) {
            switch (key) {
                case "logo" -> logo = jsonAssets.get(key).getAsString();
                case "day" -> day = jsonAssets.get(key).getAsString();
                case "night" -> night = jsonAssets.get(key).getAsString();
                case "morning" -> morning = jsonAssets.get(key).getAsString();
                case "evening" -> evening = jsonAssets.get(key).getAsString();
                case "world" -> world = jsonAssets.get(key).getAsString();
                case "world_nether" -> world_nether = jsonAssets.get(key).getAsString();
                case "world_the_end" -> world_the_end = jsonAssets.get(key).getAsString();
                case "world_moon" -> world_moon = jsonAssets.get(key).getAsString();
                case "replaymod" -> replaymod = jsonAssets.get(key).getAsString();
                case "music" -> music = jsonAssets.get(key).getAsString();
                case "voice" -> voice = jsonAssets.get(key).getAsString();
                case "unknown_world" -> unknown_world = jsonAssets.get(key).getAsString();
                case "unknown" -> unknown = jsonAssets.get(key).getAsString();
            }
        }
    }
}
