package ru.kelcuprum.simplystatus.config;

import net.minecraft.client.Minecraft;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Config {
    private final String _filePath;
    private JSONObject _jsonConfiguration;
    public Config(String filePath){
        this._filePath = filePath;
    }
    /**
     * Сохранение конфигурации
     */
    public void save(){
        Minecraft mc = Minecraft.getInstance();
        final Path configFile = mc.gameDirectory.toPath().resolve(_filePath);

        try {
            Files.createDirectories(configFile.getParent());
            Files.writeString(configFile, _jsonConfiguration.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Загрузка файла конфигов
     */
    public void load(){
        Minecraft mc = Minecraft.getInstance();
        final Path configFile = mc.gameDirectory.toPath().resolve(_filePath);
        try{
            _jsonConfiguration = configFile.toFile().exists() ? new JSONObject(Files.readString(configFile)) : new JSONObject();
        } catch (Exception e){
            e.printStackTrace();
            save();
        }

    }

    /**
     * Получение Boolean значения
     */
    public boolean getBoolean(String type, boolean defaultValue) {
        if(this._jsonConfiguration == null) this._jsonConfiguration = new JSONObject();
        if(!this._jsonConfiguration.isNull(type) && !(this._jsonConfiguration.get(type) instanceof Boolean)) setBoolean(type, defaultValue);
        return this._jsonConfiguration.isNull(type) ? defaultValue : this._jsonConfiguration.getBoolean(type);
    }
    /**
     * Задать значения Boolean
     */
    public void setBoolean(String type, boolean newValue){
        this._jsonConfiguration.put(type, newValue);
    }
    /**
     * Получение String значения
     */

    public String getString(String type, String defaultValue) {
        if(this._jsonConfiguration == null) this._jsonConfiguration = new JSONObject();
        if(!this._jsonConfiguration.isNull(type) && !(this._jsonConfiguration.get(type) instanceof String)) setString(type, defaultValue);
        return this._jsonConfiguration.isNull(type) ? defaultValue : this._jsonConfiguration.getString(type);
    }
    /**
     * Задать значения String
     */
    public void setString(String type, String newValue){
        this._jsonConfiguration.put(type, newValue);
    }

    /**
     * Получение Int значения
     */

    public int getInt(String type, int defaultValue) {
        if(this._jsonConfiguration == null) this._jsonConfiguration = new JSONObject();
        if(!this._jsonConfiguration.isNull(type) && !(this._jsonConfiguration.get(type) instanceof Integer)) setInt(type, defaultValue);
        return this._jsonConfiguration.isNull(type) ? defaultValue : this._jsonConfiguration.getInt(type);
    }
    /**
     * Задать значения Int
     */
    public void setInt(String type, int newValue){
        this._jsonConfiguration.put(type, newValue);
    }

    /**
     * Получение Long значения
     */

    public long getLong(String type, long defaultValue) {
        if(this._jsonConfiguration == null) this._jsonConfiguration = new JSONObject();
        if(!this._jsonConfiguration.isNull(type) && !(this._jsonConfiguration.get(type) instanceof Long)) setLong(type, defaultValue);
        return this._jsonConfiguration.isNull(type) ? defaultValue : this._jsonConfiguration.getLong(type);
    }
    /**
     * Задать значения Long
     */
    public void setLong(String type, long newValue){
        this._jsonConfiguration.put(type, newValue);
    }

}
