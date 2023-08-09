package ru.simplykel.simplystatus.config.gui.cloth.category;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.loader.api.FabricLoader;
import ru.simplykel.simplystatus.Client;
import ru.simplykel.simplystatus.Main;
import ru.simplykel.simplystatus.config.Localization;
import ru.simplykel.simplystatus.config.ModConfig;
import ru.simplykel.simplystatus.config.UserConfig;

public class DevConfigs {
    public ConfigCategory getCategory(ConfigBuilder builder){
        ConfigCategory category = builder.getOrCreateCategory(Localization.toText("Developer/Debug Mode"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        // Mod config
        category.addEntry(entryBuilder.startTextDescription(Localization.toText("§9# Internal configuration")).build());
        category.addEntry(entryBuilder.startTextDescription(Localization.toText("Mod version: " + FabricLoader.getInstance().getModContainer("simplystatus").get().getMetadata().getVersion().getFriendlyString())).build());
        String versionType = Main.isBetaBuild ? "§6Public Test Build" : Main.isDevBuild ? "§cDeveloper Build" : "§aRelease Build";
        category.addEntry(entryBuilder.startTextDescription(Localization.toText("Mod version type: " + versionType)).build());

        category.addEntry(entryBuilder.startBooleanToggle(Localization.toText("Debug presence"), ModConfig.debugPresence)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> ModConfig.debugPresence = newValue)
                .setTooltip(Localization.toText("§cWarning!§r\nThis function will spam in game log.\nMany lunchers do not work more than 100000 lines of logs. "))
                .build());
        category.addEntry(entryBuilder.startTextDescription(Localization.toText("Selected assets: " + UserConfig.USE_ASSETS)).build());
        category.addEntry(entryBuilder.startSelector(Localization.toText("Assets: "), ModConfig.assetsList, UserConfig.USE_ASSETS)
                .setDefaultValue(ModConfig.assetsList[0])
                .build());
        category.addEntry(entryBuilder.startTextDescription(Localization.toText("Main application ID: " + ModConfig.baseID)).build());
        if(ModConfig.mineID != null) category.addEntry(entryBuilder.startTextDescription(Localization.toText("Another application ID: " + ModConfig.mineID)).build());
        // System
        category.addEntry(entryBuilder.startTextDescription(Localization.toText("§9# Operating system")).build());
        category.addEntry(entryBuilder.startTextDescription(Localization.toText("OS name: " + System.getProperty("os.name"))).build());
        //
        category.addEntry(entryBuilder.startTextDescription(Localization.toText("OS versiom: " + System.getProperty("os.version"))).build());
        //
        category.addEntry(entryBuilder.startTextDescription(Localization.toText("OS architecture: " + System.getProperty("os.arch"))).build());

        // Java
        category.addEntry(entryBuilder.startTextDescription(Localization.toText("§9# Java")).build());
        category.addEntry(entryBuilder.startTextDescription(Localization.toText("Java vendor: " + System.getProperty("java.vendor"))).build());
        //
        category.addEntry(entryBuilder.startTextDescription(Localization.toText("Java version: " + System.getProperty("java.version"))).build());
        //
        category.addEntry(entryBuilder.startTextDescription(Localization.toText("Java home: " + System.getProperty("java.home"))).build());

        // Discord
        category.addEntry(entryBuilder.startTextDescription(Localization.toText("§9# Discord")).build());
        category.addEntry(entryBuilder.startTextDescription(Localization.toText("Application ID: " + Client.APPLICATION_ID)).build());
        //
        if(Client.USER != null && Client.CONNECTED_DISCORD) category.addEntry(entryBuilder.startTextDescription(Localization.toText("User ID: " + Client.USER.userId)).build());
        //
        category.addEntry(entryBuilder.startTextDescription(Localization.toText("Is connected: " + Client.CONNECTED_DISCORD)).build());
        return category;
    }
}
