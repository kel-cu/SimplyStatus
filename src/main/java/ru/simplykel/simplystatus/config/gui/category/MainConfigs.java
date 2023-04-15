package ru.simplykel.simplystatus.config.gui.category;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import ru.simplykel.simplystatus.Client;
import ru.simplykel.simplystatus.Main;
import ru.simplykel.simplystatus.config.Localization;
import ru.simplykel.simplystatus.config.ModConfig;
import ru.simplykel.simplystatus.config.UserConfig;

import java.util.List;

import static com.ibm.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class MainConfigs {
    public ConfigCategory getCategory(ConfigBuilder builder){
        ConfigCategory category = builder.getOrCreateCategory(Localization.getText("simplystatus.config.client"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        // ENABLE RPC
        category.addEntry(entryBuilder.startBooleanToggle(Localization.getText("simplystatus.config.client.enable_rpc"), UserConfig.ENABLE_RPC)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> UserConfig.ENABLE_RPC = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startBooleanToggle(Localization.getText("simplystatus.config.client.use_minecraft_id"), UserConfig.USE_ANOTHER_ID)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> UserConfig.USE_ANOTHER_ID = newValue)
                .build());
        // ENABLE BEDROCK ASSETS
        category.addEntry(entryBuilder.startSelector(Localization.getText("simplystatus.config.client.assets"), ModConfig.assetsList, UserConfig.USE_ASSETS)
                .setDefaultValue(ModConfig.assetsList[0])
                .setSaveConsumer(newVolume -> UserConfig.USE_ASSETS = newVolume)
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
        // 1.7.1
        //
        category.addEntry(entryBuilder.startBooleanToggle(Localization.getText("simplystatus.config.client.show_items"), UserConfig.SHOW_ITEMS)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> UserConfig.SHOW_ITEMS = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startBooleanToggle(Localization.getText("simplystatus.config.client.enable_time_cycle"), UserConfig.ENABLE_TIME_CYCLE)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> UserConfig.ENABLE_TIME_CYCLE = newValue)
                .build());
        category.addEntry(entryBuilder.startBooleanToggle(Localization.getText("simplystatus.config.client.enable_world"), UserConfig.ENABLE_WORLD)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> UserConfig.ENABLE_WORLD = newValue)
                .build());

        return category;
    }
}
