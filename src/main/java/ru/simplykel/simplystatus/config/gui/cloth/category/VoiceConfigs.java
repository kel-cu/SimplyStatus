package ru.simplykel.simplystatus.config.gui.cloth.category;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import ru.simplykel.simplystatus.config.Localization;

public class VoiceConfigs {
    public ConfigCategory getCategory(ConfigBuilder builder){
        ConfigCategory category = builder.getOrCreateCategory(Localization.getText("simplystatus.config.voice"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.localization.mod.voice"),
                        Localization.getLocalization("mod.voice", false))
                .setDefaultValue(Localization.getLcnDefault("mod.voice"))
                .setSaveConsumer(newValue -> Localization.setLocalization("mod.voice", newValue))
                .build());
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.localization.mod.voice.players.one"),
                        Localization.getLocalization("mod.voice.players.one", false))
                .setDefaultValue(Localization.getLcnDefault("mod.voice.players.one"))
                .setSaveConsumer(newValue -> Localization.setLocalization("mod.voice.players.one", newValue))
                .build());
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.localization.mod.voice.players.more"),
                        Localization.getLocalization("mod.voice.players.more", false))
                .setDefaultValue(Localization.getLcnDefault("mod.voice.players.more"))
                .setSaveConsumer(newValue -> Localization.setLocalization("mod.voice.players.more", newValue))
                .build());
        return category;
    }
}
