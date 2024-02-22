package ru.kelcuprum.simplystatus.gui.config;

import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.components.buttons.ButtonConfigBoolean;
import ru.kelcuprum.alinlib.gui.components.buttons.base.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.selector.SelectorIntegerButton;
import ru.kelcuprum.alinlib.gui.components.selector.SelectorStringButton;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.screens.ConfigScreenBuilder;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.config.ModConfig;

import static ru.kelcuprum.simplystatus.SimplyStatus.MINECRAFT;

public class MainConfigs {
    private final InterfaceUtils.DesignType designType = InterfaceUtils.DesignType.FLAT;
    public Screen build(Screen parent){
        boolean customAssetsEnable = SimplyStatus.userConfig.getBoolean("USE_CUSTOM_ASSETS", false) || SimplyStatus.userConfig.getBoolean("USE_CUSTOM_APP_ID", false);
        ConfigScreenBuilder builder = new ConfigScreenBuilder(parent, Component.translatable("simplystatus.name"), designType)
                .addPanelWidget(new Button(10,40, designType, Component.translatable("simplystatus.config.client"), (s) -> MINECRAFT.setScreen(new MainConfigs().build(parent))))
                .addPanelWidget(new Button(10,65, designType, Component.translatable("simplystatus.config.localization"), (s) -> MINECRAFT.setScreen(new LocalizationsConfigs().build(parent))))
                .addPanelWidget(new Button(10,90, designType, Component.translatable("simplystatus.config.server"), (s) -> MINECRAFT.setScreen(new ServerConfigs().build(parent))).setActive(MINECRAFT.getCurrentServer() != null))
                .addPanelWidget(new Button(10,115, designType, Component.translatable("simplystatus.config.assets"), (s) -> MINECRAFT.setScreen(new AssetsConfigs().build(parent))).setActive(customAssetsEnable))
                .addPanelWidget(new Button(10,140, designType, Component.translatable("simplystatus.config.addons"), (s) -> MINECRAFT.setScreen(new AddonsConfigs().build(parent))))
                .addPanelWidget(new Button(10,165, designType, Component.translatable("simplystatus.config.mods"), (s) -> MINECRAFT.setScreen(new ModsConfigs().build(parent))).setActive(SimplyStatus.isMusicModsEnable || SimplyStatus.isVoiceModsEnable || SimplyStatus.replayMod));
                //

        if(AlinLib.bariumConfig.getBoolean("FRIEND", true)) builder.addPanelWidget(new Button(10,190, designType, Component.translatable("simplystatus.support"), (s) -> MINECRAFT.setScreen(new ThanksScreen().build(parent))));

        builder.addWidget(new TextBox(140, 5, Component.translatable("simplystatus.config.client"), true));
        if(!ModConfig.mineID.isBlank()) builder.addWidget(new ButtonConfigBoolean(140, 30, designType, SimplyStatus.userConfig, "USE_ANOTHER_ID", false, Component.translatable("simplystatus.config.client.use_minecraft_id")))
                .addWidget(new Button(140, 55, designType, Component.translatable("simplystatus.config.reconnect"), (s) -> SimplyStatus.reconnectApp()));
        if(!customAssetsEnable) builder.addWidget(new SelectorStringButton(140, 80, designType, ModConfig.assetsList, SimplyStatus.userConfig, "USE_ASSETS", ModConfig.assetsList[0], Component.translatable("simplystatus.config.client.assets")));
        builder.addWidget(new SelectorIntegerButton(140, 105, designType, SimplyStatus.apiNames, SimplyStatus.userConfig, "USE_API_RENDER", 0, Component.translatable("simplystatus.config.client.api")))
                .addWidget(new ButtonConfigBoolean(140, 130, designType, SimplyStatus.userConfig, "SHOW_GAME_TIME", true, Component.translatable("simplystatus.config.client.show_game_started")))
                .addWidget(new ButtonConfigBoolean(140, 155, designType, SimplyStatus.userConfig, "SHOW_AVATAR_PLAYER", true, Component.translatable("simplystatus.config.client.show_avatar_player")))
                .addWidget(new ButtonConfigBoolean(140, 180, designType, SimplyStatus.userConfig, "VIEW_ITEM_OFF_HAND", false, Component.translatable("simplystatus.config.client.view_item_off_hand")))
                .addWidget(new ButtonConfigBoolean(140, 205, designType, SimplyStatus.userConfig, "VIEW_STATISTICS", true, Component.translatable("simplystatus.config.client.view_statistics")))
                .addWidget(new ButtonConfigBoolean(140, 230, designType, SimplyStatus.userConfig, "VIEW_PLAYER_NAME", true, Component.translatable("simplystatus.config.client.view_player_name")));
        return builder.build();
    }
}
