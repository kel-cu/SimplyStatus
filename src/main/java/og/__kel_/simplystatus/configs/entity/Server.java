package og.__kel_.simplystatus.configs.entity;

import og.__kel_.simplystatus.client.HotKeys;
import org.json.JSONException;
import org.json.JSONObject;

public class Server {
    public boolean viewIP;
    public boolean viewName;
    public boolean customNameEnable;
    public String customName;
    public Server(String jsonContent) throws JSONException {
        JSONObject json = new JSONObject(jsonContent);
        this.viewIP = json.getBoolean("ip");
        this.viewName = json.getBoolean("name");
        this.customName = json.getString("customName");
        this.customNameEnable = json.getBoolean("customNameEnable");
    }
}
