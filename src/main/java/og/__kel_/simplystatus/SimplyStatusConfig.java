package og.__kel_.simplystatus;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.client.MinecraftClient;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class SimplyStatusConfig {
    MinecraftClient mc = MinecraftClient.getInstance();
    public void load() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(mc.runDirectory + "/config/SimplyStatus-Configs/main.json")) {
            ClientConfig root = gson.fromJson(reader, ClientConfig.class);
            SimplyStatusClient.ViewStatic = root.getViewStatic();
            SimplyStatusClient.ViewOffHand = root.getViewOffHand();
            SimplyStatusClient.ViewRPC = root.getViewRPC();
            SimplyStatusClient.ViewUsername = root.getViewUsername();
            save();
        } catch (Exception e) {
            load_old();
        }
    }
    public void load_old() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(mc.runDirectory + "/config/simplystatus.json")) {
            ClientConfig root = gson.fromJson(reader, ClientConfig.class);
            SimplyStatusClient.ViewStatic = root.getViewStatic();
            SimplyStatusClient.ViewOffHand = root.getViewOffHand();
            SimplyStatusClient.ViewRPC = true;
            SimplyStatusClient.ViewUsername = true;
            save();
        } catch (Exception e) {
            save();
        }
    }
    public void load(String IPServer) {
        IPServer = IPServer.replace(".", "-");
        IPServer = IPServer.replace(":", "-");
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(mc.runDirectory + "/config/SimplyStatus-Servers/"+ IPServer +".json")) {
            ServerConfig root = gson.fromJson(reader, ServerConfig.class);
            SimplyStatusClient.ViewIPAddress = root.getViewIP();
            SimplyStatusClient.ViewName = root.getViewName();
        } catch (Exception e) {
            save(IPServer);
        }
    }
    public void save() {
        final Path configFile = mc.runDirectory.toPath().resolve("config/SimplyStatus-Configs/main.json");
        Gson gson = new Gson();
        JsonObject root = new JsonObject();
        root.addProperty("viewStatic", SimplyStatusClient.ViewStatic);
        root.addProperty("viewOffHand", SimplyStatusClient.ViewOffHand);
        root.addProperty("viewRPC", SimplyStatusClient.ViewRPC);
        root.addProperty("viewUsername", SimplyStatusClient.ViewUsername);
        String config = gson.toJson(root);
        try {
            Files.createDirectories(configFile.getParent());
            Files.write(configFile, config.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void save(String IPServer) {
        IPServer = IPServer.replace(".", "-");
        IPServer = IPServer.replace(":", "-");
        final Path configFile = mc.runDirectory.toPath().resolve("config/SimplyStatus-Servers/"+ IPServer +".json");
        Gson gson = new Gson();
        JsonObject root = new JsonObject();
        root.addProperty("viewIP", SimplyStatusClient.ViewIPAddress);
        root.addProperty("viewName", SimplyStatusClient.ViewName);
        String config = gson.toJson(root);
        try {
            Files.createDirectories(configFile.getParent());
            Files.write(configFile, config.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ClientConfig {
    private boolean viewStatic;
    private boolean viewOffHand;
    private boolean viewRPC;
    private boolean viewUsername;

    public boolean getViewStatic() { return viewStatic; }
    public boolean getViewOffHand() { return viewOffHand; }
    public boolean getViewRPC() {return viewRPC;}
    public boolean getViewUsername() {return viewUsername;}

    public void setViewStatic(boolean stat) {
        this.viewStatic = stat;
    }
    public void setViewOffHand(boolean stat) {
        this.viewOffHand = stat;
    }
    public void setViewRPC(boolean stat) { this.viewRPC = stat;}
    public void setViewUsername(boolean stat) { this.viewUsername = stat;}
}

class ServerConfig {
    private boolean viewIP;
    private boolean viewName;

    public boolean getViewIP() { return viewIP; }
    public boolean getViewName() { return viewName; }

    public void setViewIP(boolean stat) {
        this.viewIP = stat;
    }
    public void setViewName(boolean stat) {
        this.viewName = stat;
    }
}