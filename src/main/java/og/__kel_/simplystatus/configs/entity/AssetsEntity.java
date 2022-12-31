package og.__kel_.simplystatus.configs.entity;

import org.json.JSONException;
import org.json.JSONObject;

public class AssetsEntity {
    public static String music = "music";
    public static String day = "day_update_2";
    public static String night = "nigth_update_2";
    public static String morning = "morning_update_2";
    public static String evening = "evening_update_2";
    public static String logo = "logo";
    public static String Unknown = "unknown_world";
    public static String replay = "replaymod_logo";
    public static String overworld = "overworld";
    public static String nether = "nether";
    public static String end = "end";
    public AssetsEntity(String jsonContent) throws JSONException {
        JSONObject json = new JSONObject(jsonContent);
        if(!json.isNull("music")) music = json.getString("music");
        if(!json.isNull("day")) day = json.getString("day");
        if(!json.isNull("night")) night = json.getString("night");
        if(!json.isNull("morning")) morning = json.getString("morning");
        if(!json.isNull("evening")) evening = json.getString("evening");
        if(!json.isNull("logo")) logo = json.getString("logo");
        if(!json.isNull("Unknown")) Unknown = json.getString("Unknown");
        if(!json.isNull("replay")) replay = json.getString("replay");
        if(!json.isNull("overworld")) overworld = json.getString("overworld");
        if(!json.isNull("nether")) nether = json.getString("nether");
        if(!json.isNull("end")) end = json.getString("end");
    }
}
