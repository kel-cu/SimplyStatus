package ru.kelcuprum.sailstatus.gui;

import ru.kelcuprum.alinlib.gui.Icons;
import ru.kelcuprum.alinlib.gui.components.builder.button.*;
import ru.kelcuprum.alinlib.gui.components.builder.editbox.EditBoxBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.selector.SelectorBuilder;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.gui.components.builder.text.TextBuilder;
import ru.kelcuprum.alinlib.gui.components.editbox.EditBox;
import ru.kelcuprum.alinlib.gui.components.text.*;
import ru.kelcuprum.alinlib.gui.screens.ConfigScreenBuilder;
import ru.kelcuprum.sailstatus.SailStatus;
import ru.kelcuprum.sailstatus.config.*;

import static ru.kelcuprum.alinlib.AlinLib.MINECRAFT;
import static ru.kelcuprum.sailstatus.SailStatus.ICONS.*;

public class MainConfigs {

    public Screen build(Screen parent) {
        ConfigScreenBuilder builder = getBaseBuilder(parent);
        builder.addWidget(new TextBuilder(Component.translatable("sailstatus.config.client")))
                .addWidget(new ButtonBooleanBuilder(Component.translatable("sailstatus.config.client.show_rpc"), true).setConfig(SailStatus.userConfig, "SHOW_RPC"));
        if (!ModConfig.mineID.isBlank())
            builder.addWidget(new ButtonBooleanBuilder(Component.translatable("sailstatus.config.client.use_minecraft_id"), false).setConfig(SailStatus.userConfig, "USE_ANOTHER_ID").build())
                    .addWidget(new ButtonBuilder(Component.translatable("sailstatus.config.reconnect"), (s) -> SailStatus.reconnectApp()).build());
        builder.addWidget(new SelectorBuilder(Component.translatable("sailstatus.config.client.assets"), selectorButton -> SailStatus.userConfig.setString("USE_ASSETS", Assets.getByName(selectorButton.getList()[selectorButton.getPosition()]).id))
                .setList(Assets.getAssetsNames())
                .setValue(Assets.getPositionOnAssetsNames(Assets.getSelected().name))
                .build());
        EditBox title = new EditBoxBuilder(Component.translatable("sailstatus.config.client.button.label")).setValue("").setConfig(SailStatus.userConfig, "BUTTON.LABEL").build();
        title.setMaxLength(64);
        EditBox url = new EditBoxBuilder(Component.translatable("sailstatus.config.client.button.url")).setValue("").setConfig(SailStatus.userConfig, "BUTTON.URL").build();
        url.setMaxLength(64);
        builder.addWidget(new SelectorBuilder(Component.translatable("sailstatus.config.client.api")).setList(new String[]{
                        "CraftHead",
                        "Alina API: 2D",
                        "Alina API: 3D",
                        "Discord"
                }).setValue(0).setConfig(SailStatus.userConfig, "USE_API_RENDER").build())
                .addWidget(new ButtonBooleanBuilder(Component.translatable("sailstatus.config.client.show_game_started"), true).setConfig(SailStatus.userConfig, "SHOW_GAME_TIME").build())
                .addWidget(new ButtonBooleanBuilder(Component.translatable("sailstatus.config.client.singleplayer.world_name"), false).setConfig(SailStatus.userConfig, "SINGLEPLAYER.WORLD_NAME").build())
                .addWidget(new ButtonBooleanBuilder(Component.translatable("sailstatus.config.client.show_avatar_player"), true).setConfig(SailStatus.userConfig, "SHOW_AVATAR_PLAYER").build())
                .addWidget(new ButtonBooleanBuilder(Component.translatable("sailstatus.config.client.view_item_off_hand"), false).setConfig(SailStatus.userConfig, "VIEW_ITEM_OFF_HAND").build())
                .addWidget(new ButtonBooleanBuilder(Component.translatable("sailstatus.config.client.view_statistics"), true).setConfig(SailStatus.userConfig, "VIEW_STATISTICS").build())
                .addWidget(new ButtonBooleanBuilder(Component.translatable("sailstatus.config.client.view_player_name"), true).setConfig(SailStatus.userConfig, "VIEW_PLAYER_NAME").build())
                .addWidget(new CategoryBox(Component.translatable("sailstatus.config.client.button"))
                        .addValue(new ButtonBooleanBuilder(Component.translatable("sailstatus.config.client.button.enable"), false).setConfig(SailStatus.userConfig, "BUTTON.ENABLE").build())
                        .addValue(title)
                        .addValue(url));
        return builder.build();
    }

    public static ConfigScreenBuilder getBaseBuilder(Screen parent){
        ConfigScreenBuilder builder = new ConfigScreenBuilder(parent, Component.translatable("sailstatus.name"))
                .addPanelWidget(new ButtonBuilder(Component.translatable("sailstatus.config.client"), (s) -> MINECRAFT.setScreen(new MainConfigs().build(parent))).setCentered(false).setIcon(MONITOR).build());
        if(MINECRAFT.getCurrentServer() != null) builder.addPanelWidget(new ButtonBuilder(Component.translatable("sailstatus.config.server"), (s) -> MINECRAFT.setScreen(new ServerConfigs().build(parent))).setCentered(false).setIcon(MULTIPLAYER).build());
        builder.addPanelWidget(new ButtonBuilder(Component.translatable("sailstatus.config.localization"), (s) -> MINECRAFT.setScreen(new LocalizationsConfigs().build(parent))).setCentered(false).setIcon(LANGUAGE).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("sailstatus.config.assets"), (s) -> MINECRAFT.setScreen(new AssetsConfigs().build(parent))).setCentered(false).setIcon(ASSETS).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("sailstatus.config.addons"), (s) -> MINECRAFT.setScreen(new AddonsConfigs().build(parent))).setCentered(false).setIcon(ACCESSIBILITY).build());
        if(SailStatus.isMusicModsEnable || SailStatus.isVoiceModsEnable || SailStatus.replayMod  || SailStatus.klashback)
            builder.addPanelWidget(new ButtonBuilder(Component.translatable("sailstatus.config.mods"), (s) -> MINECRAFT.setScreen(new ModsConfigs().build(parent))).setCentered(false).setIcon(Icons.LIST).build());
        return builder;
    }
}
