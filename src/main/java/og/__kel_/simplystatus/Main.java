package og.__kel_.simplystatus;

import net.fabricmc.api.ModInitializer;
import og.__kel_.simplystatus.client.MainClient;
//import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
//import net.minecraft.util.Identifier;

public class Main implements ModInitializer {

    @Override
    public void onInitialize() {
        MainClient.log.warn("Attention, this version is not a release! This version may contain many bugs, as well as the creation of this mod, ahem....");
        MainClient.log.warn("sorry~~");
//        ServerPlayNetworking.registerGlobalReceiver(new Identifier("simplystatus", "sync_configurations"),
//                (server, playerEntity, handler, buf, responseSender) -> {
//
//                });
    }
}