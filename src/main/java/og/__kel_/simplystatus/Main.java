package og.__kel_.simplystatus;

import net.fabricmc.api.ModInitializer;
import og.__kel_.simplystatus.api.entity.addonEntity;
import og.__kel_.simplystatus.client.MainClient;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Main implements ModInitializer {
    // ip, addon
    public static final Map<String, addonEntity> addonsServers = new ConcurrentHashMap<>();
    public static boolean isDevBuild = true;
    private static boolean update = false;
    //CONFIG
    public static boolean viewStatic = true;
    public static boolean viewOffHand = false;
    public static boolean viewRPC = true;
    public static boolean viewUsername = true;
    public static boolean bedrock = false;
    public static boolean changeStatusNameInMinecraft = false;
    public static boolean lastTitle = false;
    public static boolean cringeIcons = false;
    public static boolean showTime = true;
    public static boolean showAvatar = false;
    //MODS
    public static boolean viewVoice = false;
    public static boolean viewMusicListening = false;
    public static boolean viewReplayMod = false;
    public static boolean useCustomAssets = false;
    //
    /*
    0 - Ничего
    1 - Загрузка мира
    2 - Ожидание
    3 - Ошибка, от сервера
    */
    private static int gameState = 0;

    @Override
    public void onInitialize() {
        if (isDevBuild) {
            MainClient.log.warn("[SimplyStatus] Attention, this version is not a release! This version may contain many bugs, as well as the creation of this mod, ahem....");
            MainClient.log.warn("[SimplyStatus] sorry~~");
        }
    }

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
}