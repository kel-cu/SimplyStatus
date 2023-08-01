package ru.simplykel.simplystatus;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import ru.simplykel.simplystatus.config.ModConfig;

import java.text.DecimalFormat;

public class Main implements ModInitializer {
    public static boolean isLoadingConfigs = true;
    public static boolean isDevBuild = false;
    public static boolean isBetaBuild = false;
    public static boolean useAnotherID = false;
    public static boolean useCustomID = false;
    public static String customID = "";
    public static String prefixLog = "[SimplyStatus] ";
    public static DecimalFormat DF = new DecimalFormat("#.#");
    public static boolean clothConfig = FabricLoader.getInstance().getModContainer("cloth-config").isPresent();
    public static Boolean replayMod = FabricLoader.getInstance().getModContainer("replaymod").isPresent();
    public static Boolean musicPlayer = FabricLoader.getInstance().getModContainer("musicplayer").isPresent();
    public static Boolean kelUtils = FabricLoader.getInstance().getModContainer("kelutils").isPresent();
    public static Boolean svc = FabricLoader.getInstance().getModContainer("voicechat").isPresent();
    public static Boolean yetAnotherConfigLibV3 = FabricLoader.getInstance().getModContainer("yet_another_config_lib_v3").isPresent();
    public static Boolean plasmo = FabricLoader.getInstance().getModContainer("plasmovoice").isPresent();
    public static Boolean fastload = FabricLoader.getInstance().getModContainer("fastload").isPresent();
    public static Boolean isVoiceModsEnable = (svc || plasmo);
    @Override
    public void onInitialize() {
        String version = FabricLoader.getInstance().getModContainer("simplystatus").get().getMetadata().getVersion().getFriendlyString();
        String[] versions = version.split("-");
        if(versions.length >= 2){
            if(versions[1].startsWith("dev") || versions[1].startsWith("alpha")) isDevBuild = true;
            if(versions[1].startsWith("beta") || versions[1].startsWith("pre")) isBetaBuild = true;
        };
        if(isDevBuild) {
            Client.LOG.warn(prefixLog+"Внимание!");
            Client.LOG.warn(prefixLog+"Данная версия мода не является стабильной, в случаи обнаружение ошибок обращайтесь в https://github.com/simply-kel/SimplyStatus-fabric");
            Client.LOG.warn(prefixLog+"Запущенная версия: "+version);
        } else if(isBetaBuild){
            Client.LOG.warn(prefixLog+"Внимание!");
            Client.LOG.warn(prefixLog+"Данная версия мода является для тестирование публикой, в случаи обнаружение ошибок обращайтесь в https://github.com/simply-kel/SimplyStatus-fabric");
        }
        try {
            new ModConfig();
        } catch (Exception e) {
            isLoadingConfigs = false;
            e.printStackTrace();
        }
    }
}
