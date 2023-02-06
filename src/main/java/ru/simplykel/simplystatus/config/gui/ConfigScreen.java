package ru.simplykel.simplystatus.config.gui;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableTextContent;

public class ConfigScreen {
    public static Screen buildScreen (Screen currentScreen) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(currentScreen)
                .setTitle(MutableText.of(new TranslatableTextContent("category.simplystatus.name")))
                .setTransparentBackground(true)
                .setSavingRunnable(ConfigScreen::save);

        return builder.build();
    }
    private static void save(){

    }
}
