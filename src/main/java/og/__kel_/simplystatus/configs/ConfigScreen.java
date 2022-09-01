package og.__kel_.simplystatus.configs;

import me.shedaniel.clothconfig2.api.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableTextContent;
//LiteralText
import og.__kel_.simplystatus.client.HotKeys;
import og.__kel_.simplystatus.client.MainClient;
import og.__kel_.simplystatus.Translate;


public class ConfigScreen {
    public static ConfigCategory mainCategory;
    public static ConfigCategory servCategory;
    public static ConfigCategory localizationCategory;
    public static ConfigCategory addonsPresencesCategory;
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
                    MainClient.log.info("Saves configurations!");
                    Config config = new Config();
                    config.save();
                    if(HotKeys.lastTitle != HotKeys.changeStatusNameInMinecraft){
                        HotKeys.lastTitle = HotKeys.changeStatusNameInMinecraft;
                        if(HotKeys.changeStatusNameInMinecraft){
                            MainClient.lib.Discord_ClearPresence();
                            MainClient.lib.Discord_Shutdown();
                            MainClient.applicationId = "1004398909810016276";
                        } else {
                            MainClient.lib.Discord_ClearPresence();
                            MainClient.lib.Discord_Shutdown();
                            MainClient.applicationId = "903288390072557648";
                        }
                    }
                    MainClient.lib.Discord_Initialize(MainClient.applicationId, MainClient.handlers, true, "");
                    translate.save();
                    if(mc.world != null){
                        if(!mc.isInSingleplayer() && mc.getCurrentServerEntry() != null){
                            MainClient.log.info(mc.getCurrentServerEntry().address + " saves configurations!");
                            config.save(mc.getCurrentServerEntry().address);
                        }
                    }
                });
        // mainCategory - Это экран
        mainCategory = builder.getOrCreateCategory(MutableText.of(new TranslatableTextContent("config.simplystatus.client")));
        // entryBuilder - это билдер значения в точке
        entryBuilder = builder.entryBuilder();
        // Основные настройки
        if(!MainClient.discordConnected){
            mainCategory.addEntry(entryBuilder.startTextDescription(MutableText.of(new TranslatableTextContent("config.simplystatus.notConnected"))).build());
            if(System.getProperty("os.name").toLowerCase().startsWith("linux")){
                mainCategory.addEntry(entryBuilder.startTextDescription(MutableText.of(new TranslatableTextContent("config.simplystatus.notConnectedLinux"))).build());
            }
        }
        mainCategory.addEntry(entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.changeStatusNameInMinecraft")), HotKeys.changeStatusNameInMinecraft)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HotKeys.changeStatusNameInMinecraft = newValue)
                .build());
        mainCategory.addEntry(entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.ViewRPC")), HotKeys.viewRPC)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> HotKeys.viewRPC = newValue)
                .build());
        mainCategory.addEntry(entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.ViewUsername")), HotKeys.viewUsername)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> HotKeys.viewUsername = newValue)
                .build());
        mainCategory.addEntry(entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.viewStatic")), HotKeys.viewStatic)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> HotKeys.viewStatic = newValue)
                .build());
        mainCategory.addEntry(entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.OffHand")), HotKeys.viewOffHand)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HotKeys.viewOffHand = newValue)
                .build());
        mainCategory.addEntry(entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.bedrock")), HotKeys.bedrock)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HotKeys.bedrock = newValue)
                .build());
        mainCategory.addEntry(entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.showTime")), HotKeys.showTime)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> HotKeys.showTime = newValue)
                .build());
        mainCategory.addEntry(entryBuilder.startTextDescription(MutableText.of(new TranslatableTextContent("simplystatus.special.thanks"))).build());
        // Сервер
        if(mc.world != null){
            if(!mc.isInSingleplayer() && mc.getCurrentServerEntry() != null){
                servCategory = builder.getOrCreateCategory(MutableText.of(new TranslatableTextContent("config.simplystatus.thisServer")));
                servCategory.addEntry(entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.viewIP")), HotKeys.viewIPAddress)
                        .setDefaultValue(false)
                        .setSaveConsumer(newValue -> HotKeys.viewIPAddress = newValue)
                        .build());
                servCategory.addEntry(entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.viewNameServer")), HotKeys.viewName)
                        .setDefaultValue(true)
                        .setSaveConsumer(newValue -> HotKeys.viewName = newValue)
                        .build());
                servCategory.addEntry(entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.enableCustomName")), HotKeys.customNameEnable)
                        .setDefaultValue(false)
                        .setSaveConsumer(newValue -> HotKeys.customNameEnable = newValue)
                        .build());
                servCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.CustomName")), HotKeys.customName)
                        .setDefaultValue("")
                        .setSaveConsumer(newValue -> HotKeys.customName = newValue)
                        .build());
            }
        }
        //
        // Дополнительные статусы
        //
        addonsPresencesCategory = builder.getOrCreateCategory(MutableText.of(new TranslatableTextContent("config.simplystatus.addonsPresences")));

        //addonsPresencesCategory.addEntry(entryBuilder.startTextDescription(MutableText.of(new TranslatableTextContent("config.simplystatus.addonsPresences.warning"))).build());
        if(MainClient.musicPlayer){
            addonsPresencesCategory.addEntry(entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.addonsPresences.music")), HotKeys.viewMusicListening)
                    .setDefaultValue(false)
                    .setSaveConsumer(newValue -> HotKeys.viewMusicListening = newValue).build());
        }
        if(MainClient.plasmoVoice || MainClient.svc){
            addonsPresencesCategory.addEntry(entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.addonsPresences.voice")), HotKeys.viewVoice)
                    .setDefaultValue(false)
                    .setSaveConsumer(newValue -> HotKeys.viewVoice = newValue)
                    .build());
        }
        if(MainClient.replayMod){
            addonsPresencesCategory.addEntry(entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.addonsPresences.replay")), HotKeys.viewReplayMod)
                    .setDefaultValue(false)
                    .setSaveConsumer(newValue -> HotKeys.viewReplayMod = newValue)
                    .build());
        }
        addonsPresencesCategory.addEntry(entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.addonsPresences.cringe")), HotKeys.cringeIcons)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> HotKeys.cringeIcons = newValue)
                .build());
        //
        // Локализация
        //
        localizationCategory = builder.getOrCreateCategory(MutableText.of(new TranslatableTextContent("config.simplystatus.translate")));
        // Главное меню
        localizationCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.translate.mainMenu")), translate.mainMenu)
                .setDefaultValue(MutableText.of(new TranslatableTextContent("status.simplystatus.text_MainMenu")).getString())
                .setSaveConsumer(newValue -> translate.mainMenu = newValue).build());
        // Главное меню
        localizationCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.translate.mainMenu.state")), translate.mainMenu_state)
                .setDefaultValue(MutableText.of(new TranslatableTextContent("status.simplystatus.text_MainMenu.state")).getString())
                .setSaveConsumer(newValue -> translate.mainMenu_state = newValue).build());
        // Информация
        localizationCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.translate.information")), translate.information)
                .setDefaultValue(MutableText.of(new TranslatableTextContent("status.simplystatus.text_information")).getString())
                .setSaveConsumer(newValue -> translate.information = newValue).build());
        // Неизвестный сервер
        localizationCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.translate.unknownServer")), translate.unknownServer)
                .setDefaultValue(MutableText.of(new TranslatableTextContent("status.simplystatus.text_UnknownServer")).getString())
                .setSaveConsumer(newValue -> translate.unknownServer = newValue).build());
        // Воздух
        localizationCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.translate.air")), translate.air)
                .setDefaultValue(MutableText.of(new TranslatableTextContent("status.simplystatus.text_Air")).getString())
                .setSaveConsumer(newValue -> translate.air = newValue).build());
        // Скрытый IP адрес
        localizationCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.translate.hideIP")), translate.hideIP)
                .setDefaultValue(MutableText.of(new TranslatableTextContent("status.simplystatus.text_HideIP")).getString())
                .setSaveConsumer(newValue -> translate.hideIP = newValue).build());
        // Держит предмет
        localizationCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.translate.item")), translate.item)
                .setDefaultValue(MutableText.of(new TranslatableTextContent("status.simplystatus.text_Item")).getString())
                .setSaveConsumer(newValue -> translate.item = newValue).build());
        // ОдинОчка
        localizationCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.translate.singlePlayer")), translate.singlePlayer)
                .setDefaultValue(MutableText.of(new TranslatableTextContent("status.simplystatus.text_isInSingleplayer")).getString())
                .setSaveConsumer(newValue -> translate.singlePlayer = newValue).build());
        // Возможно хороший игрок
        localizationCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.translate.maybeGoodPlayer")), translate.maybeGoodPlayer)
                .setDefaultValue(MutableText.of(new TranslatableTextContent("status.simplystatus.text_goodPlayer")).getString())
                .setSaveConsumer(newValue -> translate.maybeGoodPlayer = newValue).build());
        // Сон? Что такое сон?
        localizationCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.translate.sleep")), translate.sleep)
                .setDefaultValue(MutableText.of(new TranslatableTextContent("status.simplystatus.text_isSleep")).getString())
                .setSaveConsumer(newValue -> translate.sleep = newValue).build());
        // Мыш (кродёться)
        localizationCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.translate.sneaking")), translate.sneaking)
                .setDefaultValue(MutableText.of(new TranslatableTextContent("status.simplystatus.text_isSneaking")).getString())
                .setSaveConsumer(newValue -> translate.sneaking = newValue).build());
        //
        localizationCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.translate.voice")), translate.voice)
                .setDefaultValue(MutableText.of(new TranslatableTextContent("status.simplystatus.voice")).getString())
                .setSaveConsumer(newValue -> translate.voice = newValue).build());

        localizationCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.translate.voice_one")), translate.voice_one)
                .setDefaultValue(MutableText.of(new TranslatableTextContent("status.simplystatus.voice_one")).getString())
                .setSaveConsumer(newValue -> translate.voice_one = newValue).build());

        localizationCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.translate.voice_two")), translate.voice_two)
                .setDefaultValue(MutableText.of(new TranslatableTextContent("status.simplystatus.voice_two")).getString())
                .setSaveConsumer(newValue -> translate.voice_two = newValue).build());
        // Стак
        localizationCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.translate.stacks")), translate.stacks)
                .setDefaultValue(MutableText.of(new TranslatableTextContent("status.simplystatus.stacks")).getString())
                .setSaveConsumer(newValue -> translate.stacks = newValue).build());
        // Предметы
        localizationCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.translate.pieces")), translate.pieces)
                .setDefaultValue(MutableText.of(new TranslatableTextContent("status.simplystatus.pieces")).getString())
                .setSaveConsumer(newValue -> translate.pieces = newValue).build());
        // Предмета (RU)
        if(translate.getSelectedLang().equals("ru_ru")){
            localizationCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.translate.pieces.ru")), translate.pieces_ru_2_4)
                    .setDefaultValue(MutableText.of(new TranslatableTextContent("status.simplystatus.pieces-ru")).getString())
                    .setSaveConsumer(newValue -> translate.pieces_ru_2_4 = newValue).build());
        }
        // Сообщение смерти
        localizationCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.translate.deathOne")), translate.deathOne)
                .setDefaultValue(MutableText.of(new TranslatableTextContent("status.simplystatus.text_DeathOne")).getString())
                .setSaveConsumer(newValue -> translate.deathOne = newValue).build());
        localizationCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.translate.deathTwo")), translate.deathTwo)
                .setDefaultValue(MutableText.of(new TranslatableTextContent("status.simplystatus.text_DeathTwo")).getString())
                .setSaveConsumer(newValue -> translate.deathTwo = newValue).build());
        localizationCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.translate.deathThree")), translate.deathThree)
                .setDefaultValue(MutableText.of(new TranslatableTextContent("status.simplystatus.text_DeathThree")).getString())
                .setSaveConsumer(newValue -> translate.deathThree = newValue).build());
        // Сутки
        localizationCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.translate.day")), translate.day)
                .setDefaultValue(MutableText.of(new TranslatableTextContent("status.simplystatus.text_day")).getString())
                .setSaveConsumer(newValue -> translate.day = newValue).build());
        localizationCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.translate.night")), translate.night)
                .setDefaultValue(MutableText.of(new TranslatableTextContent("status.simplystatus.text_night")).getString())
                .setSaveConsumer(newValue -> translate.night = newValue).build());
        localizationCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.translate.morning")), translate.morning)
                .setDefaultValue(MutableText.of(new TranslatableTextContent("status.simplystatus.text_morning")).getString())
                .setSaveConsumer(newValue -> translate.morning = newValue).build());
        localizationCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.translate.evening")), translate.evening)
                .setDefaultValue(MutableText.of(new TranslatableTextContent("status.simplystatus.text_evening")).getString())
                .setSaveConsumer(newValue -> translate.evening = newValue).build());
        // Миры
        localizationCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.translate.overworld")), translate.overworld)
                .setDefaultValue(MutableText.of(new TranslatableTextContent("status.simplystatus.world_overworld")).getString())
                .setSaveConsumer(newValue -> translate.overworld = newValue).build());
        localizationCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.translate.nether")), translate.nether)
                .setDefaultValue(MutableText.of(new TranslatableTextContent("status.simplystatus.world_nether")).getString())
                .setSaveConsumer(newValue -> translate.nether = newValue).build());
        localizationCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.translate.end")), translate.end)
                .setDefaultValue(MutableText.of(new TranslatableTextContent("status.simplystatus.world_end")).getString())
                .setSaveConsumer(newValue -> translate.end = newValue).build());
        // This is fine

        localizationCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.translate.onFire")), translate.onFire)
                .setDefaultValue(MutableText.of(new TranslatableTextContent("status.simplystatus.onFire")).getString())
                .setTooltip(MutableText.of(new TranslatableTextContent("config.simplystatus.translate.onFire.tooltip")))
                .setSaveConsumer(newValue -> translate.onFire = newValue).build());
        Screen screen = builder.build();

        return screen;
    }
}