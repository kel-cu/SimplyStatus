package ru.kelcuprum.sailstatus.gui;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.text.TextBuilder;
import ru.kelcuprum.alinlib.gui.screens.ConfigScreenBuilder;
import ru.kelcuprum.sailstatus.SailStatus;
import ru.kelcuprum.sailstatus.config.Assets;
import ru.kelcuprum.sailstatus.gui.assets.*;

import static ru.kelcuprum.alinlib.AlinLib.MINECRAFT;

public class AssetsConfigs {
    int assetsSize = 0;
    boolean isLoaded = false;

    public Screen build(Screen parent) {
        ConfigScreenBuilder builder = new ConfigScreenBuilder(parent, Component.translatable("simplystatus.name"))
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.client"), (s) -> MINECRAFT.setScreen(new MainConfigs().build(parent))).build());
        if(MINECRAFT.getCurrentServer() != null) builder.addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.server"), (s) -> MINECRAFT.setScreen(new ServerConfigs().build(parent))).build());
        builder.addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.localization"), (s) -> MINECRAFT.setScreen(new LocalizationsConfigs().build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.assets"), (s) -> MINECRAFT.setScreen(new AssetsConfigs().build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.addons"), (s) -> MINECRAFT.setScreen(new AddonsConfigs().build(parent))).build());
        if(SailStatus.isMusicModsEnable || SailStatus.isVoiceModsEnable || SailStatus.replayMod  || SailStatus.klashback)
            builder.addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.mods"), (s) -> MINECRAFT.setScreen(new ModsConfigs().build(parent))).build());
        builder.addWidget(new TextBuilder(Component.translatable("simplystatus.config.assets")))
                .setOnTick((s) -> {
                    int size = 0;
                    for(String id : SailStatus.assetsNames){
                        Assets assets = Assets.getByID(id);
                        if(assets.file != null) size++;
                    }
                    if(isLoaded && (assetsSize != size)) AlinLib.MINECRAFT.setScreen(this.build(parent));
                });
        for(String id : SailStatus.assetsNames){
            Assets assets = Assets.getByID(id);
            if(assets.file != null) {
                builder.addWidget(new ButtonBuilder(Component.translatable("simplystatus.config.assets.button_name", assets.name, assets.author, assets.id), (s) -> AlinLib.MINECRAFT.setScreen(new AssetsScreen(new AssetsConfigs().build(parent), Assets.getByID(id)))).build());
                assetsSize++;
            }
        }
        isLoaded = true;
        builder.addWidget(new ButtonBuilder(Component.translatable("simplystatus.assets.create"), (s) -> MINECRAFT.setScreen(new CreateAssetsScreen(new AssetsConfigs().build(parent)))).build());


        return builder.build();
    }
}
