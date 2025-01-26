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

import static ru.kelcuprum.sailstatus.gui.MainConfigs.getBaseBuilder;

public class ModsConfigs {
    public Screen build(Screen parent){
        ConfigScreenBuilder builder = getBaseBuilder(parent);
        builder.addWidget(new TextBuilder(Component.translatable("sailstatus.config.mods")));

        if(SailStatus.isVoiceModsEnable) builder.addWidget(new CategoryBox(Component.translatable("sailstatus.config.voice"))
                .addValue(new ButtonBooleanBuilder(Component.translatable("sailstatus.config.addons.view_voice_speak"), false).setConfig(SailStatus.userConfig, "VIEW_VOICE_SPEAK").build())
                .addValue(new EditBoxBuilder(Component.translatable("sailstatus.config.localization.mod.voice")).setValue(ModConfig.baseID).setLocalization(SailStatus.localization, "mod.voice").build())
                .addValue(new EditBoxBuilder(Component.translatable("sailstatus.config.localization.mod.voice.players.one")).setValue(ModConfig.baseID).setLocalization(SailStatus.localization, "mod.voice.players.one").build())
                .addValue(new EditBoxBuilder(Component.translatable("sailstatus.config.localization.mod.voice.players.more")).setValue(ModConfig.baseID).setLocalization(SailStatus.localization, "mod.voice.players.more").build())
        );
        if(SailStatus.isMusicModsEnable) builder.addWidget(new CategoryBox(Component.translatable("sailstatus.config.music"))
                .addValue(new ButtonBooleanBuilder(Component.translatable("sailstatus.config.addons.view_music_listener"), false).setConfig(SailStatus.userConfig, "VIEW_MUSIC_LISTENER").build())
                .addValue(new EditBoxBuilder(Component.translatable("sailstatus.config.localization.mod.music")).setValue(ModConfig.baseID).setLocalization(SailStatus.localization, "mod.music").build())
                .addValue(new EditBoxBuilder(Component.translatable("sailstatus.config.localization.mod.music.menu")).setValue(ModConfig.baseID).setLocalization(SailStatus.localization, "mod.music.menu").build())
                .addValue(new EditBoxBuilder(Component.translatable("sailstatus.config.localization.mod.music.noauthor")).setValue(ModConfig.baseID).setLocalization(SailStatus.localization, "mod.music.noauthor").build())
                .addValue(new EditBoxBuilder(Component.translatable("sailstatus.config.localization.mod.music.menu.noauthor")).setValue(ModConfig.baseID).setLocalization(SailStatus.localization, "mod.music.menu.noauthor").build())
        );
        if(SailStatus.klashback || SailStatus.replayMod) builder.addWidget(new ButtonBooleanBuilder(Component.translatable("sailstatus.config.addons.view_replay_mod"), true).setConfig(SailStatus.userConfig, "VIEW_REPLAY_MOD").build());
        if(SailStatus.klashback) builder.addWidget(new CategoryBox(Component.translatable("sailstatus.config.flashback"))
                .addValue(new EditBoxBuilder(Component.translatable("sailstatus.config.localization.mod.flashback")).setValue(ModConfig.baseID).setLocalization(SailStatus.localization, "mod.flashback").build())
                .addValue(new EditBoxBuilder(Component.translatable("sailstatus.config.localization.mod.flashback.state")).setValue(ModConfig.baseID).setLocalization(SailStatus.localization, "mod.flashback.state").build())
                .addValue(new EditBoxBuilder(Component.translatable("sailstatus.config.localization.mod.flashback.date_format")).setValue(ModConfig.baseID).setLocalization(SailStatus.localization, "mod.flashback.date_format").build())
        );
        if(SailStatus.replayMod) builder.addWidget(new CategoryBox(Component.translatable("sailstatus.config.replaymod"))
                .addValue(new ButtonBooleanBuilder(Component.translatable("sailstatus.config.server.show_name"), true).setConfig(SailStatus.userConfig, "VIEW_REPLAY_MOD_SERVER_NAME").build())
                .addValue(new EditBoxBuilder(Component.translatable("sailstatus.config.localization.mod.replaymod")).setValue(ModConfig.baseID).setLocalization(SailStatus.localization, "mod.replaymod").build())
                .addValue(new EditBoxBuilder(Component.translatable("sailstatus.config.localization.mod.replaymod.state")).setValue(ModConfig.baseID).setLocalization(SailStatus.localization, "mod.replaymod.state").build())
                .addValue(new EditBoxBuilder(Component.translatable("sailstatus.config.localization.mod.replaymod.date_format")).setValue(ModConfig.baseID).setLocalization(SailStatus.localization, "mod.replaymod.date_format").build())
        );
        return builder.build();
    }
}
