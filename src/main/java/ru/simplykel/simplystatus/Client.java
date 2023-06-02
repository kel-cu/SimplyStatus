package ru.simplykel.simplystatus;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import club.minnced.discord.rpc.DiscordUser;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.message.v1.ClientReceiveMessageEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.debug.GameEventDebugRenderer;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.world.event.GameEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.glfw.GLFW;
import ru.simplykel.simplystatus.config.AssetsConfig;
import ru.simplykel.simplystatus.config.ModConfig;
import ru.simplykel.simplystatus.config.UserConfig;
import ru.simplykel.simplystatus.config.gui.ConfigScreen;
import ru.simplykel.simplystatus.info.Game;
import ru.simplykel.simplystatus.presences.MainMenu;
import ru.simplykel.simplystatus.presences.ReplayMod;
import ru.simplykel.simplystatus.presences.Unknown;
import ru.simplykel.simplystatus.presences.multiplayer.Connect;
import ru.simplykel.simplystatus.presences.multiplayer.Disconnect;
import ru.simplykel.simplystatus.presences.multiplayer.MultiPlayer;
import ru.simplykel.simplystatus.presences.singleplayer.Loading;
import ru.simplykel.simplystatus.presences.singleplayer.SinglePlayer;
import su.plo.lib.api.event.MinecraftGlobalEvent;

import java.util.Timer;
import java.util.TimerTask;

@Environment(EnvType.CLIENT)
public class Client implements ClientModInitializer {
    public static final Logger LOG = LogManager.getLogger("SimplyStatus");
    public static final DiscordRPC LIB = DiscordRPC.INSTANCE;
    public static final DiscordEventHandlers HANDLERS = new DiscordEventHandlers();
    public static Long STARTED_TIME_GAME;
    public static String APPLICATION_ID;
    public static final String STEAM_ID = "";
    public static final Boolean AUTO_REGISTER = true;
    public static boolean CONNECTED_DISCORD = false;
    public static AssetsConfig ASSETS = ModConfig.defaultAssets;
    public static DiscordUser USER;
    private static Timer TIMER = new Timer();
    private String lastException;
    @Override
    public void onInitializeClient() {
        if(!Main.isLoadingConfigs) {
            LOG.error("The configuration of the mod required to work has not been loaded! The start operation has been canceled.");
            return;
        }
        LOG.debug("I'm started work UwU");
        STARTED_TIME_GAME = System.currentTimeMillis() / 1000;
        UserConfig.load();
        Main.useAnotherID = UserConfig.USE_ANOTHER_ID;
        Main.useCustomID = UserConfig.USE_CUSTOM_APP_ID;
        KeyBinding openConfigKeyBind;
        openConfigKeyBind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "simplystatus.key.openConfig",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_F7, // The keycode of the key
                "simplystatus.name"
        ));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            assert client.player != null;
            while (openConfigKeyBind.wasPressed()) {
                if(!Main.clothConfig){
                    client.player.sendMessage(Text.translatable(("simplystatus.message.clothConfigNotFound")), true);
                    return;
                }
                final Screen current = client.currentScreen;
                Screen configScreen = ConfigScreen.buildScreen(current);
                client.setScreen(configScreen);
            }
        });
        ClientLifecycleEvents.CLIENT_STOPPING.register(client -> {
            Client.LOG.info(Main.prefixLog+"Bye =-=");
            LIB.Discord_Shutdown();
        });
        if(UserConfig.USE_CUSTOM_APP_ID && !UserConfig.CUSTOM_APP_ID.isBlank()) APPLICATION_ID = UserConfig.CUSTOM_APP_ID;
        else if(UserConfig.USE_ANOTHER_ID) APPLICATION_ID = ModConfig.mineID;
        else APPLICATION_ID = ModConfig.baseID;
        Main.customID = APPLICATION_ID;
        HANDLERS.ready = (user) -> {
            LOG.debug("The mod has been connected to Discord");
            USER = user;
            CONNECTED_DISCORD = true;
        };
        HANDLERS.disconnected = (err, reason) -> {
            LOG.debug("The mod has been pulled from Discord");
            LOG.error("Reason: "+reason);

            CONNECTED_DISCORD = false;
        };
        LIB.Discord_Initialize(APPLICATION_ID, HANDLERS, AUTO_REGISTER, STEAM_ID);
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                LIB.Discord_RunCallbacks();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {
                }
            }
        }, "SimplyStatus RPC-Callback-Handler").start();
        start();
    }

    /**
     * Запуск цикла обновления
     */
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
                        LIB.Discord_UpdatePresence(presence);
//                        LOG.info(lastException);
                        lastException = ex.getMessage();
                    }
                }
            }
        }, 2500, 2500);
    }

    /**
     * Запуск функции обновление
     */
    private void updatePresence(){
        UserConfig.load();
        ASSETS = new AssetsConfig();
        if(UserConfig.USE_CUSTOM_APP_ID) ASSETS = ModConfig.defaultUrlsAssets;
        if(UserConfig.USE_CUSTOM_ASSETS) ASSETS.loadUserAssets();
        MinecraftClient CLIENT = MinecraftClient.getInstance();
        if(UserConfig.ENABLE_RPC){
            if(CLIENT.world == null || CLIENT.player == null){
                if(Game.getGameState() == 1) new Loading();
                else if(Game.getGameState() == 2) new Connect();
                else if(Game.getGameState() == 3) new Disconnect();
                else new MainMenu();
            } else {
                if(CLIENT.isInSingleplayer()) new SinglePlayer();
                else if(CLIENT.getCurrentServerEntry() != null) new MultiPlayer();
                else if(Main.replayMod && UserConfig.VIEW_REPLAY_MOD) new ReplayMod();
                else new Unknown();
            }
        } else {
            updateDiscordPresence(null);
        }
    }
    public static void updateDiscordPresence(DiscordRichPresence presence){
        if(presence == null && ModConfig.debugPresence) LOG.info("Presence is null!");
        else if(ModConfig.debugPresence){
            LOG.info("Update presence");
            if(presence.details != null) LOG.info("Details: "+presence.details);
            if(presence.state != null) LOG.info("State: "+presence.state);
            if(presence.largeImageKey != null) LOG.info("Large Image Key: "+presence.largeImageKey);
            if(presence.largeImageText != null) LOG.info("Large Image Text: "+presence.largeImageText);
            if(presence.smallImageKey != null) LOG.info("Small Image Key: "+presence.smallImageKey);
            if(presence.smallImageText != null) LOG.info("Small Image Text: "+presence.smallImageText);
        }
        if(CONNECTED_DISCORD) LIB.Discord_UpdatePresence(presence);
    }
}
