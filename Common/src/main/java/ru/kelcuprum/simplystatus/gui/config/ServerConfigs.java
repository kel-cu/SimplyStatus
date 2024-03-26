package ru.kelcuprum.simplystatus.gui.config;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBooleanBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.editbox.EditBoxBuilder;
import ru.kelcuprum.alinlib.gui.components.buttons.ButtonConfigBoolean;
import ru.kelcuprum.alinlib.gui.components.buttons.base.Button;
import ru.kelcuprum.alinlib.gui.components.editbox.EditBoxConfigString;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.screens.ConfigScreenBuilder;
import ru.kelcuprum.simplystatus.SimplyStatus;

import static ru.kelcuprum.simplystatus.SimplyStatus.MINECRAFT;

public class ServerConfigs {
    private final InterfaceUtils.DesignType designType = InterfaceUtils.DesignType.FLAT;
    public Screen build(Screen parent){
        ConfigScreenBuilder builder = new ConfigScreenBuilder(parent, Component.translatable("simplystatus.name"), designType)
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.client"), (s) -> MINECRAFT.setScreen(new MainConfigs().build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.localization"), (s) -> MINECRAFT.setScreen(new LocalizationsConfigs().build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.server"), (s) -> MINECRAFT.setScreen(new ServerConfigs().build(parent))).build().setActive(MINECRAFT.getCurrentServer() != null))
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.assets"), (s) -> MINECRAFT.setScreen(new AssetsConfigs().build(parent))).build().setActive(SimplyStatus.userConfig.getBoolean("USE_CUSTOM_ASSETS", false) || SimplyStatus.userConfig.getBoolean("USE_CUSTOM_APP_ID", false)))
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.addons"), (s) -> MINECRAFT.setScreen(new AddonsConfigs().build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.mods"), (s) -> MINECRAFT.setScreen(new ModsConfigs().build(parent))).build().setActive(SimplyStatus.isMusicModsEnable || SimplyStatus.isVoiceModsEnable || SimplyStatus.replayMod));
                //
        if(AlinLib.bariumConfig.getBoolean("FRIEND", true)) builder.addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.support"), (s) -> MINECRAFT.setScreen(new ThanksScreen().build(parent))).build());
        builder.addWidget(new TextBox(Component.translatable("simplystatus.config.server"), true))

                .addWidget(new ButtonBooleanBuilder(Component.translatable("simplystatus.config.server.show_custom_name"), false).setConfig(SimplyStatus.userConfig, "USE_CUSTOM_NAME").build())
                .addWidget(new EditBoxBuilder(Component.translatable("simplystatus.config.server.custom_name")).setConfig(SimplyStatus.serverConfig, "CUSTOM_NAME").build())
                .addWidget(new ButtonBooleanBuilder(Component.translatable("simplystatus.config.server.show_name"), true).setConfig(SimplyStatus.userConfig, "SHOW_NAME").build())
                .addWidget(new ButtonBooleanBuilder(Component.translatable("simplystatus.config.server.show_address"), false).setConfig(SimplyStatus.userConfig, "SHOW_ADDRESS").build())
                .addWidget(new ButtonBooleanBuilder(Component.translatable("simplystatus.config.server.show_icon"), false).setConfig(SimplyStatus.userConfig, "SHOW_ICON").build())
                .addWidget(new EditBoxBuilder(Component.translatable("simplystatus.config.server.icon_url")).setValue("https://api.mcstatus.io/v2/icon/%address%").setConfig(SimplyStatus.serverConfig, "CUSTOM_NAME").build());
        return builder.build();
    }
}
