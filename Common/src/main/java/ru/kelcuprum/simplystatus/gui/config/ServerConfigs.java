package ru.kelcuprum.simplystatus.gui.config;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
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
                .addPanelWidget(new Button(10,40, designType, Component.translatable("simplystatus.config.client"), (s) -> MINECRAFT.setScreen(new MainConfigs().build(parent))))
                .addPanelWidget(new Button(10,65, designType, Component.translatable("simplystatus.config.localization"), (s) -> MINECRAFT.setScreen(new LocalizationsConfigs().build(parent))))
                .addPanelWidget(new Button(10,90, designType, Component.translatable("simplystatus.config.server"), (s) -> MINECRAFT.setScreen(new ServerConfigs().build(parent))).setActive(MINECRAFT.getCurrentServer() != null))
                .addPanelWidget(new Button(10,115, designType, Component.translatable("simplystatus.config.assets"), (s) -> MINECRAFT.setScreen(new AssetsConfigs().build(parent))).setActive(SimplyStatus.userConfig.getBoolean("USE_CUSTOM_ASSETS", false) || SimplyStatus.userConfig.getBoolean("USE_CUSTOM_APP_ID", false)))
                .addPanelWidget(new Button(10,140, designType, Component.translatable("simplystatus.config.addons"), (s) -> MINECRAFT.setScreen(new AddonsConfigs().build(parent))))
                .addPanelWidget(new Button(10,165, designType, Component.translatable("simplystatus.config.mods"), (s) -> MINECRAFT.setScreen(new ModsConfigs().build(parent))).setActive(SimplyStatus.isMusicModsEnable || SimplyStatus.isVoiceModsEnable || SimplyStatus.replayMod));
                //
        if(AlinLib.bariumConfig.getBoolean("FRIEND", true)) builder.addPanelWidget(new Button(10,165, designType, Component.translatable("simplystatus.support"), (s) -> MINECRAFT.setScreen(new ThanksScreen().build(parent))));
        builder.addWidget(new TextBox(140, 5, Component.translatable("simplystatus.config.server"), true))
                .addWidget(new ButtonConfigBoolean(140, 30, designType, SimplyStatus.serverConfig, "USE_CUSTOM_NAME", false, Component.translatable("simplystatus.config.server.show_custom_name")))
                .addWidget(new EditBoxConfigString(140, 55, false, designType, SimplyStatus.serverConfig, "CUSTOM_NAME", "", Component.translatable("simplystatus.config.server.custom_name")))
                .addWidget(new ButtonConfigBoolean(140, 80, designType, SimplyStatus.serverConfig, "SHOW_NAME", true, Component.translatable("simplystatus.config.server.show_name")))
                .addWidget(new ButtonConfigBoolean(140, 105, designType, SimplyStatus.serverConfig, "SHOW_ADDRESS", false, Component.translatable("simplystatus.config.server.show_address")))
                .addWidget(new ButtonConfigBoolean(140, 130, designType, SimplyStatus.serverConfig, "SHOW_ICON", false, Component.translatable("simplystatus.config.server.show_icon")))
                .addWidget(new EditBoxConfigString(140, 155, false, designType, SimplyStatus.serverConfig, "ICON_URL", "https://api.mcstatus.io/v2/icon/%address%", Component.translatable("simplystatus.config.server.icon_url")));
        return builder.build();
    }
}
