package og.__kel_.simplystatus;

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
    public static boolean ViewIPAddress = false;
    public static boolean ViewStatic = true;
    public static boolean ViewName = true;
    public static boolean ViewOffHand = false;
    public static boolean ViewRPC = true;
    public static boolean ViewUsername = true;
    @Override
    public void onInitializeClient() {
        SimplyStatusConfig cfg = new SimplyStatusConfig();
        cfg.load();
        KeyBinding keyBindingViewUsername;
        keyBindingViewUsername = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.simplystatus.ViewUsername", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_N, // The keycode of the key
                "category.simplystatus.name" // The translation key of the keybinding's category.
        ));
        KeyBinding keyBindingViewRPC;
        keyBindingViewRPC = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.simplystatus.ViewRPC", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_H, // The keycode of the key
                "category.simplystatus.name" // The translation key of the keybinding's category.
        ));
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
        KeyBinding keyBindingViewIPAddress;
        keyBindingViewIPAddress = KeyBindingHelper.registerKeyBinding(new KeyBinding(
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
            while (keyBindingViewIPAddress.wasPressed()) {
                if(issinglePlayer){
                    client.player.sendMessage(new TranslatableText("message.simplystatus.errorIsSinglePlayer"), false);
                    return;
                }
                if(ViewIPAddress == false){
                    ViewIPAddress = true;
                    client.player.sendMessage(new TranslatableText("message.simplystatus.ViewIPTrue"), false);
                } else{
                    ViewIPAddress = false;
                    client.player.sendMessage(new TranslatableText("message.simplystatus.ViewIPFalse"), false);
                }
                cfg.save(mc.getCurrentServerEntry().address);
            }
            while (keyBindingViewStatic.wasPressed()) {
                if(ViewStatic == false){
                    ViewStatic = true;
                    client.player.sendMessage(new TranslatableText("message.simplystatus.ViewStaticTrue"), false);
                } else{
                    ViewStatic = false;
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
                    client.player.sendMessage(new TranslatableText("message.simplystatus.ViewNameTrue"), false);
                } else{
                    ViewName = false;
                    client.player.sendMessage(new TranslatableText("message.simplystatus.ViewNameFalse"), false);
                }
                cfg.save(mc.getCurrentServerEntry().address);
            }
            while (keyBindingOffHand.wasPressed()) {
                if(ViewOffHand == false){
                    ViewOffHand = true;
                    client.player.sendMessage(new TranslatableText("message.simplystatus.ViewOffHandTrue"), false);
                } else{
                    ViewOffHand = false;
                    client.player.sendMessage(new TranslatableText("message.simplystatus.ViewOffHandFalse"), false);
                }
                cfg.save();
            }
            while (keyBindingViewRPC.wasPressed()) {
                if(ViewRPC == false){
                    ViewRPC = true;
                    client.player.sendMessage(new TranslatableText("message.simplystatus.showRPC"), false);
                } else{
                    ViewRPC = false;
                    client.player.sendMessage(new TranslatableText("message.simplystatus.hideRPC"), false);
                }
                cfg.save();
            }
            while (keyBindingViewUsername.wasPressed()) {
                if(ViewUsername == false){
                    ViewUsername = true;
                    client.player.sendMessage(new TranslatableText("message.simplystatus.showUsername"), false);
                } else{
                    ViewUsername = false;
                    client.player.sendMessage(new TranslatableText("message.simplystatus.hideUsername"), false);
                }
                //cfg.save();
            }
            while (keyBindingShowSettings.wasPressed()) {
                String SettingsText = new TranslatableText("message.simplystatus.SettingsTextClient").getString();
                String MessagePrefix = new TranslatableText("message.simplystatus.SettingState").getString();
                String message = MessagePrefix+SettingsText;
                if(ViewStatic == false){
                    message = message.replace("%static%", String.valueOf(new TranslatableText("settings.simplystatus.false").getString()));
                } else{
                    message = message.replace("%static%", String.valueOf(new TranslatableText("settings.simplystatus.true").getString()));
                }
                if(ViewOffHand == false){
                    message = message.replace("%offhand%", String.valueOf(new TranslatableText("settings.simplystatus.false").getString()));
                } else{
                    message = message.replace("%offhand%", String.valueOf(new TranslatableText("settings.simplystatus.true").getString()));
                }
                //servers
                if(!issinglePlayer){
                    message = message + new TranslatableText("message.simplystatus.SettingsTextMultiplayer").getString();
                    if(ViewName == false){
                        message = message.replace("%name%", String.valueOf(new TranslatableText("settings.simplystatus.false").getString()));
                    } else{
                        message = message.replace("%name%", String.valueOf(new TranslatableText("settings.simplystatus.true").getString()));
                    }
                    if(ViewIPAddress == false){
                        message = message.replace("%ip%", String.valueOf(new TranslatableText("settings.simplystatus.false").getString()));
                    } else{
                        message = message.replace("%ip%", String.valueOf(new TranslatableText("settings.simplystatus.true").getString()));
                    }
                }
                client.player.sendMessage(new LiteralText(message), false);
            }
        });
    }
}