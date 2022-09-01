package og.__kel_.simplystatus;

import net.fabricmc.api.ModInitializer;
import og.__kel_.simplystatus.client.MainClient;


public class Main implements ModInitializer {

    @Override
    public void onInitialize() {
        MainClient.log.warn("[SimplyStatus] Attention, this version is not a release! This version may contain many bugs, as well as the creation of this mod, ahem....");
        MainClient.log.warn("[SimplyStatus] sorry~~");
    }
}