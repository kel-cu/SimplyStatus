package og.__kel_.simplystatus.server;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import og.__kel_.simplystatus.info.assets_rpc;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Timer;
import java.util.TimerTask;

public class MainServer implements DedicatedServerModInitializer {
    public static final Logger log = LogManager.getLogger("SimplyStatus");
    static DiscordRPC lib = DiscordRPC.INSTANCE;
    String applicationId = "903288390072557648";
    String steamId = "";
    DiscordEventHandlers handlers = new DiscordEventHandlers();
    Long startTime = System.currentTimeMillis() / 1000;
    Integer times = 0;
    Timer timer = new Timer();
    public static Boolean discordConnected = false;
    private MinecraftServer server;
    @Override
    public void onInitializeServer() {
        log.info("Mod initialization started u~u");
        log.warn("At the moment, the mod for servers is in alpha version! I (Simply_Kel), do not guarantee that your server will not fail, and will not breaking! Thank you for your attention!");
        log.warn("Have to you nice coffee u~u");
        ServerLifecycleEvents.SERVER_STARTED.register(minecraftServer -> {
            this.server = minecraftServer;
        });
        handlers.ready = (user) -> {
            discordConnected = true;
            log.info("The mod has been connected to Discord");
        };
        handlers.disconnected = (err, reason) -> {
            discordConnected = false;
            log.info("The mod has been pulled from Discord");
            log.error(reason);
        };
        lib.Discord_Initialize(applicationId, handlers, true, steamId);
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                lib.Discord_RunCallbacks();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {
                }
            }
        }, "RPC-Callback-Handler").start();
        startUpdate();
    }
    private void startUpdate(){
        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                updatePresence();
            }
        }, 2500, 2500);
    }

    public void updatePresence(){
        if(!discordConnected) return;
        DiscordRichPresence MainPresence = new DiscordRichPresence();
        MainPresence.largeImageKey = "unknown_world";
        if(server == null){
            MainPresence.details = "Server loading...";
        } else {
            var worldTime = server.getWorld(World.OVERWORLD).getLunarTime();
            var mcdays = 0;
            var tipotime = 0;
            var mctime = 0;
            if(worldTime > 24000){
                mcdays = (int) (worldTime / 24000);
                tipotime = mcdays * 24000;
                mctime = (int) (worldTime - tipotime);
            }else{
                mctime = (int) worldTime;
            }
            assets_rpc assets = new assets_rpc(true, false);
            if (mctime < 6000 && mctime > 0) {
                MainPresence.largeImageKey = assets.morning;
            } else if (mctime < 12000 && mctime > 6000) {
                MainPresence.largeImageKey = assets.day;
            } else if (mctime < 16500 && mctime > 12000) {
                MainPresence.largeImageKey = assets.evening;
            } else if (mctime < 24000 && mctime > 16500) {
                MainPresence.largeImageKey = assets.night;
            }

            MainPresence.details = server.getServerMotd();
            MainPresence.state = "Players: " + server.getCurrentPlayerCount() +" out of "+server.getMaxPlayerCount();
        }
        lib.Discord_UpdatePresence(MainPresence);
    }
}