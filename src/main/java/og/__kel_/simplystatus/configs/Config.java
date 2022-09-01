package og.__kel_.simplystatus.configs;

import net.minecraft.client.MinecraftClient;
import og.__kel_.simplystatus.client.HotKeys;
import og.__kel_.simplystatus.configs.entity.Client;
import og.__kel_.simplystatus.configs.entity.Server;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Config {
    // Клиент
    public void load() {
        MinecraftClient mc = MinecraftClient.getInstance();
        try {
            String jsonContent = Files.readString(new File(mc.runDirectory + "/SimplyStatus/config.json").toPath());
            Client clientConfig = new Client(jsonContent);
            HotKeys.viewStatic = clientConfig.viewStatic;
            HotKeys.viewOffHand = clientConfig.viewOffHand;
            HotKeys.viewRPC = clientConfig.viewRPC;
            HotKeys.viewUsername = clientConfig.viewUsername;
            HotKeys.bedrock = clientConfig.bedrock;
            HotKeys.cringeIcons = clientConfig.cringe;
            HotKeys.showTime = clientConfig.showTime;
            HotKeys.changeStatusNameInMinecraft = clientConfig.changeStatusNameInMinecraft;
            HotKeys.lastTitle = clientConfig.changeStatusNameInMinecraft;
            HotKeys.viewVoice = clientConfig.viewVoice;
            HotKeys.viewReplayMod = clientConfig.viewReplayMod;
            HotKeys.viewMusicListening = clientConfig.viewMusicListening;
        } catch (Exception e) {
            save();
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
                    .put("title", HotKeys.changeStatusNameInMinecraft)
                    .put("voice", HotKeys.viewVoice)
                    .put("replay", HotKeys.viewReplayMod)
                    .put("music", HotKeys.viewMusicListening);
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
    // Сервера
    public void load(String serverAddress) {
        MinecraftClient mc = MinecraftClient.getInstance();
        serverAddress = serverAddress.replace(".", "_").replace(":", "-").toLowerCase();
        try {
            String jsonContent = Files.readString(new File(mc.runDirectory + "/SimplyStatus/servers/"+ serverAddress +".json").toPath());
            Server serverConfig = new Server(jsonContent);
            HotKeys.viewIPAddress = serverConfig.viewIP;
            HotKeys.viewName = serverConfig.viewName;
            HotKeys.customName = serverConfig.customName;
            HotKeys.customNameEnable = serverConfig.customNameEnable;
        } catch (Exception e) {
            save(serverAddress);
        }
    }
    public void save(String serverAddress) {
        MinecraftClient mc = MinecraftClient.getInstance();
        serverAddress = serverAddress.replace(".", "_").replace(":", "-").toLowerCase();

        final Path configFile = mc.runDirectory.toPath().resolve("SimplyStatus/servers/"+ serverAddress +".json");
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