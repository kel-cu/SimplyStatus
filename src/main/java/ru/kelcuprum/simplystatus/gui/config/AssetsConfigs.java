package ru.kelcuprum.simplystatus.gui.config;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.buttons.base.Button;
import ru.kelcuprum.alinlib.gui.components.editbox.base.EditBoxString;
import ru.kelcuprum.alinlib.gui.components.text.CategoryBox;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.screens.ConfigScreenBuilder;
import ru.kelcuprum.simplystatus.SimplyStatus;

public class AssetsConfigs {
    private final InterfaceUtils.DesignType designType = InterfaceUtils.DesignType.FLAT;
    public Screen build(Screen parent){
        ConfigScreenBuilder builder = new ConfigScreenBuilder(parent, Component.translatable("simplystatus.name"), designType)
                .addPanelWidget(new Button(10,40, designType, Component.translatable("simplystatus.config.client"), (s) -> Minecraft.getInstance().setScreen(new MainConfigs().build(parent))))
                .addPanelWidget(new Button(10,65, designType, Component.translatable("simplystatus.config.localization"), (s) -> Minecraft.getInstance().setScreen(new LocalizationsConfigs().build(parent))))
                .addPanelWidget(new Button(10,90, designType, Component.translatable("simplystatus.config.server"), (s) -> Minecraft.getInstance().setScreen(new ServerConfigs().build(parent))).setActive(Minecraft.getInstance().getCurrentServer() != null))
                .addPanelWidget(new Button(10,115, designType, Component.translatable("simplystatus.config.assets"), (s) -> Minecraft.getInstance().setScreen(new AssetsConfigs().build(parent))).setActive(SimplyStatus.userConfig.getBoolean("USE_CUSTOM_ASSETS", false) || SimplyStatus.userConfig.getBoolean("USE_CUSTOM_APP_ID", false)))
                .addPanelWidget(new Button(10,140, designType, Component.translatable("simplystatus.config.addons"), (s) -> Minecraft.getInstance().setScreen(new AddonsConfigs().build(parent))))
                .addPanelWidget(new Button(10,165, designType, Component.translatable("simplystatus.config.mods"), (s) -> Minecraft.getInstance().setScreen(new ModsConfigs().build(parent))).setActive(SimplyStatus.isMusicModsEnable || SimplyStatus.isVoiceModsEnable || SimplyStatus.replayMod));

        if(AlinLib.bariumConfig.getBoolean("FRIEND", true)) builder.addPanelWidget(new Button(10,190, designType, Component.translatable("simplystatus.support"), (s) -> Minecraft.getInstance().setScreen(new ThanksScreen().build(parent))));

        builder.addWidget(new TextBox(140, 5, Component.translatable("simplystatus.config.assets"), true))
                .addWidget(new CategoryBox(140, 30, Component.translatable("simplystatus.config.assets.title.menu"))
                        .addValue(new EditBoxString(140, 55, SimplyStatus.ASSETS.logo, designType,
                                Component.translatable("simplystatus.config.assets.logo"), newVal -> SimplyStatus.ASSETS.setValue("logo", newVal)))
                )
                .addWidget(new CategoryBox(140, 80, Component.translatable("simplystatus.config.assets.title.time"))
                        .addValue(new EditBoxString(140, 105, SimplyStatus.ASSETS.day, designType,
                                Component.translatable("simplystatus.config.assets.day"), newVal -> SimplyStatus.ASSETS.setValue("day", newVal)))
                        .addValue(new EditBoxString(140, 130, SimplyStatus.ASSETS.night, designType,
                                Component.translatable("simplystatus.config.assets.night"), newVal -> SimplyStatus.ASSETS.setValue("night", newVal)))
                        .addValue(new EditBoxString(140,  155, SimplyStatus.ASSETS.evening, designType,
                                Component.translatable("simplystatus.config.assets.evening"), newVal -> SimplyStatus.ASSETS.setValue("evening", newVal)))
                        .addValue(new EditBoxString(140, 180, SimplyStatus.ASSETS.morning, designType,
                                Component.translatable("simplystatus.config.assets.morning"), newVal -> SimplyStatus.ASSETS.setValue("morning", newVal)))
                )
                .addWidget(new CategoryBox(140, 205, Component.translatable("simplystatus.config.assets.title.time"))
                        .addValue(new EditBoxString(140, 230, SimplyStatus.ASSETS.world, designType,
                                Component.translatable("simplystatus.config.assets.world"), newVal -> SimplyStatus.ASSETS.setValue("world", newVal)))
                        .addValue(new EditBoxString(140, 255, SimplyStatus.ASSETS.world_nether, designType,
                                Component.translatable("simplystatus.config.assets.world_nether"), newVal -> SimplyStatus.ASSETS.setValue("world_nether", newVal)))
                        .addValue(new EditBoxString(140,  280, SimplyStatus.ASSETS.world_the_end, designType,
                                Component.translatable("simplystatus.config.assets.world_the_end"), newVal -> SimplyStatus.ASSETS.setValue("world_the_end", newVal)))
                        .addValue(new EditBoxString(140, 305, SimplyStatus.ASSETS.world_moon, designType,
                                Component.translatable("simplystatus.config.assets.world_moon"), newVal -> SimplyStatus.ASSETS.setValue("world_moon", newVal)))
                )
                .addWidget(new CategoryBox(140, 330, Component.translatable("simplystatus.config.assets.title.modification"))
                        .addValue(new EditBoxString(140, 355, SimplyStatus.ASSETS.voice, designType,
                                Component.translatable("simplystatus.config.assets.voice"), newVal -> SimplyStatus.ASSETS.setValue("voice", newVal)))
                        .addValue(new EditBoxString(140, 380, SimplyStatus.ASSETS.music, designType,
                                Component.translatable("simplystatus.config.assets.music"), newVal -> SimplyStatus.ASSETS.setValue("music", newVal)))
                        .addValue(new EditBoxString(140,  405, SimplyStatus.ASSETS.replaymod, designType,
                                Component.translatable("simplystatus.config.assets.replaymod"), newVal -> SimplyStatus.ASSETS.setValue("replaymod", newVal)))
                )
                .addWidget(new CategoryBox(140, 430, Component.translatable("simplystatus.config.assets.title.unknown"))
                        .addValue(new EditBoxString(140, 455, SimplyStatus.ASSETS.unknown_world, designType,
                                Component.translatable("simplystatus.config.assets.unknown_world"), newVal -> SimplyStatus.ASSETS.setValue("unknown_world", newVal)))
                        .addValue(new EditBoxString(140, 480, SimplyStatus.ASSETS.unknown, designType,
                                Component.translatable("simplystatus.config.assets.unknown"), newVal -> SimplyStatus.ASSETS.setValue("unknown", newVal)))
                );
        return builder.build();
    }
}
