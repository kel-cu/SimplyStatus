package ru.kelcuprum.simplystatus.gui.config;

import com.terraformersmc.modmenu.gui.ModsScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.buttons.base.Button;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.screens.ConfigScreenBuilder;
import ru.kelcuprum.simplystatus.SimplyStatus;

public class AssetsConfigs {
    private final InterfaceUtils.DesignType designType = InterfaceUtils.DesignType.FLAT;
    public Screen build(Screen parent){
        return new ConfigScreenBuilder(parent, Component.translatable("simplystatus.name"), designType)
                .addPanelWidget(new Button(10,40, designType, Component.translatable("simplystatus.config.client"), (s) -> {
                    Minecraft.getInstance().setScreen(new MainConfigs().build(parent));
                }))
                .addPanelWidget(new Button(10,65, designType, Component.translatable("simplystatus.config.localization"), (s) -> {
                    Minecraft.getInstance().setScreen(new LocalizationsConfigs().build(parent));
                }))
                .addPanelWidget(new Button(10,90, designType, Component.translatable("simplystatus.config.server"), (s) -> {
                    Minecraft.getInstance().setScreen(new ServerConfigs().build(parent));
                }).setActive(Minecraft.getInstance().getCurrentServer() != null))
                .addPanelWidget(new Button(10,115, designType, Component.translatable("simplystatus.config.assets"), (s) -> {
                    Minecraft.getInstance().setScreen(new AssetsConfigs().build(parent));
                }))
                .addPanelWidget(new Button(10,140, designType, Component.translatable("simplystatus.config.addons"), (s) -> {
                    Minecraft.getInstance().setScreen(new AddonsConfigs().build(parent));
                }))
                .addPanelWidget(new Button(10,165, designType, Component.translatable("simplystatus.config.mods"), (s) -> {
                    Minecraft.getInstance().setScreen(new ModsConfigs().build(parent));
                }).setActive(SimplyStatus.isMusicModsEnable || SimplyStatus.isVoiceModsEnable || SimplyStatus.replayMod))
                .addWidget(new TextBox(140, 5, Component.literal("Coming soon™"), true))
                .addWidget(new TextBox(140, 30, Component.literal("Coming soon™"), true))
                .addWidget(new TextBox(140, 55, Component.literal("Coming soon™"), true))
                .addWidget(new TextBox(140, 80, Component.literal("Coming soon™"), true))
                .addWidget(new TextBox(140, 105, Component.literal("Coming soon™"), true))
                .addWidget(new TextBox(140, 130, Component.literal("Coming soon™"), true))
                .addWidget(new TextBox(140, 155, Component.literal("Coming soon™"), true))
                .addWidget(new TextBox(140, 180, Component.literal("Coming soon™"), true))
                .build();
    }
}
