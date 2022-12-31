package og.__kel_.simplystatus.configs;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import og.__kel_.simplystatus.client.HotKeys;
import og.__kel_.simplystatus.client.MainClient;

@Environment(EnvType.CLIENT)
public class ModMenuIntegration implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if(MainClient.clothConfig) {
            return ConfigScreen::buildScreen;
        } else {
            return null;
        }
    }

}
