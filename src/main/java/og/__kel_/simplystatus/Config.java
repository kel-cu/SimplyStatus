package og.__kel_.simplystatus;

import net.minecraft.client.MinecraftClient;
import og.__kel_.simplystatus.client.HotKeys;
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
            HotKeys.viewStatic = clientConfig.viewStatic;
            HotKeys.viewOffHand = clientConfig.viewOffHand;
            HotKeys.viewRPC = clientConfig.viewRPC;
            HotKeys.viewUsername = clientConfig.viewUsername;
            HotKeys.bedrock = clientConfig.bedrock;
            HotKeys.cringeIcons = clientConfig.cringe;
            HotKeys.showTime = clientConfig.showTime;
            HotKeys.viewPlasmoVoice = clientConfig.viewPlasmoVoice;
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
            HotKeys.viewIPAddress = serverConfig.viewIP;
            HotKeys.viewName = serverConfig.viewName;
            HotKeys.customName = serverConfig.customName;
            HotKeys.customNameEnable = serverConfig.customNameEnable;
        } catch (Exception e) {
            save(IPServer);
        }
    }
    public void save() {
        MinecraftClient mc = MinecraftClient.getInstance();
        final Path configFile = mc.runDirectory.toPath().resolve("SimplyStatus/config.json");
        JSONObject jsonConfig = new JSONObject();
        try{
            jsonConfig.put("static", HotKeys.viewStatic)
                    .put("offhand", HotKeys.viewOffHand)
                    .put("rpc", HotKeys.viewRPC)
                    .put("username", HotKeys.viewUsername)
                    .put("bedrock", HotKeys.bedrock)
                    .put("cringe", HotKeys.cringeIcons)
                    .put("showTime", HotKeys.showTime)
                    .put("voice", HotKeys.viewPlasmoVoice);
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
            serverConfig.put("ip", HotKeys.viewIPAddress)
                    .put("name", HotKeys.viewName)
                    .put("customName", HotKeys.customName)
                    .put("customNameEnable", HotKeys.customNameEnable);
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
    public boolean viewPlasmoVoice;
    public ClientConfig(String jsonContent) throws JSONException {
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