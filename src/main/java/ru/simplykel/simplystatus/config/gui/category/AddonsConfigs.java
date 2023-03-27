package ru.simplykel.simplystatus.config.gui.category;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import ru.simplykel.simplystatus.Main;
import ru.simplykel.simplystatus.config.Localization;
import ru.simplykel.simplystatus.config.UserConfig;

public class AddonsConfigs {
    public ConfigCategory getCategory(ConfigBuilder builder){
        ConfigCategory category = builder.getOrCreateCategory(Localization.getText("simplystatus.config.addons"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        // Voice
        if(Main.svc || Main.plasmo) category.addEntry(entryBuilder.startBooleanToggle(Localization.getText("simplystatus.config.addons.view_voice_speak"), UserConfig.VIEW_VOICE_SPEAK)
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
        //
        category.addEntry(entryBuilder.startBooleanToggle(Localization.getText("simplystatus.config.addons.use_custom_assets"), UserConfig.USE_CUSTOM_ASSETS)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> UserConfig.USE_CUSTOM_ASSETS = newValue)
                .build());
        return category;
    }
}
