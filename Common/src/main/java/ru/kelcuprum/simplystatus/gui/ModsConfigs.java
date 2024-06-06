package ru.kelcuprum.simplystatus.gui;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBooleanBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.editbox.EditBoxBuilder;
import ru.kelcuprum.alinlib.gui.components.text.CategoryBox;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.screens.ConfigScreenBuilder;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.config.ModConfig;

import static ru.kelcuprum.simplystatus.SimplyStatus.MINECRAFT;

public class ModsConfigs {
    private final InterfaceUtils.DesignType designType = InterfaceUtils.DesignType.FLAT;
    public Screen build(Screen parent){
        ConfigScreenBuilder builder =  new ConfigScreenBuilder(parent, Component.translatable("simplystatus.name"), designType)
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.client"), (s) -> MINECRAFT.setScreen(new MainConfigs().build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.localization"), (s) -> MINECRAFT.setScreen(new LocalizationsConfigs().build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.server"), (s) -> MINECRAFT.setScreen(new ServerConfigs().build(parent))).build().setActive(MINECRAFT.getCurrentServer() != null))
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.assets"), (s) -> MINECRAFT.setScreen(new AssetsConfigs().build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.addons"), (s) -> MINECRAFT.setScreen(new AddonsConfigs().build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.mods"), (s) -> MINECRAFT.setScreen(new ModsConfigs().build(parent))).build().setActive(SimplyStatus.isMusicModsEnable || SimplyStatus.isVoiceModsEnable || SimplyStatus.replayMod));
                //
        if(AlinLib.bariumConfig.getBoolean("FRIEND", true)) builder.addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.support"), (s) -> MINECRAFT.setScreen(new ThanksScreen().build(parent))).build());

        builder.addWidget(new TextBox(Component.translatable("simplystatus.config.mods"), true));

        if(SimplyStatus.isVoiceModsEnable) builder.addWidget(new CategoryBox(Component.translatable("simplystatus.config.voice"))
                .addValue(new ButtonBooleanBuilder(Component.translatable("simplystatus.config.addons.view_voice_speak"), false).setConfig(SimplyStatus.userConfig, "VIEW_VOICE_SPEAK").build())
                .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.mod.voice")).setValue(ModConfig.baseID).setLocalization(SimplyStatus.localization, "mod.voice").build())
                .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.mod.voice.players.one")).setValue(ModConfig.baseID).setLocalization(SimplyStatus.localization, "mod.voice.players.one").build())
                .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.mod.voice.players.more")).setValue(ModConfig.baseID).setLocalization(SimplyStatus.localization, "mod.voice.players.more").build())
        );
        if(SimplyStatus.isMusicModsEnable) builder.addWidget(new CategoryBox(Component.translatable("simplystatus.config.music"))
                .addValue(new ButtonBooleanBuilder(Component.translatable("simplystatus.config.addons.view_music_listener"), false).setConfig(SimplyStatus.userConfig, "VIEW_MUSIC_LISTENER").build())
                .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.mod.music")).setValue(ModConfig.baseID).setLocalization(SimplyStatus.localization, "mod.music").build())
                .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.mod.music.menu")).setValue(ModConfig.baseID).setLocalization(SimplyStatus.localization, "mod.music.menu").build())
                .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.mod.music.noauthor")).setValue(ModConfig.baseID).setLocalization(SimplyStatus.localization, "mod.music.noauthor").build())
                .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.mod.music.menu.noauthor")).setValue(ModConfig.baseID).setLocalization(SimplyStatus.localization, "mod.music.menu.noauthor").build())
        );
        if(SimplyStatus.replayMod) builder.addWidget(new CategoryBox(Component.translatable("simplystatus.config.replaymod"))
                .addValue(new ButtonBooleanBuilder(Component.translatable("simplystatus.config.addons.view_replay_mod"), true).setConfig(SimplyStatus.userConfig, "VIEW_REPLAY_MOD").build())
                .addValue(new ButtonBooleanBuilder(Component.translatable("simplystatus.config.server.show_name"), true).setConfig(SimplyStatus.userConfig, "VIEW_REPLAY_MOD_SERVER_NAME").build())
                .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.mod.replaymod")).setValue(ModConfig.baseID).setLocalization(SimplyStatus.localization, "mod.replaymod").build())
                .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.mod.replaymod.state")).setValue(ModConfig.baseID).setLocalization(SimplyStatus.localization, "mod.replaymod.state").build())
                .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.mod.replaymod.date_format")).setValue(ModConfig.baseID).setLocalization(SimplyStatus.localization, "mod.replaymod.date_format").build())
            );
        return builder.build();
    }
}
