package ru.kelcuprum.simplystatus;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import club.minnced.discord.rpc.DiscordUser;
import com.google.gson.JsonObject;
import com.jagrosh.discordipc.IPCClient;
import com.jagrosh.discordipc.IPCListener;
import com.jagrosh.discordipc.entities.Packet;
import com.jagrosh.discordipc.entities.RichPresence;
import com.jagrosh.discordipc.entities.User;
import com.jagrosh.discordipc.exceptions.NoDiscordClientException;
import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;
import ru.kelcuprum.simplystatus.config.AssetsConfig;
import ru.kelcuprum.alinlib.config.Config;
import ru.kelcuprum.simplystatus.config.ModConfig;
import ru.kelcuprum.simplystatus.config.gui.YACLConfigScreen;
import ru.kelcuprum.simplystatus.info.Game;
import ru.kelcuprum.simplystatus.info.Player;
import ru.kelcuprum.simplystatus.localization.Localization;
import ru.kelcuprum.simplystatus.mods.Music;
import ru.kelcuprum.simplystatus.mods.Voice;
import ru.kelcuprum.simplystatus.presence.MainMenu;
import ru.kelcuprum.simplystatus.presence.ReplayMod;
import ru.kelcuprum.simplystatus.presence.Unknown;
import ru.kelcuprum.simplystatus.presence.multiplayer.Connect;
import ru.kelcuprum.simplystatus.presence.multiplayer.Disconnect;
import ru.kelcuprum.simplystatus.presence.multiplayer.MultiPlayer;
import ru.kelcuprum.simplystatus.presence.singleplayer.Loading;
import ru.kelcuprum.simplystatus.presence.singleplayer.SinglePlayer;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

import static ru.kelcuprum.simplystatus.localization.Localization.compile;

public class SimplyStatus implements ClientModInitializer {
    private static final Timer TIMER = new Timer();
    public static IPCClient client = new IPCClient(345229890980937739L);

    // Mod Statud build
    public static String version = "0.0.0";
    public static boolean isDevBuild = false;
    public static boolean isBetaBuild = false;
    // User Configurations
    public static Config userConfig = new Config("config/SimplyStatus/config.json");
    public static Config serverConfig = new Config("config/SimplyStatus/servers/default.json");
    public static AssetsConfig ASSETS = ModConfig.defaultAssets;
    // Another shit
    public static String[] apiNames = {
            "CraftHead",
            "Alina API: 2D",
            "Alina API: 3D"
    };
    public static boolean useAnotherID = false;
    public static boolean useCustomID = false;
    public static String customID = "";
    public static DecimalFormat DF = new DecimalFormat("#.##");
    private String lastException;
    public static DiscordRichPresence lastPresence;
    public static Long TIME_STARTED_CLIENT;
    // Logs
    public static final Logger LOG = LogManager.getLogger("SimplyStatus");
    public static void log(String message) {log(message, Level.INFO);};
    public static void log(String message, Level level){ LOG.log(level, "["+LOG.getName()+"] "+message);}
    // Mods is present
    public static Boolean yacl = FabricLoader.getInstance().getModContainer("yet_another_config_lib_v3").isPresent();
    public static Boolean replayMod = FabricLoader.getInstance().getModContainer("replaymod").isPresent();
    public static Boolean musicPlayer = FabricLoader.getInstance().getModContainer("musicplayer").isPresent();
    public static Boolean waterPlayer = FabricLoader.getInstance().getModContainer("waterplayer").isPresent();
    public static Boolean svc = FabricLoader.getInstance().getModContainer("voicechat").isPresent();
    public static Boolean plasmo = FabricLoader.getInstance().getModContainer("plasmovoice").isPresent();
    public static Boolean isVoiceModsEnable = (svc || plasmo);
    public static Boolean isMusicModsEnable = (musicPlayer || waterPlayer);
    // Discord
//    public static final DiscordRPC LIB = DiscordRPC.INSTANCE;
//    public static final DiscordEventHandlers HANDLERS = new DiscordEventHandlers();
//    public static DiscordUser USER_OLD;
    public static User USER;
    public static String APPLICATION_ID;
//    public static final String STEAM_ID = "";
//    public static final Boolean AUTO_REGISTER = true;
    // Discord Status
    public static boolean CONNECTED_DISCORD = false;

