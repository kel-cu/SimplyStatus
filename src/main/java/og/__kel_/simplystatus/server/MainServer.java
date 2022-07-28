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
        log.info("Mod initialization started u~u");
        log.warn("At the moment, the mod for servers is in alpha version! I (Simply_Kel), do not guarantee that your server will not fail, and will not breaking! Thank you for your attention!");
        log.warn("Have to you nice coffee u~u");
        ServerLifecycleEvents.SERVER_STARTED.register(server -> {
            this.server = server;
        });
        onUpdatePresence();
    }
    public void onUpdatePresence(){

    }

    public MinecraftServer getServer() {
        return server;
    }
}