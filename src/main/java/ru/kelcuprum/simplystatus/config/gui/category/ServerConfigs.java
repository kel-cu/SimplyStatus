package ru.kelcuprum.simplystatus.config.gui.category;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.impl.controller.BooleanControllerBuilderImpl;
import dev.isxander.yacl3.impl.controller.StringControllerBuilderImpl;
import net.minecraft.client.Minecraft;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.config.Config;
import ru.kelcuprum.simplystatus.localization.Localization;

public class ServerConfigs {
    public ConfigCategory getCategory(){
        SimplyStatus.serverConfig = new Config(String.format("config/SimplyStatus/servers/%s.json", Minecraft.getInstance().getCurrentServer().ip.replace(":", "_")));
        SimplyStatus.serverConfig.load();
        ConfigCategory.Builder category = ConfigCategory.createBuilder()
                .name(Localization.getText("simplystatus.config.server"));

        category.option(
                Option.createBuilder(boolean.class)
                        .name(Localization.getText("simplystatus.config.server.show_custom_name"))
                        .description(OptionDescription.createBuilder()
                                .text(Localization.getText("simplystatus.config.server.show_custom_name.description")).build())
                        .binding(false,
                                () -> SimplyStatus.serverConfig.getBoolean("USE_CUSTOM_NAME",false),
                                newVal -> SimplyStatus.serverConfig.setBoolean("USE_CUSTOM_NAME", newVal))
                        .controller(BooleanControllerBuilderImpl::new)
                        .build()
        );
        category.option(
                Option.createBuilder(String.class)
                        .name(Localization.getText("simplystatus.config.server.custom_name"))
                        .binding("Custom name",
                                () -> SimplyStatus.serverConfig.getString("CUSTOM_NAME", ""),
                                newVal -> SimplyStatus.serverConfig.setString("CUSTOM_NAME", newVal))
                        .controller(StringControllerBuilderImpl::new)
                        .build()
        );
        category.option(
                Option.createBuilder(boolean.class)
                        .name(Localization.getText("simplystatus.config.server.show_name"))
                        .description(OptionDescription.createBuilder()
                                .text(Localization.getText("simplystatus.config.server.show_name.description")).build())
                        .binding(false,
                                () -> SimplyStatus.serverConfig.getBoolean("SHOW_NAME",true),
                                newVal -> SimplyStatus.serverConfig.setBoolean("SHOW_NAME", newVal))
                        .controller(BooleanControllerBuilderImpl::new)
                        .build()
        );

        category.option(
                Option.createBuilder(boolean.class)
                        .name(Localization.getText("simplystatus.config.server.show_address"))
                        .description(OptionDescription.createBuilder()
                                .text(Localization.getText("simplystatus.config.server.show_address.description")).build())
                        .binding(false,
                                () -> SimplyStatus.serverConfig.getBoolean("SHOW_ADDRESS",false),
                                newVal -> SimplyStatus.serverConfig.setBoolean("SHOW_ADDRESS", newVal))
                        .controller(BooleanControllerBuilderImpl::new)
                        .build()
        );
        category.option(
                Option.createBuilder(boolean.class)
                        .name(Localization.getText("simplystatus.config.server.show_icon"))
                        .description(OptionDescription.createBuilder()
                                .text(Localization.getText("simplystatus.config.server.show_icon.description")).build())
                        .binding(false,
                                () -> SimplyStatus.serverConfig.getBoolean("SHOW_ICON", false),
                                newVal -> SimplyStatus.serverConfig.setBoolean("SHOW_ICON", newVal))
                        .controller(BooleanControllerBuilderImpl::new)
                        .build()
        );
        category.option(
                Option.createBuilder(String.class)
                        .name(Localization.getText("simplystatus.config.server.icon_url"))
                        .description(OptionDescription.createBuilder()
                                .text(Localization.getText("simplystatus.config.server.icon_url.description")).build())
                        .binding("https://api.mcstatus.io/v2/icon/%address%",
                                () -> SimplyStatus.serverConfig.getString("ICON_URL", ""),
                                newVal -> SimplyStatus.serverConfig.setString("ICON_URL", newVal))
                        .controller(StringControllerBuilderImpl::new)
                        .build()
        );

        return category.build();
    }
}
