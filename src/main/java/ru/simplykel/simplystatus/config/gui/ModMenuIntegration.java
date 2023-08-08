package ru.simplykel.simplystatus.config.gui;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import ru.simplykel.simplystatus.Main;
import ru.simplykel.simplystatus.config.UserConfig;
import ru.simplykel.simplystatus.config.gui.cloth.ConfigScreen;
import ru.simplykel.simplystatus.config.gui.yacl.YACLConfigScreen;

@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return this::setScreen;
    }
    public Screen setScreen(Screen currentScreen){
        if(Main.clothConfig && !(UserConfig.USE_YACL_CONFIGURATION && Main.yetAnotherConfigLibV3)) {
            return ConfigScreen.buildScreen(currentScreen);
        } else if(Main.yetAnotherConfigLibV3){
            return YACLConfigScreen.buildScreen(currentScreen);
        } else {
            return null;
        }
    }

}
