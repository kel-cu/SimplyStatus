package ru.kelcuprum.simplystatus.gui;

import com.google.gson.JsonArray;
import net.minecraft.Util;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import org.apache.logging.log4j.Level;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBooleanBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBuilder;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.screens.ConfigScreenBuilder;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.config.ModConfig;

import static ru.kelcuprum.alinlib.WebAPI.getJsonArray;
import static ru.kelcuprum.simplystatus.SimplyStatus.MINECRAFT;


public class ThanksScreen {
    private final InterfaceUtils.DesignType designType = InterfaceUtils.DesignType.FLAT;
    public Screen build(Screen parent){
        try {
            JsonArray data = getJsonArray("https://api.kelcuprum.ru/boosty/thanks");
            SimplyStatus.thanks = ModConfig.jsonArrayToStringArray(data);
        } catch (Exception e){
            SimplyStatus.log(e.getLocalizedMessage(), Level.ERROR);
        }
        ConfigScreenBuilder builder = new ConfigScreenBuilder(parent, Component.translatable("simplystatus.name"), designType)
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.client"), (s) -> MINECRAFT.setScreen(new MainConfigs().build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.localization"), (s) -> MINECRAFT.setScreen(new LocalizationsConfigs().build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.server"), (s) -> MINECRAFT.setScreen(new ServerConfigs().build(parent))).build().setActive(MINECRAFT.getCurrentServer() != null))
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.assets"), (s) -> MINECRAFT.setScreen(new AssetsConfigs().build(parent))).build().setActive(SimplyStatus.userConfig.getBoolean("USE_CUSTOM_ASSETS", false) || SimplyStatus.userConfig.getBoolean("USE_CUSTOM_APP_ID", false)))
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.addons"), (s) -> MINECRAFT.setScreen(new AddonsConfigs().build(parent))).build())
                .addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.config.mods"), (s) -> MINECRAFT.setScreen(new ModsConfigs().build(parent))).build().setActive(SimplyStatus.isMusicModsEnable || SimplyStatus.isVoiceModsEnable || SimplyStatus.replayMod));
                //
        if(AlinLib.bariumConfig.getBoolean("FRIEND", true)) builder.addPanelWidget(new ButtonBuilder(Component.translatable("simplystatus.support"), (s) -> MINECRAFT.setScreen(new ThanksScreen().build(parent))).build());

        builder.addWidget(new TextBox(Component.translatable("simplystatus.support"), true));
        builder.addWidget(new TextBox(Component.translatable("simplystatus.support.url"), false, (s) -> Util.getPlatform().openUri("https://kelcuprum.ru/r/boo")));
        builder.addWidget(new TextBox(Component.translatable("simplystatus.support.special_thanks"), false));
        for(String pwgood3 : SimplyStatus.thanks){
            builder.addWidget(new TextBox(Component.literal("- "+pwgood3).setStyle(Style.EMPTY.withBold(true)), false));
        }
        builder.addWidget(new ButtonBooleanBuilder(Component.translatable("simplystatus.support.friend"),true).setConfig(AlinLib.bariumConfig, "FRIENDS").build()
                        .setDescription(Component.translatable("simplystatus.support.friend.description")));
        return builder.build();
    }
}
