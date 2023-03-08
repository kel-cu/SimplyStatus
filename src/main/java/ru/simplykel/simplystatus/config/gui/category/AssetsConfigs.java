package ru.simplykel.simplystatus.config.gui.category;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableTextContent;
import ru.simplykel.simplystatus.Client;
import ru.simplykel.simplystatus.config.ModConfig;

public class AssetsConfigs {
    public ConfigCategory getCategory(ConfigBuilder builder){
        ConfigCategory category = builder.getOrCreateCategory(MutableText.of(new TranslatableTextContent("simplystatus.config.assets")));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        //
        category.addEntry(entryBuilder.startStrField(
                        MutableText.of(new TranslatableTextContent("simplystatus.config.assets.logo")),
                        Client.ASSETS.logo)
                .setDefaultValue(ModConfig.defaultAssets.logo)
                .setSaveConsumer(newValue -> Client.ASSETS.logo = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                        MutableText.of(new TranslatableTextContent("simplystatus.config.assets.day")),
                        Client.ASSETS.day)
                .setDefaultValue(ModConfig.defaultAssets.day)
                .setSaveConsumer(newValue -> Client.ASSETS.day = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                        MutableText.of(new TranslatableTextContent("simplystatus.config.assets.night")),
                        Client.ASSETS.night)
                .setDefaultValue(ModConfig.defaultAssets.night)
                .setSaveConsumer(newValue -> Client.ASSETS.night = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                        MutableText.of(new TranslatableTextContent("simplystatus.config.assets.morning")),
                        Client.ASSETS.morning)
                .setDefaultValue(ModConfig.defaultAssets.morning)
                .setSaveConsumer(newValue -> Client.ASSETS.morning = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                        MutableText.of(new TranslatableTextContent("simplystatus.config.assets.evening")),
                        Client.ASSETS.evening)
                .setDefaultValue(ModConfig.defaultAssets.evening)
                .setSaveConsumer(newValue -> Client.ASSETS.evening = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                        MutableText.of(new TranslatableTextContent("simplystatus.config.assets.world")),
                        Client.ASSETS.world)
                .setDefaultValue(ModConfig.defaultAssets.world)
                .setSaveConsumer(newValue -> Client.ASSETS.world = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                        MutableText.of(new TranslatableTextContent("simplystatus.config.assets.world_nether")),
                        Client.ASSETS.world_nether)
                .setDefaultValue(ModConfig.defaultAssets.world_nether)
                .setSaveConsumer(newValue -> Client.ASSETS.world_nether = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                        MutableText.of(new TranslatableTextContent("simplystatus.config.assets.world_the_end")),
                        Client.ASSETS.world_the_end)
                .setDefaultValue(ModConfig.defaultAssets.world_the_end)
                .setSaveConsumer(newValue -> Client.ASSETS.world_the_end = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                        MutableText.of(new TranslatableTextContent("simplystatus.config.assets.music")),
                        Client.ASSETS.music)
                .setDefaultValue(ModConfig.defaultAssets.music)
                .setSaveConsumer(newValue -> Client.ASSETS.music = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                        MutableText.of(new TranslatableTextContent("simplystatus.config.assets.replaymod")),
                        Client.ASSETS.replaymod)
                .setDefaultValue(ModConfig.defaultAssets.replaymod)
                .setSaveConsumer(newValue -> Client.ASSETS.replaymod = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                        MutableText.of(new TranslatableTextContent("simplystatus.config.assets.voice")),
                        Client.ASSETS.voice)
                .setDefaultValue(ModConfig.defaultAssets.voice)
                .setSaveConsumer(newValue -> Client.ASSETS.voice = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                        MutableText.of(new TranslatableTextContent("simplystatus.config.assets.unknown")),
                        Client.ASSETS.unknown)
                .setDefaultValue(ModConfig.defaultAssets.unknown)
                .setSaveConsumer(newValue -> Client.ASSETS.unknown = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                        MutableText.of(new TranslatableTextContent("simplystatus.config.assets.unknown_world")),
                        Client.ASSETS.unknown_world)
                .setDefaultValue(ModConfig.defaultAssets.unknown_world)
                .setSaveConsumer(newValue -> Client.ASSETS.unknown_world = newValue)
                .build());
        return category;
    }
}
