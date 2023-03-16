package ru.simplykel.simplystatus.config.gui.category;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import ru.simplykel.simplystatus.config.Localization;
import ru.simplykel.simplystatus.config.UserConfig;

public class MusicPlayerConfigs {
    public ConfigCategory getCategory(ConfigBuilder builder){
        ConfigCategory category = builder.getOrCreateCategory(Text.translatable("simplystatus.config.musicplayer"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        //
        category.addEntry(entryBuilder.startStrField(
                        Text.translatable("simplystatus.config.localization.mod.music.menu"),
                        Localization.getLocalization("mod.music.menu", false))
                .setDefaultValue(Localization.getLcnDefault("mod.music.menu"))
                .setSaveConsumer(newValue -> Localization.setLocalization("mod.music.menu", newValue))
                .build());
        category.addEntry(entryBuilder.startStrField(
                        Text.translatable("simplystatus.config.localization.mod.music"),
                        Localization.getLocalization("mod.music", false))
                .setDefaultValue(Localization.getLcnDefault("mod.music"))
                .setSaveConsumer(newValue -> Localization.setLocalization("mod.music", newValue))
                .build());
        category.addEntry(entryBuilder.startStrField(
                        Text.translatable("simplystatus.config.localization.mod.music.format.artist"),
                        Localization.getLocalization("mod.music.format.artist", false))
                .setDefaultValue(Localization.getLcnDefault("mod.music.format.artist"))
                .setSaveConsumer(newValue -> Localization.setLocalization("mod.music.format.artist", newValue))
                .build());
        category.addEntry(entryBuilder.startStrField(
                        Text.translatable("simplystatus.config.localization.mod.music.format"),
                        Localization.getLocalization("mod.music.format", false))
                .setDefaultValue(Localization.getLcnDefault("mod.music.format"))
                .setSaveConsumer(newValue -> Localization.setLocalization("mod.music.format", newValue))
                .build());
        return category;
    }
}
