package og.__kel_.simplystatus.configs.entity;

import org.json.JSONException;
import org.json.JSONObject;

public class Client {

    public boolean viewStatic;
    public boolean viewOffHand;
    public boolean viewRPC;
    public boolean viewUsername;
    public boolean bedrock;
    public boolean cringe;
    public boolean showTime;
    public boolean viewPlasmoVoice;
    public Client(String jsonContent) throws JSONException {
        JSONObject json = new JSONObject(jsonContent);
        this.viewStatic = json.getBoolean("static");
        this.viewOffHand = json.getBoolean("offhand");
        this.viewRPC = json.getBoolean("rpc");
        this.viewUsername = json.getBoolean("username");
        this.bedrock = json.getBoolean("bedrock");
        this.cringe = json.getBoolean("cringe");
        this.showTime = json.getBoolean("showTime");
        this.viewPlasmoVoice = json.getBoolean("voice");
    }
}
