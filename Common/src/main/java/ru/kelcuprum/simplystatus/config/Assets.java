package ru.kelcuprum.simplystatus.config;

import com.google.gson.JsonObject;
import ru.kelcuprum.simplystatus.SimplyStatus;

import java.util.Objects;

public class Assets {
    protected String id;
    protected String name;

    protected JsonObject data;
    protected JsonObject icons;
    protected Assets defaultAssets;
    public Assets(JsonObject data){
        if(isValidObject(data)){
            throw new RuntimeException(String.format("The facility does not meet the criteria. JSON: %S", data.toString()));
        }
        this.data = data;
        this.id = data.get("id").getAsString();
        this.name = data.get("name").getAsString();
        this.icons = data.getAsJsonObject("icons");
        SimplyStatus.log(String.format("Loaded %s assets", name));
    }

    private boolean isValidObject(JsonObject data){
        boolean failed = false;
        if(data.get("id").isJsonNull()) failed = true;
        else if(data.get("name").isJsonNull()) failed = true;
        else if(data.get("icons").isJsonNull()) failed = true;
        return failed;
    }

    public void setDefaultAssets(Assets defaultAssets) {
        this.defaultAssets = defaultAssets;
    }

    public String getIcon(String id){
        String icon = defaultAssets == null ? "https://cdn.kelcuprum.ru/other/error.png" : defaultAssets.getIcon(id);
        if(!icons.get(id).isJsonNull() && icons.getAsJsonPrimitive(id).isString()) icon = icons.get(id).getAsString();
        return icon;
    }
    public static void registerAsset(Assets assets){
        SimplyStatus.assets.put(assets.id, assets);
        SimplyStatus.assetsNames.add(assets.id);
        SimplyStatus.log(String.format("Register %s by id %s", assets.name, assets.id));
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
}