    @Override
    public void onInitializeClient() {
        userConfig.load();
        serverConfig.load();
        checkVersion();
        try {
            new ModConfig();
        } catch (Exception e) {
            log("The default configuration of the mod was not loaded, no launch possible!", Level.ERROR);
            e.printStackTrace();
            return;
        }
        useAnotherID = userConfig.getBoolean("USE_ANOTHER_ID", false);
        useCustomID = userConfig.getBoolean("USE_CUSTOM_APP_ID", false);
        TIME_STARTED_CLIENT = System.currentTimeMillis() / 1000;
        Localization.init();
        registerApplications();
        registerKeyBinds();
        ClientLifecycleEvents.CLIENT_STARTED.register(client -> {
            log(String.format("Client %s is up and running!", client.getLaunchedVersion()));
        });
    }
    private void registerApplications(){

//        HANDLERS.ready = (user) -> {
//            log("The mod has been connected to Discord", Level.DEBUG);
//            USER_OLD = user;
//            CONNECTED_DISCORD = true;
//        };
//        HANDLERS.disconnected = (err, reason) -> {
//            log("The mod has been pulled from Discord", Level.DEBUG);
//            log(String.format("Reason: %s", reason), Level.DEBUG);
//            CONNECTED_DISCORD = false;
//        };
        APPLICATION_ID = userConfig.getBoolean("USE_ANOTHER_ID", false) ? ModConfig.mineID : ModConfig.baseID;
        if(userConfig.getBoolean("USE_CUSTOM_APP_ID", false)&& !userConfig.getString("CUSTOM_APP_ID", ModConfig.baseID).isBlank()) APPLICATION_ID = userConfig.getString("CUSTOM_APP_ID", ModConfig.baseID);
        customID = APPLICATION_ID;
        client = new IPCClient(Long.parseLong(APPLICATION_ID));
        setupListener();
        try {
            client.connect();
        } catch (Exception ex){
            ex.printStackTrace();
        }
//        LIB.Discord_Initialize(APPLICATION_ID, HANDLERS, AUTO_REGISTER, STEAM_ID);
//        new Thread(() -> {
//            while (!Thread.currentThread().isInterrupted()) {
//                LIB.Discord_RunCallbacks();
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException ignored) {
//                }
//            }
//        }, "SimplyStatus RPC-Callback-Handler").start();
        start();
    }
    private void start(){
        TIMER.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    if(CONNECTED_DISCORD) updatePresence();
                    if(lastException != null) lastException = null;
                } catch(Exception ex){
                    if(lastException == null || !lastException.equals(ex.getMessage())){
                        ex.printStackTrace();
                        DiscordRichPresence presence = new DiscordRichPresence();
                        presence.largeImageKey = "unknown_world";
                        presence.details = "There was an error, look in the console";
                        presence.state = "And report the bug on GitHub";
                        updateDiscordPresence(presence);
                        lastException = ex.getMessage();
                    }
                }
            }
        }, 500, 500);
    }
    public static void setupListener(){
        client.setListener(new IPCListener(){
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
            public void onReady(IPCClient client)
            {
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

    private void updatePresence(){
        ASSETS = new AssetsConfig();
        if(userConfig.getBoolean("USE_CUSTOM_APP_ID", false)) ASSETS = ModConfig.defaultUrlsAssets;
        if(userConfig.getBoolean("USE_CUSTOM_ASSETS", false)) ASSETS.loadUserAssets();
        Minecraft CLIENT = Minecraft.getInstance();
        if(userConfig.getBoolean("ENABLE_RPC", true)){
            if(CLIENT.level == null || CLIENT.player == null){
                switch(Game.getState()){
                    case 1 -> new Loading();
                    case 2 -> new Connect();
                    case 3 -> new Disconnect();
                    default -> new MainMenu();
                }
            } else {
                if(CLIENT.isSingleplayer()) new SinglePlayer();
                else if(CLIENT.getCurrentServer() != null) new MultiPlayer();
                else if(SimplyStatus.replayMod && userConfig.getBoolean("VIEW_REPLAY_MOD", true)) new ReplayMod();
                else new Unknown();
            }
        } else {
            updateDiscordPresence(null);
        }
    }
    public static void updateContentPresenceByConfigs(DiscordRichPresence presence) { updateContentPresenceByConfigs(presence, false); }
    public static void updateContentPresenceByConfigs(DiscordRichPresence presence, boolean isServer){

        if(SimplyStatus.userConfig.getBoolean("SHOW_GAME_TIME", true)) presence.startTimestamp = SimplyStatus.TIME_STARTED_CLIENT;
        ///
        if(userConfig.getBoolean("VIEW_VOICE_SPEAK", false) && (isVoiceModsEnable && new Voice().isSpeak)) {
            Voice mod = new Voice();
            String info = mod.isSelfTalk ? Localization.getLocalization("mod.voice", false) : mod.isOnePlayer ? Localization.getLocalization("mod.voice.one", false) : Localization.getLocalization("mod.voice.more", false);
            presence.smallImageKey = ASSETS.voice;
            presence.smallImageText = Localization.run(compile(info));
        } else if(userConfig.getBoolean("VIEW_MUSIC_LISTENER", false) && (isMusicModsEnable && !new Music().paused)) {
            presence.smallImageKey = ASSETS.music;
            presence.smallImageText = new Music().artistIsNull ? Localization.getLocalization("mod.music.noauthor", true) : Localization.getLocalization("mod.music", true);
        } else if(isServer && (serverConfig.getBoolean("SHOW_ICON", false) && (serverConfig.getString("ICON_URL", "").length() != 0))){
            presence.smallImageKey = serverConfig.getString("ICON_URL", "").replace("%address%", Minecraft.getInstance().getCurrentServer().ip);
            presence.smallImageText = Localization.run(compile("{player.scene}"));
        } else if(userConfig.getBoolean("SHOW_AVATAR_PLAYER", true)) {
            presence.smallImageKey = Player.getURLAvatar();
            presence.smallImageText = Player.getName();
        }
    }
    public static void updateDiscordPresence(DiscordRichPresence presence){
        if(presence == null && ModConfig.debugPresence) LOG.info("Presence is null!");
        else if(ModConfig.debugPresence){
            LOG.info("Content presence");
            if(presence.details != null) LOG.info("Details: "+presence.details);
            if(presence.state != null) LOG.info("State: "+presence.state);
            if(presence.largeImageKey != null) LOG.info("Large Image Key: "+presence.largeImageKey);
            if(presence.largeImageText != null) LOG.info("Large Image Text: "+presence.largeImageText);
            if(presence.smallImageKey != null) LOG.info("Small Image Key: "+presence.smallImageKey);
            if(presence.smallImageText != null) LOG.info("Small Image Text: "+presence.smallImageText);
        }
        if(lastPresence == null || !lastPresence.equals(presence)){
            lastPresence = presence;
            RichPresence.Builder rich = new RichPresence.Builder();
            if(presence != null) {
                if (presence.details != null) rich.setDetails(presence.details);
                if (presence.state != null) rich.setState(presence.state);
                if (presence.startTimestamp >= 0) rich.setStartTimestamp(presence.startTimestamp);
                if (presence.endTimestamp >= 0) rich.setEndTimestamp(presence.endTimestamp);
                if (presence.largeImageKey != null && presence.largeImageText == null)
                    rich.setLargeImage(presence.largeImageKey);
                if (presence.largeImageKey != null && presence.largeImageText != null)
                    rich.setLargeImage(presence.largeImageKey, presence.largeImageText);
                if (presence.smallImageKey != null && presence.smallImageText == null)
                    rich.setSmallImage(presence.smallImageKey);
                if (presence.smallImageKey != null && presence.smallImageText != null)
                    rich.setSmallImage(presence.smallImageKey, presence.smallImageText);
            }
//            if(CONNECTED_DISCORD) LIB.Discord_UpdatePresence(presence);
            if(CONNECTED_DISCORD) client.sendRichPresence(rich.build());
            if(ModConfig.debugPresence) LOG.info("Update presence");
        }
    }
    private void registerKeyBinds(){
        if(yacl){
            KeyMapping openConfigKeyBind;
            openConfigKeyBind = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                    "simplystatus.key.openConfig",
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_UNKNOWN, // The keycode of the key
                    "simplystatus.name"
            ));
            ClientTickEvents.END_CLIENT_TICK.register(client -> {
                assert client.player != null;
                while (openConfigKeyBind.consumeClick()) {
                    final Screen current = client.screen;
                    Screen configScreen = YACLConfigScreen.buildScreen(current);
                    client.setScreen(configScreen);
                }
            });
        } else log("Configuration hotkey has not been registered, no desired mod found");
    }
    private void checkVersion(){
        if(FabricLoader.getInstance().getModContainer("simplystatus").isEmpty()) return;
        version = FabricLoader.getInstance().getModContainer("simplystatus").get().getMetadata().getVersion().getFriendlyString();
        String[] versions = version.split("-");
        if(versions.length >= 2){
            if(versions[1].startsWith("dev") || versions[1].startsWith("alpha")) isDevBuild = true;
            if(versions[1].startsWith("beta") || versions[1].startsWith("pre")) isBetaBuild = true;
        };
        if(isDevBuild) {
            log("ЭТОТ МОД НЕ ЯВЛЯЕТСЯ ОФИЦИАЛЬНЫМ [ПРОДУКТОМ/УСЛУГОЙ/СОБЫТИЕМ И т.п.] MINECRAFT. НЕ ОДОБРЕНО И НЕ СВЯЗАНО С КОМПАНИЕЙ MOJANG ИЛИ MICROSOFT", Level.WARN);
            log("Warning!", Level.WARN);
            log("This version of the mod is not stable, in case of bugs, please contact https://github.com/simply-kel/SimplyStatus", Level.WARN);
            log(String.format("Running version: %s", version), Level.WARN);
        } else if(isBetaBuild){
            log("Warning!", Level.WARN);
            log("This version of the mod is for testing by the public, in case of bugs, please contact https://github.com/simply-kel/SimplyStatus", Level.WARN);
        }
    }
}