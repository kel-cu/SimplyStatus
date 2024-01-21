package ru.kelcuprum.simplystatus.gui.config;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.buttons.ButtonConfigBoolean;
import ru.kelcuprum.alinlib.gui.components.buttons.base.Button;
import ru.kelcuprum.alinlib.gui.components.editbox.EditBoxConfigString;
import ru.kelcuprum.alinlib.gui.components.text.CategoryBox;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.screens.ConfigScreenBuilder;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.config.ModConfig;

public class AddonsConfigs {
    private final InterfaceUtils.DesignType designType = InterfaceUtils.DesignType.FLAT;
    public Screen build(Screen parent){
        return new ConfigScreenBuilder(parent, Component.translatable("simplystatus.name"), designType)
                .addPanelWidget(new Button(10,40, designType, Component.translatable("simplystatus.config.client"), (s) -> {
                    Minecraft.getInstance().setScreen(new MainConfigs().build(parent));
                }))
                .addPanelWidget(new Button(10,65, designType, Component.translatable("simplystatus.config.localization"), (s) -> {
                    Minecraft.getInstance().setScreen(new LocalizationsConfigs().build(parent));
                }))
                .addPanelWidget(new Button(10,90, designType, Component.translatable("simplystatus.config.server"), (s) -> {
                    Minecraft.getInstance().setScreen(new ServerConfigs().build(parent));
                }).setActive(Minecraft.getInstance().getCurrentServer() != null))
                .addPanelWidget(new Button(10,115, designType, Component.translatable("simplystatus.config.assets"), (s) -> {
                    Minecraft.getInstance().setScreen(new AssetsConfigs().build(parent));
                }).setActive(SimplyStatus.userConfig.getBoolean("USE_CUSTOM_ASSETS", false) || SimplyStatus.userConfig.getBoolean("USE_CUSTOM_APP_ID", false)))
                .addPanelWidget(new Button(10,140, designType, Component.translatable("simplystatus.config.addons"), (s) -> {
                    Minecraft.getInstance().setScreen(new AddonsConfigs().build(parent));
                }))
                .addPanelWidget(new Button(10,165, designType, Component.translatable("simplystatus.config.mods"), (s) -> {
                    Minecraft.getInstance().setScreen(new ModsConfigs().build(parent));
                }).setActive(SimplyStatus.isMusicModsEnable || SimplyStatus.isVoiceModsEnable || SimplyStatus.replayMod))
                .addWidget(new TextBox(140, 5, Component.translatable("simplystatus.config.addons"), true))
                .addWidget(new CategoryBox(140, 30, Component.translatable("simplystatus.config.addons.indicators"))
                        .addValue(new ButtonConfigBoolean(140, 55, designType, SimplyStatus.userConfig, "SHOW_ITEMS", true, Component.translatable("simplystatus.config.addons.show_items")))
                        .addValue(new ButtonConfigBoolean(140, 80, designType, SimplyStatus.userConfig, "ENABLE_TIME_CYCLE", true, Component.translatable("simplystatus.config.addons.enable_time_cycle")))
                        .addValue(new ButtonConfigBoolean(140, 105, designType, SimplyStatus.userConfig, "ENABLE_WORLD", true, Component.translatable("simplystatus.config.addons.enable_world")))
                )
                .addWidget(new CategoryBox(140, 130, Component.translatable("simplystatus.config.addons.custom"))
                                .addValue(new ButtonConfigBoolean(140, 155, designType, SimplyStatus.userConfig, "USE_CUSTOM_ASSETS", false, Component.translatable("simplystatus.config.addons.use_custom_assets")))
                                .addValue(new ButtonConfigBoolean(140, 180, designType, SimplyStatus.userConfig, "USE_CUSTOM_APP_ID", false, Component.translatable("simplystatus.config.addons.use_custom_app_id")))
                                .addValue(new EditBoxConfigString(140, 205, false, designType, SimplyStatus.userConfig, "CUSTOM_APP_ID", ModConfig.baseID, Component.translatable("simplystatus.config.addons.custom_app_id")))
                        //"CUSTOM_APP_ID", ModConfig.baseID
                )
                .build();
    }
}
