package ru.simplykel.simplystatus.config.gui.category;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import ru.simplykel.simplystatus.config.ModConfig;
import ru.simplykel.simplystatus.config.UserConfig;

public class MainConfigs {
    public ConfigCategory getCategory(ConfigBuilder builder){
        ConfigCategory category = builder.getOrCreateCategory(Text.translatable("simplystatus.config.client"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        // ENABLE RPC
        category.addEntry(entryBuilder.startBooleanToggle(Text.translatable("simplystatus.config.client.enable_rpc"), UserConfig.ENABLE_RPC)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> UserConfig.ENABLE_RPC = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startBooleanToggle(Text.translatable("simplystatus.config.client.use_minecraft_id"), UserConfig.USE_ANOTHER_ID)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> UserConfig.USE_ANOTHER_ID = newValue)
                .build());
        // ENABLE BEDROCK ASSETS
        if(ModConfig.bedrockAssets != null){
            category.addEntry(entryBuilder.startBooleanToggle(Text.translatable("simplystatus.config.client.enable_bedrock_assets"), UserConfig.ENABLE_BEDROCK_ASSETS)
                    .setDefaultValue(false)
                    .setSaveConsumer(newValue -> UserConfig.ENABLE_BEDROCK_ASSETS = newValue)
                    .build());
        }
        // SHOW GAME STARTED
        category.addEntry(entryBuilder.startBooleanToggle(Text.translatable("simplystatus.config.client.show_game_started"), UserConfig.SHOW_GAME_STARTED)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> UserConfig.SHOW_GAME_STARTED = newValue)
                .build());
        // SHOW AVATAR PLAYER
        category.addEntry(entryBuilder.startBooleanToggle(Text.translatable("simplystatus.config.client.show_avatar_player"), UserConfig.SHOW_AVATAR_PLAYER)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> UserConfig.SHOW_AVATAR_PLAYER = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startBooleanToggle(Text.translatable("simplystatus.config.client.view_item_off_hand"), UserConfig.VIEW_ITEM_OFF_HAND)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> UserConfig.VIEW_ITEM_OFF_HAND = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startBooleanToggle(Text.translatable("simplystatus.config.client.view_statistics"), UserConfig.VIEW_STATISTICS)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> UserConfig.VIEW_STATISTICS = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startBooleanToggle(Text.translatable("simplystatus.config.client.view_player_name"), UserConfig.VIEW_PLAYER_NAME)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> UserConfig.VIEW_PLAYER_NAME = newValue)
                .build());

        return category;
    }
}
