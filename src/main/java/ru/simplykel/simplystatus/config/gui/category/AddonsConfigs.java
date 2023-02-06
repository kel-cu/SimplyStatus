package ru.simplykel.simplystatus.config.gui.category;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableTextContent;

public class AddonsConfigs {
    public ConfigCategory getCategory(ConfigBuilder builder){
        ConfigCategory category = builder.getOrCreateCategory(MutableText.of(new TranslatableTextContent("config.simplystatus.client")));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        return category;
    }
}
