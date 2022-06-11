package og.__kel_.simplystatus;

import net.minecraft.client.MinecraftClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Config {
    public void load() {
        MinecraftClient mc = MinecraftClient.getInstance();
        try {
            String jsonContent = Files.readString(new File(mc.runDirectory + "/SimplyStatus/config.json").toPath());
            ClientConfig clientConfig = new ClientConfig(jsonContent);
            Client.ViewStatic = clientConfig.viewStatic;
            Client.ViewOffHand = clientConfig.viewOffHand;
            Client.ViewRPC = clientConfig.viewRPC;
            Client.ViewUsername = clientConfig.viewUsername;
            Client.Bedrock = clientConfig.bedrock;
            Client.CringeIcons = clientConfig.cringe;
            Client.showTime = clientConfig.showTime;
        } catch (Exception e) {
            save();
        }
    }
    public void load(String IPServer) {
        MinecraftClient mc = MinecraftClient.getInstance();
        IPServer = IPServer.replace(".", "_").replace(":", "-").toLowerCase();
        try {
            String jsonContent = Files.readString(new File(mc.runDirectory + "/SimplyStatus/servers/"+ IPServer +".json").toPath());
            ServerConfig serverConfig = new ServerConfig(jsonContent);
            Client.ViewIPAddress = serverConfig.viewIP;
            Client.ViewName = serverConfig.viewName;
            Client.customName = serverConfig.customName;
            Client.customNameEnable = serverConfig.customNameEnable;
        } catch (Exception e) {
            save(IPServer);
        }
    }
    public void save() {
        MinecraftClient mc = MinecraftClient.getInstance();
        final Path configFile = mc.runDirectory.toPath().resolve("SimplyStatus/config.json");
        JSONObject jsonConfig = new JSONObject();
        try{
            jsonConfig.put("static", Client.ViewStatic)
                    .put("offhand", Client.ViewOffHand)
                    .put("rpc", Client.ViewRPC)
                    .put("username", Client.ViewUsername)
                    .put("bedrock", Client.Bedrock)
                    .put("cringe", Client.CringeIcons)
                    .put("showTime", Client.showTime);
        } catch (JSONException ej){
            System.out.println(ej.getMessage());
        }
        try {
            Files.createDirectories(configFile.getParent());
            Files.writeString(configFile, jsonConfig.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void save(String IPServer) {
        MinecraftClient mc = MinecraftClient.getInstance();
        IPServer = IPServer.replace(".", "_").replace(":", "-").toLowerCase();

        final Path configFile = mc.runDirectory.toPath().resolve("SimplyStatus/servers/"+ IPServer +".json");
        JSONObject serverConfig = new JSONObject();
        try{
            serverConfig.put("ip", Client.ViewIPAddress)
                    .put("name", Client.ViewName)
                    .put("customName", Client.customName)
                    .put("customNameEnable", Client.customNameEnable);
        } catch (JSONException ej){
            System.out.println(ej.getMessage());
        }
        try {
            Files.createDirectories(configFile.getParent());
            Files.writeString(configFile, serverConfig.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientConfig {
    public boolean viewStatic;
    public boolean viewOffHand;
    public boolean viewRPC;
    public boolean viewUsername;
    public boolean bedrock;
    public boolean cringe;
    public boolean showTime;
    public ClientConfig(String jsonContent) throws JSONException {
        JSONObject json = new JSONObject(jsonContent);
        this.viewStatic = json.getBoolean("static");
        this.viewOffHand = json.getBoolean("offhand");
        this.viewRPC = json.getBoolean("rpc");
        this.viewUsername = json.getBoolean("username");
        this.bedrock = json.getBoolean("bedrock");
        this.cringe = json.getBoolean("cringe");
        this.showTime = json.getBoolean("showTime");
    }
}

class ServerConfig {
    public boolean viewIP;
    public boolean viewName;

    public boolean customNameEnable;
    public String customName;
    public ServerConfig(String jsonContent) throws JSONException {
        JSONObject json = new JSONObject(jsonContent);
        this.viewIP = json.getBoolean("ip");
        this.viewName = json.getBoolean("name");
        this.customName = json.getString("customName");
        this.customNameEnable = json.getBoolean("customNameEnable");
    }
}