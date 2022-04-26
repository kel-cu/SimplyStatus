package og.__kel_.simplystatus.configs;

import me.shedaniel.clothconfig2.api.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.TranslatableText;
import og.__kel_.simplystatus.SimplyStatusClient;
import og.__kel_.simplystatus.SimplyStatusConfig;
import og.__kel_.simplystatus.SimplyStatusTranslate;


public class ConfigScreen {
    public static ConfigCategory mainCategory;
    public static ConfigCategory servCategory;
    public static ConfigEntryBuilder entryBuilder;
    public static SimplyStatusTranslate translate = new SimplyStatusTranslate();
    public static Screen buildScreen (Screen currentScreen) {
        MinecraftClient mc = MinecraftClient.getInstance();
        translate.selectedLang();
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(currentScreen)
                .setTitle(new TranslatableText("category.simplystatus.name"))
                .setTransparentBackground(true)
                .setSavingRunnable(() -> {
                    System.out.println("SimplyStatus saves configurations!");
                    SimplyStatusConfig config = new SimplyStatusConfig();
                    config.save();
                    if(mc.world != null){
                        if(!mc.isInSingleplayer()){
                            System.out.println("Server saves configurations!");
                            config.save(mc.getCurrentServerEntry().address);
                        }
                    }
                });
        // mainCategory - Это экран
        mainCategory = builder.getOrCreateCategory(new TranslatableText("config.simplystatus.client"));
        // entryBuilder - это билдер значения в точке
        entryBuilder = builder.entryBuilder();
        // Основные настройки
        mainCategory.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("config.simplystatus.ViewRPC"), SimplyStatusClient.ViewRPC)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> SimplyStatusClient.ViewRPC = newValue)
                .build());
        mainCategory.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("config.simplystatus.ViewUsername"), SimplyStatusClient.ViewUsername)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> SimplyStatusClient.ViewUsername = newValue)
                .build());
        mainCategory.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("config.simplystatus.viewStatic"), SimplyStatusClient.ViewStatic)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> SimplyStatusClient.ViewStatic = newValue)
                .build());
        mainCategory.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("config.simplystatus.OffHand"), SimplyStatusClient.ViewOffHand)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> SimplyStatusClient.ViewOffHand = newValue)
                .build());
        mainCategory.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("config.simplystatus.bedrock"), SimplyStatusClient.Bedrock)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> SimplyStatusClient.Bedrock = newValue)
                .build());
        mainCategory.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("config.simplystatus.cringe"), SimplyStatusClient.CringeIcons)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> SimplyStatusClient.CringeIcons = newValue)
                .build());
        // Сервер
        if(mc.world != null){
            if(!mc.isInSingleplayer()){
                servCategory = builder.getOrCreateCategory(new TranslatableText("config.simplystatus.thisServer"));
                servCategory.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("key.simplystatus.viewIP"), SimplyStatusClient.ViewIPAddress)
                        .setDefaultValue(false)
                        .setSaveConsumer(newValue -> SimplyStatusClient.ViewIPAddress = newValue)
                        .build());
                servCategory.addEntry(entryBuilder.startBooleanToggle(new TranslatableText("key.simplystatus.viewNameServer"), SimplyStatusClient.ViewName)
                        .setDefaultValue(true)
                        .setSaveConsumer(newValue -> SimplyStatusClient.ViewName = newValue)
                        .build());
            }
        }

        Screen screen = builder.build();

        return screen;
    }
}