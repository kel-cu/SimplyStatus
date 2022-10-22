package og.__kel_.simplystatus.info;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.*;
import og.__kel_.simplystatus.Main;

public class GameStateHandler {
    private static final MinecraftClient mc = MinecraftClient.getInstance();
    private static Screen lastScreen;

    public static void tick() {
        if (mc.currentScreen != null) screenTick();
        if (lastScreen != mc.currentScreen) {
            if (lastScreen != null) screenChange(lastScreen);
            lastScreen = mc.currentScreen;
        }
    }

    private static void screenTick() {
//        var api = BetterTaskbarAPI.getInstance();
//        MainClient.log.warn(mc.currentScreen.getClass().getName());
        if (mc.currentScreen instanceof LevelLoadingScreen loadingScreen) {
            Main.setGameState(1);
        } else if (mc.currentScreen instanceof ProgressScreen || mc.currentScreen instanceof ConnectScreen || mc.currentScreen instanceof DownloadingTerrainScreen) {
            Main.setGameState(2);
        } else  if (mc.currentScreen instanceof DisconnectedScreen) {
            Main.setGameState(3);
        } else {
            Main.setGameState(0);
        }
        Main.setUpdated(true);
    }

    private static void screenChange(Screen lastScreen) {
//        var api = BetterTaskbarAPI.getInstance();
        if (Main.isUpdate() && (lastScreen instanceof DisconnectedScreen || lastScreen instanceof LevelLoadingScreen || lastScreen instanceof ProgressScreen || lastScreen instanceof ConnectScreen || lastScreen instanceof ConfirmScreen || lastScreen instanceof DownloadingTerrainScreen)) {
            Main.setUpdated(false);
        }
    }
}
