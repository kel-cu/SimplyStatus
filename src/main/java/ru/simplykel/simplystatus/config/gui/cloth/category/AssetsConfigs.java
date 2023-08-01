package ru.simplykel.simplystatus.config.gui.cloth.category;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.MinecraftClient;
import ru.simplykel.simplystatus.Client;
import ru.simplykel.simplystatus.config.Localization;
import ru.simplykel.simplystatus.config.ModConfig;

public class AssetsConfigs {
    public ConfigCategory getCategory(ConfigBuilder builder){
        MinecraftClient CLIENT = MinecraftClient.getInstance();
        ConfigCategory category = builder.getOrCreateCategory(Localization.getText("simplystatus.config.assets"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        ///
        category.addEntry(entryBuilder.startTextDescription(Localization.getText("simplystatus.config.assets.title.menu")).build());
        //
        category.addEntry(entryBuilder.startStrField(
                                Localization.getText("simplystatus.config.assets.logo"),
                        Client.ASSETS.logo)
                .setDefaultValue(ModConfig.defaultAssets.logo)
                .setSaveConsumer(newValue -> Client.ASSETS.logo = newValue)
                .build());
        ///
        category.addEntry(entryBuilder.startTextDescription(Localization.getText("simplystatus.config.assets.title.time")).build());
        //
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.assets.morning"),
                        Client.ASSETS.morning)
                .setDefaultValue(ModConfig.defaultAssets.morning)
                .setSaveConsumer(newValue -> Client.ASSETS.morning = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                                Localization.getText("simplystatus.config.assets.day"),
                        Client.ASSETS.day)
                .setDefaultValue(ModConfig.defaultAssets.day)
                .setSaveConsumer(newValue -> Client.ASSETS.day = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.assets.evening"),
                        Client.ASSETS.evening)
                .setDefaultValue(ModConfig.defaultAssets.evening)
                .setSaveConsumer(newValue -> Client.ASSETS.evening = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                                Localization.getText("simplystatus.config.assets.night"),
                        Client.ASSETS.night)
                .setDefaultValue(ModConfig.defaultAssets.night)
                .setSaveConsumer(newValue -> Client.ASSETS.night = newValue)
                .build());
        ///
        category.addEntry(entryBuilder.startTextDescription(Localization.getText("simplystatus.config.assets.title.worlds")).build());
        //
        category.addEntry(entryBuilder.startStrField(
                                Localization.getText("simplystatus.config.assets.world"),
                        Client.ASSETS.world)
                .setDefaultValue(ModConfig.defaultAssets.world)
                .setSaveConsumer(newValue -> Client.ASSETS.world = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                                Localization.getText("simplystatus.config.assets.world_nether"),
                        Client.ASSETS.world_nether)
                .setDefaultValue(ModConfig.defaultAssets.world_nether)
                .setSaveConsumer(newValue -> Client.ASSETS.world_nether = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                                Localization.getText("simplystatus.config.assets.world_the_end"),
                        Client.ASSETS.world_the_end)
                .setDefaultValue(ModConfig.defaultAssets.world_the_end)
                .setSaveConsumer(newValue -> Client.ASSETS.world_the_end = newValue)
                .build());
        //
        if(CLIENT.getGameVersion().equals("23w13a_or_b")){
            category.addEntry(entryBuilder.startStrField(
                            Localization.getText("simplystatus.config.assets.world_moon"),
                            Client.ASSETS.world_moon)
                    .setDefaultValue(ModConfig.defaultAssets.world_moon)
                    .setSaveConsumer(newValue -> Client.ASSETS.world_moon = newValue)
                    .build());
        }
        ///
        category.addEntry(entryBuilder.startTextDescription(Localization.getText("simplystatus.config.assets.title.modification")).build());
        //
        category.addEntry(entryBuilder.startStrField(
                                Localization.getText("simplystatus.config.assets.music"),
                        Client.ASSETS.music)
                .setDefaultValue(ModConfig.defaultAssets.music)
                .setSaveConsumer(newValue -> Client.ASSETS.music = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                                Localization.getText("simplystatus.config.assets.replaymod"),
                        Client.ASSETS.replaymod)
                .setDefaultValue(ModConfig.defaultAssets.replaymod)
                .setSaveConsumer(newValue -> Client.ASSETS.replaymod = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                                Localization.getText("simplystatus.config.assets.voice"),
                        Client.ASSETS.voice)
                .setDefaultValue(ModConfig.defaultAssets.voice)
                .setSaveConsumer(newValue -> Client.ASSETS.voice = newValue)
                .build());
        ///
        category.addEntry(entryBuilder.startTextDescription(Localization.getText("simplystatus.config.assets.title.unknown")).build());
        //
        category.addEntry(entryBuilder.startStrField(
                                Localization.getText("simplystatus.config.assets.unknown"),
                        Client.ASSETS.unknown)
                .setDefaultValue(ModConfig.defaultAssets.unknown)
                .setSaveConsumer(newValue -> Client.ASSETS.unknown = newValue)
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                                Localization.getText("simplystatus.config.assets.unknown_world"),
                        Client.ASSETS.unknown_world)
                .setDefaultValue(ModConfig.defaultAssets.unknown_world)
                .setSaveConsumer(newValue -> Client.ASSETS.unknown_world = newValue)
                .build());
        return category;
    }
}
