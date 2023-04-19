package ru.simplykel.simplystatus.info;
import io.github.bumblesoftware.fastload.client.BuildingTerrainScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.*;
import ru.simplykel.simplystatus.Main;

public class Game {

    private static final MinecraftClient CLIENT = MinecraftClient.getInstance();
    private static Screen lastScreen;
    private static boolean update = false;
    private static int gameState = 0;
    public static boolean isUpdate() {
        return update;
    }

    public static void setUpdated(boolean updated) {
        update = updated;
    }

    public static int getGameState() {
        return gameState;
    }

    public static void setGameState(int newGameState) {
        gameState = newGameState;
    }
    public static void tick() {
        if (CLIENT.currentScreen != null) {
            if(Main.fastload) screenTickFastLoad(); else screenTick();
        };
        if (lastScreen != CLIENT.currentScreen) {
            if (lastScreen != null) screenChange(lastScreen);
            lastScreen = CLIENT.currentScreen;
        }
    }

    private static void screenTick() {
//        var api = BetterTaskbarAPI.getInstance();
//        MainClient.log.warn(mc.currentScreen.getClass().getName());
        if (CLIENT.currentScreen instanceof LevelLoadingScreen loadingScreen) {
            setGameState(1);
        } else if (CLIENT.currentScreen instanceof ProgressScreen || CLIENT.currentScreen instanceof ConnectScreen || CLIENT.currentScreen instanceof DownloadingTerrainScreen) {
            setGameState(2);
        } else  if (CLIENT.currentScreen instanceof DisconnectedScreen) {
            setGameState(3);
        } else {
            setGameState(0);
        }
        setUpdated(true);
    }
    private static void screenTickFastLoad() {
        if (CLIENT.currentScreen instanceof LevelLoadingScreen || CLIENT.currentScreen instanceof BuildingTerrainScreen) {
            setGameState(1);
        } else if (CLIENT.currentScreen instanceof ProgressScreen || CLIENT.currentScreen instanceof ConnectScreen || CLIENT.currentScreen instanceof DownloadingTerrainScreen) {
            setGameState(2);
        } else  if (CLIENT.currentScreen instanceof DisconnectedScreen) {
            setGameState(3);
        } else {
            setGameState(0);
        }
        setUpdated(true);
    }

    private static void screenChange(Screen lastScreen) {
//        var api = BetterTaskbarAPI.getInstance();
        if (isUpdate() && (lastScreen instanceof DisconnectedScreen || lastScreen instanceof LevelLoadingScreen || lastScreen instanceof ProgressScreen || lastScreen instanceof ConnectScreen || lastScreen instanceof ConfirmScreen || lastScreen instanceof DownloadingTerrainScreen)) {
            setUpdated(false);
        }
    }
}
