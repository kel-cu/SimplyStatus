package og.__kel_.simplystatus;

import net.fabricmc.api.ModInitializer;
import og.__kel_.simplystatus.client.MainClient;


public class Main implements ModInitializer {
    private static boolean update = false;
    /*
    0 - Ничего
    1 - Загрузка мира
    2 - Ожидание
    3 - Ошибка, от сервера
    */
    private static int gameState = 0;
    @Override
    public void onInitialize() {
        MainClient.log.warn("[SimplyStatus] Attention, this version is not a release! This version may contain many bugs, as well as the creation of this mod, ahem....");
        MainClient.log.warn("[SimplyStatus] sorry~~");
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