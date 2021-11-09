package og.simplystatus.kel.simplystatus;

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
    private final Path configFile = mc.runDirectory.toPath().resolve("config/simplystatus.json");
    public void load() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(mc.runDirectory + "/config/simplystatus.json")) {
            SimplyStatusRoot root = gson.fromJson(reader, SimplyStatusRoot.class);
            SimplyStatusClient.ViewIP = root.getViewIP();
            SimplyStatusClient.ViewStatic = root.getViewStatic();
        } catch (Exception e) {
            save();
        }
    }
    public void save() {
        Gson gson = new Gson();
        JsonObject root = new JsonObject();
        root.addProperty("viewIP", SimplyStatusClient.ViewIP);
        root.addProperty("viewStatic", SimplyStatusClient.ViewStatic);
        String config = gson.toJson(root);
        try {
            Files.createDirectories(configFile.getParent());
            Files.write(configFile, config.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
