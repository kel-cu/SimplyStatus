package og.__kel_.simplystatus.configs;

import net.minecraft.client.MinecraftClient;
import og.__kel_.simplystatus.Main;
import og.__kel_.simplystatus.Translate;
import og.__kel_.simplystatus.client.HotKeys;
import og.__kel_.simplystatus.client.MainClient;
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
            Main.viewStatic = clientConfig.viewStatic;
            Main.viewOffHand = clientConfig.viewOffHand;
            Main.viewRPC = clientConfig.viewRPC;
            Main.viewUsername = clientConfig.viewUsername;
            Main.bedrock = clientConfig.bedrock;
            Main.cringeIcons = clientConfig.cringe;
            Main.showTime = clientConfig.showTime;
            Main.changeStatusNameInMinecraft = clientConfig.changeStatusNameInMinecraft;
            Main.lastTitle = clientConfig.changeStatusNameInMinecraft;
            Main.viewVoice = clientConfig.viewVoice;
            Main.viewReplayMod = clientConfig.viewReplayMod;
            Main.viewMusicListening = clientConfig.viewMusicListening;
            Main.showAvatar = clientConfig.showAvatar;
            Main.useCustomAssets = clientConfig.useCustomAssets;
        } catch (Exception e) {
            save();
        }
    }
    public void save() {
        MinecraftClient mc = MinecraftClient.getInstance();
        final Path configFile = mc.runDirectory.toPath().resolve("SimplyStatus/config.json");
        JSONObject jsonConfig = new JSONObject();
        try{
            jsonConfig.put("static", Main.viewStatic)
                    .put("offhand", Main.viewOffHand)
                    .put("rpc", Main.viewRPC)
                    .put("username", Main.viewUsername)
                    .put("bedrock", Main.bedrock)
                    .put("cringe", Main.cringeIcons)
                    .put("showTime", Main.showTime)
                    .put("title", Main.changeStatusNameInMinecraft)
                    .put("voice", Main.viewVoice)
                    .put("replay", Main.viewReplayMod)
                    .put("music", Main.viewMusicListening)
                    .put("avatar", Main.showAvatar)
                    .put("customAssets", Main.useCustomAssets);
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
            HotKeys.useAddon = serverConfig.useAddon;
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
                    .put("customNameEnable", HotKeys.customNameEnable)
                    .put("useAddon", HotKeys.useAddon);
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
    public static void saveGUI(){
        MainClient.log.info("Saves configurations!");
        MinecraftClient mc = MinecraftClient.getInstance();
        ConfigScreen.config.save();
        ConfigScreen.assetsConfig.save();
        if(Main.lastTitle != Main.changeStatusNameInMinecraft){
            Main.lastTitle = Main.changeStatusNameInMinecraft;
            if(Main.changeStatusNameInMinecraft){
                MainClient.lib.Discord_ClearPresence();
                MainClient.lib.Discord_Shutdown();
                MainClient.applicationId = "1004398909810016276";
            } else {
                MainClient.lib.Discord_ClearPresence();
                MainClient.lib.Discord_Shutdown();
                MainClient.applicationId = "903288390072557648";
            }
        }
        MainClient.lib.Discord_Initialize(MainClient.applicationId, MainClient.handlers, true, "");
        ConfigScreen.translate.save();
        if(mc.world != null){
            if(!mc.isInSingleplayer() && mc.getCurrentServerEntry() != null){
                MainClient.log.info(mc.getCurrentServerEntry().address + " saves configurations!");
                ConfigScreen.config.save(mc.getCurrentServerEntry().address);
            }
        }
    }

}