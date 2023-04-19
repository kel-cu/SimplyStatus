package ru.simplykel.simplystatus.config.gui;

import com.mojang.authlib.yggdrasil.response.User;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import ru.simplykel.simplystatus.Client;
import ru.simplykel.simplystatus.Main;
import ru.simplykel.simplystatus.config.Localization;
import ru.simplykel.simplystatus.config.ModConfig;
import ru.simplykel.simplystatus.config.ServerConfig;
import ru.simplykel.simplystatus.config.UserConfig;
import ru.simplykel.simplystatus.config.gui.category.*;

public class ConfigScreen {
    public static Screen buildScreen (Screen currentScreen) {
        MinecraftClient CLIENT = MinecraftClient.getInstance();
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(currentScreen)
                .setTitle(Localization.getText("simplystatus.name"))
                .setTransparentBackground(true)
                .setSavingRunnable(ConfigScreen::save);
        new MainConfigs().getCategory(builder);
        new LocalizationsConfig().getCategory(builder);
        if(UserConfig.USE_CUSTOM_ASSETS) new AssetsConfigs().getCategory(builder);
        new AddonsConfigs().getCategory(builder);
        if(!CLIENT.isInSingleplayer() && CLIENT.getCurrentServerEntry() != null) {
            new ServerConfigs().getCategory(builder);
        }
        if(Main.replayMod && UserConfig.VIEW_REPLAY_MOD){
            new ReplayModConfigs().getCategory(builder);
        }
        if(Main.musicPlayer && UserConfig.VIEW_MUSIC_LISTENER){
            new MusicPlayerConfigs().getCategory(builder);
        }
        return builder.build();
    }
    private static void save(){
        MinecraftClient CLIENT = MinecraftClient.getInstance();
        Client.LOG.info(Main.prefixLog+"Save user configs...");
        Client.ASSETS.saveUserAssets();
        if((Main.useAnotherID != UserConfig.USE_ANOTHER_ID) && (!UserConfig.USE_CUSTOM_APP_ID)){
            Main.useAnotherID = UserConfig.USE_ANOTHER_ID;
            String APPLICATION_ID = "";
            if(UserConfig.USE_ANOTHER_ID) APPLICATION_ID = ModConfig.mineID;
            else APPLICATION_ID = ModConfig.baseID;

            Client.LIB.Discord_Shutdown();
            Client.LIB.Discord_Initialize(APPLICATION_ID, Client.HANDLERS, Client.AUTO_REGISTER, Client.STEAM_ID);
        } else if(UserConfig.USE_CUSTOM_APP_ID && !Main.customID.equals(UserConfig.CUSTOM_APP_ID)){
            String APPLICATION_ID = UserConfig.CUSTOM_APP_ID;
            Client.LIB.Discord_Shutdown();
            Client.LIB.Discord_Initialize(APPLICATION_ID, Client.HANDLERS, Client.AUTO_REGISTER, Client.STEAM_ID);

        } else {
            String APPLICATION_ID = UserConfig.CUSTOM_APP_ID;
            if(APPLICATION_ID.isBlank()){
                APPLICATION_ID = ModConfig.baseID;
                UserConfig.CUSTOM_APP_ID = APPLICATION_ID;
            }
            Client.LIB.Discord_Shutdown();;
            Client.LIB.Discord_Initialize(APPLICATION_ID, Client.HANDLERS, Client.AUTO_REGISTER, Client.STEAM_ID);
        }
        UserConfig.save();
        if(!CLIENT.isInSingleplayer() && CLIENT.getCurrentServerEntry() != null){
            Client.LOG.info(Main.prefixLog+"Save server configs...");
            ServerConfig.save();
        }
    }
}
