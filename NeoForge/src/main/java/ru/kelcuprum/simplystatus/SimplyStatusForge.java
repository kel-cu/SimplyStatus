package ru.kelcuprum.simplystatus;

import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import ru.kelcuprum.simplystatus.gui.MainConfigs;

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
                IConfigScreenFactory.class,
                () -> (minecraftClient, screen) -> new MainConfigs().build(screen));
    }
}
