package ru.simplykel.simplystatus.config.gui;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import ru.simplykel.simplystatus.Main;
import ru.simplykel.simplystatus.config.UserConfig;
import ru.simplykel.simplystatus.config.gui.cloth.ConfigScreen;
import ru.simplykel.simplystatus.config.gui.yacl.YACLConfigScreen;

@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if(Main.clothConfig && !(UserConfig.USE_YACL_CONFIGURATION && Main.yetAnotherConfigLibV3)) {
            return ConfigScreen::buildScreen;
        } else if(Main.yetAnotherConfigLibV3){
            return YACLConfigScreen::buildScreen;
        } else {
            return null;
        }
    }

}
