package og.__kel_.simplystatus;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableTextContent;

import org.lwjgl.glfw.GLFW;

public class Client implements ClientModInitializer {
    // The KeyBinding declaration and registration are commonly executed here statically
    public static boolean ViewIPAddress = false;
    public static boolean ViewName = true;
    public static boolean ViewStatic = true;
    public static boolean ViewOffHand = false;
    public static boolean ViewRPC = true;
    public static boolean ViewUsername = true;
    public static boolean Bedrock = false;
    public static boolean CringeIcons = false;
    public static boolean showTime = true;
    public static boolean modMessageInChat = false;
    public static boolean broadcast = false;

    public static boolean customNameEnable = false;
    public static String customName = "";
    public Config configs = new Config();
    @Override
    public void onInitializeClient() {
        Config cfg = new Config();
        configs = cfg;
        cfg.load();
        KeyBinding keyBindingViewUsername;
        keyBindingViewUsername = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.simplystatus.ViewUsername", 
                InputUtil.Type.KEYSYM, 
                GLFW.GLFW_KEY_N, // The keycode of the key
                "category.simplystatus.name" 
        ));
        KeyBinding keyBindingViewRPC;
        keyBindingViewRPC = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.simplystatus.ViewRPC", 
                InputUtil.Type.KEYSYM, 
                GLFW.GLFW_KEY_H, // The keycode of the key
                "category.simplystatus.name" 
        ));
        KeyBinding keyBindingBedrock;
        keyBindingBedrock = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.simplystatus.bedrock", 
                InputUtil.Type.KEYSYM, 
                GLFW.GLFW_KEY_B, // The keycode of the key
                "category.simplystatus.name" 
        ));
        KeyBinding keyBindingOffHand;
        keyBindingOffHand = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.simplystatus.OffHand", 
                InputUtil.Type.KEYSYM, 
                GLFW.GLFW_KEY_F7, // The keycode of the key
                "category.simplystatus.name" 
        ));
        KeyBinding keyBindingViewNameServer;
        keyBindingViewNameServer = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.simplystatus.viewNameServer", 
                InputUtil.Type.KEYSYM, 
                GLFW.GLFW_KEY_F8, // The keycode of the key
                "category.simplystatus.name" 
        ));
        KeyBinding keyBindingViewIPAddress;
        keyBindingViewIPAddress = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.simplystatus.viewIP", 
                InputUtil.Type.KEYSYM, 
                GLFW.GLFW_KEY_F9, // The keycode of the ke
                "category.simplystatus.name" 
        ));
        KeyBinding keyBindingViewStatic;
        keyBindingViewStatic = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.simplystatus.viewStatic", 
                InputUtil.Type.KEYSYM, 
                GLFW.GLFW_KEY_F10, // The keycode of the key
                "category.simplystatus.name" 
        ));
        KeyBinding keyBindingShowTime;
        keyBindingShowTime = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.simplystatus.showTime", 
                InputUtil.Type.KEYSYM, 
                GLFW.GLFW_KEY_R, // The keycode of the key
                "category.simplystatus.name" 
        ));

        KeyBinding keyBindingSettings;
        keyBindingSettings = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.simplystatus.settings",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_F4, // The keycode of the key
                "category.simplystatus.name"
        ));

        Config config = new Config();
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            MinecraftClient mc = MinecraftClient.getInstance();
            boolean issinglePlayer = mc.isInSingleplayer();
            assert client.player != null;
            while (keyBindingViewIPAddress.wasPressed()) {
                if(issinglePlayer){
                    client.player.sendMessage(MutableText.of(new TranslatableTextContent("message.simplystatus.errorIsSinglePlayer")), false);
                    return;
                }
                if(!ViewIPAddress){
                    ViewIPAddress = true;
                    client.player.sendMessage(MutableText.of(new TranslatableTextContent("message.simplystatus.ViewIPTrue")), false);
                } else{
                    ViewIPAddress = false;
                    client.player.sendMessage(MutableText.of(new TranslatableTextContent("message.simplystatus.ViewIPFalse")), false);
                }
                config.save(mc.getCurrentServerEntry().address);
            }
            while (keyBindingViewStatic.wasPressed()) {
                if(!ViewStatic){
                    ViewStatic = true;
                    client.player.sendMessage(MutableText.of(new TranslatableTextContent("message.simplystatus.ViewStaticTrue")), false);
                } else{
                    ViewStatic = false;
                    client.player.sendMessage(MutableText.of(new TranslatableTextContent("message.simplystatus.ViewStaticFalse")), false);
                }
                config.save();
            }
            while (keyBindingBedrock.wasPressed()) {
                if(!Bedrock){
                    Bedrock = true;
                    client.player.sendMessage(MutableText.of(new TranslatableTextContent("message.simplystatus.bedrockTrue")), modMessageInChat);
                } else{
                    Bedrock = false;
                    client.player.sendMessage(MutableText.of(new TranslatableTextContent("message.simplystatus.bedrockFalse")), modMessageInChat);
                }
                config.save();
            }
            while (keyBindingShowTime.wasPressed()) {
                if(!showTime){
                    showTime = true;
                    client.player.sendMessage(MutableText.of(new TranslatableTextContent("message.simplystatus.showTimeTrue")), modMessageInChat);
                } else{
                    showTime = false;
                    client.player.sendMessage(MutableText.of(new TranslatableTextContent("message.simplystatus.showTimeFalse")), modMessageInChat);
                }
                config.save();
            }
            while (keyBindingViewNameServer.wasPressed()) {
                if(issinglePlayer){
                    client.player.sendMessage(MutableText.of(new TranslatableTextContent("message.simplystatus.errorIsSinglePlayer")), modMessageInChat);
                    return;
                }
                if(!ViewName){
                    ViewName = true;
                    client.player.sendMessage(MutableText.of(new TranslatableTextContent("message.simplystatus.ViewNameTrue")), modMessageInChat);
                } else{
                    ViewName = false;
                    client.player.sendMessage(MutableText.of(new TranslatableTextContent("message.simplystatus.ViewNameFalse")), modMessageInChat);
                }
                config.save(mc.getCurrentServerEntry().address);
            }
            while (keyBindingOffHand.wasPressed()) {
                if(!ViewOffHand){
                    ViewOffHand = true;
                    client.player.sendMessage(MutableText.of(new TranslatableTextContent("message.simplystatus.ViewOffHandTrue")), modMessageInChat);
                } else{
                    ViewOffHand = false;
                    client.player.sendMessage(MutableText.of(new TranslatableTextContent("message.simplystatus.ViewOffHandFalse")), modMessageInChat);
                }
                config.save();
            }
            while (keyBindingViewRPC.wasPressed()) {
                if(!ViewRPC){
                    ViewRPC = true;
                    client.player.sendMessage(MutableText.of(new TranslatableTextContent("message.simplystatus.showRPC")), modMessageInChat);
                } else{
                    ViewRPC = false;
                    client.player.sendMessage(MutableText.of(new TranslatableTextContent("message.simplystatus.hideRPC")), modMessageInChat);
                }
                config.save();
            }
            while (keyBindingViewUsername.wasPressed()) {
                if(!ViewUsername){
                    ViewUsername = true;
                    client.player.sendMessage(MutableText.of(new TranslatableTextContent("message.simplystatus.showUsername")), modMessageInChat);
                } else{
                    ViewUsername = false;
                    client.player.sendMessage(MutableText.of(new TranslatableTextContent("message.simplystatus.hideUsername")), modMessageInChat);
                }
                config.save();
            }
        });
    }
}