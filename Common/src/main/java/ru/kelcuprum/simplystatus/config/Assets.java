package ru.kelcuprum.simplystatus.config;

import com.google.gson.JsonObject;
import net.minecraft.util.GsonHelper;
import org.apache.logging.log4j.Level;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.simplystatus.SimplyStatus;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class Assets {
    public String id;
    public String name;
    public String author;

    protected JsonObject data;
    protected JsonObject icons;
    protected Assets defaultAssets;
    protected File file;
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
                data.addProperty("name", "Example name");
                data.add("icons", new JsonObject());
            } else throw new RuntimeException(String.format("The facility does not meet the criteria. JSON: %S", data.toString()));
        }
        this.id = data.get("id").getAsString();
        this.name = data.get("name").getAsString();
        this.icons = data.getAsJsonObject("icons");
        this.author = isJsonNull(data, "author") ? AlinLib.MINECRAFT.getGameProfile().getName() : data.get("author").getAsString();
    }

    private boolean isValidObject(JsonObject data){
        boolean failed = false;
        if(data == null) return true;
        if(isJsonNull(data,"id")) failed = true;
        else if(isJsonNull(data,"name")) failed = true;
        else if(isJsonNull(data,"icons")) failed = true;
        return failed;
    }

    public void setDefaultAssets(Assets defaultAssets) {
        this.defaultAssets = defaultAssets;
    }

    public String getIcon(String id){
        String icon = defaultAssets == null ? "https://cdn.kelcuprum.ru/other/error.png" : defaultAssets.getIcon(id);
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

    public Assets delete(){
        if(file == null) return this;
        file.delete();
        file = null;
        return this;
    }

    public static boolean isJsonNull(JsonObject json, String id) {
        if(json == null) return true;
        if (!json.has(id))
            return true;

        return json.get(id).isJsonNull();
    }

    public static void registerAsset(Assets assets){
        if(SimplyStatus.assets.get(assets.id) == null) {
            SimplyStatus.assetsNames.add(assets.id);
            SimplyStatus.log(String.format("Register %s by id %s", assets.name, assets.id));
        }
        SimplyStatus.assets.put(assets.id, assets);
    }

    public static String[] getAssetsNames(){
        String[] list = new String[SimplyStatus.assetsNames.size()];
        int i = 0;
        for(String id : SimplyStatus.assetsNames){
            list[i] = SimplyStatus.assets.get(id).name;
            i++;
        }
        return list;
    }

    public static Assets getByID(String id){
        Assets assets = SimplyStatus.assets.getOrDefault(id, ModConfig.defaultAssets);
        assets.setDefaultAssets(ModConfig.defaultAssets);
        return assets;
    }

    public static Assets getSelected(){
        String name = SimplyStatus.userConfig.getString("USE_ASSETS", getAssetsNames()[0]);
        Assets assets = ModConfig.defaultAssets;
        for(String id : SimplyStatus.assetsNames){
            Assets assetsByID = SimplyStatus.assets.get(id);
            if(Objects.equals(assetsByID.name, name)){
                assets = assetsByID;
                return assets;
            }
        }
        return assets;
    }
    public static void loadFiles(){
        File assets = SimplyStatus.MINECRAFT.gameDirectory.toPath().resolve("config/SimplyStatus/assets").toFile();
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
