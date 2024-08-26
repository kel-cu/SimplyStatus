package ru.kelcuprum.simplystatus.config;

import com.google.gson.JsonObject;
import net.minecraft.util.GsonHelper;
import org.apache.logging.log4j.Level;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.info.Player;
import ru.kelcuprum.simplystatus.SimplyStatus;

import java.io.*;
import java.nio.file.*;
import java.util.Objects;

public class Assets {
    public String id;
    public String name;
    public String base;
    public String author;

    protected JsonObject data;
    protected JsonObject icons;
    protected Assets defaultAssets;
    public File file;
    public Assets(File file){
        this.file = file;
        final Path assets = file.toPath();
        try {
            data = assets.toFile().exists() ? GsonHelper.parse(Files.readString(assets)) : new JsonObject();
        } catch (Exception e) {
            data = new JsonObject();
            SimplyStatus.log(e.getLocalizedMessage(), Level.ERROR);
        }
        load();
    }
    public Assets(JsonObject data){
        this.data = data;
        load();
    }
    protected void load(){
        if(isValidObject(data)){
            if(file != null){
                data = new JsonObject();
                data.addProperty("id", file.getName().replace(".json", ""));
                data.addProperty("name", file.getName().replace(".json", ""));
                if(defaultAssets != null) data.addProperty("base", defaultAssets.id);
                data.add("icons", new JsonObject());
            } else throw new RuntimeException(String.format("The facility does not meet the criteria. JSON: %S", data.toString()));
        }
        this.id = data.get("id").getAsString();
        this.name = data.get("name").getAsString();
        this.icons = data.getAsJsonObject("icons");
        if(!isJsonNull(data, "base") && data.getAsJsonPrimitive("base").isString()) this.base = data.get("base").getAsString();
        this.author = isJsonNull(data, "author") ? Player.getName() : data.get("author").getAsString();
    }

    private boolean isValidObject(JsonObject data){
        boolean failed = false;
        if(data == null) return true;
        if(isJsonNull(data,"id")) failed = true;
        else if(isJsonNull(data,"name")) failed = true;
        else if(isJsonNull(data,"icons")) failed = true;
        return failed;
    }

    public Assets setBaseAssets(String id){
        this.base = id;
        this.data.addProperty("base", id);
        return this;
    }

    public Assets getBaseAssets(){
        Assets baseAssets = null;
        if(base != null) baseAssets = Assets.getByID(base);
        else if(!isJsonNull(data, "base") && data.getAsJsonPrimitive("base").isString()){
            base = data.get("base").getAsString();
            baseAssets = Assets.getByID(base);
        }
        if(baseAssets != null) baseAssets.setDefaultAssets(ModConfig.defaultAssets);
        return baseAssets;
    }
    public void setDefaultAssets(Assets defaultAssets) {
        this.defaultAssets = defaultAssets;
    }

    public String getIcon(String id){
        String icon = defaultAssets == null ? "https://cdn.kelcuprum.ru/other/error.png" : defaultAssets.getIconFromData(id);
        if(!isJsonNull(icons, id) && icons.getAsJsonPrimitive(id).isString()) icon = getIconFromData(id);
        else if(getBaseAssets() != null && !getBaseAssets().name.equals(name)) icon = getBaseAssets().getIcon(id);
        return icon;
    }
    public String getIconFromData(String id){
        String icon = "https://cdn.kelcuprum.ru/other/error.png";
        if(!isJsonNull(icons, id) && icons.getAsJsonPrimitive(id).isString()) icon = icons.get(id).getAsString();
        return icon;
    }

    public Assets setName(String name){
        this.name = name;
        data.addProperty("name", name);
        return this;
    }

    public Assets setAuthor(String author){
        this.author = author;
        data.addProperty("author", author);
        return this;
    }

    public Assets setIcon(String id, String url){
        icons.addProperty(id, url);
        data.add("icons", icons);
        return this;
    }

    public Assets save(){
        if(file == null) return this;
        final Path configFile = file.toPath();

        try {
            Files.createDirectories(configFile.getParent());
            Files.writeString(configFile, data.toString());
        } catch (IOException e) {
            SimplyStatus.log(e.getLocalizedMessage(), Level.ERROR);
        }
        registerAsset(this);
        return this;
    }

