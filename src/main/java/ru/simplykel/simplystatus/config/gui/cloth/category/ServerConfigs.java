package ru.simplykel.simplystatus.config.gui.cloth.category;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import ru.simplykel.simplystatus.config.Localization;
import ru.simplykel.simplystatus.config.ServerConfig;

public class ServerConfigs {
    public ConfigCategory getCategory(ConfigBuilder builder){
        ConfigCategory category = builder.getOrCreateCategory(Localization.getText("simplystatus.config.server"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        //
        category.addEntry(entryBuilder.startBooleanToggle(Localization.getText("simplystatus.config.server.show_address"), ServerConfig.SHOW_ADDRESS)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> ServerConfig.SHOW_ADDRESS = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startBooleanToggle(Localization.getText("simplystatus.config.server.show_name_in_list"), ServerConfig.SHOW_NAME_IN_LIST)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> ServerConfig.SHOW_NAME_IN_LIST = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startBooleanToggle(Localization.getText("simplystatus.config.server.show_custom_name"), ServerConfig.SHOW_CUSTOM_NAME)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> ServerConfig.SHOW_CUSTOM_NAME = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startStrField(Localization.getText("simplystatus.config.server.custom_name"), ServerConfig.CUSTOM_NAME)
                .setDefaultValue("Custom name")
                .setSaveConsumer(newValue -> ServerConfig.CUSTOM_NAME = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startBooleanToggle(Localization.getText("simplystatus.config.server.show_icon"), ServerConfig.SHOW_ICON)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> ServerConfig.SHOW_ICON = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startStrField(Localization.getText("simplystatus.config.server.icon_url"), ServerConfig.ICON_URL)
                .setDefaultValue("Icon URL")
                .setSaveConsumer(newValue -> ServerConfig.ICON_URL = newValue)
                .build());
        return category;
    }
}
