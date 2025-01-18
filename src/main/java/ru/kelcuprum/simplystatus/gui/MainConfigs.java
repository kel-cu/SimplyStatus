package ru.kelcuprum.simplystatus.gui;

import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.components.builder.button.*;
import ru.kelcuprum.alinlib.gui.components.builder.editbox.EditBoxBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.selector.SelectorBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.gui.components.builder.text.TextBuilder;
import ru.kelcuprum.alinlib.gui.components.editbox.EditBox;
import ru.kelcuprum.alinlib.gui.components.text.*;
import ru.kelcuprum.alinlib.gui.screens.ConfigScreenBuilder;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.config.*;

import static ru.kelcuprum.alinlib.AlinLib.MINECRAFT;

public class MainConfigs {

    public Screen build(Screen parent) {
        ConfigScreenBuilder builder = new ConfigScreenBuilder(parent, Component.translatable("simplystatus.name"))
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.client"), (s) -> MINECRAFT.setScreen(new MainConfigs().build(parent))).build());
        if(MINECRAFT.getCurrentServer() != null) builder.addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.server"), (s) -> MINECRAFT.setScreen(new ServerConfigs().build(parent))).build());
        builder.addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.localization"), (s) -> MINECRAFT.setScreen(new LocalizationsConfigs().build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.assets"), (s) -> MINECRAFT.setScreen(new AssetsConfigs().build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.addons"), (s) -> MINECRAFT.setScreen(new AddonsConfigs().build(parent))).build());
        if(SimplyStatus.isMusicModsEnable || SimplyStatus.isVoiceModsEnable || SimplyStatus.replayMod  || SimplyStatus.klashback)
            builder.addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.mods"), (s) -> MINECRAFT.setScreen(new ModsConfigs().build(parent))).build());
        builder.addWidget(new TextBuilder(Component.translatable("simplystatus.config.client")))
                .addWidget(new ButtonBooleanBuilder(Component.translatable("simplystatus.config.client.show_rpc"), true).setConfig(SimplyStatus.userConfig, "SHOW_RPC"));
        if (!ModConfig.mineID.isBlank())
            builder.addWidget(new ButtonBooleanBuilder(Component.translatable("simplystatus.config.client.use_minecraft_id"), false).setConfig(SimplyStatus.userConfig, "USE_ANOTHER_ID").build())
                    .addWidget(new ButtonBuilder(Component.translatable("simplystatus.config.reconnect"), (s) -> SimplyStatus.reconnectApp()).build());
        builder.addWidget(new SelectorBuilder(Component.translatable("simplystatus.config.client.assets"), selectorButton -> SimplyStatus.userConfig.setString("USE_ASSETS", Assets.getByName(selectorButton.getList()[selectorButton.getPosition()]).id))
                .setList(Assets.getAssetsNames())
                .setValue(Assets.getPositionOnAssetsNames(Assets.getSelected().name))
                .build());
        EditBox title = new EditBoxBuilder(Component.translatable("simplystatus.config.client.button.label")).setValue("").setConfig(SimplyStatus.userConfig, "BUTTON.LABEL").build();
        title.setMaxLength(64);
        EditBox url = new EditBoxBuilder(Component.translatable("simplystatus.config.client.button.url")).setValue("").setConfig(SimplyStatus.userConfig, "BUTTON.URL").build();
        url.setMaxLength(64);
        builder.addWidget(new SelectorBuilder(Component.translatable("simplystatus.config.client.api")).setList(new String[]{
                        "CraftHead",
                        "Alina API: 2D",
                        "Alina API: 3D",
                        "Discord"
                }).setValue(0).setConfig(SimplyStatus.userConfig, "USE_API_RENDER").build())
                .addWidget(new ButtonBooleanBuilder(Component.translatable("simplystatus.config.client.show_game_started"), true).setConfig(SimplyStatus.userConfig, "SHOW_GAME_TIME").build())
                .addWidget(new ButtonBooleanBuilder(Component.translatable("simplystatus.config.client.singleplayer.world_name"), false).setConfig(SimplyStatus.userConfig, "SINGLEPLAYER.WORLD_NAME").build())
                .addWidget(new ButtonBooleanBuilder(Component.translatable("simplystatus.config.client.show_avatar_player"), true).setConfig(SimplyStatus.userConfig, "SHOW_AVATAR_PLAYER").build())
                .addWidget(new ButtonBooleanBuilder(Component.translatable("simplystatus.config.client.view_item_off_hand"), false).setConfig(SimplyStatus.userConfig, "VIEW_ITEM_OFF_HAND").build())
                .addWidget(new ButtonBooleanBuilder(Component.translatable("simplystatus.config.client.view_statistics"), true).setConfig(SimplyStatus.userConfig, "VIEW_STATISTICS").build())
                .addWidget(new ButtonBooleanBuilder(Component.translatable("simplystatus.config.client.view_player_name"), true).setConfig(SimplyStatus.userConfig, "VIEW_PLAYER_NAME").build())
                .addWidget(new CategoryBox(Component.translatable("simplystatus.config.client.button"))
                        .addValue(new ButtonBooleanBuilder(Component.translatable("simplystatus.config.client.button.enable"), false).setConfig(SimplyStatus.userConfig, "BUTTON.ENABLE").build())
                        .addValue(title)
                        .addValue(url));
        return builder.build();
    }
}
