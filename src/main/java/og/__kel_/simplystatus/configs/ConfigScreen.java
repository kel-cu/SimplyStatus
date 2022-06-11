package og.__kel_.simplystatus.configs;

import me.shedaniel.clothconfig2.api.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.text.LiteralTextContent;
//LiteralText
import net.minecraft.util.Identifier;
import og.__kel_.simplystatus.Client;
import og.__kel_.simplystatus.Config;
import og.__kel_.simplystatus.Main;
import og.__kel_.simplystatus.Translate;
import og.__kel_.simplystatus.info.Game;


public class ConfigScreen {
    public static ConfigCategory mainCategory;
    public static ConfigCategory servCategory;
    public static ConfigEntryBuilder entryBuilder;
    public static Translate translate = new Translate();
    public static Screen buildScreen (Screen currentScreen) {
        MinecraftClient mc = MinecraftClient.getInstance();
        translate.selectedLang();
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(currentScreen)
                .setTitle(MutableText.of(new TranslatableTextContent("category.simplystatus.name")))
                .setTransparentBackground(true)
                .setSavingRunnable(() -> {
                    Main.log.info("Saves configurations!");
                    Config config = new Config();
                    config.save();
                    if(mc.world != null){
                        if(!mc.isInSingleplayer()){
                            Main.log.info(mc.getCurrentServerEntry().address + " saves configurations!");
                            config.save(mc.getCurrentServerEntry().address);
                        }
                    }
                });
        // mainCategory - Это экран
        mainCategory = builder.getOrCreateCategory(MutableText.of(new TranslatableTextContent("config.simplystatus.client")));
        // entryBuilder - это билдер значения в точке
        entryBuilder = builder.entryBuilder();
        // Основные настройки
        if(!Main.discordConnected){
            mainCategory.addEntry(entryBuilder.startTextDescription(MutableText.of(new TranslatableTextContent("config.simplystatus.notConnected"))).build());
            if(System.getProperty("os.name").toLowerCase().startsWith("linux")){
                mainCategory.addEntry(entryBuilder.startTextDescription(MutableText.of(new TranslatableTextContent("config.simplystatus.notConnectedLinux"))).build());
            }
        }
        mainCategory.setBackground(Identifier.of("stone.png", "assets/minecraft/textures/gui/advancements/backgrounds"));
        mainCategory.addEntry(entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.ViewRPC")), Client.ViewRPC)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> Client.ViewRPC = newValue)
                .build());
        mainCategory.addEntry(entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.ViewUsername")), Client.ViewUsername)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> Client.ViewUsername = newValue)
                .build());
        mainCategory.addEntry(entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.viewStatic")), Client.ViewStatic)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> Client.ViewStatic = newValue)
                .build());
        mainCategory.addEntry(entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.OffHand")), Client.ViewOffHand)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> Client.ViewOffHand = newValue)
                .build());
        mainCategory.addEntry(entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.bedrock")), Client.Bedrock)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> Client.Bedrock = newValue)
                .build());
        mainCategory.addEntry(entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.cringe")), Client.CringeIcons)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> Client.CringeIcons = newValue)
                .build());
        // Сервер
        Game game = new Game();
        if(mc.world != null){
            if(!mc.isInSingleplayer()){
                servCategory = builder.getOrCreateCategory(MutableText.of(new TranslatableTextContent("config.simplystatus.thisServer")));
                servCategory.addEntry(entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.viewIP")), Client.ViewIPAddress)
                        .setDefaultValue(false)
                        .setSaveConsumer(newValue -> Client.ViewIPAddress = newValue)
                        .build());
                servCategory.addEntry(entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.viewNameServer")), Client.ViewName)
                        .setDefaultValue(true)
                        .setSaveConsumer(newValue -> Client.ViewName = newValue)
                        .build());
                servCategory.addEntry(entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.enableCustomName")), Client.customNameEnable)
                        .setDefaultValue(false)
                        .setSaveConsumer(newValue -> Client.customNameEnable = newValue)
                        .build());
                servCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.CustomName")), Client.customName)
                        .setDefaultValue("")
                        .setSaveConsumer(newValue -> Client.customName = newValue)
                        .build());
            }
        }

        Screen screen = builder.build();

        return screen;
    }
}