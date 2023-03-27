package ru.simplykel.simplystatus.config;

import net.minecraft.client.MinecraftClient;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ServerConfig {
    public static boolean SHOW_ADDRESS = false;
    public static boolean SHOW_NAME_IN_LIST = true;
    public static boolean SHOW_CUSTOM_NAME = false;
    public static String CUSTOM_NAME = "";
    public static boolean SHOW_ICON = false;
    public static String ICON_URL = "";

    /**
     * Сохранение конфигурации
     */
    public static void save(){
        MinecraftClient CLIENT = MinecraftClient.getInstance();
        final Path configFile = CLIENT.runDirectory.toPath().resolve("SimplyStatus/servers/"+CLIENT.getCurrentServerEntry().address+".json");
        JSONObject jsonConfig = new JSONObject();
        jsonConfig.put("SHOW_ADDRESS", SHOW_ADDRESS)
                .put("SHOW_NAME_IN_LIST", SHOW_NAME_IN_LIST)
                .put("SHOW_CUSTOM_NAME", SHOW_CUSTOM_NAME)
                .put("CUSTOM_NAME", CUSTOM_NAME)
                .put("SHOW_ICON", SHOW_ICON)
                .put("ICON_URL", ICON_URL);
        try {
            Files.createDirectories(configFile.getParent());
            Files.writeString(configFile, jsonConfig.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void clear(){
        SHOW_ADDRESS = false;
        SHOW_ICON = false;
        SHOW_CUSTOM_NAME = false;
        SHOW_NAME_IN_LIST = true;
        ICON_URL = "";
        CUSTOM_NAME = "";
    }
    /**
     * Загрузка файла конфигов
     */
    public static void load(){
        MinecraftClient CLIENT = MinecraftClient.getInstance();
        final Path configFile = CLIENT.runDirectory.toPath().resolve("SimplyStatus/servers/"+CLIENT.getCurrentServerEntry().address+".json");
        try{
            JSONObject jsonConfig = new JSONObject(Files.readString(configFile));
            if(!jsonConfig.isNull("SHOW_ADDRESS")) SHOW_ADDRESS = jsonConfig.getBoolean("SHOW_ADDRESS");
            else SHOW_ADDRESS = false;
            if(!jsonConfig.isNull("SHOW_NAME_IN_LIST")) SHOW_NAME_IN_LIST = jsonConfig.getBoolean("SHOW_NAME_IN_LIST");
            else SHOW_NAME_IN_LIST = true;
            if(!jsonConfig.isNull("SHOW_CUSTOM_NAME")) SHOW_CUSTOM_NAME = jsonConfig.getBoolean("SHOW_CUSTOM_NAME");
            else SHOW_CUSTOM_NAME = false;
            if(!jsonConfig.isNull("CUSTOM_NAME")) CUSTOM_NAME = jsonConfig.getString("CUSTOM_NAME");
            else CUSTOM_NAME = "";
            if(!jsonConfig.isNull("SHOW_ICON")) SHOW_ICON = jsonConfig.getBoolean("SHOW_ICON");
            else SHOW_ICON = false;
            if(!jsonConfig.isNull("ICON_URL")) ICON_URL = jsonConfig.getString("ICON_URL");
            else ICON_URL = "";
        } catch (Exception e){
            e.printStackTrace();
            clear();
            save();
        }

    }
}
