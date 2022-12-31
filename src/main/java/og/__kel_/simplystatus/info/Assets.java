package og.__kel_.simplystatus.info;

import og.__kel_.simplystatus.configs.AssetsConfig;

public class Assets {
    public String music = "music";
    public String day = "day_update_2";
    public String night = "nigth_update_2";
    public String morning = "morning_update_2";
    public String evening = "evening_update_2";
    public String logo = "logo";
    public String Unknown = "unknown_world";
    public String replay = "replaymod_logo";
    public String overworld = "overworld";
    public String nether = "nether";
    public String end = "end";
    public Assets(Boolean Custom, Boolean Bedrock, Boolean Cringe){
        if(Custom){
            AssetsConfig config = new AssetsConfig();
            this.day = config.day;
            this.night = config.night;
            this.morning = config.morning;
            this.evening = config.evening;
            this.logo = config.logo;
            this.overworld = config.overworld;
            this.nether = config.nether;
            this.end = config.end;
            this.music = config.music;
            this.replay = config.replay;
            this.Unknown = config.Unknown;
            return;
        }
        if(Bedrock){
            this.day = "day_be";
            this.night = "night_be";
            this.morning = "morning_be";
            this.evening = "evening_be";
            this.logo = "logo_be";
        }
        if(Cringe){
            this.overworld = "overworld_icon";
            this.nether = "nether_icon";
            this.end = "end_icon";
        }
    }
}
