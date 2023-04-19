package ru.simplykel.simplystatus.config;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
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
    /**
     * default иконки для GUI конфигов с использованием ссылок
     */
    public static AssetsConfig defaultUrlsAssets;
    /**
    *
     */
    public static String[] assetsList;
    /**
     *
     */
    public static JSONObject assets;
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
        if(config.isNull("assets")) throw new Exception("Не найдены стандартные иконки, который требуется для запуска мода!");
        else assets = config.getJSONObject("assets");
        if(config.getJSONObject("assets").isNull("default")) {
            throw new Exception("Не найдены стандартные иконки, который требуется для запуска мода!");
        } else defaultAssets = new AssetsConfig(config.getJSONObject("assets").getJSONObject("default"), true);
        if(config.getJSONObject("assets").isNull("~urls")) {
            throw new Exception("Не найдены стандартные ссылки на иконки, который требуется для запуска мода!");
        } else defaultUrlsAssets = new AssetsConfig(config.getJSONObject("assets").getJSONObject("~urls"), true);
        if(config.isNull("assets_list")) assetsList = new String[]{"Default"};
        else assetsList = jsonArrayToStringArray(config.getJSONArray("assets_list"));
        MOD_CONFIG_STRING = config.toString();
    }
    public String[] jsonArrayToStringArray(JSONArray jsonArray) {
        int arraySize = jsonArray.length();
        String[] stringArray = new String[arraySize];

        for(int i=0; i<arraySize; i++) {
            stringArray[i] = jsonArray.getString(i);
        }

        return stringArray;
    };
}
