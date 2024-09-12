package ru.kelcuprum.simplystatus;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.loader.api.FabricLoader;

public class SimplyStatusFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        SimplyStatus.replayMod = FabricLoader.getInstance().getModContainer("replaymod").isPresent();
        SimplyStatus.waterPlayer = FabricLoader.getInstance().getModContainer("waterplayer").isPresent();
        SimplyStatus.svc = FabricLoader.getInstance().getModContainer("voicechat").isPresent();
        SimplyStatus.plasmo = FabricLoader.getInstance().getModContainer("plasmovoice").isPresent();
        SimplyStatus.klashback = FabricLoader.getInstance().getModContainer("flashback").isPresent();
        SimplyStatus.isVoiceModsEnable = (SimplyStatus.svc || SimplyStatus.plasmo);
        SimplyStatus.isMusicModsEnable = SimplyStatus.waterPlayer;
        SimplyStatus.onInitializeClient();
    }
}
