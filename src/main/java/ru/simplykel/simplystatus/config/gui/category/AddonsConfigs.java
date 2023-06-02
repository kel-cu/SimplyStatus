package ru.simplykel.simplystatus.config.gui.category;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import ru.simplykel.simplystatus.Main;
import ru.simplykel.simplystatus.config.Localization;
import ru.simplykel.simplystatus.config.ModConfig;
import ru.simplykel.simplystatus.config.UserConfig;

public class AddonsConfigs {
    public ConfigCategory getCategory(ConfigBuilder builder){
        ConfigCategory category = builder.getOrCreateCategory(Localization.getText("simplystatus.config.addons"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        if(Main.isVoiceModsEnable || Main.replayMod || Main.musicPlayer) category.addEntry(entryBuilder.startTextDescription(Localization.getText("simplystatus.config.addons.modifications")).build());
        // Voice
        if(Main.isVoiceModsEnable) category.addEntry(entryBuilder.startBooleanToggle(Localization.getText("simplystatus.config.addons.view_voice_speak"), UserConfig.VIEW_VOICE_SPEAK)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> UserConfig.VIEW_VOICE_SPEAK = newValue)
                .build());
        //
        if(Main.replayMod) category.addEntry(entryBuilder.startBooleanToggle(Localization.getText("simplystatus.config.addons.view_replay_mod"), UserConfig.VIEW_REPLAY_MOD)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> UserConfig.VIEW_REPLAY_MOD = newValue)
                .build());
        //
        if(Main.musicPlayer) category.addEntry(entryBuilder.startBooleanToggle(Localization.getText("simplystatus.config.addons.view_music_listener"), UserConfig.VIEW_MUSIC_LISTENER)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> UserConfig.VIEW_MUSIC_LISTENER = newValue)
                .build());
        category.addEntry(entryBuilder.startTextDescription(Localization.getText("simplystatus.config.addons.indicators")).build());
        //
        category.addEntry(entryBuilder.startBooleanToggle(Localization.getText("simplystatus.config.addons.show_items"), UserConfig.SHOW_ITEMS)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> UserConfig.SHOW_ITEMS = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startBooleanToggle(Localization.getText("simplystatus.config.addons.enable_time_cycle"), UserConfig.ENABLE_TIME_CYCLE)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> UserConfig.ENABLE_TIME_CYCLE = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startBooleanToggle(Localization.getText("simplystatus.config.addons.enable_world"), UserConfig.ENABLE_WORLD)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> UserConfig.ENABLE_WORLD = newValue)
                .build());
        category.addEntry(entryBuilder.startTextDescription(Localization.getText("simplystatus.config.addons.custom")).build());
        //
        category.addEntry(entryBuilder.startBooleanToggle(Localization.getText("simplystatus.config.addons.use_custom_assets"), UserConfig.USE_CUSTOM_ASSETS)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> UserConfig.USE_CUSTOM_ASSETS = newValue)
                .build());

        //
        category.addEntry(entryBuilder.startBooleanToggle(Localization.getText("simplystatus.config.client.use_custom_app_id"), UserConfig.USE_CUSTOM_APP_ID)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> UserConfig.USE_CUSTOM_APP_ID = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startStrField(Localization.getText("simplystatus.config.addons.custom_app_id"), UserConfig.CUSTOM_APP_ID)
                .setDefaultValue(ModConfig.baseID)
                .setSaveConsumer(newValue -> UserConfig.CUSTOM_APP_ID = newValue)
                .build());
        return category;
    }
}
