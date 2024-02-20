package ru.kelcuprum.simplystatus;

import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.ConfigScreenHandler;
import ru.kelcuprum.simplystatus.gui.config.MainConfigs;

@Mod("simplystatus")
public class SimplyStatusForge {
    public SimplyStatusForge(){
        SimplyStatus.replayMod = ModList.get().isLoaded("replaymod");
        SimplyStatus.waterPlayer = ModList.get().isLoaded("waterplayer");
        SimplyStatus.svc = ModList.get().isLoaded("voicechat");
        SimplyStatus.plasmo = ModList.get().isLoaded("plasmovoice");
        SimplyStatus.onInitializeClient();
        ModLoadingContext.get().registerExtensionPoint(
                ConfigScreenHandler.ConfigScreenFactory.class,
                () -> new ConfigScreenHandler.ConfigScreenFactory((minecraftClient, screen) -> new MainConfigs().build(screen)));
        SimplyStatus.startClient();
    }
}
