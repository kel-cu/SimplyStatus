package ru.kelcuprum.simplystatus.config.gui;

import dev.isxander.yacl3.api.YetAnotherConfigLib;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.config.ModConfig;
import ru.kelcuprum.simplystatus.config.gui.category.*;
import ru.kelcuprum.simplystatus.localization.Localization;

public class YACLConfigScreen {
    public static Screen buildScreen (Screen currentScreen) {
        Minecraft CLIENT = Minecraft.getInstance();
        YetAnotherConfigLib.Builder screen = YetAnotherConfigLib.createBuilder()
                .title(Localization.getText("simplystatus.name"))
                .save(YACLConfigScreen::save);
        screen.category(new MainConfigs().getCategory());
        if(!CLIENT.isSingleplayer() && CLIENT.getCurrentServer() != null) {
            screen.category(new ServerConfigs().getCategory());
        }
        screen.category(new AddonsConfigs().getCategory());
        screen.category(new LocalizationConfigs().getCategory());
        if(SimplyStatus.userConfig.getBoolean("USE_CUSTOM_ASSETS", false) || SimplyStatus.userConfig.getBoolean("USE_CUSTOM_APP_ID", false)) screen.category(new AssetsConfigs().getCategory());
        screen.category(new ReplayConfigs().getCategory());
        screen.category(new MusicConfigs().getCategory());
        screen.category(new VoiceConfigs().getCategory());
        return screen.build().generateScreen(currentScreen);
    }
    private static void save(){
        Minecraft CLIENT = Minecraft.getInstance();
        SimplyStatus.log("Save user configs...");
        SimplyStatus.ASSETS.saveUserAssets();
        if(SimplyStatus.userConfig.getBoolean("USE_CUSTOM_APP_ID", false) && !SimplyStatus.customID.equals(SimplyStatus.userConfig.getString("CUSTOM_APP_ID", ModConfig.baseID))){
            SimplyStatus.useCustomID = true;
            String APPLICATION_ID = SimplyStatus.userConfig.getString("CUSTOM_APP_ID", ModConfig.baseID);
            if(APPLICATION_ID.isBlank()){
                APPLICATION_ID = ModConfig.baseID;
                SimplyStatus.userConfig.setString("CUSTOM_APP_ID", APPLICATION_ID);
            }
            if(!SimplyStatus.customID.equals(APPLICATION_ID)) {
                SimplyStatus.customID = APPLICATION_ID;
                SimplyStatus.LIB.Discord_Shutdown();
                SimplyStatus.LIB.Discord_Initialize(APPLICATION_ID, SimplyStatus.HANDLERS, SimplyStatus.AUTO_REGISTER, SimplyStatus.STEAM_ID);
                SimplyStatus.lastPresence = null;
            }
        } else if((SimplyStatus.useAnotherID != SimplyStatus.userConfig.getBoolean("USE_ANOTHER_ID", false)) || (SimplyStatus.useCustomID != SimplyStatus.userConfig.getBoolean("USE_CUSTOM_APP_ID", false))){
            SimplyStatus.useAnotherID = SimplyStatus.userConfig.getBoolean("USE_ANOTHER_ID", false);
            SimplyStatus.useCustomID = SimplyStatus.userConfig.getBoolean("USE_CUSTOM_APP_ID", false);
            SimplyStatus.customID = "";
            String APPLICATION_ID = SimplyStatus.userConfig.getBoolean("USE_ANOTHER_ID", false) ? ModConfig.mineID : ModConfig.baseID;

            SimplyStatus.LIB.Discord_Shutdown();
            SimplyStatus.LIB.Discord_Initialize(APPLICATION_ID, SimplyStatus.HANDLERS, SimplyStatus.AUTO_REGISTER, SimplyStatus.STEAM_ID);
            SimplyStatus.lastPresence = null;
        }
        SimplyStatus.userConfig.save();
        if(!CLIENT.isSingleplayer() && CLIENT.getCurrentServer() != null){
            SimplyStatus.log("Save server configs...");
            SimplyStatus.serverConfig.save();
        }
    }
}
