package ru.kelcuprum.sailstatus.gui;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.gui.components.builder.editbox.EditBoxBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.text.TextBuilder;
import ru.kelcuprum.alinlib.gui.components.text.*;
import ru.kelcuprum.alinlib.gui.screens.ConfigScreenBuilder;
import ru.kelcuprum.sailstatus.SailStatus;

import static ru.kelcuprum.sailstatus.gui.MainConfigs.getBaseBuilder;

public class LocalizationsConfigs {
    public Screen build(Screen parent){
        ConfigScreenBuilder builder = getBaseBuilder(parent);
        builder.addWidget(new TextBuilder(Component.translatable("sailstatus.config.localization")))
                .addWidget(new CategoryBox(Component.translatable("sailstatus.config.localization.title.menu"))
                        .addValue(new EditBoxBuilder(Component.translatable("sailstatus.config.localization.mainmenu")).setLocalization(SailStatus.localization, "mainmenu").build())
                        .addValue(new EditBoxBuilder(Component.translatable("sailstatus.config.localization.mainmenu.icon")).setLocalization(SailStatus.localization, "mainmenu.icon").build())
                        .addValue(new EditBoxBuilder(Component.translatable("sailstatus.config.localization.mainmenu.state")).setLocalization(SailStatus.localization, "mainmenu.state").build())
                        .changeState(false)
                )
                .addWidget(new CategoryBox(Component.translatable("sailstatus.config.localization.title.singleplayer"))
                        .addValue(new EditBoxBuilder(Component.translatable("sailstatus.config.localization.singleplayer")).setLocalization(SailStatus.localization, "singleplayer").build())
                        .addValue(new EditBoxBuilder(Component.translatable("sailstatus.config.localization.world.loading")).setLocalization(SailStatus.localization, "world.loading").build())
                        .changeState(false)
                )
                .addWidget(new CategoryBox(Component.translatable("sailstatus.config.localization.title.multiplayer"))
                        .addValue(new EditBoxBuilder(Component.translatable("sailstatus.config.localization.address.hidden")).setLocalization(SailStatus.localization, "address.hidden").build())
                        .addValue(new EditBoxBuilder(Component.translatable("sailstatus.config.localization.server.connecting")).setLocalization(SailStatus.localization, "server.connecting").build())
                        .addValue(new EditBoxBuilder(Component.translatable("sailstatus.config.localization.server.disconnected")).setLocalization(SailStatus.localization, "server.disconnected").build())
                        .changeState(false)
                )
                .addWidget(new CategoryBox(Component.translatable("sailstatus.config.localization.title.player"))
                        .addValue(new EditBoxBuilder(Component.translatable("sailstatus.config.localization.item")).setLocalization(SailStatus.localization, "item").build())
                        .addValue(new EditBoxBuilder(Component.translatable("sailstatus.config.localization.item.count")).setLocalization(SailStatus.localization, "item.count").build())
                        .addValue(new EditBoxBuilder(Component.translatable("sailstatus.config.localization.player.sleep")).setLocalization(SailStatus.localization, "player.sleep").build())
                        .addValue(new EditBoxBuilder(Component.translatable("sailstatus.config.localization.player.sneak")).setLocalization(SailStatus.localization, "player.sneak").build())
                        .addValue(new EditBoxBuilder(Component.translatable("sailstatus.config.localization.player.on.fire")).setLocalization(SailStatus.localization, "player.on.fire").build())
                        .addValue(new EditBoxBuilder(Component.translatable("sailstatus.config.localization.player.on.water")).setLocalization(SailStatus.localization, "player.on.water").build())
                        .addValue(new EditBoxBuilder(Component.translatable("sailstatus.config.localization.player.statistics")).setLocalization(SailStatus.localization, "player.statistics").build())
                        .addValue(new EditBoxBuilder(Component.translatable("sailstatus.config.localization.player.world.state")).setLocalization(SailStatus.localization, "player.world.state").build())
                        .changeState(false)
                )
                .addWidget(new CategoryBox(Component.translatable("sailstatus.config.localization.title.unknown"))
                        .addValue(new EditBoxBuilder(Component.translatable("sailstatus.config.localization.unknown.server")).setLocalization(SailStatus.localization, "unknown.server").build())
                        .addValue(new EditBoxBuilder(Component.translatable("sailstatus.config.localization.unknown.world")).setLocalization(SailStatus.localization, "unknown.world").build())
                        .addValue(new EditBoxBuilder(Component.translatable("sailstatus.config.localization.unknown")).setLocalization(SailStatus.localization, "unknown").build())
                        .changeState(false)
                );
        return builder.build();
    }
}
