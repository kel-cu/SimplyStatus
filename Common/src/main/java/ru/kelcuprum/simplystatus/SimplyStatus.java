package ru.kelcuprum.simplystatus;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.IPCListener;
import com.jagrosh.discordipc.entities.ActivityType;
import com.jagrosh.discordipc.entities.Packet;
import com.jagrosh.discordipc.entities.RichPresence;
import com.jagrosh.discordipc.entities.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.meteordev.starscript.value.Value;
import org.meteordev.starscript.value.ValueMap;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.api.events.alinlib.LocalizationEvents;
import ru.kelcuprum.alinlib.api.events.client.ClientLifecycleEvents;
import ru.kelcuprum.alinlib.api.events.client.ClientTickEvents;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.alinlib.config.Localization;
import ru.kelcuprum.simplystatus.config.Assets;
import ru.kelcuprum.simplystatus.config.ModConfig;
import ru.kelcuprum.simplystatus.info.Client;
import ru.kelcuprum.simplystatus.info.PresencePlayer;
import ru.kelcuprum.simplystatus.info.PresenceWorld;
import ru.kelcuprum.simplystatus.mods.WaterPlayerSupport;
import ru.kelcuprum.simplystatus.mods.Voice;
import ru.kelcuprum.simplystatus.presence.*;
import ru.kelcuprum.simplystatus.presence.singleplayer.*;
import ru.kelcuprum.simplystatus.presence.multiplayer.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static ru.kelcuprum.alinlib.WebAPI.getJsonArray;

public class SimplyStatus {
    // Mod Statud build
    public static String[] thanks = {};
    // User Configurations
    public static Config userConfig = new Config("config/SimplyStatus/config.json");
    public static Config serverConfig = new Config("config/SimplyStatus/servers/default.json");
    public static Localization localization = new Localization("simplystatus.presence", "config/SimplyStatus/lang");
    public static HashMap<String, Assets> assets = new HashMap<>();
    public static HashMap<String, Assets> modAssets = new HashMap<>();
    public static ArrayList<String> assetsNames = new ArrayList<>();
    // Another shit
    public static double isPEnable = Math.random();
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
    public static Boolean isVoiceModsEnable = false;
    public static Boolean isMusicModsEnable = false;
    // Discord
    public static RichPresence lastPresence;
    public static IPCClient client;
    public static User USER;
    public static String APPLICATION_ID;
    public static boolean CONNECTED = false;

    public static boolean GAME_STARTED = false;
    
    public static WaterPlayerSupport waterPlayerSupport = new WaterPlayerSupport();

    public static void onInitializeClient() {
        userConfig.load();
        serverConfig.load();
        try {
            ModConfig.load();
        } catch (Exception e) {
            log("The default configuration of the mod was not loaded, no launch possible!", Level.ERROR);
            log(e.getLocalizedMessage(), Level.ERROR);
            return;
        }
        Assets.loadFiles();
        TIME_STARTED_CLIENT = parseSeconds(System.currentTimeMillis());
        ClientLifecycleEvents.CLIENT_STARTED.register(client -> startClient());
        ClientLifecycleEvents.CLIENT_FULL_STARTED.register(client -> GAME_STARTED = true);
        ClientLifecycleEvents.CLIENT_STOPPING.register(client1 -> exitApplication());
        LocalizationEvents.DEFAULT_PARSER_INIT.register((parser) -> {
            parser.ss.set("player.scene", () -> Value.string(PresenceWorld.getScene()));
            parser.ss.set("player.name", () -> Value.string(PresencePlayer.getName()));
            parser.ss.set("discord", new ValueMap()
                    .set("name", () -> Value.string(SimplyStatus.USER.getName()))
                    .set("nickname", () -> Value.string(SimplyStatus.USER.getNickname()))
                    .set("discriminator", () -> Value.string(SimplyStatus.USER.getDiscriminator()))
                    .set("id", () -> Value.string(SimplyStatus.USER.getId()))
                    .set("avatar", () -> Value.string(SimplyStatus.USER.getAvatarUrl()))
            );
            if(SimplyStatus.isVoiceModsEnable){
                parser.ss.set("voice", new ValueMap()
                        .set("listener", () -> Value.string(new Voice().listener))
                );
            }
            if(SimplyStatus.replayMod){
                parser.ss.set("replay", new ValueMap()
                                        .set("date", () -> {
                                            String strDateFormat = SimplyStatus.localization.getLocalization("mod.replaymod.date_format", false);
                                            DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
                                            return Value.string(dateFormat.format(new ru.kelcuprum.simplystatus.mods.ReplayMod().date));
                                        })
//                        .set("date", () -> Value.string(getReplayDateFormat()))
                        .set("name", () -> Value.string(SimplyStatus.userConfig.getBoolean("VIEW_REPLAY_MOD_SERVER_NAME", true) ? new ru.kelcuprum.simplystatus.mods.ReplayMod().name : new ru.kelcuprum.simplystatus.mods.ReplayMod().address))
                );
            }
        });
        registerApplication();
    }

