package og.__kel_.simplystatus.configs;

import me.shedaniel.clothconfig2.api.*;
import net.minecraft.SharedConstants;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
//LiteralText
import og.__kel_.simplystatus.Main;
import og.__kel_.simplystatus.api.entity.addonEntity;
import og.__kel_.simplystatus.client.HotKeys;
import og.__kel_.simplystatus.client.MainClient;
import og.__kel_.simplystatus.Translate;
import org.jetbrains.annotations.NotNull;


public class ConfigScreen {
    public static ConfigCategory mainCategory;
    public static ConfigCategory servCategory;
    public static ConfigCategory assetsCategory;
    public static ConfigCategory localizationCategory;
    public static ConfigCategory addonsPresencesCategory;
    public static ConfigCategory devCategory;
    public static ConfigEntryBuilder entryBuilder;
    public static Translate translate = new Translate();
    public static AssetsConfig assetsConfig = new AssetsConfig();
    public static Config config = new Config();
    public static Screen buildScreen (Screen currentScreen) {
        MinecraftClient mc = MinecraftClient.getInstance();
        translate.selectedLang();

        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(currentScreen)
                .setTitle(MutableText.of(new TranslatableTextContent("category.simplystatus.name")))
                .setTransparentBackground(true)
                .setSavingRunnable(Config::saveGUI);
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
        mainCategory.addEntry(entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.changeStatusNameInMinecraft")), Main.changeStatusNameInMinecraft)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> Main.changeStatusNameInMinecraft = newValue)
                .build());
        mainCategory.addEntry(entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.ViewRPC")), Main.viewRPC)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> Main.viewRPC = newValue)
                .build());
        mainCategory.addEntry(entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.ViewUsername")), Main.viewUsername)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> Main.viewUsername = newValue)
                .build());
        mainCategory.addEntry(entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.viewStatic")), Main.viewStatic)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> Main.viewStatic = newValue)
                .build());
        mainCategory.addEntry(entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.OffHand")), Main.viewOffHand)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> Main.viewOffHand = newValue)
                .build());
        mainCategory.addEntry(entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.bedrock")), Main.bedrock)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> Main.bedrock = newValue)
                .build());
        mainCategory.addEntry(entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.showTime")), Main.showTime)
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> Main.showTime = newValue)
                .build());
        // mainCategory.addEntry(entryBuilder.startTextDescription(MutableText.of(new TranslatableTextContent("simplystatus.special.thanks"))).build());
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
                if(Main.addonsServers.get(mc.getCurrentServerEntry().address) != null){
                    addonEntity addon = Main.addonsServers.get(mc.getCurrentServerEntry().address);
                    servCategory.addEntry(entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.useAddon")), HotKeys.useAddon)
                            .setDefaultValue(true)
                            .setTooltip(MutableText.of(new LiteralTextContent(addon.getName()+"\n"+addon.getDescription())))
                            .setSaveConsumer(newValue -> HotKeys.useAddon = newValue)
                            .build());
                }
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

