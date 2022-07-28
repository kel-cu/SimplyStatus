package og.__kel_.simplystatus.info;

public class Assets {
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
    public Assets(Boolean Bedrock, Boolean Cringe){
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
