package ru.kelcuprum.simplystatus;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.IPCListener;
import com.jagrosh.discordipc.entities.Packet;
import com.jagrosh.discordipc.entities.RichPresence;
import com.jagrosh.discordipc.entities.User;
import net.minecraft.client.Minecraft;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.config.Localization;
import ru.kelcuprum.simplystatus.config.AssetsConfig;
import ru.kelcuprum.simplystatus.config.ModConfig;
import ru.kelcuprum.simplystatus.info.Client;
import ru.kelcuprum.simplystatus.info.Player;
import ru.kelcuprum.simplystatus.localization.StarScript;
import ru.kelcuprum.simplystatus.mods.Music;
import ru.kelcuprum.simplystatus.mods.Voice;
import ru.kelcuprum.simplystatus.presence.singleplayer.Loading;
import ru.kelcuprum.simplystatus.presence.LoadingGame;
import ru.kelcuprum.simplystatus.presence.MainMenu;
import ru.kelcuprum.simplystatus.presence.ReplayMod;
import ru.kelcuprum.simplystatus.presence.Unknown;
import ru.kelcuprum.simplystatus.presence.multiplayer.Connect;
import ru.kelcuprum.simplystatus.presence.multiplayer.Disconnect;
import ru.kelcuprum.simplystatus.presence.multiplayer.MultiPlayer;
import ru.kelcuprum.simplystatus.presence.singleplayer.SinglePlayer;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import static ru.kelcuprum.alinlib.WebAPI.getJsonArray;

public class SimplyStatus {
    private static final Timer TIMER = new Timer();

    // Mod Statud build
    public static String[] thanks = {};
    // User Configurations
    public static Config userConfig = new Config("config/SimplyStatus/config.json");
    public static Config serverConfig = new Config("config/SimplyStatus/servers/default.json");
    public static Localization localization = new Localization("simplystatus.presence", "config/SimplyStatus/lang");
    public static AssetsConfig ASSETS = ModConfig.defaultAssets;
    // Another shit
    public static Minecraft MINECRAFT = Minecraft.getInstance();
    public static double isPEnable = Math.random();
    public static String[] apiNames = {
            "CraftHead",
            "Alina API: 2D",
            "Alina API: 3D"
    };
    public static boolean useAnotherID = false;
    public static boolean useCustomID = false;
    public static String customID = "";
    public static DecimalFormat DF = new DecimalFormat("#.##");
    private static String lastException;
    public static Long TIME_STARTED_CLIENT;
    // Logs
    public static final Logger LOG = LogManager.getLogger("SimplyStatus");

    public static void log(String message) {
        log(message, Level.INFO);
    }

    public static void log(String message, Level level) {
        LOG.log(level, "[" + LOG.getName() + "] " + message);
    }

    // Mods is present
    public static Boolean replayMod = false;
    public static Boolean waterPlayer = false;
    public static Boolean svc = false;
    public static Boolean plasmo = false;
    public static Boolean isVoiceModsEnable = (svc || plasmo);
    public static Boolean isMusicModsEnable = waterPlayer;
    // Discord
    public static RichPresence lastPresence;
    public static IPCClient client;
    public static User USER;
    public static String APPLICATION_ID;
    public static boolean CONNECTED_DISCORD = false;

    public static void onInitializeClient() {
        userConfig.load();
        serverConfig.load();
        localization.setParser((s) -> StarScript.run(StarScript.compile(s)));
        try {
            new ModConfig();
        } catch (Exception e) {
            log("The default configuration of the mod was not loaded, no launch possible!", Level.ERROR);
            log(e.getLocalizedMessage(), Level.ERROR);
            return;
        }
        useAnotherID = userConfig.getBoolean("USE_ANOTHER_ID", false);
        useCustomID = userConfig.getBoolean("USE_CUSTOM_APP_ID", false);
        TIME_STARTED_CLIENT = System.currentTimeMillis() / 1000;
        StarScript.init();
        registerApplications();
    }
    // -=-=-=-=-=-=-=-=-
    public static void startClient(){
        log(String.format("Client %s is up and running!", MINECRAFT.getLaunchedVersion()));
        try {
            JsonArray data = getJsonArray("https://api.kelcuprum.ru/boosty/thanks");
            thanks = ModConfig.jsonArrayToStringArray(data);
        } catch (Exception e){
            log(e.getLocalizedMessage(), Level.ERROR);
        }
    }
    public static void stopClient(){
        log("Client stopped");
        client.close();
    }
    // -=-=-=-=-=-=-=-=-

