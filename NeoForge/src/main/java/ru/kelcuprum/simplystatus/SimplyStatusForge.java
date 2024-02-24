package ru.kelcuprum.simplystatus;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.javafmlmod.FMLJavaModLoadingContext;
import net.neoforged.neoforge.client.ConfigScreenHandler;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.GameShuttingDownEvent;
import ru.kelcuprum.simplystatus.gui.config.MainConfigs;

@Mod("simplystatus")
public class SimplyStatusForge {
    public SimplyStatusForge(){
        SimplyStatus.replayMod = ModList.get().isLoaded("replaymod");
        SimplyStatus.waterPlayer = ModList.get().isLoaded("waterplayer");
        SimplyStatus.svc = ModList.get().isLoaded("voicechat");
        SimplyStatus.plasmo = ModList.get().isLoaded("plasmovoice");
        SimplyStatus.isVoiceModsEnable = (SimplyStatus.svc || SimplyStatus.plasmo);
        SimplyStatus.isMusicModsEnable = SimplyStatus.waterPlayer;
        SimplyStatus.onInitializeClient();
        ModLoadingContext.get().registerExtensionPoint(
                ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory((minecraftClient, screen) -> new MainConfigs().build(screen)));
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::start);
		NeoForge.EVENT_BUS.register(this);
    }

	 private void start(final FMLCommonSetupEvent event) {
        SimplyStatus.startClient();
	}
    @SubscribeEvent
    public void stop(GameShuttingDownEvent event) {
        SimplyStatus.stopClient();
    }
}
