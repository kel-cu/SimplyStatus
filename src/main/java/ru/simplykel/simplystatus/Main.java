package ru.simplykel.simplystatus;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import ru.simplykel.simplystatus.config.ModConfig;

import java.text.DecimalFormat;

public class Main implements ModInitializer {
    public static boolean isLoadingConfigs = true;
    public static boolean isDevBuild = true;
    public static String prefixLog = "[SimplyStatus] ";
    public static DecimalFormat DF = new DecimalFormat("#.#");
    public static boolean clothConfig = FabricLoader.getInstance().getModContainer("cloth-config").isPresent();
    @Override
    public void onInitialize() {
        if(isDevBuild){
            Client.LOG.warn(prefixLog+"Внимание!");
            Client.LOG.warn(prefixLog+"Данная версия мода не является стабильной, в случаи обнаружение ошибок обращайтесь в https://github.com/simply-kel/SimplyStatus-fabric");
            Client.LOG.warn(prefixLog+"*ЕСЛИ У ВАС СБРОСИЛИСЬ КОНФИГИ ЭТО ВАША ВИНА, ЭТО БЫЛО НАПИСАНО В CHANGE-LOG!*");
        }
        try {
            new ModConfig();
        } catch (Exception e) {
            isLoadingConfigs = false;
            e.printStackTrace();
        }
    }
}
