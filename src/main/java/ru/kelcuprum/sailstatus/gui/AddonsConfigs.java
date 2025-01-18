package ru.kelcuprum.sailstatus.gui;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.gui.components.builder.button.*;
import ru.kelcuprum.alinlib.gui.components.builder.editbox.EditBoxBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.text.TextBuilder;
import ru.kelcuprum.alinlib.gui.components.text.*;
import ru.kelcuprum.alinlib.gui.screens.ConfigScreenBuilder;
import ru.kelcuprum.sailstatus.SailStatus;
import ru.kelcuprum.sailstatus.config.ModConfig;

import static ru.kelcuprum.alinlib.AlinLib.MINECRAFT;

public class AddonsConfigs {

    public Screen build(Screen parent) {
        ConfigScreenBuilder builder = new ConfigScreenBuilder(parent, Component.translatable("simplystatus.name"))
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.client"), (s) -> MINECRAFT.setScreen(new MainConfigs().build(parent))).build());
        if(MINECRAFT.getCurrentServer() != null) builder.addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.server"), (s) -> MINECRAFT.setScreen(new ServerConfigs().build(parent))).build());
        builder.addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.localization"), (s) -> MINECRAFT.setScreen(new LocalizationsConfigs().build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.assets"), (s) -> MINECRAFT.setScreen(new AssetsConfigs().build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.addons"), (s) -> MINECRAFT.setScreen(new AddonsConfigs().build(parent))).build());
        if(SailStatus.isMusicModsEnable || SailStatus.isVoiceModsEnable || SailStatus.replayMod  || SailStatus.klashback)
            builder.addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.mods"), (s) -> MINECRAFT.setScreen(new ModsConfigs().build(parent))).build());
        builder.addWidget(new TextBuilder(Component.translatable("simplystatus.config.addons")))
                .addWidget(new CategoryBox(Component.translatable("simplystatus.config.addons.indicators"))
                        .addValue(new ButtonBooleanBuilder(Component.translatable("simplystatus.config.addons.show_items"), true).setConfig(SailStatus.userConfig, "SHOW_ITEMS").build())
                        .addValue(new ButtonBooleanBuilder(Component.translatable("simplystatus.config.addons.enable_time_cycle"), true).setConfig(SailStatus.userConfig, "ENABLE_TIME_CYCLE").build())
                        .addValue(new ButtonBooleanBuilder(Component.translatable("simplystatus.config.addons.enable_world"), true).setConfig(SailStatus.userConfig, "ENABLE_WORLD").build())
                )
                .addWidget(new CategoryBox(Component.translatable("simplystatus.config.addons.custom"))
                        .addValue(new ButtonBooleanBuilder(Component.translatable("simplystatus.config.addons.use_custom_app_id"), false).setConfig(SailStatus.userConfig, "USE_CUSTOM_APP_ID").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.addons.custom_app_id")).setValue(ModConfig.baseID).setConfig(SailStatus.userConfig, "CUSTOM_APP_ID").build())
                        .addValue(new ButtonBuilder(Component.translatable("simplystatus.config.reconnect"), (s) -> SailStatus.reconnectApp()).build())
                );
        return builder.build();
    }
}
