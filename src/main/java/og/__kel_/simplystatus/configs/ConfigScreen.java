package og.__kel_.simplystatus.configs;

import me.shedaniel.clothconfig2.api.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import og.__kel_.simplystatus.SimplyStatusClient;
import og.__kel_.simplystatus.SimplyStatusMain;
import og.__kel_.simplystatus.SimplyStatusTranslate;
//import og.__kel_.simplystatus.SimplyStatusTranslate;

public class ConfigScreen {
    public static ConfigCategory mainCategory;
    public static ConfigCategory langCategory;
    public static ConfigCategory servCategory;
    public static ConfigEntryBuilder entryBuilder;
    static MinecraftClient mc = MinecraftClient.getInstance();
    public static SimplyStatusTranslate translate = new SimplyStatusTranslate();

    public static Screen buildScreen (Screen currentScreen) {
        translate.selectedLang();
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(currentScreen)
                .setTitle(new TranslatableText("category.simplystatus.name"))
                .setTransparentBackground(true)
                .setSavingRunnable(() -> {
                    System.out.println("SimplyStatus saves configurations!");
                    SimplyStatusClient.configs.save();
                    translate.SaveJSONLang(mc.getGame().getSelectedLanguage().getCode());
                    if(mc.world != null){
                        if(!mc.isInSingleplayer()){
                            SimplyStatusClient.configs.save(mc.getCurrentServerEntry().address);
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
        // Локализация
        langCategory = builder.getOrCreateCategory(new TranslatableText("config.simplystatus.localization"));
        langCategory.addEntry(entryBuilder.startTextDescription(new TranslatableText("config.simplystatus.descriptionLang"))
                .build());
        langCategory.addEntry(entryBuilder.startStringDropdownMenu(new LiteralText("text_MainMenu"), translate.text_MainMenu)
                .setDefaultValue(new TranslatableText("status.simplystatus.text_MainMenu").getString())
                .setTooltip(new TranslatableText("tooltip.simplystatus.onlyDiscordName"))
                .setSaveConsumer(newValue -> translate.text_MainMenu = newValue)
                .build());
        langCategory.addEntry(entryBuilder.startStringDropdownMenu(new LiteralText("text_DeathOne"), translate.text_DeathOne)
                .setDefaultValue(new TranslatableText("status.simplystatus.text_DeathOne").getString())
                .setTooltip(new TranslatableText("tooltip.simplystatus.onlyDiscordName"))
                .setSaveConsumer(newValue -> translate.text_DeathOne = newValue)
                .build());
        langCategory.addEntry(entryBuilder.startStringDropdownMenu(new LiteralText("text_DeathTwo"), translate.text_DeathTwo)
                .setDefaultValue(new TranslatableText("status.simplystatus.text_DeathTwo").getString())
                .setTooltip(new TranslatableText("tooltip.simplystatus.onlyDiscordName"))
                .setSaveConsumer(newValue -> translate.text_DeathTwo = newValue)
                .build());
        langCategory.addEntry(entryBuilder.startStringDropdownMenu(new LiteralText("text_DeathThree"), translate.text_DeathThree)
                .setDefaultValue(new TranslatableText("status.simplystatus.text_DeathThree").getString())
                .setTooltip(new TranslatableText("tooltip.simplystatus.onlyDiscordName"))
                .setSaveConsumer(newValue -> translate.text_DeathThree = newValue)
                .build());
        langCategory.addEntry(entryBuilder.startStringDropdownMenu(new LiteralText("text_information"), translate.text_information)
                .setDefaultValue(new TranslatableText("status.simplystatus.information").getString())
                .setTooltip(new TranslatableText("tooltip.simplystatus.information"))
                .setSaveConsumer(newValue -> translate.text_information = newValue)
                .build());
        langCategory.addEntry(entryBuilder.startStringDropdownMenu(new LiteralText("text_goodPlayer"), translate.text_goodPlayer)
                .setDefaultValue(new TranslatableText("status.simplystatus.text_goodPlayer").getString())
                .setTooltip(new TranslatableText("tooltip.simplystatus.onlyDiscordName"))
                .setSaveConsumer(newValue -> translate.text_goodPlayer = newValue)
                .build());
        langCategory.addEntry(entryBuilder.startStringDropdownMenu(new LiteralText("text_Air"), translate.text_Air)
                .setDefaultValue(new TranslatableText("status.simplystatus.text_Air").getString())
                .setTooltip(new TranslatableText("tooltip.simplystatus.onlyDiscordName"))
                .setSaveConsumer(newValue -> translate.text_Air = newValue)
                .build());
        langCategory.addEntry(entryBuilder.startStringDropdownMenu(new LiteralText("text_UnknownServer"), translate.text_UnknownServer)
                .setDefaultValue(new TranslatableText("status.simplystatus.text_UnknownServer").getString())
                .setSaveConsumer(newValue -> translate.text_UnknownServer = newValue)
                .build());
        langCategory.addEntry(entryBuilder.startStringDropdownMenu(new LiteralText("text_isInSingleplayer"), translate.text_isInSingleplayer)
                .setDefaultValue(new TranslatableText("status.simplystatus.text_isInSingleplayer").getString())
                .setSaveConsumer(newValue -> translate.text_isInSingleplayer = newValue)
                .build());
        langCategory.addEntry(entryBuilder.startStringDropdownMenu(new LiteralText("text_HideIP"), translate.text_HideIP)
                .setDefaultValue(new TranslatableText("status.simplystatus.text_HideIP").getString())
                .setSaveConsumer(newValue -> translate.text_HideIP = newValue)
                .build());
        langCategory.addEntry(entryBuilder.startStringDropdownMenu(new LiteralText("text_Item"), translate.text_Item)
                .setDefaultValue(new TranslatableText("status.simplystatus.text_Item").getString())
                .setTooltip(new TranslatableText("tooltip.simplystatus.item"))
                .setSaveConsumer(newValue -> translate.text_Item = newValue)
                .build());
        langCategory.addEntry(entryBuilder.startStringDropdownMenu(new LiteralText("text_isSleep"), translate.text_isSleep)
                .setDefaultValue(new TranslatableText("status.simplystatus.text_isSleep").getString())
                .setSaveConsumer(newValue -> translate.text_isSleep = newValue)
                .setTooltip(new TranslatableText("tooltip.simplystatus.allReplaceName"))
                .build());
        langCategory.addEntry(entryBuilder.startStringDropdownMenu(new LiteralText("text_isSneaking"), translate.text_isSneaking)
                .setDefaultValue(new TranslatableText("status.simplystatus.text_isSneaking").getString())
                .setTooltip(new TranslatableText("tooltip.simplystatus.allReplaceName"))
                .setSaveConsumer(newValue -> translate.text_isSneaking = newValue)
                .build());
        langCategory.addEntry(entryBuilder.startStringDropdownMenu(new LiteralText("pieces"), translate.pieces)
                .setDefaultValue(new TranslatableText("status.simplystatus.pieces").getString())
                .setTooltip(new TranslatableText("tooltip.simplystatus.count"))
                .setSaveConsumer(newValue -> translate.pieces = newValue)
                .build());
        if(mc.getGame().getSelectedLanguage().getCode().equals("ru_ru")){
            langCategory.addEntry(entryBuilder.startStringDropdownMenu(new LiteralText("pieces_ru_2_4"), translate.pieces_ru_2_4)
                    .setDefaultValue(new TranslatableText("status.simplystatus.pieces-ru-2-4").getString())
                    .setTooltip(new TranslatableText("tooltip.simplystatus.count"))
                    .setSaveConsumer(newValue -> translate.pieces = newValue)
                    .build());
        }
        langCategory.addEntry(entryBuilder.startStringDropdownMenu(new LiteralText("stacks"), translate.stacks)
                .setDefaultValue(new TranslatableText("status.simplystatus.stacks").getString())
                .setTooltip(new TranslatableText("tooltip.simplystatus.count"))
                .setSaveConsumer(newValue -> translate.stacks = newValue)
                .build());
        langCategory.addEntry(entryBuilder.startStringDropdownMenu(new LiteralText("text_day"), translate.text_day)
                .setDefaultValue(new TranslatableText("status.simplystatus.text_day").getString())
                .setSaveConsumer(newValue -> translate.text_day = newValue)
                .build());
        langCategory.addEntry(entryBuilder.startStringDropdownMenu(new LiteralText("text_night"), translate.text_night)
                .setDefaultValue(new TranslatableText("status.simplystatus.text_night").getString())
                .setSaveConsumer(newValue -> translate.text_night = newValue)
                .build());
        langCategory.addEntry(entryBuilder.startStringDropdownMenu(new LiteralText("text_morning"), translate.text_morning)
                .setDefaultValue(new TranslatableText("status.simplystatus.text_morning").getString())
                .setSaveConsumer(newValue -> translate.text_morning = newValue)
                .build());
        langCategory.addEntry(entryBuilder.startStringDropdownMenu(new LiteralText("text_evening"), translate.text_evening)
                .setDefaultValue(new TranslatableText("status.simplystatus.text_evening").getString())
                .setSaveConsumer(newValue -> translate.text_evening = newValue)
                .build());
        langCategory.addEntry(entryBuilder.startStringDropdownMenu(new LiteralText("world_overworld"), translate.world_overworld)
                .setDefaultValue(new TranslatableText("status.simplystatus.world_overworld").getString())
                .setSaveConsumer(newValue -> translate.world_overworld = newValue)
                .build());
        langCategory.addEntry(entryBuilder.startStringDropdownMenu(new LiteralText("world_nether"), translate.world_nether)
                .setDefaultValue(new TranslatableText("status.simplystatus.world_nether").getString())
                .setSaveConsumer(newValue -> translate.world_nether = newValue)
                .build());
        langCategory.addEntry(entryBuilder.startStringDropdownMenu(new LiteralText("world_end"), translate.world_end)
                .setDefaultValue(new TranslatableText("status.simplystatus.world_end").getString())
                .setSaveConsumer(newValue -> translate.world_end = newValue)
                .build());

        Screen screen = builder.build();

        return screen;
    }
}