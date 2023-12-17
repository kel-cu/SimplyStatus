package ru.kelcuprum.simplystatus.config.gui;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.Screen;
import ru.kelcuprum.simplystatus.config.gui.config.ClientConfigs;

@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return this::setScreen;
    }
    public Screen setScreen(Screen currentScreen){
        return new ClientConfigs(currentScreen);
//        if(SimplyStatus.yacl){
//            return YACLConfigScreen.buildScreen(currentScreen);
//        } else {
//            return null;
//        }
    }

}
