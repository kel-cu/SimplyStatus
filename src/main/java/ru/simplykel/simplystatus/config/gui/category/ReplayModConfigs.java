package ru.simplykel.simplystatus.config.gui.category;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import ru.simplykel.simplystatus.config.Localization;
import ru.simplykel.simplystatus.config.UserConfig;

public class ReplayModConfigs {
    public ConfigCategory getCategory(ConfigBuilder builder){
        ConfigCategory category = builder.getOrCreateCategory(Text.translatable("simplystatus.config.replaymod"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        //
        category.addEntry(entryBuilder.startBooleanToggle(Text.translatable("simplystatus.config.replaymod.view_replay_mod_server_name"), UserConfig.VIEW_REPLAY_MOD_SERVER_NAME)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> UserConfig.VIEW_REPLAY_MOD_SERVER_NAME = newValue)
                .build());
        category.addEntry(entryBuilder.startStrField(
                        Text.translatable("simplystatus.config.localization.mod.replaymod"),
                        Localization.getLocalization("mod.replaymod", false))
                .setDefaultValue(Localization.getLcnDefault("mod.replaymod"))
                .setSaveConsumer(newValue -> Localization.setLocalization("mod.replaymod", newValue))
                .build());
        category.addEntry(entryBuilder.startStrField(
                        Text.translatable("simplystatus.config.localization.mod.replaymod.state"),
                        Localization.getLocalization("mod.replaymod.state", false))
                .setDefaultValue(Localization.getLcnDefault("mod.replaymod.state"))
                .setSaveConsumer(newValue -> Localization.setLocalization("mod.replaymod.state", newValue))
                .build());
        category.addEntry(entryBuilder.startStrField(
                        Text.translatable("simplystatus.config.localization.mod.replaymod.date_format"),
                        Localization.getLocalization("mod.replaymod.date_format", false))
                .setDefaultValue(Localization.getLcnDefault("mod.replaymod.date_format"))
                .setSaveConsumer(newValue -> Localization.setLocalization("mod.replaymod.date_format", newValue))
                .build());
        return category;
    }
}
