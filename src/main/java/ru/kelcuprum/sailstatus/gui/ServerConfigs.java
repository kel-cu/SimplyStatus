package ru.kelcuprum.sailstatus.gui;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.gui.components.builder.button.*;
import ru.kelcuprum.alinlib.gui.components.builder.editbox.EditBoxBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.text.TextBuilder;
import ru.kelcuprum.alinlib.gui.screens.ConfigScreenBuilder;
import ru.kelcuprum.sailstatus.SailStatus;

import static ru.kelcuprum.sailstatus.gui.MainConfigs.getBaseBuilder;

public class ServerConfigs {
    public Screen build(Screen parent){
        ConfigScreenBuilder builder = getBaseBuilder(parent);
        builder.addWidget(new TextBuilder(Component.translatable("sailstatus.config.server")))
                .addWidget(new ButtonBooleanBuilder(Component.translatable("sailstatus.config.server.show_icon"), false).setConfig(SailStatus.userConfig, "SHOW_ICON").build())
                .addWidget(new EditBoxBuilder(Component.translatable("sailstatus.config.server.icon_url")).setValue("https://api.mcstatus.io/v2/icon/%address%").setConfig(SailStatus.serverConfig, "CUSTOM_NAME").build());
        return builder.build();
    }
}