    private static void registerApplications() {
        APPLICATION_ID = userConfig.getBoolean("USE_ANOTHER_ID", false) ? ModConfig.mineID : ModConfig.baseID;
        if (userConfig.getBoolean("USE_CUSTOM_APP_ID", false) && !userConfig.getString("CUSTOM_APP_ID", ModConfig.baseID).isBlank())
            APPLICATION_ID = userConfig.getString("CUSTOM_APP_ID", ModConfig.baseID);
        customID = APPLICATION_ID;
        client = new IPCClient(Long.parseLong(APPLICATION_ID));
        setupListener();
        try {
            client.connect();
        } catch (Exception ex) {
            log(ex.getLocalizedMessage(), Level.ERROR);
        }
        start();
    }

    public static void setupListener() {
        client.setListener(new IPCListener() {
            @Override
            public void onPacketSent(IPCClient ipcClient, Packet packet) {

            }

            @Override
            public void onPacketReceived(IPCClient ipcClient, Packet packet) {

            }

            @Override
            public void onActivityJoin(IPCClient ipcClient, String s) {

            }

            @Override
            public void onActivitySpectate(IPCClient ipcClient, String s) {

            }

            @Override
            public void onActivityJoinRequest(IPCClient ipcClient, String s, User user) {

            }

            @Override
            public void onReady(IPCClient client) {
                log("The mod has been connected to Discord", Level.DEBUG);
                USER = client.getCurrentUser();
                CONNECTED_DISCORD = true;
            }

            @Override
            public void onClose(IPCClient ipcClient, JsonObject jsonObject) {
                CONNECTED_DISCORD = false;
            }

            @Override
            public void onDisconnect(IPCClient ipcClient, Throwable throwable) {
                log("The mod has been pulled from Discord", Level.DEBUG);
                log(String.format("Reason: %s", throwable.getLocalizedMessage()), Level.DEBUG);
                CONNECTED_DISCORD = false;
            }
        });
    }

