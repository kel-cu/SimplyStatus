package ru.simplykel.simplystatus;

import club.minnced.discord.rpc.DiscordEventHandlers;
import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordUser;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
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
//                client.player.sendMessage(Text.of("The keybind unready to work"), true);
                if(!Main.clothConfig){
                    client.player.sendMessage(Text.translatable(("simplystatus.message.clothConfigNotFound")), true);
                    return;
                }
                final Screen current = client.currentScreen;
                Screen configScreen = ConfigScreen.buildScreen(current);
                client.setScreen(configScreen);
            }
        });
        if(UserConfig.USE_ANOTHER_ID) APPLICATION_ID = ModConfig.mineID;
        else APPLICATION_ID = ModConfig.baseID;
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
                updatePresence();
            }
        }, 2500, 2500);
    }

    /**
     * Запуск функции обновление
     */
    private void updatePresence(){
        if(!CONNECTED_DISCORD) return;
        UserConfig.load();
        ASSETS = new AssetsConfig(UserConfig.ENABLE_BEDROCK_ASSETS);
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
            LIB.Discord_UpdatePresence(null);
        }
    }
}
