package og.__kel_.simplystatus.server;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;

public class Main implements ModInitializer {

    @Override
    public void onInitialize() {
        ServerPlayNetworking.registerGlobalReceiver(new Identifier("simplystatus", "sync_configurations"),
                (server, playerEntity, handler, buf, responseSender) -> {

                });
    }
}