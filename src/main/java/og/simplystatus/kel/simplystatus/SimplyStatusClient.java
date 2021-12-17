package og.simplystatus.kel.simplystatus;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import org.lwjgl.glfw.GLFW;

public class SimplyStatusClient implements ClientModInitializer {
    // The KeyBinding declaration and registration are commonly executed here statically
    public static boolean ViewIP = false;
    public static boolean ViewStatic = true;
    public static boolean ViewName = true;
    public static boolean ViewOffHand = false;
    @Override
    public void onInitializeClient() {
        SimplyStatusRoot root = new SimplyStatusRoot();
        ServerConfig SRoot = new ServerConfig();
        SimplyStatusConfig cfg = new SimplyStatusConfig();
        // Event registration will be executed inside this method
        KeyBinding keyBindingShowSettings;
        keyBindingShowSettings = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.simplystatus.ShowSettings", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_F6, // The keycode of the key
                "category.simplystatus.name" // The translation key of the keybinding's category.
        ));
        KeyBinding keyBindingOffHand;
        keyBindingOffHand = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.simplystatus.OffHand", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_F7, // The keycode of the key
                "category.simplystatus.name" // The translation key of the keybinding's category.
        ));
        KeyBinding keyBindingViewNameServer;
        keyBindingViewNameServer = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.simplystatus.viewNameServer", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_F8, // The keycode of the key
                "category.simplystatus.name" // The translation key of the keybinding's category.
        ));
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
            MinecraftClient mc = MinecraftClient.getInstance();
            boolean issinglePlayer = mc.isInSingleplayer();
            while (keyBindingViewIP.wasPressed()) {
                if(issinglePlayer){
                    client.player.sendMessage(new TranslatableText("message.simplystatus.errorIsSinglePlayer"), false);
                    return;
                }
                if(ViewIP == false){
                    ViewIP = true;
                    SRoot.setViewIP(ViewIP);
                    client.player.sendMessage(new TranslatableText("message.simplystatus.ViewIPTrue"), false);
                } else{
                    ViewIP = false;
                    SRoot.setViewIP(ViewIP);
                    client.player.sendMessage(new TranslatableText("message.simplystatus.ViewIPFalse"), false);
                }
                cfg.save(mc.getCurrentServerEntry().address);
            }
            while (keyBindingViewStatic.wasPressed()) {
                if(ViewStatic == false){
                    ViewStatic = true;
                    root.setViewStatic(ViewStatic);
                    client.player.sendMessage(new TranslatableText("message.simplystatus.ViewStaticTrue"), false);
                } else{
                    ViewStatic = false;
                    root.setViewStatic(ViewStatic);
                    client.player.sendMessage(new TranslatableText("message.simplystatus.ViewStaticFalse"), false);
                }
                cfg.save();
            }
            while (keyBindingViewNameServer.wasPressed()) {
                if(issinglePlayer){
                    client.player.sendMessage(new TranslatableText("message.simplystatus.errorIsSinglePlayer"), false);
                    return;
                }
                if(ViewName == false){
                    ViewName = true;
                    SRoot.setViewName(ViewName);
                    client.player.sendMessage(new TranslatableText("message.simplystatus.ViewNameTrue"), false);
                } else{
                    ViewName = false;
                    SRoot.setViewName(ViewName);
                    client.player.sendMessage(new TranslatableText("message.simplystatus.ViewNameFalse"), false);
                }
                cfg.save(mc.getCurrentServerEntry().address);
            }
            while (keyBindingOffHand.wasPressed()) {
                if(ViewOffHand == false){
                    ViewOffHand = true;
                    root.setViewOffHand(ViewOffHand);
                    client.player.sendMessage(new TranslatableText("message.simplystatus.ViewOffHandTrue"), false);
                } else{
                    ViewOffHand = false;
                    root.setViewOffHand(ViewOffHand);
                    client.player.sendMessage(new TranslatableText("message.simplystatus.ViewOffHandFalse"), false);
                }
                cfg.save();
            }
            while (keyBindingShowSettings.wasPressed()) {
                String SettingsText = new TranslatableText("message.simplystatus.SettingsText").getString();
                String MessagePrefix = new TranslatableText("message.simplystatus.SettingState").getString();
                if(ViewIP == false){
                    SettingsText = SettingsText.replace("%ip%", String.valueOf(new TranslatableText("settings.simplystatus.false").getString()));
                } else{
                    SettingsText = SettingsText.replace("%ip%", String.valueOf(new TranslatableText("settings.simplystatus.true").getString()));
                }
                if(ViewStatic == false){
                    SettingsText = SettingsText.replace("%static%", String.valueOf(new TranslatableText("settings.simplystatus.false").getString()));
                } else{
                    SettingsText = SettingsText.replace("%static%", String.valueOf(new TranslatableText("settings.simplystatus.true").getString()));
                }
                if(ViewName == false){
                    SettingsText = SettingsText.replace("%name%", String.valueOf(new TranslatableText("settings.simplystatus.false").getString()));
                } else{
                    SettingsText = SettingsText.replace("%name%", String.valueOf(new TranslatableText("settings.simplystatus.true").getString()));
                }
                if(ViewOffHand == false){
                    SettingsText = SettingsText.replace("%offhand%", String.valueOf(new TranslatableText("settings.simplystatus.false").getString()));
                } else{
                    SettingsText = SettingsText.replace("%offhand%", String.valueOf(new TranslatableText("settings.simplystatus.true").getString()));
                }
                client.player.sendMessage(new LiteralText(MessagePrefix + SettingsText), false);
            }
        });
    }
}