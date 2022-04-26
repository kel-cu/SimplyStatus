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
    public static boolean ViewName = true;
    public static boolean ViewStatic = true;
    public static boolean ViewOffHand = false;
    public static boolean ViewRPC = true;
    public static boolean ViewUsername = true;
    public static boolean Bedrock = false;
    public static boolean CringeIcons = false;
    public SimplyStatusConfig configs = new SimplyStatusConfig();
    @Override
    public void onInitializeClient() {
        SimplyStatusConfig cfg = new SimplyStatusConfig();
        configs = cfg;
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
        KeyBinding keyBindingBedrock;
        keyBindingBedrock = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.simplystatus.bedrock", // The translation key of the keybinding's name
                InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
                GLFW.GLFW_KEY_B, // The keycode of the key
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

        SimplyStatusConfig config = new SimplyStatusConfig();
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            MinecraftClient mc = MinecraftClient.getInstance();
            boolean issinglePlayer = mc.isInSingleplayer();
            assert client.player != null;
            while (keyBindingViewIPAddress.wasPressed()) {
                if(issinglePlayer){
                    client.player.sendMessage(new TranslatableText("message.simplystatus.errorIsSinglePlayer"), false);
                    return;
                }
                if(!ViewIPAddress){
                    ViewIPAddress = true;
                    client.player.sendMessage(new TranslatableText("message.simplystatus.ViewIPTrue"), false);
                } else{
                    ViewIPAddress = false;
                    client.player.sendMessage(new TranslatableText("message.simplystatus.ViewIPFalse"), false);
                }
                config.save(mc.getCurrentServerEntry().address);
            }
            while (keyBindingViewStatic.wasPressed()) {
                if(!ViewStatic){
                    ViewStatic = true;
                    client.player.sendMessage(new TranslatableText("message.simplystatus.ViewStaticTrue"), false);
                } else{
                    ViewStatic = false;
                    client.player.sendMessage(new TranslatableText("message.simplystatus.ViewStaticFalse"), false);
                }
                config.save();
            }
            while (keyBindingBedrock.wasPressed()) {
                if(!Bedrock){
                    Bedrock = true;
                    client.player.sendMessage(new TranslatableText("message.simplystatus.bedrockTrue"), false);
                } else{
                    Bedrock = false;
                    client.player.sendMessage(new TranslatableText("message.simplystatus.bedrockFalse"), false);
                }
                config.save();
            }
            while (keyBindingViewNameServer.wasPressed()) {
                if(issinglePlayer){
                    client.player.sendMessage(new TranslatableText("message.simplystatus.errorIsSinglePlayer"), false);
                    return;
                }
                if(!ViewName){
                    ViewName = true;
                    client.player.sendMessage(new TranslatableText("message.simplystatus.ViewNameTrue"), false);
                } else{
                    ViewName = false;
                    client.player.sendMessage(new TranslatableText("message.simplystatus.ViewNameFalse"), false);
                }
                config.save(mc.getCurrentServerEntry().address);
            }
            while (keyBindingOffHand.wasPressed()) {
                if(!ViewOffHand){
                    ViewOffHand = true;
                    client.player.sendMessage(new TranslatableText("message.simplystatus.ViewOffHandTrue"), false);
                } else{
                    ViewOffHand = false;
                    client.player.sendMessage(new TranslatableText("message.simplystatus.ViewOffHandFalse"), false);
                }
                config.save();
            }
            while (keyBindingViewRPC.wasPressed()) {
                if(!ViewRPC){
                    ViewRPC = true;
                    client.player.sendMessage(new TranslatableText("message.simplystatus.showRPC"), false);
                } else{
                    ViewRPC = false;
                    client.player.sendMessage(new TranslatableText("message.simplystatus.hideRPC"), false);
                }
                config.save();
            }
            while (keyBindingViewUsername.wasPressed()) {
                if(!ViewUsername){
                    ViewUsername = true;
                    client.player.sendMessage(new TranslatableText("message.simplystatus.showUsername"), false);
                } else{
                    ViewUsername = false;
                    client.player.sendMessage(new TranslatableText("message.simplystatus.hideUsername"), false);
                }
                config.save();
            }
            while (keyBindingShowSettings.wasPressed()) {
                String SettingsText = new TranslatableText("message.simplystatus.SettingsTextClient").getString();
                String MessagePrefix = new TranslatableText("message.simplystatus.SettingState").getString();
                String message = MessagePrefix+SettingsText;
                if(!ViewStatic){
                    message = message.replace("%static%", String.valueOf(new TranslatableText("settings.simplystatus.false").getString()));
                } else{
                    message = message.replace("%static%", String.valueOf(new TranslatableText("settings.simplystatus.true").getString()));
                }
                if(!ViewOffHand){
                    message = message.replace("%offhand%", String.valueOf(new TranslatableText("settings.simplystatus.false").getString()));
                } else{
                    message = message.replace("%offhand%", String.valueOf(new TranslatableText("settings.simplystatus.true").getString()));
                }
                if(!ViewUsername){
                    message = message.replace("%username%", String.valueOf(new TranslatableText("settings.simplystatus.false").getString()));
                } else{
                    message = message.replace("%username%", String.valueOf(new TranslatableText("settings.simplystatus.true").getString()));
                }
                if(!ViewRPC){
                    message = message.replace("%status%", String.valueOf(new TranslatableText("settings.simplystatus.false").getString()));
                } else{
                    message = message.replace("%status%", String.valueOf(new TranslatableText("settings.simplystatus.true").getString()));
                }
                //servers
                if(!issinglePlayer){
                    message = message + new TranslatableText("message.simplystatus.SettingsTextMultiplayer").getString();
                    if(!ViewName){
                        message = message.replace("%name%", String.valueOf(new TranslatableText("settings.simplystatus.false").getString()));
                    } else{
                        message = message.replace("%name%", String.valueOf(new TranslatableText("settings.simplystatus.true").getString()));
                    }
                    if(!ViewIPAddress){
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