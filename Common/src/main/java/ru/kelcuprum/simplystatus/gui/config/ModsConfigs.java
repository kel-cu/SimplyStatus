package ru.kelcuprum.simplystatus.gui.config;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.buttons.ButtonConfigBoolean;
import ru.kelcuprum.alinlib.gui.components.buttons.base.Button;
import ru.kelcuprum.alinlib.gui.components.editbox.EditBoxLocalization;
import ru.kelcuprum.alinlib.gui.components.text.CategoryBox;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.screens.ConfigScreenBuilder;
import ru.kelcuprum.simplystatus.SimplyStatus;

import static ru.kelcuprum.simplystatus.SimplyStatus.MINECRAFT;

public class ModsConfigs {
    private final InterfaceUtils.DesignType designType = InterfaceUtils.DesignType.FLAT;
    public Screen build(Screen parent){
        ConfigScreenBuilder builder =  new ConfigScreenBuilder(parent, Component.translatable("simplystatus.name"), designType)
                .addPanelWidget(new Button(10,40, designType, Component.translatable("simplystatus.config.client"), (s) -> MINECRAFT.setScreen(new MainConfigs().build(parent))))
                .addPanelWidget(new Button(10,65, designType, Component.translatable("simplystatus.config.localization"), (s) -> MINECRAFT.setScreen(new LocalizationsConfigs().build(parent))))
                .addPanelWidget(new Button(10,90, designType, Component.translatable("simplystatus.config.server"), (s) -> MINECRAFT.setScreen(new ServerConfigs().build(parent))).setActive(MINECRAFT.getCurrentServer() != null))
                .addPanelWidget(new Button(10,115, designType, Component.translatable("simplystatus.config.assets"), (s) -> MINECRAFT.setScreen(new AssetsConfigs().build(parent))).setActive(SimplyStatus.userConfig.getBoolean("USE_CUSTOM_ASSETS", false) || SimplyStatus.userConfig.getBoolean("USE_CUSTOM_APP_ID", false)))
                .addPanelWidget(new Button(10,140, designType, Component.translatable("simplystatus.config.addons"), (s) -> MINECRAFT.setScreen(new AddonsConfigs().build(parent))))
                .addPanelWidget(new Button(10,165, designType, Component.translatable("simplystatus.config.mods"), (s) -> MINECRAFT.setScreen(new ModsConfigs().build(parent))).setActive(SimplyStatus.isMusicModsEnable || SimplyStatus.isVoiceModsEnable || SimplyStatus.replayMod));
                //
        if(AlinLib.bariumConfig.getBoolean("FRIEND", true)) builder.addPanelWidget(new Button(10,190, designType, Component.translatable("simplystatus.support"), (s) -> MINECRAFT.setScreen(new ThanksScreen().build(parent))));

        builder.addWidget(new TextBox(140, 5, Component.translatable("simplystatus.config.mods"), true));

        if(SimplyStatus.isVoiceModsEnable) builder.addWidget(new CategoryBox(140, -20, Component.translatable("simplystatus.config.voice"))
                .addValue(new ButtonConfigBoolean(140, -20, designType, SimplyStatus.userConfig, "VIEW_VOICE_SPEAK", true, Component.translatable("simplystatus.config.addons.view_voice_speak")))
                .addValue(new EditBoxLocalization(140, -20, designType, SimplyStatus.localization, "mod.voice", Component.translatable("simplystatus.config.localization.mod.voice")))
                .addValue(new EditBoxLocalization(140, -20, designType, SimplyStatus.localization, "mod.voice.players.one", Component.translatable("simplystatus.config.localization.mod.voice.players.one")))
                .addValue(new EditBoxLocalization(140, -20, designType, SimplyStatus.localization, "mod.voice.players.more", Component.translatable("simplystatus.config.localization.mod.voice.players.more")))
        );
        if(SimplyStatus.isMusicModsEnable) builder.addWidget(new CategoryBox(140, -20, Component.translatable("simplystatus.config.music"))
                .addValue(new ButtonConfigBoolean(140, -20, designType, SimplyStatus.userConfig, "VIEW_MUSIC_LISTENER", false, Component.translatable("simplystatus.config.addons.view_music_listener")))
                .addValue(new EditBoxLocalization(140, -20, designType, SimplyStatus.localization, "mod.music", Component.translatable("simplystatus.config.localization.mod.music")))
                .addValue(new EditBoxLocalization(140, -20, designType, SimplyStatus.localization, "mod.music.menu", Component.translatable("simplystatus.config.localization.mod.music.menu")))
                .addValue(new EditBoxLocalization(140, -20, designType, SimplyStatus.localization, "mod.music.noauthor", Component.translatable("simplystatus.config.localization.mod.music.noauthor")))
                .addValue(new EditBoxLocalization(140, -20, designType, SimplyStatus.localization, "mod.music.menu.noauthor", Component.translatable("simplystatus.config.localization.mod.music.menu.noauthor")))
        );
        if(SimplyStatus.replayMod) builder.addWidget(new CategoryBox(140, -20, Component.translatable("simplystatus.config.replaymod"))
                .addValue(new ButtonConfigBoolean(140, -20, designType, SimplyStatus.userConfig, "VIEW_REPLAY_MOD", true, Component.translatable("simplystatus.config.addons.view_replay_mod")))
                    .addValue(new ButtonConfigBoolean(140, -20, designType, SimplyStatus.userConfig, "VIEW_REPLAY_MOD_SERVER_NAME", true, Component.translatable("simplystatus.config.server.show_name")))
                    .addValue(new EditBoxLocalization(140, -20, designType, SimplyStatus.localization, "mod.replaymod", Component.translatable("simplystatus.config.localization.mod.replaymod")))
                    .addValue(new EditBoxLocalization(140, -20, designType, SimplyStatus.localization, "mod.replaymod.state", Component.translatable("simplystatus.config.localization.mod.replaymod.state")))
            );
        return builder.build();
    }
}
