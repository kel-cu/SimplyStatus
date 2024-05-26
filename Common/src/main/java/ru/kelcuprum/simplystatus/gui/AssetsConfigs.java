package ru.kelcuprum.simplystatus.gui;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBuilder;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.screens.ConfigScreenBuilder;
import ru.kelcuprum.simplystatus.SimplyStatus;

import static ru.kelcuprum.simplystatus.SimplyStatus.MINECRAFT;

public class AssetsConfigs {
    private final InterfaceUtils.DesignType designType = InterfaceUtils.DesignType.FLAT;
    public Screen build(Screen parent){
        ConfigScreenBuilder builder = new ConfigScreenBuilder(parent, Component.translatable("simplystatus.name"))
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.client"), (s) -> MINECRAFT.setScreen(new MainConfigs().build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.localization"), (s) -> MINECRAFT.setScreen(new LocalizationsConfigs().build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.server"), (s) -> MINECRAFT.setScreen(new ServerConfigs().build(parent))).build().setActive(MINECRAFT.getCurrentServer() != null))
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.assets"), (s) -> MINECRAFT.setScreen(new AssetsConfigs().build(parent))).build().setActive(SimplyStatus.userConfig.getBoolean("USE_CUSTOM_ASSETS", false) || SimplyStatus.userConfig.getBoolean("USE_CUSTOM_APP_ID", false)))
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.addons"), (s) -> MINECRAFT.setScreen(new AddonsConfigs().build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.mods"), (s) -> MINECRAFT.setScreen(new ModsConfigs().build(parent))).build().setActive(SimplyStatus.isMusicModsEnable || SimplyStatus.isVoiceModsEnable || SimplyStatus.replayMod));

        if(AlinLib.bariumConfig.getBoolean("FRIEND", true)) builder.addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.support"), (s) -> MINECRAFT.setScreen(new ThanksScreen().build(parent))).build());

        builder.addWidget(new TextBox( Component.translatable("simplystatus.config.assets"), true));
//                .addWidget(new CategoryBox(Component.translatable("simplystatus.config.assets.title.menu"))
//                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.assets.logo"), newVal -> SimplyStatus.ASSETS.setValue("logo", newVal)).setValue(SimplyStatus.ASSETS.logo).build())
//                )
//                .addWidget(new CategoryBox(Component.translatable("simplystatus.config.assets.title.time"))
//                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.assets.day"), newVal -> SimplyStatus.ASSETS.setValue("day", newVal)).setValue(SimplyStatus.ASSETS.day).build())
//                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.assets.night"), newVal -> SimplyStatus.ASSETS.setValue("night", newVal)).setValue(SimplyStatus.ASSETS.night).build())
//                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.assets.evening"), newVal -> SimplyStatus.ASSETS.setValue("evening", newVal)).setValue(SimplyStatus.ASSETS.evening).build())
//                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.assets.morning"), newVal -> SimplyStatus.ASSETS.setValue("morning", newVal)).setValue(SimplyStatus.ASSETS.morning).build())
//                )
//                .addWidget(new CategoryBox(Component.translatable("simplystatus.config.assets.title.time"))
//                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.assets.world"), newVal -> SimplyStatus.ASSETS.setValue("world", newVal)).setValue(SimplyStatus.ASSETS.day).build())
//                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.assets.world_nether"), newVal -> SimplyStatus.ASSETS.setValue("world_nether", newVal)).setValue(SimplyStatus.ASSETS.night).build())
//                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.assets.world_the_end"), newVal -> SimplyStatus.ASSETS.setValue("world_the_end", newVal)).setValue(SimplyStatus.ASSETS.evening).build())
//                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.assets.world_moon"), newVal -> SimplyStatus.ASSETS.setValue("world_moon", newVal)).setValue(SimplyStatus.ASSETS.morning).build())
//                )
//                .addWidget(new CategoryBox(Component.translatable("simplystatus.config.assets.title.modification"))
//                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.assets.voice"), newVal -> SimplyStatus.ASSETS.setValue("voice", newVal)).setValue(SimplyStatus.ASSETS.day).build())
//                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.assets.music"), newVal -> SimplyStatus.ASSETS.setValue("music", newVal)).setValue(SimplyStatus.ASSETS.night).build())
//                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.assets.replaymod"), newVal -> SimplyStatus.ASSETS.setValue("replaymod", newVal)).setValue(SimplyStatus.ASSETS.evening).build())
//                )
//                .addWidget(new CategoryBox(Component.translatable("simplystatus.config.assets.title.unknown"))
//                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.assets.unknown_world"), newVal -> SimplyStatus.ASSETS.setValue("unknown_world", newVal)).setValue(SimplyStatus.ASSETS.night).build())
//                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.assets.unknown"), newVal -> SimplyStatus.ASSETS.setValue("unknown", newVal)).setValue(SimplyStatus.ASSETS.evening).build())
//                );
        return builder.build();
    }
}
