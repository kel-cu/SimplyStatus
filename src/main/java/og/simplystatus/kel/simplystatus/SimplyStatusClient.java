package og.simplystatus.kel.simplystatus;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.LiteralText;
import org.lwjgl.glfw.GLFW;

public class SimplyStatusClient implements ClientModInitializer {

    // The KeyBinding declaration and registration are commonly executed here statically
    public static boolean ViewIP = false;
    public static boolean ViewStatic = true;
    @Override
    public void onInitializeClient() {
        SimplyStatusRoot root = new SimplyStatusRoot();
        SimplyStatusConfig cfg = new SimplyStatusConfig();
        // Event registration will be executed inside this method
        KeyBinding keyBindingViewIP;
        keyBindingViewIP = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.simplystatus.viewIP", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_F9, // The keycode of the ke
                "category.simplystatus.name" // The translation key of the keybinding's category.
        ));
        KeyBinding keyBindingViewStatic;
        keyBindingViewStatic = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.simplystatus.viewStatic", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_F10, // The keycode of the key
                "category.simplystatus.name" // The translation key of the keybinding's category.
        ));
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBindingViewIP.wasPressed()) {
                if(ViewIP == false){
                    ViewIP = true;
                    root.setViewIP(ViewIP);
                    client.player.sendMessage(new LiteralText("[§6SimplyStatus§r] IP адрес сервера теперь виден!"), false);
                } else{
                    ViewIP = false;
                    root.setViewIP(ViewIP);
                    client.player.sendMessage(new LiteralText("[§6SimplyStatus§r] IP адрес сервера теперь скрыт!"), false);
                }
                cfg.save();
            }
            while (keyBindingViewStatic.wasPressed()) {
                if(ViewStatic == false){
                    ViewStatic = true;
                    root.setViewStatic(ViewStatic);
                    client.player.sendMessage(new LiteralText("[§6SimplyStatus§r] Ваша статистика теперь видна!"), false);
                } else{
                    ViewStatic = false;
                    root.setViewStatic(ViewStatic);
                    client.player.sendMessage(new LiteralText("[§6SimplyStatus§r] Ваша статистика теперь скрыта!"), false);
                }
                cfg.save();
            }
        });
    }
}