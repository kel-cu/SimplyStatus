package og.__kel_.simplystatus.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableTextContent;

import og.__kel_.simplystatus.configs.Config;
import og.__kel_.simplystatus.configs.ConfigScreen;
import org.lwjgl.glfw.GLFW;

public class HotKeys implements ClientModInitializer {
    public static boolean viewIPAddress = false;
    public static boolean viewName = true;
    public static boolean viewStatic = true;
    public static boolean viewOffHand = false;
    public static boolean viewRPC = true;
    public static boolean viewUsername = true;
    public static boolean bedrock = false;
    public static boolean changeStatusNameInMinecraft = false;
    public static boolean lastTitle = false;
    public static boolean cringeIcons = false;
    public static boolean showTime = true;
    public static boolean modMessageInChat = false;
    public static boolean customNameEnable = false;
    public static String customName = "";


    public static boolean viewVoice = false;
    public static boolean viewMusicListening = false;
    public static boolean viewReplayMod = false;

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
        KeyBinding configScreenKey;
        configScreenKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.simplystatus.configScreen",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_F4, // The keycode of the key
                "category.simplystatus.name"
        ));

        Config config = new Config();
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            MinecraftClient mc = MinecraftClient.getInstance();
            boolean issinglePlayer = mc.isInSingleplayer();
            assert client.player != null;
            while (configScreenKey.wasPressed()) {
                if(FabricLoader.getInstance().getModContainer("cloth-config").isEmpty()){
                    client.player.sendMessage(MutableText.of(new TranslatableTextContent(("message.simplystatus.clothConfigNotFound"))), modMessageInChat);
                    return;
                }
                final Screen current = client.currentScreen;
                Screen configScreen = ConfigScreen.buildScreen(current);
                client.setScreen(configScreen);
            }
            while (keyBindingViewIPAddress.wasPressed()) {
                if(issinglePlayer){
                    client.player.sendMessage(MutableText.of(new TranslatableTextContent("message.simplystatus.errorIsSinglePlayer")), false);
                    return;
                }
                viewIPAddress = !viewIPAddress;
                client.player.sendMessage(MutableText.of(new TranslatableTextContent((viewIPAddress ? "message.simplystatus.viewIP.false" : "message.simplystatus.viewIP.true"))), modMessageInChat);
                config.save(mc.getCurrentServerEntry().address);
            }
            while (keyBindingViewStatic.wasPressed()) {
                viewStatic = !viewStatic;
                client.player.sendMessage(MutableText.of(new TranslatableTextContent((viewStatic ? "message.simplystatus.viewStatic.false" : "message.simplystatus.viewStatic.true"))), modMessageInChat);
                config.save();
            }
            while (keyBindingBedrock.wasPressed()) {
                bedrock = !bedrock;
                client.player.sendMessage(MutableText.of(new TranslatableTextContent((bedrock ? "message.simplystatus.bedrock.false" : "message.simplystatus.bedrock.true"))), modMessageInChat);
                config.save();
            }
            while (keyBindingShowTime.wasPressed()) {
                showTime = !showTime;
                client.player.sendMessage(MutableText.of(new TranslatableTextContent((showTime ? "message.simplystatus.showTime.false" : "message.simplystatus.showTime.true"))), modMessageInChat);
                config.save();
            }
            while (keyBindingViewNameServer.wasPressed()) {
                if(issinglePlayer){
                    client.player.sendMessage(MutableText.of(new TranslatableTextContent("message.simplystatus.errorIsSinglePlayer")), modMessageInChat);
                    return;
                }
                viewName = !viewName;
                client.player.sendMessage(MutableText.of(new TranslatableTextContent((viewName ? "message.simplystatus.ViewName.false" : "message.simplystatus.ViewName.true"))), modMessageInChat);
                config.save(mc.getCurrentServerEntry().address);
            }
            while (keyBindingOffHand.wasPressed()) {
                viewOffHand = !viewOffHand;
                client.player.sendMessage(MutableText.of(new TranslatableTextContent((viewOffHand ? "message.simplystatus.viewOffHand.false" : "message.simplystatus.viewOffHand.true"))), modMessageInChat);

                config.save();
            }
            while (keyBindingViewRPC.wasPressed()) {
                viewRPC = !viewRPC;
                client.player.sendMessage(MutableText.of(new TranslatableTextContent((viewRPC ? "message.simplystatus.rpc.false" : "message.simplystatus.rpc.true"))), modMessageInChat);
                config.save();
            }
            while (keyBindingViewUsername.wasPressed()) {
                viewUsername = !viewUsername;
                client.player.sendMessage(MutableText.of(new TranslatableTextContent((viewUsername ? "message.simplystatus.username.false" : "message.simplystatus.username.true"))), modMessageInChat);
                config.save();
            }
        });
    }
}