    public static long parseSeconds(long mills){
        return (mills-(mills % 1000)) /1000;
    }

    // -=-=-=-=-=-=-=-=-
    public static void startClient() {
        log(String.format("Client %s is up and running!", AlinLib.MINECRAFT.getLaunchedVersion()));
        try {
            JsonArray data = getJsonArray("https://api.kelcuprum.ru/boosty/thanks");
            thanks = ModConfig.jsonArrayToStringArray(data);
        } catch (Exception e) {
            log(e.getLocalizedMessage(), Level.ERROR);
        }
    }
    // -=-=-=-=-=-=-=-=-

    private static void registerApplication() {
        APPLICATION_ID = userConfig.getBoolean("USE_ANOTHER_ID", false) ? ModConfig.mineID : ModConfig.baseID;
        if (userConfig.getBoolean("USE_CUSTOM_APP_ID", false) && !userConfig.getString("CUSTOM_APP_ID", ModConfig.baseID).isBlank())
            APPLICATION_ID = userConfig.getString("CUSTOM_APP_ID", ModConfig.baseID);
        client = new IPCClient(Long.parseLong(APPLICATION_ID));
        setupListener();
        try {
            client.connect();
        } catch (Exception ex) {
            log(ex.getLocalizedMessage(), Level.ERROR);
        }
        ClientTickEvents.END_CLIENT_TICK.register(minecraft -> {
            try{
                updatePresence();
            } catch (Exception ex) {
                if (lastException == null || !lastException.equals(ex.getMessage())) {
                    lastException = ex.getMessage();
                    log(ex.getLocalizedMessage(), Level.ERROR);
                    RichPresence.Builder presence = new RichPresence.Builder()
                            .setActivityType(ActivityType.Competing)
                            .setDetails("There was an error, look in the console")
                            .setState("And report the bug on GitHub")
                            .setLargeImage(Assets.getSelected().getIcon("unknown"));
                    if(CONNECTED) sendPresence(presence.build());
                }
            }
        });
    }

    public static void exitApplication() {
        log("Client stopped");
        client.close();
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
                CONNECTED = true;
            }

            @Override
            public void onClose(IPCClient ipcClient, JsonObject jsonObject) {
                CONNECTED = false;
            }

