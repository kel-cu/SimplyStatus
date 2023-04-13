package ru.simplykel.simplystatus.config;

import net.minecraft.client.MinecraftClient;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class UserConfig {
    public static boolean ENABLE_RPC = true;
    public static boolean ENABLE_BEDROCK_ASSETS = false;
    public static boolean SHOW_GAME_STARTED = true;
    public static boolean SHOW_AVATAR_PLAYER = true;
    public static boolean VIEW_ITEM_OFF_HAND = false;
    public static boolean VIEW_STATISTICS = true;
    public static boolean VIEW_PLAYER_NAME = true;
    public static boolean VIEW_VOICE_SPEAK = false;
    public static boolean VIEW_REPLAY_MOD = true;
    public static boolean VIEW_REPLAY_MOD_SERVER_NAME = false;
    public static boolean VIEW_MUSIC_LISTENER = false;
    public static boolean USE_CUSTOM_ASSETS = false;
    public static boolean USE_ANOTHER_ID = false;
    // 1.7.1
    public static boolean SHOW_ITEMS = true;
    public static boolean ENABLE_TIME_CYCLE = true;
    public static boolean ENABLE_WORLD = true;

    /**
     * Сохранение конфигурации
     */
    public static void save(){
        MinecraftClient mc = MinecraftClient.getInstance();
        final Path configFile = mc.runDirectory.toPath().resolve("SimplyStatus/config.json");
        JSONObject jsonConfig = new JSONObject();
        jsonConfig.put("ENABLE_RPC", ENABLE_RPC)
                .put("ENABLE_BEDROCK_ASSETS", ENABLE_BEDROCK_ASSETS)
                .put("SHOW_AVATAR_PLAYER", SHOW_AVATAR_PLAYER)
                .put("SHOW_GAME_STARTED", SHOW_GAME_STARTED)
                .put("VIEW_ITEM_OFF_HAND", VIEW_ITEM_OFF_HAND)
                .put("VIEW_STATISTICS", VIEW_STATISTICS)
                .put("VIEW_PLAYER_NAME", VIEW_PLAYER_NAME)
                .put("VIEW_VOICE_SPEAK", VIEW_VOICE_SPEAK)
                .put("VIEW_REPLAY_MOD", VIEW_REPLAY_MOD)
                .put("VIEW_REPLAY_MOD_SERVER_NAME", VIEW_REPLAY_MOD_SERVER_NAME)
                .put("VIEW_MUSIC_LISTENER", VIEW_MUSIC_LISTENER)
                .put("USE_CUSTOM_ASSETS", USE_CUSTOM_ASSETS)
                .put("USE_ANOTHER_ID", USE_ANOTHER_ID)
                .put("SHOW_ITEMS", SHOW_ITEMS)
                .put("ENABLE_TIME_CYCLE", ENABLE_TIME_CYCLE)
                .put("ENABLE_WORLD", ENABLE_WORLD);
        try {
            Files.createDirectories(configFile.getParent());
            Files.writeString(configFile, jsonConfig.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Загрузка файла конфигов
     */
    public static void load(){
        MinecraftClient mc = MinecraftClient.getInstance();
        final Path configFile = mc.runDirectory.toPath().resolve("SimplyStatus/config.json");
        try{
            JSONObject jsonConfig = new JSONObject(Files.readString(configFile));
            if(!jsonConfig.isNull("ENABLE_RPC")) ENABLE_RPC = jsonConfig.getBoolean("ENABLE_RPC");
            else ENABLE_RPC = true;
            if(!jsonConfig.isNull("ENABLE_BEDROCK_ASSETS")) ENABLE_BEDROCK_ASSETS = jsonConfig.getBoolean("ENABLE_BEDROCK_ASSETS");
            else ENABLE_BEDROCK_ASSETS = false;
            if(!jsonConfig.isNull("SHOW_AVATAR_PLAYER")) SHOW_AVATAR_PLAYER = jsonConfig.getBoolean("SHOW_AVATAR_PLAYER");
            else SHOW_AVATAR_PLAYER = true;
            if(!jsonConfig.isNull("SHOW_GAME_STARTED")) SHOW_GAME_STARTED = jsonConfig.getBoolean("SHOW_GAME_STARTED");
            else SHOW_GAME_STARTED = true;

            if(!jsonConfig.isNull("VIEW_ITEM_OFF_HAND")) VIEW_ITEM_OFF_HAND = jsonConfig.getBoolean("VIEW_ITEM_OFF_HAND");
            else VIEW_ITEM_OFF_HAND = false;
            if(!jsonConfig.isNull("VIEW_MUSIC_LISTENER")) VIEW_MUSIC_LISTENER = jsonConfig.getBoolean("VIEW_MUSIC_LISTENER");
            else VIEW_MUSIC_LISTENER = false;
            if(!jsonConfig.isNull("VIEW_STATISTICS")) VIEW_STATISTICS = jsonConfig.getBoolean("VIEW_STATISTICS");
            else VIEW_STATISTICS = true;
            if(!jsonConfig.isNull("VIEW_PLAYER_NAME")) VIEW_PLAYER_NAME = jsonConfig.getBoolean("VIEW_PLAYER_NAME");
            else VIEW_PLAYER_NAME = true;
            if(!jsonConfig.isNull("VIEW_VOICE_SPEAK")) VIEW_VOICE_SPEAK = jsonConfig.getBoolean("VIEW_VOICE_SPEAK");
            else VIEW_VOICE_SPEAK = false;
            if(!jsonConfig.isNull("VIEW_REPLAY_MOD")) VIEW_REPLAY_MOD = jsonConfig.getBoolean("VIEW_REPLAY_MOD");
            else VIEW_REPLAY_MOD = true;
            if(!jsonConfig.isNull("VIEW_REPLAY_MOD_SERVER_NAME")) VIEW_REPLAY_MOD_SERVER_NAME = jsonConfig.getBoolean("VIEW_REPLAY_MOD_SERVER_NAME");
            else VIEW_REPLAY_MOD_SERVER_NAME = false;

            if(!jsonConfig.isNull("USE_CUSTOM_ASSETS")) USE_CUSTOM_ASSETS = jsonConfig.getBoolean("USE_CUSTOM_ASSETS");
            else USE_CUSTOM_ASSETS = false;
            if(!jsonConfig.isNull("USE_ANOTHER_ID")) USE_ANOTHER_ID = jsonConfig.getBoolean("USE_ANOTHER_ID");
            else USE_ANOTHER_ID = false;


            if(!jsonConfig.isNull("SHOW_ITEMS")) SHOW_ITEMS = jsonConfig.getBoolean("SHOW_ITEMS");
            else SHOW_ITEMS = true;
            if(!jsonConfig.isNull("ENABLE_TIME_CYCLE")) ENABLE_TIME_CYCLE = jsonConfig.getBoolean("ENABLE_TIME_CYCLE");
            else ENABLE_TIME_CYCLE = true;
            if(!jsonConfig.isNull("ENABLE_WORLD")) ENABLE_WORLD = jsonConfig.getBoolean("ENABLE_WORLD");
            else ENABLE_WORLD = true;

        } catch (Exception e){
            e.printStackTrace();
            save();
        }

    }
}
