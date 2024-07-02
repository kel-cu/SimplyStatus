package ru.kelcuprum.simplystatus.gui;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.editbox.EditBoxBuilder;
import ru.kelcuprum.alinlib.gui.components.text.CategoryBox;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.screens.ConfigScreenBuilder;
import ru.kelcuprum.simplystatus.SimplyStatus;

import static ru.kelcuprum.simplystatus.SimplyStatus.MINECRAFT;

public class LocalizationsConfigs {
    public Screen build(Screen parent){
        ConfigScreenBuilder builder = new ConfigScreenBuilder(parent, Component.translatable("simplystatus.name"))
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.client"), (s) -> MINECRAFT.setScreen(new MainConfigs().build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.localization"), (s) -> MINECRAFT.setScreen(new LocalizationsConfigs().build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.server"), (s) -> MINECRAFT.setScreen(new ServerConfigs().build(parent))).build().setActive(MINECRAFT.getCurrentServer() != null))
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.assets"), (s) -> MINECRAFT.setScreen(new AssetsConfigs().build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.addons"), (s) -> MINECRAFT.setScreen(new AddonsConfigs().build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.mods"), (s) -> MINECRAFT.setScreen(new ModsConfigs().build(parent))).build().setActive(SimplyStatus.isMusicModsEnable || SimplyStatus.isVoiceModsEnable || SimplyStatus.replayMod));

        if(AlinLib.bariumConfig.getBoolean("FRIEND", true)) builder.addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.support"), (s) -> MINECRAFT.setScreen(new ThanksScreen().build(parent))).build());

        builder.addWidget(new TextBox(Component.translatable("simplystatus.config.localization"), true))
                .addWidget(new CategoryBox(Component.translatable("simplystatus.config.localization.title.menu"))
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.mainmenu")).setLocalization(SimplyStatus.localization, "mainmenu").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.mainmenu.icon")).setLocalization(SimplyStatus.localization, "mainmenu.icon").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.mainmenu.state")).setLocalization(SimplyStatus.localization, "mainmenu.state").build())
                )
                .addWidget(new CategoryBox(Component.translatable("simplystatus.config.localization.title.singleplayer"))
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.singleplayer")).setLocalization(SimplyStatus.localization, "singleplayer").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.world.loading")).setLocalization(SimplyStatus.localization, "world.loading").build())
                )
                .addWidget(new CategoryBox(Component.translatable("simplystatus.config.localization.title.multiplayer"))
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.address.hidden")).setLocalization(SimplyStatus.localization, "address.hidden").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.server.connecting")).setLocalization(SimplyStatus.localization, "server.connecting").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.server.disconnected")).setLocalization(SimplyStatus.localization, "server.disconnected").build())
                )
                .addWidget(new CategoryBox(Component.translatable("simplystatus.config.localization.title.player"))
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.item")).setLocalization(SimplyStatus.localization, "item").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.item.count")).setLocalization(SimplyStatus.localization, "item.count").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.player.sleep")).setLocalization(SimplyStatus.localization, "player.sleep").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.player.sneak")).setLocalization(SimplyStatus.localization, "player.sneak").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.player.on.fire")).setLocalization(SimplyStatus.localization, "player.on.fire").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.player.on.water")).setLocalization(SimplyStatus.localization, "player.on.water").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.player.statistics")).setLocalization(SimplyStatus.localization, "player.statistics").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.player.world.state")).setLocalization(SimplyStatus.localization, "player.world.state").build())
                )
                .addWidget(new CategoryBox(Component.translatable("simplystatus.config.localization.title.death"))
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.death.one")).setLocalization(SimplyStatus.localization, "death.one").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.death.two")).setLocalization(SimplyStatus.localization, "death.two").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.death.three")).setLocalization(SimplyStatus.localization, "death.three").build())
                )
                .addWidget(new CategoryBox(Component.translatable("simplystatus.config.localization.title.worlds"))
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.world.overworld")).setLocalization(SimplyStatus.localization, "world.overworld").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.world.nether")).setLocalization(SimplyStatus.localization, "world.nether").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.world.the_end")).setLocalization(SimplyStatus.localization, "world.the_end").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.world.moon")).setLocalization(SimplyStatus.localization, "world.moon").build())
                )
                .addWidget(new CategoryBox(Component.translatable("simplystatus.config.localization.title.time"))
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.time.day")).setLocalization(SimplyStatus.localization, "time.day").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.time.night")).setLocalization(SimplyStatus.localization, "time.night").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.time.morning")).setLocalization(SimplyStatus.localization, "time.morning").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.time.evening")).setLocalization(SimplyStatus.localization, "time.evening").build())
                )
                .addWidget(new CategoryBox(Component.translatable("simplystatus.config.localization.title.unknown"))
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.unknown.server")).setLocalization(SimplyStatus.localization, "unknown.server").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.unknown.world")).setLocalization(SimplyStatus.localization, "unknown.world").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.unknown")).setLocalization(SimplyStatus.localization, "unknown").build())
                );
        return builder.build();
    }
}