        if(MainClient.musicPlayer){
            addonsPresencesCategory.addEntry(entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.addonsPresences.music")), Main.viewMusicListening)
                    .setTooltip(MutableText.of(new TranslatableTextContent("config.simplystatus.addonsPresences.music.tooltip")))
                    .setDefaultValue(false)
                    .setSaveConsumer(newValue -> Main.viewMusicListening = newValue)
                    .build());
        }
        if(MainClient.plasmoVoice || MainClient.svc){
            addonsPresencesCategory.addEntry(entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.addonsPresences.voice")), Main.viewVoice)
                    .setTooltip(MutableText.of(new TranslatableTextContent("config.simplystatus.addonsPresences.voice.tooltip")))
                    .setDefaultValue(false)
                    .setSaveConsumer(newValue -> Main.viewVoice = newValue)
                    .build());
        }
        if(MainClient.replayMod){
            addonsPresencesCategory.addEntry(entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.addonsPresences.replay")), Main.viewReplayMod)
                    .setTooltip(MutableText.of(new TranslatableTextContent("config.simplystatus.addonsPresences.replay.tooltip")))
                    .setDefaultValue(false)
                    .setSaveConsumer(newValue -> Main.viewReplayMod = newValue)
                    .build());
        }
        addonsPresencesCategory.addEntry(
                entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.addonsPresences.avatar")), Main.showAvatar)
                        .setDefaultValue(false)
                        .setSaveConsumer(newValue -> Main.showAvatar = newValue)
                        .build());
        addonsPresencesCategory.addEntry(
                entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.addonsPresences.customAssets")), Main.useCustomAssets)
                        .setDefaultValue(false)
                        .setTooltip(MutableText.of(new TranslatableTextContent("config.simplystatus.addonsPresences.customAssets.tooltip")))
                        .setSaveConsumer(newValue -> Main.useCustomAssets = newValue)
                        .build());
        addonsPresencesCategory.addEntry(
                entryBuilder.startBooleanToggle(MutableText.of(new TranslatableTextContent("config.simplystatus.addonsPresences.cringe")), Main.cringeIcons)
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> Main.cringeIcons = newValue)
                .build());
        //
        // Иконки
        //
        assetsCategory = builder.getOrCreateCategory(MutableText.of(new TranslatableTextContent("config.simplystatus.assets")));
        assetsCategory.addEntry(entryBuilder.startTextDescription(MutableText.of(new TranslatableTextContent("config.simplystatus.addonsPresences.customAssets.tooltip"))).build());
        assetsCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.assets.logo")), assetsConfig.logo)
                .setDefaultValue("logo")
                .setSaveConsumer(newValue -> assetsConfig.logo = newValue).build());
        assetsCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.assets.Unknown")), assetsConfig.Unknown)
                .setDefaultValue("unknown_world")
                .setSaveConsumer(newValue -> assetsConfig.Unknown = newValue).build());
        assetsCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.assets.replay")), assetsConfig.replay)
                .setDefaultValue("replaymod_logo")
                .setSaveConsumer(newValue -> assetsConfig.replay = newValue).build());
        assetsCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.assets.music")), assetsConfig.music)
                .setDefaultValue("music")
                .setSaveConsumer(newValue -> assetsConfig.music = newValue).build());
        assetsCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.assets.day")), assetsConfig.day)
                .setDefaultValue("day_update_2")
                .setSaveConsumer(newValue -> assetsConfig.day = newValue).build());
        assetsCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.assets.night")), assetsConfig.night)
                .setDefaultValue("nigth_update_2")
                .setSaveConsumer(newValue -> assetsConfig.night = newValue).build());
        assetsCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.assets.morning")), assetsConfig.morning)
                .setDefaultValue("morning_update_2")
                .setSaveConsumer(newValue -> assetsConfig.morning = newValue).build());
        assetsCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.assets.evening")), assetsConfig.evening)
                .setDefaultValue("evening_update_2")
                .setSaveConsumer(newValue -> assetsConfig.evening = newValue).build());
        assetsCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.assets.overworld")), assetsConfig.overworld)
                .setDefaultValue("overworld")
                .setSaveConsumer(newValue -> assetsConfig.overworld = newValue).build());
        assetsCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.assets.nether")), assetsConfig.nether)
                .setDefaultValue("nether")
                .setSaveConsumer(newValue -> assetsConfig.nether = newValue).build());
        assetsCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.assets.end")), assetsConfig.end)
                .setDefaultValue("end")
                .setSaveConsumer(newValue -> assetsConfig.end = newValue).build());

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
        // Главное меню [State/Music]
        if(MainClient.musicPlayer && Main.viewMusicListening){
            localizationCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.translate.mainMenu.state.music")), translate.mainMenu_state_music)
                    .setDefaultValue(MutableText.of(new TranslatableTextContent("status.simplystatus.text_MainMenu.state.music")).getString())
                    .setSaveConsumer(newValue -> translate.mainMenu_state_music = newValue)
                    .build());
        }
        // Подключение к серверу
        localizationCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.translate.connect")), translate.connect)
                .setDefaultValue(MutableText.of(new TranslatableTextContent("status.simplystatus.connect")).getString())
                .setSaveConsumer(newValue -> translate.connect = newValue).build());
        // Отключен от сервера
        localizationCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.translate.disconnect")), translate.disconnect)
                .setDefaultValue(MutableText.of(new TranslatableTextContent("status.simplystatus.disconnect")).getString())
                .setSaveConsumer(newValue -> translate.disconnect = newValue).build());
        // Загрузка мира
        localizationCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.translate.loadWorld")), translate.worldLoading)
                .setDefaultValue(MutableText.of(new TranslatableTextContent("status.simplystatus.loadWorld")).getString())
                .setSaveConsumer(newValue -> translate.worldLoading = newValue).build());
        // Информация
        localizationCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.translate.information")), translate.information)
                .setDefaultValue(MutableText.of(new TranslatableTextContent("status.simplystatus.text_information")).getString())
                .setSaveConsumer(newValue -> translate.information = newValue).build());
        // Статистика
        localizationCategory.addEntry(entryBuilder.startStrField(MutableText.of(new TranslatableTextContent("config.simplystatus.translate.stats")), translate.stats)
                .setDefaultValue(MutableText.of(new TranslatableTextContent("status.simplystatus.stats")).getString())
                .setSaveConsumer(newValue -> translate.stats = newValue).build());
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
        if(Main.isDevBuild){
            devCategory = builder.getOrCreateCategory(MutableText.of(new TranslatableTextContent("config.simplystatus.dev")));
            devCategory.addEntry(entryBuilder.startTextDescription(MutableText.of(new LiteralTextContent("# §7Software information"))).build());
            devCategory.addEntry(entryBuilder.startTextDescription(MutableText.of(new LiteralTextContent("System: "+System.getProperty("os.name")))).build());
            devCategory.addEntry(entryBuilder.startTextDescription(MutableText.of(new LiteralTextContent("System Version: "+System.getProperty("os.version")))).build());
            devCategory.addEntry(entryBuilder.startTextDescription(MutableText.of(new LiteralTextContent("System Arch.: "+System.getProperty("os.arch")))).build());
            devCategory.addEntry(entryBuilder.startTextDescription(MutableText.of(new LiteralTextContent("Java: "+System.getProperty("java.version")))).build());
            devCategory.addEntry(entryBuilder.startTextDescription(MutableText.of(new LiteralTextContent("Java Vendor: "+System.getProperty("java.vendor")+" "+System.getProperty("java.vendor.url")))).build());
            devCategory.addEntry(entryBuilder.startTextDescription(MutableText.of(new LiteralTextContent("User started game: "+System.getProperty("user.name")))).build());
            devCategory.addEntry(entryBuilder.startTextDescription(MutableText.of(new LiteralTextContent("# §7Minecraft information"))).build());
            devCategory.addEntry(entryBuilder.startTextDescription(MutableText.of(new LiteralTextContent("Version: "+ SharedConstants.getGameVersion().getName() +
                    " (" + mc.getGameVersion() + "/" + ClientBrandRetriever.getClientModName() + ("release".equalsIgnoreCase(mc.getVersionType()) ? "" : "/" + mc.getVersionType()) + ")"))).build());
            devCategory.addEntry(entryBuilder.startTextDescription(MutableText.of(new LiteralTextContent("# §7Minecraft session information"))).build());
            devCategory.addEntry(entryBuilder.startTextDescription(MutableText.of(new LiteralTextContent("Username: "+mc.getSession().getUsername()))).build());
            devCategory.addEntry(entryBuilder.startTextDescription(MutableText.of(new LiteralTextContent("UUID: "+mc.getSession().getUuid()))).build());
            devCategory.addEntry(entryBuilder.startTextDescription(MutableText.of(new LiteralTextContent("Type: "+mc.getSession().getAccountType().getName()))).build());
            devCategory.addEntry(entryBuilder.startTextDescription(MutableText.of(new LiteralTextContent("# §7Discord information"))).build());
            devCategory.addEntry(entryBuilder.startTextDescription(MutableText.of(new LiteralTextContent("App. ID: "+MainClient.applicationId))).build());
            devCategory.addEntry(entryBuilder.startTextDescription(MutableText.of(new LiteralTextContent("Connected: "+MainClient.discordConnected))).build());
            if(MainClient.discordConnected){
                devCategory.addEntry(entryBuilder.startTextDescription(MutableText.of(new LiteralTextContent("Tag: "+MainClient.player.username+"#"+ MainClient.player.discriminator))).build());
            }


        }

        Screen screen = builder.build();

        return screen;
    }
}