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

public class AddonsConfigs {

    public Screen build(Screen parent) {
        ConfigScreenBuilder builder = getBaseBuilder(parent);
        builder.addWidget(new TextBuilder(Component.translatable("sailstatus.config.addons")))
                .addWidget(new CategoryBox(Component.translatable("sailstatus.config.addons.indicators"))
                        .addValue(new ButtonBooleanBuilder(Component.translatable("sailstatus.config.addons.show_items"), true).setConfig(SailStatus.userConfig, "SHOW_ITEMS").build())
                        .addValue(new ButtonBooleanBuilder(Component.translatable("sailstatus.config.addons.enable_time_cycle"), true).setConfig(SailStatus.userConfig, "ENABLE_TIME_CYCLE").build())
                        .addValue(new ButtonBooleanBuilder(Component.translatable("sailstatus.config.addons.enable_world"), true).setConfig(SailStatus.userConfig, "ENABLE_WORLD").build())
                )
                .addWidget(new CategoryBox(Component.translatable("sailstatus.config.addons.custom"))
                        .addValue(new ButtonBooleanBuilder(Component.translatable("sailstatus.config.addons.use_custom_app_id"), false).setConfig(SailStatus.userConfig, "USE_CUSTOM_APP_ID").build())
                        .addValue(new EditBoxBuilder(Component.translatable("sailstatus.config.addons.custom_app_id")).setValue(ModConfig.baseID).setConfig(SailStatus.userConfig, "CUSTOM_APP_ID").build())
                        .addValue(new ButtonBuilder(Component.translatable("sailstatus.config.reconnect"), (s) -> SailStatus.reconnectApp()).build())
                );
        return builder.build();
    }
}
