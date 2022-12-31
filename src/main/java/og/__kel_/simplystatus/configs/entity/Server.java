package og.__kel_.simplystatus.configs.entity;

import og.__kel_.simplystatus.client.HotKeys;
import org.json.JSONException;
import org.json.JSONObject;

public class Server {
    public boolean viewIP;
    public boolean viewName;
    public boolean customNameEnable;
    public String customName;
    public boolean useAddon;
    public Server(String jsonContent) throws JSONException {
        JSONObject json = new JSONObject(jsonContent);
        if(!json.isNull("ip")) this.viewIP = json.getBoolean("ip");
        else this.viewIP = false;
        if(!json.isNull("name")) this.viewName = json.getBoolean("name");
        else this.viewName = true;
        if(!json.isNull("customName")) this.customName = json.getString("customName");
        else this.customName = "";
        if(!json.isNull("customNameEnable")) this.customNameEnable = json.getBoolean("customNameEnable");
        else this.customNameEnable = false;
        if(!json.isNull("useAddon")) this.useAddon = json.getBoolean("useAddon");
        else this.useAddon = true;
    }
}
