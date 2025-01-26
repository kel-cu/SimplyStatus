package ru.kelcuprum.sailstatus;

import com.google.gson.*;
import com.jagrosh.discordipc.*;
import com.jagrosh.discordipc.entities.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.*;
import org.meteordev.starscript.value.*;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.api.events.alinlib.LocalizationEvents;
import ru.kelcuprum.alinlib.api.events.client.*;
import ru.kelcuprum.alinlib.config.*;
import ru.kelcuprum.sailstatus.config.*;
import ru.kelcuprum.sailstatus.info.*;
import ru.kelcuprum.sailstatus.mods.*;
import ru.kelcuprum.sailstatus.presence.*;
import ru.kelcuprum.sailstatus.presence.ingame.MultiPlayer;
import ru.kelcuprum.sailstatus.presence.ingame.SaveWorld;
import ru.kelcuprum.sailstatus.presence.ingame.SinglePlayer;
import ru.kelcuprum.sailstatus.presence.menu.Connect;
import ru.kelcuprum.sailstatus.presence.menu.Disconnect;
import ru.kelcuprum.sailstatus.presence.menu.Loading;
import ru.kelcuprum.sailstatus.presence.menu.MainMenu;
import ru.kelcuprum.sailstatus.presence.mods.Flashback;
import ru.kelcuprum.sailstatus.presence.mods.ReplayMod;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class SailStatus implements ClientModInitializer {
    // User Configurations
    public static Config userConfig = new Config("config/SailStatus/config.json");
    public static Config serverConfig = new Config("config/SailStatus/servers/default.json");
    public static Localization localization = new Localization("sailstatus.presence", "config/SailStatus/lang");
    public static HashMap<String, Assets> assets = new HashMap<>();
    public static HashMap<String, Assets> modAssets = new HashMap<>();
    public static ArrayList<String> assetsNames = new ArrayList<>();
    // Another shit
    public static double isPEnable = Math.random();
    private static String lastException;
    public static Long TIME_STARTED_CLIENT = 0L;
    // Logs
    public static final Logger LOG = LogManager.getLogger("SailStatus");

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
    public static Boolean klashback = false;
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

    @Override
    public void onInitializeClient() {
        SailStatus.replayMod = FabricLoader.getInstance().getModContainer("replaymod").isPresent();
        SailStatus.waterPlayer = FabricLoader.getInstance().getModContainer("waterplayer").isPresent();
        SailStatus.svc = FabricLoader.getInstance().getModContainer("voicechat").isPresent();
        SailStatus.plasmo = FabricLoader.getInstance().getModContainer("plasmovoice").isPresent();
        SailStatus.klashback = FabricLoader.getInstance().getModContainer("flashback").isPresent();
        SailStatus.isVoiceModsEnable = (SailStatus.svc || SailStatus.plasmo);
        SailStatus.isMusicModsEnable = SailStatus.waterPlayer;
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
        ClientLifecycleEvents.CLIENT_FULL_STARTED.register(client -> {
            TIME_STARTED_CLIENT = parseSeconds(System.currentTimeMillis());
            GAME_STARTED = true;
            startClient();
        });
        ClientLifecycleEvents.CLIENT_STOPPING.register(client1 -> exitApplication());
        LocalizationEvents.DEFAULT_PARSER_INIT.register((parser) -> {
            parser.ss.set("player.scene", () -> Value.string(PresenceWorld.getScene()));
            parser.ss.set("player.name", () -> Value.string(PresencePlayer.getName()));
            parser.ss.set("discord", new ValueMap()
                    .set("name", () -> Value.string(SailStatus.USER.getName()))
                    .set("nickname", () -> Value.string(SailStatus.USER.getNickname()))
                    .set("discriminator", () -> Value.string(SailStatus.USER.getDiscriminator()))
                    .set("id", () -> Value.string(SailStatus.USER.getId()))
                    .set("avatar", () -> Value.string(SailStatus.USER.getAvatarUrl()))
            );
            if(SailStatus.isVoiceModsEnable){
                parser.ss.set("voice", new ValueMap()
                        .set("listener", () -> Value.string(new Voice().listener))
                );
            }
            if(SailStatus.replayMod){
                parser.ss.set("replay", new ValueMap()
                        .set("date", () -> Value.string(getReplayDateFormat()))
                        .set("name", () -> Value.string(SailStatus.userConfig.getBoolean("VIEW_REPLAY_MOD_SERVER_NAME", true) ? new ReplayModComp().name : new ReplayModComp().address))
                );
            }
            if(SailStatus.klashback){
                parser.ss.set("flashback.replay", new ValueMap()
                        .set("date", () -> Value.string(getReplayDateFormat$flashback()))
                        .set("name", () -> Value.string(new FlashbackComp().name))
                );
            }
        });
    }
    public static String getReplayDateFormat(){
        String strDateFormat = SailStatus.localization.getLocalization("mod.replaymod.date_format", false);
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        return dateFormat.format(new ReplayModComp().date);
    }
    public static String getReplayDateFormat$flashback(){
        String strDateFormat = SailStatus.localization.getLocalization("mod.flashback.date_format", false);
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        return dateFormat.format(new FlashbackComp().date);
    }

    public static long parseSeconds(long mills){
        return (mills-(mills % 1000)) /1000;
    }

    //

    private static final Timer TIMER = new Timer();
    private static void start() {
        TIMER.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
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
            }
        }, 250, 250);
    }

    // -=-=-=-=-=-=-=-=-
    public static void startClient() {
        registerApplication();
        start();
        log(String.format("Client %s is up and running!", AlinLib.MINECRAFT.getLaunchedVersion()));
    }
    // -=-=-=-=-=-=-=-=-

    private static void registerApplication() {
        if(!CONNECTED) {
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
        } else log("The client is already running");
    }

    public static void exitApplication() {
        if(CONNECTED) {
            log("Client stopped");
            CONNECTED = false;
            client.close();
        }
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
                if (SailStatus.replayMod && ReplayModComp.isInReplay() && userConfig.getBoolean("VIEW_REPLAY_MOD", true)) new ReplayMod();
                else if (SailStatus.klashback && FlashbackComp.isInReplay() && userConfig.getBoolean("VIEW_REPLAY_MOD", true)) new Flashback();
                else if (AlinLib.MINECRAFT.isSingleplayer() || AlinLib.MINECRAFT.hasSingleplayerServer()) new SinglePlayer();
                else if (AlinLib.MINECRAFT.getCurrentServer() != null) new MultiPlayer();
                else switch (Client.getState()) {
                        case 1 -> new LoadingResources();
                        case 2 -> new Loading();
                        case 3 -> new Connect();
                        case 4 -> new Disconnect();
                        case 5 -> new SaveWorld();
                        default -> new Unknown();
                    }
            } else {
                switch (Client.getState()) {
                    case 1 -> new LoadingResources();
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
        if (SailStatus.userConfig.getBoolean("SHOW_GAME_TIME", true))
            presence.setStartTimestamp(SailStatus.TIME_STARTED_CLIENT);
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

        if (SailStatus.userConfig.getBoolean("BUTTON.ENABLE", false)) {
            JsonArray buttons = new JsonArray();
            if (!SailStatus.userConfig.getString("BUTTON.LABEL", "").isBlank() && !SailStatus.userConfig.getString("BUTTON.URL", "").isBlank()) {
                JsonObject button = new JsonObject();
                button.addProperty("label", SailStatus.userConfig.getString("BUTTON.LABEL", ""));
                button.addProperty("url", SailStatus.userConfig.getString("BUTTON.URL", ""));
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
                SailStatus.log(ex.getMessage() == null ? ex.getClass().getName() : ex.getMessage(), Level.ERROR);
            }
        }
    }

    public static void reconnectApp() {
        exitApplication();
        lastPresence = null;
        registerApplication();
    }

    public interface ICONS {
        ResourceLocation MULTIPLAYER = ResourceLocation.fromNamespaceAndPath("sailstatus", "textures/gui/multiplayer.png");
        ResourceLocation ASSETS = ResourceLocation.fromNamespaceAndPath("sailstatus", "textures/gui/assets.png");
        ResourceLocation MONITOR = ResourceLocation.fromNamespaceAndPath("sailstatus", "textures/gui/monitor.png");
        ResourceLocation ACCESSIBILITY = ResourceLocation.fromNamespaceAndPath("sailstatus", "textures/gui/accessibility.png");
        ResourceLocation LANGUAGE = ResourceLocation.fromNamespaceAndPath("sailstatus", "textures/gui/language.png");
    }
}