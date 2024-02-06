package ru.kelcuprum.simplystatus.config;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.util.GsonHelper;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ModConfig {
    public static boolean debugPresence = false;
    /**
     * Базовое ID RPC мода
     */
    public static String baseID = "";
    /**
     * ID с другим названием в духе Minecraft
     */
    public static String mineID = "";
    /**
     * default иконки для GUI конфигов
     */
    public static AssetsConfig defaultAssets;
    /**
     * default иконки для GUI конфигов с использованием ссылок
     */
    public static AssetsConfig defaultUrlsAssets;
    /**
     * Список доступных для выбора ассеты
     */
    public static String[] assetsList = new String[]{"Default"};
    /**
     * JSON обьект ассетов
     */
    public static JsonObject assets;
    /**
     * String версия конфигов
     */
    public static String MOD_CONFIG_STRING = "";
    /**
     * Использование default конфигов мода, чтобы не указывать в коде и не искать потом их везде
     * + Облеглчение работы ребят которые делают кастомы
     */
    public ModConfig() throws Exception {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("simplystatus.config.mod.json");
        if (stream == null) throw new RuntimeException("stream is null!");
        MOD_CONFIG_STRING = IOUtils.toString(stream, StandardCharsets.UTF_8);
        JsonObject config = GsonHelper.parse(MOD_CONFIG_STRING);
        for (String key : config.keySet()) {
            switch (key.toLowerCase()) {
                case "baseid" -> baseID = config.get(key).getAsString();
                case "debugpresence" -> debugPresence = config.get(key).getAsBoolean();
                case "mineid" -> mineID = config.get(key).getAsString();
                case "assets" -> {
                    JsonObject json = config.get(key).getAsJsonObject();
                    assets = json;
                    for (String keyJSON : json.keySet()) {
                        switch (keyJSON.toLowerCase()) {
                            case "default" -> defaultAssets = new AssetsConfig(json.get(keyJSON).getAsJsonObject());
                            case "~urls" -> defaultUrlsAssets = new AssetsConfig(json.get(keyJSON).getAsJsonObject());
                        }
                    }
                }
                case "assetsnames" -> assetsList = jsonArrayToStringArray(config.get("assetsnames").getAsJsonArray());
            }
        }
        if(baseID.isBlank()) throw new Exception("Не найден baseID, который требуется для запуска мода!");
        if(assets == null) throw new Exception("Не найден набор иконок, который требуется для запуска мода!");
        if(defaultAssets == null) throw new Exception("Не найдены стандартные иконки, который требуется для запуска мода!");
        if(defaultUrlsAssets == null) throw new Exception("Не найдены стандартные ссылки на иконки, которые требуются для запуска мода!");
        MOD_CONFIG_STRING = config.toString();
    }
    public String[] jsonArrayToStringArray(JsonArray jsonArray) {
        int arraySize = jsonArray.size();
        String[] stringArray = new String[arraySize];

        for(int i=0; i<arraySize; i++) {
            stringArray[i] = jsonArray.get(i).getAsString();
        }

        return stringArray;
    }
}
