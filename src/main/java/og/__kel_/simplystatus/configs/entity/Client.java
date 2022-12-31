package og.__kel_.simplystatus.configs.entity;

import org.json.JSONException;
import org.json.JSONObject;

public class Client {

    public boolean viewStatic = true;
    public boolean viewOffHand = false;
    public boolean viewRPC = true;
    public boolean viewUsername = true;
    public boolean bedrock = false;
    public boolean cringe = false;
    public boolean showTime = true;
    public boolean changeStatusNameInMinecraft = false;
    public boolean viewVoice = false;
    public boolean viewMusicListening = false;
    public boolean viewReplayMod = false;
    public boolean showAvatar = false;
    public boolean useCustomAssets = false;
    public Client(String jsonContent) throws JSONException {
        JSONObject json = new JSONObject(jsonContent);
        if(!json.isNull("static")) this.viewStatic = json.getBoolean("static");
        if(!json.isNull("offhand")) this.viewOffHand = json.getBoolean("offhand");
        if(!json.isNull("rpc")) this.viewRPC = json.getBoolean("rpc");
        if(!json.isNull("username")) this.viewUsername = json.getBoolean("username");
        if(!json.isNull("bedrock")) this.bedrock = json.getBoolean("bedrock");
        if(!json.isNull("cringe")) this.cringe = json.getBoolean("cringe");
        if(!json.isNull("showTime")) this.viewRPC = json.getBoolean("showTime");
        if(!json.isNull("title")) this.changeStatusNameInMinecraft = json.getBoolean("title");
        if(!json.isNull("voice")) this.viewVoice = json.getBoolean("voice");
        if(!json.isNull("music")) this.viewMusicListening = json.getBoolean("music");
        if(!json.isNull("replay")) this.viewReplayMod = json.getBoolean("replay");
        if(!json.isNull("avatar")) this.showAvatar = json.getBoolean("avatar");
        if(!json.isNull("customAssets")) this.useCustomAssets = json.getBoolean("customAssets");
    }
}
