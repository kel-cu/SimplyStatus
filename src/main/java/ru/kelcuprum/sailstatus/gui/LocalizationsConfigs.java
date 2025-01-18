package ru.kelcuprum.sailstatus.gui;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.editbox.EditBoxBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.text.TextBuilder;
import ru.kelcuprum.alinlib.gui.components.text.*;
import ru.kelcuprum.alinlib.gui.screens.ConfigScreenBuilder;
import ru.kelcuprum.sailstatus.SailStatus;

import static ru.kelcuprum.alinlib.AlinLib.MINECRAFT;

public class LocalizationsConfigs {
    public Screen build(Screen parent){
        ConfigScreenBuilder builder = new ConfigScreenBuilder(parent, Component.translatable("simplystatus.name"))
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.client"), (s) -> MINECRAFT.setScreen(new MainConfigs().build(parent))).build());
        if(MINECRAFT.getCurrentServer() != null) builder.addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.server"), (s) -> MINECRAFT.setScreen(new ServerConfigs().build(parent))).build());
        builder.addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.localization"), (s) -> MINECRAFT.setScreen(new LocalizationsConfigs().build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.assets"), (s) -> MINECRAFT.setScreen(new AssetsConfigs().build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.addons"), (s) -> MINECRAFT.setScreen(new AddonsConfigs().build(parent))).build());
        if(SailStatus.isMusicModsEnable || SailStatus.isVoiceModsEnable || SailStatus.replayMod  || SailStatus.klashback)
            builder.addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.mods"), (s) -> MINECRAFT.setScreen(new ModsConfigs().build(parent))).build());
        builder.addWidget(new TextBuilder(Component.translatable("simplystatus.config.localization")))
                .addWidget(new CategoryBox(Component.translatable("simplystatus.config.localization.title.menu"))
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.mainmenu")).setLocalization(SailStatus.localization, "mainmenu").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.mainmenu.icon")).setLocalization(SailStatus.localization, "mainmenu.icon").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.mainmenu.state")).setLocalization(SailStatus.localization, "mainmenu.state").build())
                )
                .addWidget(new CategoryBox(Component.translatable("simplystatus.config.localization.title.singleplayer"))
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.singleplayer")).setLocalization(SailStatus.localization, "singleplayer").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.world.loading")).setLocalization(SailStatus.localization, "world.loading").build())
                )
                .addWidget(new CategoryBox(Component.translatable("simplystatus.config.localization.title.multiplayer"))
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.address.hidden")).setLocalization(SailStatus.localization, "address.hidden").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.server.connecting")).setLocalization(SailStatus.localization, "server.connecting").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.server.disconnected")).setLocalization(SailStatus.localization, "server.disconnected").build())
                )
                .addWidget(new CategoryBox(Component.translatable("simplystatus.config.localization.title.player"))
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.item")).setLocalization(SailStatus.localization, "item").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.item.count")).setLocalization(SailStatus.localization, "item.count").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.player.sleep")).setLocalization(SailStatus.localization, "player.sleep").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.player.sneak")).setLocalization(SailStatus.localization, "player.sneak").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.player.on.fire")).setLocalization(SailStatus.localization, "player.on.fire").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.player.on.water")).setLocalization(SailStatus.localization, "player.on.water").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.player.statistics")).setLocalization(SailStatus.localization, "player.statistics").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.player.world.state")).setLocalization(SailStatus.localization, "player.world.state").build())
                )
                .addWidget(new CategoryBox(Component.translatable("simplystatus.config.localization.title.death"))
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.death.one")).setLocalization(SailStatus.localization, "death.one").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.death.two")).setLocalization(SailStatus.localization, "death.two").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.death.three")).setLocalization(SailStatus.localization, "death.three").build())
                )
                .addWidget(new CategoryBox(Component.translatable("simplystatus.config.localization.title.unknown"))
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.unknown.server")).setLocalization(SailStatus.localization, "unknown.server").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.unknown.world")).setLocalization(SailStatus.localization, "unknown.world").build())
                        .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.localization.unknown")).setLocalization(SailStatus.localization, "unknown").build())
                );
        return builder.build();
    }
}
