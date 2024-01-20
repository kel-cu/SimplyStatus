package ru.kelcuprum.simplystatus.gui.config;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.buttons.base.Button;
import ru.kelcuprum.alinlib.gui.components.editbox.EditBoxLocalization;
import ru.kelcuprum.alinlib.gui.components.text.CategoryBox;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.screens.ConfigScreenBuilder;
import ru.kelcuprum.simplystatus.SimplyStatus;

public class LocalizationsConfigs {
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
                .addWidget(new TextBox(140, 5, Component.translatable("simplystatus.config.localization"), true))
                .addWidget(new CategoryBox(140, 30, Component.translatable("simplystatus.config.localization.title.menu"))
                        .addValue(new EditBoxLocalization(140, 55, designType, SimplyStatus.localization, "mainmenu", Component.translatable("simplystatus.config.localization.mainmenu")))
                        .addValue(new EditBoxLocalization(140, 80, designType, SimplyStatus.localization, "mainmenu.state", Component.translatable("simplystatus.config.localization.mainmenu.state")))
                )
                .addWidget(new CategoryBox(140, 105, Component.translatable("simplystatus.config.localization.title.singleplayer"))
                        .addValue(new EditBoxLocalization(140, 130, designType, SimplyStatus.localization, "singleplayer", Component.translatable("simplystatus.config.localization.singleplayer")))
                        .addValue(new EditBoxLocalization(140, 155, designType, SimplyStatus.localization, "world.loading", Component.translatable("simplystatus.config.localization.world.loading")))
                )
                .addWidget(new CategoryBox(140, 180, Component.translatable("simplystatus.config.localization.title.multiplayer"))
                        .addValue(new EditBoxLocalization(140, 205, designType, SimplyStatus.localization, "address.hidden", Component.translatable("simplystatus.config.localization.address.hidden")))
                        .addValue(new EditBoxLocalization(140, 230, designType, SimplyStatus.localization, "server.connecting", Component.translatable("simplystatus.config.localization.server.connecting")))
                        .addValue(new EditBoxLocalization(140, 255, designType, SimplyStatus.localization, "server.disconnected", Component.translatable("simplystatus.config.localization.server.disconnected")))
                )
                .addWidget(new CategoryBox(140, 280, Component.translatable("simplystatus.config.localization.title.player"))
                        .addValue(new EditBoxLocalization(140, 305, designType, SimplyStatus.localization, "item", Component.translatable("simplystatus.config.localization.item")))
                        .addValue(new EditBoxLocalization(140, 330, designType, SimplyStatus.localization, "item.count", Component.translatable("simplystatus.config.localization.item.count")))
                        .addValue(new EditBoxLocalization(140, 355, designType, SimplyStatus.localization, "player.sleep", Component.translatable("simplystatus.config.localization.player.sleep")))
                        .addValue(new EditBoxLocalization(140, 380, designType, SimplyStatus.localization, "player.sneak", Component.translatable("simplystatus.config.localization.player.sneak")))
                        .addValue(new EditBoxLocalization(140, 405, designType, SimplyStatus.localization, "player.on.fire", Component.translatable("simplystatus.config.localization.player.on.fire")))
                        .addValue(new EditBoxLocalization(140, 430, designType, SimplyStatus.localization, "player.on.water", Component.translatable("simplystatus.config.localization.player.on.water")))
                        .addValue(new EditBoxLocalization(140, 455, designType, SimplyStatus.localization, "player.statistics", Component.translatable("simplystatus.config.localization.player.statistics")))
                        .addValue(new EditBoxLocalization(140, 480, designType, SimplyStatus.localization, "player.world.state", Component.translatable("simplystatus.config.localization.player.world.state")))
                )
                .addWidget(new CategoryBox(140, 505, Component.translatable("simplystatus.config.localization.title.death"))
                        .addValue(new EditBoxLocalization(140, 530, designType, SimplyStatus.localization, "death.one", Component.translatable("simplystatus.config.localization.death.one")))
                        .addValue(new EditBoxLocalization(140, 555, designType, SimplyStatus.localization, "death.two", Component.translatable("simplystatus.config.localization.death.two")))
                        .addValue(new EditBoxLocalization(140, 580, designType, SimplyStatus.localization, "death.three", Component.translatable("simplystatus.config.localization.death.three")))
                )
                .addWidget(new CategoryBox(140, 605, Component.translatable("simplystatus.config.localization.title.worlds"))
                        .addValue(new EditBoxLocalization(140, 630, designType, SimplyStatus.localization, "world.overworld", Component.translatable("simplystatus.config.localization.world.overworld")))
                        .addValue(new EditBoxLocalization(140, 655, designType, SimplyStatus.localization, "world.nether", Component.translatable("simplystatus.config.localization.world.nether")))
                        .addValue(new EditBoxLocalization(140, 680, designType, SimplyStatus.localization, "world.the_end", Component.translatable("simplystatus.config.localization.world.the_end")))
                        .addValue(new EditBoxLocalization(140, 705, designType, SimplyStatus.localization, "world.moon", Component.translatable("simplystatus.config.localization.world.moon")))
                )
                .addWidget(new CategoryBox(140, 730, Component.translatable("simplystatus.config.localization.title.time"))
                        .addValue(new EditBoxLocalization(140, 755, designType, SimplyStatus.localization, "time.day", Component.translatable("simplystatus.config.localization.time.day")))
                        .addValue(new EditBoxLocalization(140, 780, designType, SimplyStatus.localization, "time.night", Component.translatable("simplystatus.config.localization.time.night")))
                        .addValue(new EditBoxLocalization(140, 805, designType, SimplyStatus.localization, "time.morning", Component.translatable("simplystatus.config.localization.time.morning")))
                        .addValue(new EditBoxLocalization(140, 830, designType, SimplyStatus.localization, "time.evening", Component.translatable("simplystatus.config.localization.time.evening")))
                )
                .addWidget(new CategoryBox(140, 855, Component.translatable("simplystatus.config.localization.title.unknown"))
                        .addValue(new EditBoxLocalization(140, 880, designType, SimplyStatus.localization, "unknown.server", Component.translatable("simplystatus.config.localization.unknown.server")))
                        .addValue(new EditBoxLocalization(140, 905, designType, SimplyStatus.localization, "unknown.world", Component.translatable("simplystatus.config.localization.unknown.world")))
                        .addValue(new EditBoxLocalization(140, 930, designType, SimplyStatus.localization, "unknown", Component.translatable("simplystatus.config.localization.unknown")))
                )
                .build();
    }
}
