package ru.simplykel.simplystatus;

import info.u_team.music_player.MusicPlayerMod;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import ru.simplykel.simplystatus.config.ModConfig;

import java.text.DecimalFormat;

public class Main implements ModInitializer {
    public static boolean isLoadingConfigs = true;
    public static boolean isDevBuild = true;
    public static boolean configWarn = true;
    public static boolean useAnotherID = false;
    public static String prefixLog = "[SimplyStatus] ";
    public static DecimalFormat DF = new DecimalFormat("#.#");
    public static boolean clothConfig = FabricLoader.getInstance().getModContainer("cloth-config").isPresent();
    public static Boolean replayMod = FabricLoader.getInstance().getModContainer("replaymod").isPresent();
    public static Boolean musicPlayer = FabricLoader.getInstance().getModContainer("musicplayer").isPresent();
    public static Boolean svc = FabricLoader.getInstance().getModContainer("voicechat").isPresent();
    public static Boolean plasmo = FabricLoader.getInstance().getModContainer("plasmo_voice").isPresent();
    public static Boolean isVoiceModsEnable = (svc || plasmo);
    @Override
    public void onInitialize() {
        if(isDevBuild) {
            Client.LOG.warn(prefixLog+"Внимание!");
            Client.LOG.warn(prefixLog+"Данная версия мода не является стабильной, в случаи обнаружение ошибок обращайтесь в https://github.com/simply-kel/SimplyStatus-fabric");
        }
        if(configWarn) Client.LOG.warn(prefixLog+"*С обновления 1.6.x на 1.7, у вас сбросятся конфигурации мода. Просьба обратить внимание на это, вам придётся самому настраивать. Может вы сделаете лучше, чем прежде -w-*");
        try {
            new ModConfig();
        } catch (Exception e) {
            isLoadingConfigs = false;
            e.printStackTrace();
        }
    }
}
