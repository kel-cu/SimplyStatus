package ru.simplykel.simplystatus.config.gui.cloth.category;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import ru.simplykel.simplystatus.config.Localization;
import ru.simplykel.simplystatus.config.ModConfig;
import ru.simplykel.simplystatus.config.UserConfig;

import java.util.Arrays;

public class MainConfigs {
    public ConfigCategory getCategory(ConfigBuilder builder){
        ConfigCategory category = builder.getOrCreateCategory(Localization.getText("simplystatus.config.client"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        // YACL
        category.addEntry(entryBuilder.startBooleanToggle(Localization.getText("simplystatus.config.client.use_yacl"), UserConfig.USE_YACL_CONFIGURATION)
                .setTooltip(Localization.getText("simplystatus.config.client.use_yacl.description"))
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> UserConfig.USE_YACL_CONFIGURATION = newValue)
                .build());
        // ENABLE RPC
        category.addEntry(entryBuilder.startBooleanToggle(Localization.getText("simplystatus.config.client.enable_rpc"), UserConfig.ENABLE_RPC)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> UserConfig.ENABLE_RPC = newValue)
                .build());
        //
        if(!UserConfig.USE_CUSTOM_APP_ID) category.addEntry(entryBuilder.startBooleanToggle(Localization.getText("simplystatus.config.client.use_minecraft_id"), UserConfig.USE_ANOTHER_ID)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> UserConfig.USE_ANOTHER_ID = newValue)
                .build());
        // USE ASSETS
        if(!UserConfig.USE_CUSTOM_ASSETS && !UserConfig.USE_CUSTOM_APP_ID) category.addEntry(entryBuilder.startSelector(Localization.getText("simplystatus.config.client.assets"), ModConfig.assetsList, UserConfig.USE_ASSETS)
                .setDefaultValue(ModConfig.assetsList[0])
                .setSaveConsumer(newVolume -> UserConfig.USE_ASSETS = newVolume)
                .build());
        String[] apiNames = ru.simplykel.simplystatus.config.gui.yacl.category.MainConfigs.apiNames;
        category.addEntry(entryBuilder.startSelector(Localization.getText("simplystatus.config.client.api"), apiNames, apiNames[UserConfig.USE_API_RENDER])
                .setDefaultValue(apiNames[0])
                .setSaveConsumer(newVolume -> UserConfig.USE_API_RENDER = Arrays.asList(apiNames).indexOf(newVolume))
                .build());
        // SHOW GAME STARTED
        category.addEntry(entryBuilder.startBooleanToggle(Localization.getText("simplystatus.config.client.show_game_started"), UserConfig.SHOW_GAME_STARTED)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> UserConfig.SHOW_GAME_STARTED = newValue)
                .build());
        // SHOW AVATAR PLAYER
        category.addEntry(entryBuilder.startBooleanToggle(Localization.getText("simplystatus.config.client.show_avatar_player"), UserConfig.SHOW_AVATAR_PLAYER)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> UserConfig.SHOW_AVATAR_PLAYER = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startBooleanToggle(Localization.getText("simplystatus.config.client.view_item_off_hand"), UserConfig.VIEW_ITEM_OFF_HAND)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> UserConfig.VIEW_ITEM_OFF_HAND = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startBooleanToggle(Localization.getText("simplystatus.config.client.view_statistics"), UserConfig.VIEW_STATISTICS)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> UserConfig.VIEW_STATISTICS = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startBooleanToggle(Localization.getText("simplystatus.config.client.view_player_name"), UserConfig.VIEW_PLAYER_NAME)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> UserConfig.VIEW_PLAYER_NAME = newValue)
                .build());

        return category;
    }
}
