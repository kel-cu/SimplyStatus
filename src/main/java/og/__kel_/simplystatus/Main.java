package og.__kel_.simplystatus;

import club.minnced.discord.rpc.*;

import net.fabricmc.api.ModInitializer;

//
import java.util.Timer;
import java.util.TimerTask;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.minecraft.client.MinecraftClient;
// Presences
import og.__kel_.simplystatus.presences.Menu;
import og.__kel_.simplystatus.presences.MultiPlayer;
import og.__kel_.simplystatus.presences.SinglePlayer;
import og.__kel_.simplystatus.presences.UnknownScene;

public class Main implements ModInitializer {
    public static final Logger log = LogManager.getLogger("SimplyStatus");
    static DiscordRPC lib = DiscordRPC.INSTANCE;
    String applicationId = "903288390072557648";
    String steamId = "";
    DiscordEventHandlers handlers = new DiscordEventHandlers();
    Long startTime = System.currentTimeMillis() / 1000;
    MinecraftClient mc = MinecraftClient.getInstance();
    static Translate Translate = new Translate();
    public static Translate localization = Translate;
    public static DiscordUser player;
    Integer times = 0;
    Timer timer = new Timer();
    public static String DiscordName = "";
    public static Boolean lastMessageDeath = false;
    public static String lastTextDeath = "";
    public static Boolean discordConnected = false;

    @Override
    public void onInitialize() {
        log.info("I'm started work UwU");
        Translate.selectedLang();
        Config config = new Config();
        config.load();

        handlers.ready = (user) -> {
            log.info("Discord Connected");
            player = user;
            DiscordName = user.username;
            discordConnected = true;
        };
        handlers.disconnected = (err, user) -> {
            log.error(user);
            discordConnected = false;
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
    private void MainPresenceBasic() {
        new Menu(lib, mc, Translate, startTime);
    }
    private void updatePresence() {
        try{
            if(!discordConnected) return;
            if(Client.ViewRPC) {
                Config cfg = new Config();
                Translate.selectedLang();
                if (mc.world == null) {
                    MainPresenceBasic();
                } else {
                    boolean isSinglePlayer = mc.isInSingleplayer();
                    if (isSinglePlayer) {
                        new SinglePlayer(lib, mc, Translate, startTime, cfg);
                    } else {
                        new MultiPlayer(lib, mc, Translate, startTime, cfg);
                    }
                }
            } else {
                lib.Discord_UpdatePresence(null);
            }
        } catch(NullPointerException err){
            if(err.toString().contains("Cannot read field \"username\" because \"og.__kel_.simplystatus.Main.player\" is null")){
                return;
            }else if(Client.ViewRPC) {
                new UnknownScene(lib, mc, Translate, startTime, err);
            } else {
                lib.Discord_UpdatePresence(null);
            }
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

