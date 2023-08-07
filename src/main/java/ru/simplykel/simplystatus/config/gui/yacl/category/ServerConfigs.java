package ru.simplykel.simplystatus.config.gui.yacl.category;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.impl.controller.BooleanControllerBuilderImpl;
import dev.isxander.yacl3.impl.controller.StringControllerBuilderImpl;
import ru.simplykel.simplystatus.Main;
import ru.simplykel.simplystatus.config.Localization;
import ru.simplykel.simplystatus.config.ServerConfig;
import ru.simplykel.simplystatus.config.UserConfig;

public class ServerConfigs {
    public ConfigCategory getCategory(){
        ConfigCategory.Builder category = ConfigCategory.createBuilder()
                .name(Localization.getText("simplystatus.config.server"));

        category.option(
                Option.createBuilder(boolean.class)
                        .name(Localization.getText("simplystatus.config.server.show_address"))
                        .description(OptionDescription.createBuilder()
                                .text(Localization.getText("simplystatus.config.server.show_address.description")).build())
                        .binding(false,
                                () -> ServerConfig.SHOW_ADDRESS,
                                newVal -> ServerConfig.SHOW_ADDRESS = newVal)
                        .controller(BooleanControllerBuilderImpl::new)
                        .build()
        );
        category.option(
                Option.createBuilder(boolean.class)
                        .name(Localization.getText("simplystatus.config.server.show_name_in_list"))
                        .description(OptionDescription.createBuilder()
                                .text(Localization.getText("simplystatus.config.server.show_name_in_list.description")).build())
                        .binding(true,
                                () -> ServerConfig.SHOW_NAME_IN_LIST,
                                newVal -> ServerConfig.SHOW_NAME_IN_LIST = newVal)
                        .controller(BooleanControllerBuilderImpl::new)
                        .build()
        );
        category.option(
                Option.createBuilder(boolean.class)
                        .name(Localization.getText("simplystatus.config.server.show_custom_name"))
                        .description(OptionDescription.createBuilder()
                                .text(Localization.getText("simplystatus.config.server.show_custom_name.description")).build())
                        .binding(false,
                                () -> ServerConfig.SHOW_CUSTOM_NAME,
                                newVal -> ServerConfig.SHOW_CUSTOM_NAME = newVal)
                        .controller(BooleanControllerBuilderImpl::new)
                        .build()
        );
        category.option(
                Option.createBuilder(String.class)
                        .name(Localization.getText("simplystatus.config.server.custom_name"))
                        .binding("Custom name",
                                () -> ServerConfig.CUSTOM_NAME,
                                newVal -> ServerConfig.CUSTOM_NAME = newVal)
                        .controller(StringControllerBuilderImpl::new)
                        .build()
        );
        category.option(
                Option.createBuilder(boolean.class)
                        .name(Localization.getText("simplystatus.config.server.show_icon"))
                        .description(OptionDescription.createBuilder()
                                .text(Localization.getText("simplystatus.config.server.show_icon.description")).build())
                        .binding(false,
                                () -> ServerConfig.SHOW_ICON,
                                newVal -> ServerConfig.SHOW_ICON = newVal)
                        .controller(BooleanControllerBuilderImpl::new)
                        .build()
        );
        category.option(
                Option.createBuilder(String.class)
                        .name(Localization.getText("simplystatus.config.server.icon_url"))
                        .description(OptionDescription.createBuilder()
                                .text(Localization.getText("simplystatus.config.server.icon_url.description")).build())
                        .binding("Icon URL",
                                () -> ServerConfig.ICON_URL,
                                newVal -> ServerConfig.ICON_URL = newVal)
                        .controller(StringControllerBuilderImpl::new)
                        .build()
        );

        return category.build();
    }
}
