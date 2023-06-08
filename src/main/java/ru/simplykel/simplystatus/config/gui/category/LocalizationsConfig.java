package ru.simplykel.simplystatus.config.gui.category;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.MinecraftClient;
import ru.simplykel.simplystatus.config.Localization;

public class LocalizationsConfig {
    public ConfigCategory getCategory(ConfigBuilder builder){
        MinecraftClient CLIENT = MinecraftClient.getInstance();
        ConfigCategory category = builder.getOrCreateCategory(Localization.getText("simplystatus.config.localization"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        ///
        category.addEntry(entryBuilder.startTextDescription(Localization.getText("simplystatus.config.localization.title.menu")).build());
        //
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.localization.mainmenu"),
                        Localization.getLocalization("mainmenu", false))
                .setDefaultValue(Localization.getLcnDefault("mainmenu"))
                .setSaveConsumer(newValue -> Localization.setLocalization("mainmenu", newValue))
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.localization.mainmenu.state"),
                        Localization.getLocalization("mainmenu.state", false))
                .setDefaultValue(Localization.getLcnDefault("mainmenu.state"))
                .setSaveConsumer(newValue -> Localization.setLocalization("mainmenu.state", newValue))
                .build());
        ///
        category.addEntry(entryBuilder.startTextDescription(Localization.getText("simplystatus.config.localization.title.singleplayer")).build());
        //
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.localization.singleplayer"),
                        Localization.getLocalization("singleplayer", false))
                .setDefaultValue(Localization.getLcnDefault("singleplayer"))
                .setSaveConsumer(newValue -> Localization.setLocalization("singleplayer", newValue))
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.localization.world.loading"),
                        Localization.getLocalization("world.loading", false))
                .setDefaultValue(Localization.getLcnDefault("world.loading"))
                .setSaveConsumer(newValue -> Localization.setLocalization("world.loading", newValue))
                .build());
        ///
        category.addEntry(entryBuilder.startTextDescription(Localization.getText("simplystatus.config.localization.title.multiplayer")).build());
        //
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.localization.address.hidden"),
                        Localization.getLocalization("address.hidden", false))
                .setDefaultValue(Localization.getLcnDefault("address.hidden"))
                .setSaveConsumer(newValue -> Localization.setLocalization("address.hidden", newValue))
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.localization.address"),
                        Localization.getLocalization("address", false))
                .setDefaultValue(Localization.getLcnDefault("address"))
                .setSaveConsumer(newValue -> Localization.setLocalization("address", newValue))
                .build());

        //
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.localization.server.connecting"),
                        Localization.getLocalization("server.connecting", false))
                .setDefaultValue(Localization.getLcnDefault("server.connecting"))
                .setSaveConsumer(newValue -> Localization.setLocalization("server.connecting", newValue))
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.localization.server.disconnected"),
                        Localization.getLocalization("server.disconnected", false))
                .setDefaultValue(Localization.getLcnDefault("server.disconnected"))
                .setSaveConsumer(newValue -> Localization.setLocalization("server.disconnected", newValue))
                .build());
        ///
        category.addEntry(entryBuilder.startTextDescription(Localization.getText("simplystatus.config.localization.title.player")).build());
        //
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.localization.player.sleep"),
                        Localization.getLocalization("player.sleep", false))
                .setDefaultValue(Localization.getLcnDefault("player.sleep"))
                .setSaveConsumer(newValue -> Localization.setLocalization("player.sleep", newValue))
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.localization.player.sneak"),
                        Localization.getLocalization("player.sneak", false))
                .setDefaultValue(Localization.getLcnDefault("player.sneak"))
                .setSaveConsumer(newValue -> Localization.setLocalization("player.sneak", newValue))
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.localization.player.on.fire"),
                        Localization.getLocalization("player.on.fire", false))
                .setDefaultValue(Localization.getLcnDefault("player.on.fire"))
                .setSaveConsumer(newValue -> Localization.setLocalization("player.on.fire", newValue))
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.localization.player.on.water"),
                        Localization.getLocalization("player.on.water", false))
                .setDefaultValue(Localization.getLcnDefault("player.on.water"))
                .setSaveConsumer(newValue -> Localization.setLocalization("player.on.water", newValue))
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.localization.player.statistics"),
                        Localization.getLocalization("player.statistics", false))
                .setDefaultValue(Localization.getLcnDefault("player.statistics"))
                .setSaveConsumer(newValue -> Localization.setLocalization("player.statistics", newValue))
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.localization.player.world.state"),
                        Localization.getLocalization("player.world.state", false))
                .setDefaultValue(Localization.getLcnDefault("player.world.state"))
                .setSaveConsumer(newValue -> Localization.setLocalization("player.world.state", newValue))
                .build());
        ///
        category.addEntry(entryBuilder.startTextDescription(Localization.getText("simplystatus.config.localization.title.death")).build());
        //
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.localization.death.one"),
                        Localization.getLocalization("death.one", false))
                .setDefaultValue(Localization.getLcnDefault("death.one"))
                .setSaveConsumer(newValue -> Localization.setLocalization("death.one", newValue))
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.localization.death.two"),
                        Localization.getLocalization("death.two", false))
                .setDefaultValue(Localization.getLcnDefault("death.two"))
                .setSaveConsumer(newValue -> Localization.setLocalization("death.two", newValue))
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.localization.death.three"),
                        Localization.getLocalization("death.three", false))
                .setDefaultValue(Localization.getLcnDefault("death.three"))
                .setSaveConsumer(newValue -> Localization.setLocalization("death.three", newValue))
                .build());
        ///
        category.addEntry(entryBuilder.startTextDescription(Localization.getText("simplystatus.config.localization.title.item")).build());
        //
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.localization.item.air"),
                        Localization.getLocalization("item.air", false))
                .setDefaultValue(Localization.getLcnDefault("item.air"))
                .setSaveConsumer(newValue -> Localization.setLocalization("item.air", newValue))
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.localization.item"),
                        Localization.getLocalization("item", false))
                .setDefaultValue(Localization.getLcnDefault("item"))
                .setSaveConsumer(newValue -> Localization.setLocalization("item", newValue))
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.localization.item.format"),
                        Localization.getLocalization("item.format", false))
                .setDefaultValue(Localization.getLcnDefault("item.format"))
                .setSaveConsumer(newValue -> Localization.setLocalization("item.format", newValue))
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.localization.item.format.count"),
                        Localization.getLocalization("item.format.count", false))
                .setDefaultValue(Localization.getLcnDefault("item.format.count"))
                .setSaveConsumer(newValue -> Localization.setLocalization("item.format.count", newValue))
                .build());
        ///
        category.addEntry(entryBuilder.startTextDescription(Localization.getText("simplystatus.config.localization.title.worlds")).build());
        //
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.localization.world.overworld"),
                        Localization.getLocalization("world.overworld", false))
                .setDefaultValue(Localization.getLcnDefault("world.overworld"))
                .setSaveConsumer(newValue -> Localization.setLocalization("world.overworld", newValue))
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.localization.world.nether"),
                        Localization.getLocalization("world.nether", false))
                .setDefaultValue(Localization.getLcnDefault("world.nether"))
                .setSaveConsumer(newValue -> Localization.setLocalization("world.nether", newValue))
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.localization.world.the_end"),
                        Localization.getLocalization("world.the_end", false))
                .setDefaultValue(Localization.getLcnDefault("world.the_end"))
                .setSaveConsumer(newValue -> Localization.setLocalization("world.the_end", newValue))
                .build());
        //
        if(CLIENT.getGameVersion().equals("23w13a_or_b")){
            category.addEntry(entryBuilder.startStrField(
                            Localization.getText("simplystatus.config.localization.world.moon"),
                            Localization.getLocalization("world.moon", false))
                    .setDefaultValue(Localization.getLcnDefault("world.moon"))
                    .setSaveConsumer(newValue -> Localization.setLocalization("world.moon", newValue))
                    .build());
        }
        ///
        category.addEntry(entryBuilder.startTextDescription(Localization.getText("simplystatus.config.localization.title.time")).build());
        //
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.localization.time.day"),
                        Localization.getLocalization("time.day", false))
                .setDefaultValue(Localization.getLcnDefault("time.day"))
                .setSaveConsumer(newValue -> Localization.setLocalization("time.day", newValue))
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.localization.time.night"),
                        Localization.getLocalization("time.night", false))
                .setDefaultValue(Localization.getLcnDefault("time.night"))
                .setSaveConsumer(newValue -> Localization.setLocalization("time.night", newValue))
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.localization.time.morning"),
                        Localization.getLocalization("time.morning", false))
                .setDefaultValue(Localization.getLcnDefault("time.morning"))
                .setSaveConsumer(newValue -> Localization.setLocalization("time.morning", newValue))
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.localization.time.evening"),
                        Localization.getLocalization("time.evening", false))
                .setDefaultValue(Localization.getLcnDefault("time.evening"))
                .setSaveConsumer(newValue -> Localization.setLocalization("time.evening", newValue))
                .build());
        ///
        category.addEntry(entryBuilder.startTextDescription(Localization.getText("simplystatus.config.localization.title.unknown")).build());
        //
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.localization.unknown.world"),
                        Localization.getLocalization("unknown.world", false))
                .setDefaultValue(Localization.getLcnDefault("unknown.world"))
                .setSaveConsumer(newValue -> Localization.setLocalization("unknown.world", newValue))
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.localization.unknown.server"),
                        Localization.getLocalization("unknown.server", false))
                .setDefaultValue(Localization.getLcnDefault("unknown.server"))
                .setSaveConsumer(newValue -> Localization.setLocalization("unknown.server", newValue))
                .build());
        //
        category.addEntry(entryBuilder.startStrField(
                        Localization.getText("simplystatus.config.localization.unknown"),
                        Localization.getLocalization("unknown", false))
                .setDefaultValue(Localization.getLcnDefault("unknown"))
                .setSaveConsumer(newValue -> Localization.setLocalization("unknown", newValue))
                .build());
        return category;
    }
}
