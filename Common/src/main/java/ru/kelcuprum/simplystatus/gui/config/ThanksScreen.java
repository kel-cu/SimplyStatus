package ru.kelcuprum.simplystatus.gui.config;

import com.google.gson.JsonArray;
import net.minecraft.Util;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import org.apache.logging.log4j.Level;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.buttons.ButtonConfigBoolean;
import ru.kelcuprum.alinlib.gui.components.buttons.base.Button;
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
                .addPanelWidget(new Button(10,40, designType, Component.translatable("simplystatus.config.client"), (s) -> MINECRAFT.setScreen(new MainConfigs().build(parent))))
                .addPanelWidget(new Button(10,65, designType, Component.translatable("simplystatus.config.localization"), (s) -> MINECRAFT.setScreen(new LocalizationsConfigs().build(parent))))
                .addPanelWidget(new Button(10,90, designType, Component.translatable("simplystatus.config.server"), (s) -> MINECRAFT.setScreen(new ThanksScreen().build(parent))).setActive(MINECRAFT.getCurrentServer() != null))
                .addPanelWidget(new Button(10,115, designType, Component.translatable("simplystatus.config.assets"), (s) -> MINECRAFT.setScreen(new AssetsConfigs().build(parent))).setActive(SimplyStatus.userConfig.getBoolean("USE_CUSTOM_ASSETS", false) || SimplyStatus.userConfig.getBoolean("USE_CUSTOM_APP_ID", false)))
                .addPanelWidget(new Button(10,140, designType, Component.translatable("simplystatus.config.addons"), (s) -> MINECRAFT.setScreen(new AddonsConfigs().build(parent))))
                .addPanelWidget(new Button(10,165, designType, Component.translatable("simplystatus.config.mods"), (s) -> MINECRAFT.setScreen(new ModsConfigs().build(parent))).setActive(SimplyStatus.isMusicModsEnable || SimplyStatus.isVoiceModsEnable || SimplyStatus.replayMod));
                //
        if(AlinLib.bariumConfig.getBoolean("FRIEND", true)) builder.addPanelWidget(new Button(10,190, designType, Component.translatable("simplystatus.support"), (s) -> MINECRAFT.setScreen(new ThanksScreen().build(parent))));

        builder.addWidget(new TextBox(140, 5, Component.translatable("simplystatus.support"), true));
        builder.addWidget(new TextBox(140, 30, Component.translatable("simplystatus.support.url"), false, (s) -> {
            Util.getPlatform().openUri("https://kelcuprum.ru/r/boo");
        }));
        builder.addWidget(new TextBox(140, 55, Component.translatable("simplystatus.support.special_thanks"), false));
        for(String dobryack : SimplyStatus.thanks){
            builder.addWidget(new TextBox(140, -20, Component.literal("- "+dobryack).setStyle(Style.EMPTY.withBold(true)), false));
        }
        builder.addWidget(new ButtonConfigBoolean(140, -20, designType, AlinLib.bariumConfig, "FRIEND", true, Component.translatable("simplystatus.support.friend"))
                        .setDescription(Component.translatable("simplystatus.support.friend.description")));
        return builder.build();
    }
}
