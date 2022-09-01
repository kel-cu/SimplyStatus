package og.__kel_.simplystatus.server;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MainServer implements DedicatedServerModInitializer {
    public static final Logger log = LogManager.getLogger("SimplyStatus");
    private MinecraftServer server;
    @Override
    public void onInitializeServer() {
        log.info("[SimplyStatus] Dude! This mod is pre-designed for the customer!\n" +
                "The mod does not support server-side work...");
        log.info("[SimplyStatus] But... Maybe there will be support for servers, stay tuned and maybe for people like you there will be one :3");
    }

    public MinecraftServer getServer() {
        return server;
    }
}