    private static void start() {
        TIMER.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    if (CONNECTED_DISCORD) updatePresence();
                    if (lastException != null) lastException = null;
                } catch (Exception ex) {
                    if (lastException == null || !lastException.equals(ex.getMessage())) {
                        log(ex.getLocalizedMessage(), Level.ERROR);
                        RichPresence.Builder presence = new RichPresence.Builder()
                                .setDetails("There was an error, look in the console")
                                .setState("And report the bug on GitHub")
                                .setLargeImage("unknown_world");
                        client.sendRichPresence(presence.build());
                        lastException = ex.getMessage();
                    }
                }
            }
        }, 500, 500);
    }

    private static void updatePresence() {
        ASSETS = new AssetsConfig();
        if (userConfig.getBoolean("USE_CUSTOM_APP_ID", false)) ASSETS = ModConfig.defaultUrlsAssets;
        if (userConfig.getBoolean("USE_CUSTOM_ASSETS", false)) ASSETS.loadUserAssets();
        Minecraft CLIENT = Minecraft.getInstance();
        if (userConfig.getBoolean("ENABLE_RPC", true)) {
            if (CLIENT.level == null || CLIENT.player == null) {
                switch (Client.getState()) {
                    case 1 -> new LoadingGame();
                    case 2 -> new Loading();
                    case 3 -> new Connect();
                    case 4 -> new Disconnect();
                    default -> new MainMenu();
                }
            } else {
                if (CLIENT.isSingleplayer() || CLIENT.hasSingleplayerServer()) new SinglePlayer();
                else if (CLIENT.getCurrentServer() != null) new MultiPlayer();
                else if (SimplyStatus.replayMod && userConfig.getBoolean("VIEW_REPLAY_MOD", true)) new ReplayMod();
                else new Unknown();
            }
        } else {
            updateDiscordPresence(null);
        }
    }

    public static void updateContentPresenceByConfigs(RichPresence.Builder presence) {
        updateContentPresenceByConfigs(presence, false);
    }

    public static void updateContentPresenceByConfigs(RichPresence.Builder presence, boolean isServer) {

        if (SimplyStatus.userConfig.getBoolean("SHOW_GAME_TIME", true))
            presence.setStartTimestamp(SimplyStatus.TIME_STARTED_CLIENT);
        ///
        if (userConfig.getBoolean("VIEW_VOICE_SPEAK", false) && (isVoiceModsEnable && new Voice().isSpeak)) {
            Voice mod = new Voice();
            String info = mod.isSelfTalk ? localization.getLocalization("mod.voice", false) : mod.isOnePlayer ? localization.getLocalization("mod.voice.one", false) : localization.getLocalization("mod.voice.more", false);
            presence.setSmallImage(ASSETS.voice, localization.getParsedText(info));
        } else if (userConfig.getBoolean("VIEW_MUSIC_LISTENER", false) && (isMusicModsEnable && !new Music().paused)) {
            presence.setSmallImage(ASSETS.music, localization.getLocalization(new Music().artistIsNull ? "mod.music.noauthor" : "mod.music", true));
        } else if (isServer && (serverConfig.getBoolean("SHOW_ICON", false) && (!serverConfig.getString("ICON_URL", "").isEmpty()))) {
            presence.setSmallImage(serverConfig.getString("ICON_URL", "").replace("%address%", MINECRAFT.getCurrentServer().ip), localization.getParsedText("{player.scene}"));
        } else if (userConfig.getBoolean("SHOW_AVATAR_PLAYER", true)) {
            presence.setSmallImage(Player.getURLAvatar(), Player.getName());
        }
    }

    public static void updateDiscordPresence(RichPresence presence) {
        if (presence == null && ModConfig.debugPresence) LOG.info("Presence is null!");
        if (lastPresence == null || !lastPresence.equals(presence)) {
            lastPresence = presence;
            if (CONNECTED_DISCORD) client.sendRichPresence(presence);
            if (ModConfig.debugPresence) LOG.info("Update presence");
        }
    }

    public static void reconnectApp() {
        if (SimplyStatus.userConfig.getBoolean("USE_CUSTOM_APP_ID", false) && !SimplyStatus.customID.equals(SimplyStatus.userConfig.getString("CUSTOM_APP_ID", ModConfig.baseID))) {
            SimplyStatus.useCustomID = true;
            String APPLICATION_ID = SimplyStatus.userConfig.getString("CUSTOM_APP_ID", ModConfig.baseID);
            if (APPLICATION_ID.isBlank()) {
                APPLICATION_ID = ModConfig.baseID;
                SimplyStatus.userConfig.setString("CUSTOM_APP_ID", APPLICATION_ID);
            }
            if (!SimplyStatus.customID.equals(APPLICATION_ID)) {
                SimplyStatus.customID = APPLICATION_ID;
                SimplyStatus.client.close();
                SimplyStatus.client = new IPCClient(Long.parseLong(APPLICATION_ID));
                SimplyStatus.setupListener();
                try {
                    SimplyStatus.client.connect();
                } catch (Exception ex) {
                    log(ex.getLocalizedMessage(), Level.ERROR);
                }
                SimplyStatus.lastPresence = null;
            }
        } else if ((SimplyStatus.useAnotherID != SimplyStatus.userConfig.getBoolean("USE_ANOTHER_ID", false)) || (SimplyStatus.useCustomID != SimplyStatus.userConfig.getBoolean("USE_CUSTOM_APP_ID", false))) {
            SimplyStatus.useAnotherID = SimplyStatus.userConfig.getBoolean("USE_ANOTHER_ID", false);
            SimplyStatus.useCustomID = SimplyStatus.userConfig.getBoolean("USE_CUSTOM_APP_ID", false);
            SimplyStatus.customID = "";
            String APPLICATION_ID = SimplyStatus.userConfig.getBoolean("USE_ANOTHER_ID", false) ? ModConfig.mineID : ModConfig.baseID;
            SimplyStatus.client.close();
            SimplyStatus.client = new IPCClient(Long.parseLong(APPLICATION_ID));
            SimplyStatus.setupListener();
            try {
                SimplyStatus.client.connect();
            } catch (Exception ex) {
                log(ex.getLocalizedMessage(), Level.ERROR);
            }
            SimplyStatus.lastPresence = null;
        }
    }
}