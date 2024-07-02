package ru.kelcuprum.simplystatus.gui;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBooleanBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.editbox.EditBoxBuilder;
import ru.kelcuprum.alinlib.gui.components.text.CategoryBox;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.screens.ConfigScreenBuilder;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.config.ModConfig;

import static ru.kelcuprum.simplystatus.SimplyStatus.MINECRAFT;

public class AddonsConfigs {

    public Screen build(Screen parent) {
        ConfigScreenBuilder builder = new ConfigScreenBuilder(parent, Component.translatable("simplystatus.name"))
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.client"), (s) -> MINECRAFT.setScreen(new MainConfigs().build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.localization"), (s) -> MINECRAFT.setScreen(new LocalizationsConfigs().build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.server"), (s) -> MINECRAFT.setScreen(new ServerConfigs().build(parent))).build().setActive(MINECRAFT.getCurrentServer() != null))
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.assets"), (s) -> MINECRAFT.setScreen(new AssetsConfigs().build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.addons"), (s) -> MINECRAFT.setScreen(new AddonsConfigs().build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.mods"), (s) -> MINECRAFT.setScreen(new ModsConfigs().build(parent))).build().setActive(SimplyStatus.isMusicModsEnable || SimplyStatus.isVoiceModsEnable || SimplyStatus.replayMod));

        if(AlinLib.bariumConfig.getBoolean("FRIEND", true)) builder.addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.support"), (s) -> MINECRAFT.setScreen(new ThanksScreen().build(parent))).build());

        builder.addWidget(new TextBox(Component.translatable("simplystatus.config.addons"), true))
                .addWidget(new CategoryBox(Component.translatable("simplystatus.config.addons.indicators"))
                        .addValue(new ButtonBooleanBuilder(Component.translatable("simplystatus.config.addons.show_items"), true).setConfig(SimplyStatus.userConfig, "SHOW_ITEMS").build())
                        .addValue(new ButtonBooleanBuilder(Component.translatable("simplystatus.config.addons.enable_time_cycle"), true).setConfig(SimplyStatus.userConfig, "ENABLE_TIME_CYCLE").build())
                        .addValue(new ButtonBooleanBuilder(Component.translatable("simplystatus.config.addons.enable_world"), true).setConfig(SimplyStatus.userConfig, "ENABLE_WORLD").build())
                )
                .addWidget(new CategoryBox(Component.translatable("simplystatus.config.addons.custom"))
                        .addValue(new ButtonBooleanBuilder(Component.translatable("simplystatus.config.addons.use_custom_app_id"), false).setConfig(SimplyStatus.userConfig, "USE_CUSTOM_APP_ID").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.addons.custom_app_id")).setValue(ModConfig.baseID).setConfig(SimplyStatus.userConfig, "CUSTOM_APP_ID").build())
                        .addValue(new ButtonBuilder(Component.translatable("simplystatus.config.reconnect"), (s) -> SimplyStatus.reconnectApp()).build())
                );
        return builder.build();
    }
}