    public void delete(){
        if(file == null) return;
        SimplyStatus.assetsNames.remove(getPositionOnAssetsNames(this.name));
        SimplyStatus.assets.remove(this.id, this);
        file.delete();
        file = null;
    }

    public static boolean isJsonNull(JsonObject json, String id) {
        if(json == null) return true;
        if (!json.has(id))
            return true;

        return json.get(id).isJsonNull();
    }

    public static void registerAsset(Assets assets){
        if(assets.file == null) {
            SimplyStatus.modAssets.put(assets.id, assets);
            SimplyStatus.assets.put(assets.id, assets);
            SimplyStatus.assetsNames.add(assets.id);
            SimplyStatus.log(String.format("[Mod] Registration of %s by id %s", assets.name, assets.id));
        } else {
            if(SimplyStatus.modAssets.get(assets.id) == null) {
                if(SimplyStatus.assets.get(assets.id) == null) {
                    SimplyStatus.assetsNames.add(assets.id);
                }
                SimplyStatus.assets.put(assets.id, assets);
                SimplyStatus.log(String.format("[File] Registration of %s by id %s", assets.name, assets.id));
            } else SimplyStatus.log(String.format("[File] Registration of %s by id %s failed, conflict of internal assets", assets.name, assets.id));
        }
    }

    public static String[] getAssetsNames(){
        String[] list = new String[SimplyStatus.assetsNames.size()];
        int i = 0;
        for(String id : SimplyStatus.assetsNames){
            list[i] = SimplyStatus.assets.getOrDefault(id, ModConfig.defaultAssets).name;
            i++;
        }
        return list;
    }
    public static String[] getAssetsNames(String name){
        String[] list = new String[SimplyStatus.assetsNames.size()-1];
        int i = 0;
        for(String id : SimplyStatus.assetsNames){
            if(!SimplyStatus.assets.getOrDefault(id, ModConfig.defaultAssets).name.equals(name)) {
                list[i] = SimplyStatus.assets.getOrDefault(id, ModConfig.defaultAssets).name;
                i++;
            }
        }
        return list;
    }

    public static int getPositionOnAssetsNames(String name){
        int i = 0;
        for(String id : getAssetsNames()){
            if(id.equals(name)) break;
            else i++;
        }
        return i;
    }

    public static int getPositionOnAssetsNames(String name, String withoutName){
        int i = 0;
        for(String id : getAssetsNames(withoutName)){
            if(id.equals(name)) break;
            else i++;
        }
        return i;
    }

    public static Assets getByName(String name){
        Assets assets = ModConfig.defaultAssets;
        for(String id : SimplyStatus.assetsNames){
            Assets assetsById = getByID(id);
            if(Objects.equals(assetsById.name, name)){
                assets=assetsById;
            };
        }
        assets.setDefaultAssets(ModConfig.defaultAssets);
        return assets;
    }

    public static Assets getByID(String id){
        Assets assets = SimplyStatus.assets.getOrDefault(id, ModConfig.defaultAssets);
        assets.setDefaultAssets(ModConfig.defaultAssets);
        return assets;
    }

    public static Assets getSelected(){
        String id = SimplyStatus.userConfig.getString("USE_ASSETS", ModConfig.defaultAssets.id);
        return getByID(id);
    }
    public static void loadFiles(){
        File assets = AlinLib.MINECRAFT.gameDirectory.toPath().resolve("config/SimplyStatus/assets").toFile();
        if(assets.exists() && assets.isDirectory()){
            for(File assetsFile : Objects.requireNonNull(assets.listFiles())){
                if(assetsFile.isFile() && assetsFile.getName().endsWith(".json")){
                    try {
                        Assets assetsObject = new Assets(assetsFile);
                        assetsObject.setDefaultAssets(ModConfig.defaultAssets);
                        registerAsset(assetsObject);
                    } catch (Exception e){
                        SimplyStatus.log(e.getLocalizedMessage(), Level.ERROR);
                    }
                }
            }
        }
    }
}
