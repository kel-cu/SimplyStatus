package og.__kel_.simplystatus;

import net.minecraft.client.MinecraftClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SimplyStatusConfig {
    public void load() {
        MinecraftClient mc = MinecraftClient.getInstance();
        try {
            String jsonContent = Files.readString(new File(mc.runDirectory + "/SimplyStatus/config.json").toPath());
            ClientConfig clientConfig = new ClientConfig(jsonContent);
            SimplyStatusClient.ViewStatic = clientConfig.viewStatic;
            SimplyStatusClient.ViewOffHand = clientConfig.viewOffHand;
            SimplyStatusClient.ViewRPC = clientConfig.viewRPC;
            SimplyStatusClient.ViewUsername = clientConfig.viewUsername;
            SimplyStatusClient.Bedrock = clientConfig.bedrock;
            SimplyStatusClient.CringeIcons = clientConfig.cringe;
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
            SimplyStatusClient.ViewIPAddress = serverConfig.viewIP;
            SimplyStatusClient.ViewName = serverConfig.viewName;
        } catch (Exception e) {
            save(IPServer);
        }
    }
    public void save() {
        MinecraftClient mc = MinecraftClient.getInstance();
        final Path configFile = mc.runDirectory.toPath().resolve("SimplyStatus/config.json");
        JSONObject jsonConfig = new JSONObject();
        try{
            jsonConfig.put("static", SimplyStatusClient.ViewStatic);
            jsonConfig.put("offhand", SimplyStatusClient.ViewOffHand);
            jsonConfig.put("rpc", SimplyStatusClient.ViewRPC);
            jsonConfig.put("username", SimplyStatusClient.ViewUsername);
            jsonConfig.put("bedrock", SimplyStatusClient.Bedrock);
            jsonConfig.put("cringe", SimplyStatusClient.CringeIcons);
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
        serverConfig.put("ip", SimplyStatusClient.ViewIPAddress);
        serverConfig.put("name", SimplyStatusClient.ViewName);
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
    public ClientConfig(String jsonContent) throws JSONException {
        JSONObject json = new JSONObject(jsonContent);
        this.viewStatic = json.getBoolean("static");
        this.viewOffHand = json.getBoolean("offhand");
        this.viewRPC = json.getBoolean("rpc");
        this.viewUsername = json.getBoolean("username");
        this.bedrock = json.getBoolean("bedrock");
        this.cringe = json.getBoolean("cringe");
    }
}

class ServerConfig {
    public boolean viewIP;
    public boolean viewName;
    public ServerConfig(String jsonContent) throws JSONException {
        JSONObject json = new JSONObject(jsonContent);
        this.viewIP = json.getBoolean("ip");
        this.viewName = json.getBoolean("name");
    }
}