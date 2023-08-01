package ru.simplykel.simplystatus.config.gui.yacl;

import dev.isxander.yacl3.api.YetAnotherConfigLib;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import ru.simplykel.simplystatus.Client;
import ru.simplykel.simplystatus.Main;
import ru.simplykel.simplystatus.config.Localization;
import ru.simplykel.simplystatus.config.ModConfig;
import ru.simplykel.simplystatus.config.ServerConfig;
import ru.simplykel.simplystatus.config.UserConfig;
import ru.simplykel.simplystatus.config.gui.yacl.category.*;

public class YACLConfigScreen {
    public static Screen buildScreen (Screen currentScreen) {
        MinecraftClient CLIENT = MinecraftClient.getInstance();
        YetAnotherConfigLib.Builder screen = YetAnotherConfigLib.createBuilder()
                .title(Localization.getText("simplystatus.name"))
                .save(YACLConfigScreen::save);
        //        new MainConfigs().getCategory(builder);
        //        new LocalizationsConfig().getCategory(builder);
        //        if(UserConfig.USE_CUSTOM_ASSETS || UserConfig.USE_CUSTOM_APP_ID) new AssetsConfigs().getCategory(builder);
        //        new AddonsConfigs().getCategory(builder);
        //        if(!CLIENT.isInSingleplayer() && CLIENT.getCurrentServerEntry() != null) {
        //            new ServerConfigs().getCategory(builder);
        //        }
        //        if(Main.replayMod && UserConfig.VIEW_REPLAY_MOD){
        //            new ReplayModConfigs().getCategory(builder);
        //        }
        //        if(Main.isVoiceModsEnable && UserConfig.VIEW_VOICE_SPEAK){
        //            new VoiceConfigs().getCategory(builder);
        //        }
        //        if((Main.musicPlayer || Main.kelUtils) && UserConfig.VIEW_MUSIC_LISTENER){
        //            new MusicPlayerConfigs().getCategory(builder);
        //        }
        //        if(Main.isBetaBuild || Main.isDevBuild){
        //            new DevConfigs().getCategory(builder);
        //        }
        screen.category(new MainConfigs().getCategory());
        if(!CLIENT.isInSingleplayer() && CLIENT.getCurrentServerEntry() != null) {
            screen.category(new ServerConfigs().getCategory());
        }
        screen.category(new AddonsConfigs().getCategory());
        screen.category(new LocalizationConfigs().getCategory());
        if(UserConfig.USE_CUSTOM_ASSETS || UserConfig.USE_CUSTOM_APP_ID) screen.category(new AssetsConfigs().getCategory());
        screen.category(new ReplayConfigs().getCategory());
        screen.category(new MusicConfigs().getCategory());
        screen.category(new VoiceConfigs().getCategory());
        return screen.build().generateScreen(currentScreen);
    }
    private static void save(){
        MinecraftClient CLIENT = MinecraftClient.getInstance();
        Client.LOG.info(Main.prefixLog+"Save user configs...");
        Client.ASSETS.saveUserAssets();
        if(UserConfig.USE_CUSTOM_APP_ID && !Main.customID.equals(UserConfig.CUSTOM_APP_ID)){
            Main.useCustomID = true;
            String APPLICATION_ID = UserConfig.CUSTOM_APP_ID;
            if(APPLICATION_ID.isBlank()){
                APPLICATION_ID = ModConfig.baseID;
                UserConfig.CUSTOM_APP_ID = APPLICATION_ID;
            }
            if(!Main.customID.equals(APPLICATION_ID)) {
                Main.customID = APPLICATION_ID;
                Client.LIB.Discord_Shutdown();
                Client.LIB.Discord_Initialize(APPLICATION_ID, Client.HANDLERS, Client.AUTO_REGISTER, Client.STEAM_ID);
            }
        } else if((Main.useAnotherID != UserConfig.USE_ANOTHER_ID) || (Main.useCustomID != UserConfig.USE_CUSTOM_APP_ID)){
            Main.useAnotherID = UserConfig.USE_ANOTHER_ID;
            Main.useCustomID = UserConfig.USE_CUSTOM_APP_ID;
            Main.customID = "";
            String APPLICATION_ID = "";
            if(UserConfig.USE_ANOTHER_ID) APPLICATION_ID = ModConfig.mineID;
            else APPLICATION_ID = ModConfig.baseID;

            Client.LIB.Discord_Shutdown();
            Client.LIB.Discord_Initialize(APPLICATION_ID, Client.HANDLERS, Client.AUTO_REGISTER, Client.STEAM_ID);
        }
        UserConfig.save();
        if(!CLIENT.isInSingleplayer() && CLIENT.getCurrentServerEntry() != null){
            Client.LOG.info(Main.prefixLog+"Save server configs...");
            ServerConfig.save();
        }
    }
}