            @Override
            public void onDisconnect(IPCClient ipcClient, Throwable throwable) {
                log("The mod has been pulled from Discord", Level.DEBUG);
                log(String.format("Reason: %s", throwable.getLocalizedMessage()), Level.DEBUG);
                CONNECTED = false;
            }
        });
    }



    private static void updatePresence() {
        if (userConfig.getBoolean("SHOW_RPC", true)) {
            if(waterPlayer) waterPlayerSupport.update();
            if (AlinLib.MINECRAFT.level != null && AlinLib.MINECRAFT.player != null) {
                if (AlinLib.MINECRAFT.isSingleplayer() || AlinLib.MINECRAFT.hasSingleplayerServer()) new SinglePlayer();
                else if (AlinLib.MINECRAFT.getCurrentServer() != null) new MultiPlayer();
                else if (SimplyStatus.replayMod && userConfig.getBoolean("VIEW_REPLAY_MOD", true)) new ReplayMod();
                else new Unknown();
            } else {
                switch (Client.getState()) {
                    case 1 -> new LoadingGame();
                    case 2 -> new Loading();
                    case 3 -> new Connect();
                    case 4 -> new Disconnect();
                    default -> new MainMenu();
                }
            }
        } else sendPresence(null);
    }

    public static void updateContentPresenceByConfigs(RichPresence.Builder presence) {
        updateContentPresenceByConfigs(presence, false);
    }

    public static void updateContentPresenceByConfigs(RichPresence.Builder presence, boolean isServer) {
        updateContentPresenceByConfigs(presence, isServer, false);
    }

    public static void updateContentPresenceByConfigs(RichPresence.Builder presence, boolean isServer, boolean isMenu) {
        if (SimplyStatus.userConfig.getBoolean("SHOW_GAME_TIME", true))
            presence.setStartTimestamp(SimplyStatus.TIME_STARTED_CLIENT);
        if (userConfig.getBoolean("VIEW_VOICE_SPEAK", false) && (isVoiceModsEnable && new Voice().isSpeak)) {
            Voice mod = new Voice();
            String info = mod.isSelfTalk ? localization.getLocalization("mod.voice", false) : mod.isOnePlayer ? localization.getLocalization("mod.voice.one", false) : localization.getLocalization("mod.voice.more", false);
            presence.setSmallImage(Assets.getSelected().getIcon("voice"), localization.getParsedText(info));
        } else if (userConfig.getBoolean("VIEW_MUSIC_LISTENER", false) && (isMusicModsEnable && !waterPlayerSupport.paused) && !isMenu) {
            presence.setSmallImage(Assets.getSelected().getIcon("music"), localization.getLocalization(waterPlayerSupport.artistIsNull ? "mod.music.noauthor" : "mod.music", true));
        } else if (isServer && (serverConfig.getBoolean("SHOW_ICON", false) && (!serverConfig.getString("ICON_URL", "").isEmpty()))) {
            presence.setSmallImage(serverConfig.getString("ICON_URL", "").replace("%address%", Objects.requireNonNull(AlinLib.MINECRAFT.getCurrentServer()).ip), localization.getParsedText("{player.scene}"));
        } else if (userConfig.getBoolean("SHOW_AVATAR_PLAYER", true)) {
            presence.setSmallImage(PresencePlayer.getURLAvatar(), PresencePlayer.getName());
        }

        if (SimplyStatus.userConfig.getBoolean("BUTTON.ENABLE", false)) {
            JsonArray buttons = new JsonArray();
            if (!SimplyStatus.userConfig.getString("BUTTON.LABEL", "").isBlank() && !SimplyStatus.userConfig.getString("BUTTON.URL", "").isBlank()) {
                JsonObject button = new JsonObject();
                button.addProperty("label", SimplyStatus.userConfig.getString("BUTTON.LABEL", ""));
                button.addProperty("url", SimplyStatus.userConfig.getString("BUTTON.URL", ""));
                buttons.add(button);
            }
            presence.setButtons(buttons);
        }
    }


    public static boolean EMPTY = true;

    public static void sendPresence(RichPresence presence) {
        if (!EMPTY && CONNECTED && presence == null) {
            if (lastPresence != null) exitApplication();
            lastPresence = null;
            EMPTY = true;
        } else if (presence != null && (lastPresence == null || (!lastPresence.toJson().toString().equalsIgnoreCase(presence.toJson().toString())))) {
            if (EMPTY) registerApplication();
            EMPTY = false;
            try {
                if (CONNECTED) client.sendRichPresence(presence);
                lastPresence = presence;
            } catch (Exception ex) {
                SimplyStatus.log(ex.getMessage() == null ? ex.getClass().getName() : ex.getMessage(), Level.ERROR);
            }
        }
    }

    public static void reconnectApp() {
        exitApplication();
        lastPresence = null;
        registerApplication();
    }
}