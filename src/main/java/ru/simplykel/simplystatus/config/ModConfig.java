package ru.simplykel.simplystatus.config;

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ModConfig {
    /**
     * Базовое ID RPC мода
     */
    public static String baseID;
    /**
     * ID с другим названием в духе Minecraft
     */
    public static String mineID;
    /**
     * default иконки для GUI конфигов
     */
    public static AssetsConfig defaultAssets;
    public static AssetsConfig bedrockAssets;

    /**
     * Использование default конфигов мода, чтобы не указывать в коде и не искать потом их везде
     * + Облеглчение работы ребят которые делают кастомы
     */
    public static String MOD_CONFIG_STRING = "";
    public ModConfig() throws Exception {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream stream = loader.getResourceAsStream("simplystatus.config.mod.json");
        JSONObject config = new JSONObject(IOUtils.toString(stream, StandardCharsets.UTF_8));
        if(config.isNull("baseID")) {
            throw new Exception("Не найден baseID, который требуется для запуска мода!");
        } else baseID = config.getString("baseID");
        if(config.isNull("mineID")) mineID = null;
        else mineID = config.getString("mineID");
        if(config.isNull("assetsDefault")) {
            throw new Exception("Не найден defaultAssets, который требуется для запуска мода!");
        } else defaultAssets = new AssetsConfig(config.getJSONObject("assetsDefault"), true, false);
        if(config.isNull("bedrockAssets")) bedrockAssets = null;
        else bedrockAssets = new AssetsConfig(config.getJSONObject("bedrockAssets"), true, true);
        MOD_CONFIG_STRING = config.toString();
    }
}
