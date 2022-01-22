package og.__kel_.simplystatus;

import club.minnced.discord.rpc.*;
import net.fabricmc.api.ModInitializer;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import net.minecraft.client.MinecraftClient;
import net.minecraft.network.MessageType;
import net.minecraft.text.LiteralText;
// Presences
import og.__kel_.simplystatus.presences.Menu;
import og.__kel_.simplystatus.presences.MultiPlayer;
import og.__kel_.simplystatus.presences.SinglePlayer;

public class SimplyStatusMain implements ModInitializer {
    DiscordRPC lib = DiscordRPC.INSTANCE;
    String applicationId = "903288390072557648";
    String steamId = "";
    DiscordEventHandlers handlers = new DiscordEventHandlers();
    Long start_time = System.currentTimeMillis() / 1000;

    static MinecraftClient mc = MinecraftClient.getInstance();
    SimplyStatusTranslate Translate = new SimplyStatusTranslate();

    Integer times = 0;
    Timer timer = new Timer();
    public static String DiscordName = "<unknown>";
    @Override
    public void onInitialize() {
        Translate.selectedLang();
        SimplyStatusConfig cfg = new SimplyStatusConfig();
        cfg.load();

        handlers.ready = (user) -> {
            System.out.println("SimplyStatus running");
            DiscordName = user.username;
        };

        lib.Discord_Initialize(applicationId, handlers, true, steamId);

        MainPresenceBasic();
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
    private void MainPresenceBasic() {
        new Menu(lib, mc, Translate, start_time);
    }
    private void updatePresence() {
        if(SimplyStatusClient.ViewRPC) {
            SimplyStatusConfig cfg = new SimplyStatusConfig();
            Translate.selectedLang();
            if (mc.world == null) {
                MainPresenceBasic();
            } else {
                boolean isSinglePlayer = mc.isInSingleplayer();
                if (isSinglePlayer) {
                    new SinglePlayer(lib, mc, Translate, start_time, cfg);
                } else {

                    new MultiPlayer(lib, mc, Translate, start_time, cfg);
                }
            }
        } else {
            lib.Discord_UpdatePresence(null);
        }
    }
    public static String[] OhNoCringe =  {
            "§4",
            "§c",
            "§6",
            "§e",
            "§z",
            "§a",
            "§b",
            "§3",
            "§1",
            "§9",
            "§d",
            "§5",
            "§f",
            "§7",
            "§8",
            "§0",
            "§r",
            "§l",
            "§o",
            "§n",
            "§m",
            "§k"
    };
}

