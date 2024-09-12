package ru.kelcuprum.simplystatus.gui;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBuilder;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.screens.ConfigScreenBuilder;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.config.Assets;
import ru.kelcuprum.simplystatus.gui.assets.*;

import static ru.kelcuprum.alinlib.AlinLib.MINECRAFT;

public class AssetsConfigs {
    int assetsSize = 0;
    boolean isLoaded = false;

    public Screen build(Screen parent) {
        ConfigScreenBuilder builder = new ConfigScreenBuilder(parent, Component.translatable("simplystatus.name"))
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.client"), (s) -> MINECRAFT.setScreen(new MainConfigs().build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.localization"), (s) -> MINECRAFT.setScreen(new LocalizationsConfigs().build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.server"), (s) -> MINECRAFT.setScreen(new ServerConfigs().build(parent))).build().setActive(MINECRAFT.getCurrentServer() != null))
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.assets"), (s) -> MINECRAFT.setScreen(new AssetsConfigs().build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.addons"), (s) -> MINECRAFT.setScreen(new AddonsConfigs().build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.mods"), (s) -> MINECRAFT.setScreen(new ModsConfigs().build(parent))).build().setActive(SimplyStatus.isMusicModsEnable || SimplyStatus.isVoiceModsEnable || SimplyStatus.replayMod  || SimplyStatus.klashback));

        if (AlinLib.bariumConfig.getBoolean("FRIEND", true))
            builder.addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.support"), (s) -> MINECRAFT.setScreen(new ThanksScreen().build(parent))).build());

        builder.addWidget(new TextBox(Component.translatable("simplystatus.config.assets"), true))
                .setOnTick((s) -> {
                    int size = 0;
                    for(String id : SimplyStatus.assetsNames){
                        Assets assets = Assets.getByID(id);
                        if(assets.file != null) size++;
                    }
                    if(isLoaded && (assetsSize != size)) AlinLib.MINECRAFT.setScreen(this.build(parent));
                });
        for(String id : SimplyStatus.assetsNames){
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
