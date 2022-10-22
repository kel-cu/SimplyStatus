package og.__kel_.simplystatus.client;

import club.minnced.discord.rpc.*;

import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.IPCListener;
import com.jagrosh.discordipc.entities.RichPresence;
import com.jagrosh.discordipc.exceptions.NoDiscordClientException;
import net.fabricmc.api.ClientModInitializer;

//
import java.time.OffsetDateTime;
import java.util.Timer;
import java.util.TimerTask;

import net.fabricmc.loader.api.FabricLoader;
import og.__kel_.simplystatus.Main;
import og.__kel_.simplystatus.configs.Config;
import og.__kel_.simplystatus.Translate;
import og.__kel_.simplystatus.presences.*;
import og.__kel_.simplystatus.presences.multi.Connect;
import og.__kel_.simplystatus.presences.multi.Disconnect;
import og.__kel_.simplystatus.presences.multi.MultiPlayer;
import og.__kel_.simplystatus.presences.single.ProgressScreenPresence;
import og.__kel_.simplystatus.presences.single.SinglePlayer;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.minecraft.client.MinecraftClient;
// Presences


public class MainClient implements ClientModInitializer {
    public static final Logger log = LogManager.getLogger("SimplyStatus");
    public static DiscordRPC lib = DiscordRPC.INSTANCE;
    public static String applicationId = "903288390072557648";
    // SimplyStatus = 903288390072557648
    // Minecraft = 1004398909810016276
    //String applicationId = "1004398909810016276";
    String steamId = "";
    public static DiscordEventHandlers handlers = new DiscordEventHandlers();
    Long startTime = System.currentTimeMillis() / 1000;
    MinecraftClient client = MinecraftClient.getInstance();
    static Translate Translate = new Translate();
    public static Translate localization = Translate;
    public static DiscordUser player;
    Integer times = 0;
    Timer timer = new Timer();
    public static String DiscordName = "";
    public static Boolean lastMessageDeath = false;
    public static String lastTextDeath = "";
    public static Boolean discordConnected = false;
    public static Boolean musicPlayer = FabricLoader.getInstance().getModContainer("musicplayer").isPresent();
    public static Boolean replayMod = FabricLoader.getInstance().getModContainer("replaymod").isPresent();
    public static Boolean plasmoVoice = FabricLoader.getInstance().getModContainer("plasmo_voice").isPresent();
    // Simple Voice Chat
    public static Boolean svc = FabricLoader.getInstance().getModContainer("voicechat").isPresent();

    @Override
    public void onInitializeClient() {
        //new Game().checkLicense(MinecraftClient.getInstance());
        log.info("I'm started work UwU");
        Translate.selectedLang();
        Config config = new Config();
        config.load();
        if(HotKeys.changeStatusNameInMinecraft){
            applicationId = "1004398909810016276";
            log.info("[SimplyStatus] The app has been changed to an app called \"Minecraft\"");
        }
        handlers.ready = (user) -> {
            log.info("The mod has been connected to Discord");
            player = user;
            DiscordName = user.username;
            discordConnected = true;
        };
        handlers.disconnected = (err, reason) -> {
            log.info("The mod has been pulled from Discord");
            log.error("Reason: "+reason);

            discordConnected = false;
        };
        handlers.joinRequest = (user) -> {
            log.info("JOIN REQUEST: "+user.userId);
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
        }, "SimplyStatus RPC-Callback-Handler").start();
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
        new Menu(lib, client, Translate, startTime);
    }
    private void updatePresence() {
        try{
            if(!discordConnected) return;
            if(HotKeys.viewRPC) {
                Config cfg = new Config();
                Translate.selectedLang();
                if (client.world == null) {
                    if(!HotKeys.customName.equals("")){
                        HotKeys.customName = "";
                        HotKeys.customNameEnable = false;
                    }
                    if(Main.getGameState() == 1){
                        new ProgressScreenPresence(lib, client, Translate, startTime);
                    } else if(Main.getGameState() == 2){
                        new Connect(lib, client, Translate, startTime, cfg);
                    } else if(Main.getGameState() == 3){
                        new Disconnect(lib, client, Translate, startTime, cfg);
                    } else {
                        MainPresenceBasic();
                    }
                } else {
                    boolean isSinglePlayer = client.isInSingleplayer();
                    if (isSinglePlayer) {
                        new SinglePlayer(lib, client, Translate, startTime, cfg);
                    } else if(client.getCurrentServerEntry() != null){
                        new MultiPlayer(lib, client, Translate, startTime, cfg);
                    } else if(replayMod){
                        new ReplayMod(lib, client, Translate, startTime, cfg);
                    } else {
                        new UnknownScene(lib, client, Translate, startTime);
                    }
                }
            } else {
                lib.Discord_UpdatePresence(null);
            }
        } catch(NullPointerException err){
            if(HotKeys.viewRPC) {
                new UnknownScene(lib, client, Translate, startTime, err);
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

