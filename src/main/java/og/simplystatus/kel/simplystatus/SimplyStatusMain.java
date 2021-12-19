package og.simplystatus.kel.simplystatus;

import club.minnced.discord.rpc.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;

import java.util.Timer;
import java.util.TimerTask;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.dimension.DimensionType;
import org.lwjgl.glfw.GLFW;

public class SimplyStatusMain implements ModInitializer {
    DiscordRPC lib = DiscordRPC.INSTANCE;
    String applicationId = "903288390072557648";
    String steamId = "";
    DiscordEventHandlers handlers = new DiscordEventHandlers();
    Long start_time = System.currentTimeMillis() / 1000;

    MinecraftClient mc = MinecraftClient.getInstance();
    SimplyStatusTranslate Translate = new SimplyStatusTranslate();

    Integer times = 0;
    Timer timer = new Timer();
    @Override
    public void onInitialize() {
        Translate.selectedLang();
        SimplyStatusConfig cfg = new SimplyStatusConfig();
        cfg.load();

        handlers.ready = (user) -> System.out.println("SimplyStatus running");

        lib.Discord_Initialize(applicationId, handlers, true, steamId);

        MainPresenceBasic();
        new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                lib.Discord_RunCallbacks();
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ignored) {
                }
            }
        }, "RPC-Callback-Handler").start();

        timer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {
                updatePresence();
            }
        }, 5000, 5000);
    }

    private void MainPresenceBasic() {
        Translate.selectedLang();
        DiscordRichPresence MainPresence = new DiscordRichPresence();
        MainPresence.startTimestamp = start_time;
        MainPresence.state = "Minecraft " + mc.getGame().getVersion().getName() + "/" + mc.getVersionType();
        MainPresence.details = Translate.textMainMenu;
        MainPresence.largeImageKey = "logo";
        MainPresence.largeImageText = Translate.text_goodPlayer;
        lib.Discord_UpdatePresence(MainPresence);
    }
    private void updatePresence() {
        Translate.selectedLang();
        if(mc.world == null){
            MainPresenceBasic();
        } else {
            SimplyStatusConfig cfg = new SimplyStatusConfig();
            times++;
            String PlayerName = new TranslatableText("status.simplystatus.unknownNamePlayer").getString();;

            boolean issinglePlayer = mc.isInSingleplayer();
            DimensionType dimtype = mc.world.getDimension();
            Identifier dimKey = mc.world.getRegistryManager().get(Registry.DIMENSION_TYPE_KEY).getId(dimtype);
            DiscordRichPresence presence = new DiscordRichPresence();
            presence.startTimestamp = start_time;
            presence.largeImageKey = "logo";
            presence.largeImageText = Translate.text_goodPlayer;

            if(mc.player!=null){
                if(mc.player.getDisplayName() != null){
                    PlayerName = mc.player.getDisplayName().getString();
                } else if(mc.player.getName() != null){
                    PlayerName = mc.player.getName().getString();
                }
                ItemStack main_item = mc.player.getStackInHand(Hand.MAIN_HAND);
                ItemStack off_item = mc.player.getStackInHand(Hand.OFF_HAND);
                String mitem = main_item.getItem().toString();
                String oitem = off_item.getItem().toString();
                if(mc.player.isSleeping()){
                    presence.details = Translate.text_isSleep;
                }else if (!mitem.equals("air")) {
                    presence.details = Translate.textItem + "«" + main_item.getName().getString() + "»";
                } else {
                    if(SimplyStatusClient.ViewOffHand == true){
                        if (!oitem.equals("air")) {
                            presence.details = Translate.textItem + "«" + off_item.getName().getString() + "»";
                        } else {
                            if(SimplyStatusClient.ViewStatic == true){
                                if(mc.player.isDead()) {
                                    var randomNumber = Math.floor(Math.random() * 2);
                                    if(randomNumber == 0){
                                        presence.details = Translate.textDeathOne;
                                    }else if(randomNumber == 1){
                                        presence.details = Translate.textDeathTwo;
                                    }else if(randomNumber == 2){
                                        presence.details = Translate.textDeathThree;
                                    }
                                } else {
                                    var playerHealth = mc.player.getHealth() / 2;
                                    var playerHealthMax = mc.player.getMaxHealth() / 2;
                                    var playerArmor = mc.player.getArmor() / 2;
                                    presence.details = Math.ceil(playerHealth) + "/" + Math.ceil(playerHealthMax) + "❤ | " + Math.ceil(playerArmor) + "🛡️";
                                }
                            } else {
                                presence.details = Translate.textAir;
                            }
                        }
                    } else {
                        if(SimplyStatusClient.ViewStatic == true){
                            if(mc.player.isDead()) {
                                var randomNumber = Math.floor(Math.random() * 2);
                                if(randomNumber == 0){
                                    presence.details = Translate.textDeathOne;
                                }else if(randomNumber == 1){
                                    presence.details = Translate.textDeathTwo;
                                }else if(randomNumber == 2){
                                    presence.details = Translate.textDeathThree;
                                }
                            } else {
                                var playerHealth = Math.ceil(mc.player.getHealth() / 2);
                                var playerHealthMax = Math.ceil(mc.player.getMaxHealth() / 2);
                                var playerArmor = Math.ceil(mc.player.getArmor() / 2);
                                presence.details = playerHealth + "/" + playerHealthMax + "❤ | " + playerArmor + "🛡️";
                            }
                        } else {
                            presence.details = Translate.textAir;
                        }
                    }
                }
            }

            var worldTime = mc.world.getLunarTime();

            if (!issinglePlayer) {
                String server_string = "";
                cfg.load(mc.getCurrentServerEntry().address);

                if (SimplyStatusClient.ViewName == true) {
                    if(mc.getCurrentServerEntry().name == null){
                        server_string = new TranslatableText("status.simplystatus.unknownNameServer").getString();
                    } else {
                        server_string = mc.getCurrentServerEntry().name;
                    }

                } else {
                    server_string = mc.getCurrentServerEntry().address;
                }
                if (server_string.equals("")) {
                    presence.state = Translate.textUnknownServer;
                } else {
                    if (SimplyStatusClient.ViewIP == true) {
                        presence.state = PlayerName + " | " + server_string;
                    } else {
                        presence.state = PlayerName + " | " + Translate.textHideIP;
                    }
                    presence.smallImageKey = "logo";
                    presence.smallImageText = "Minecraft " + mc.getGame().getVersion().getName() + "/" + mc.getVersionType();

                    // time
                    if(worldTime > 24000){
                        var mcdays = worldTime / 24000;
                        var tipotime = mcdays * 24000;
                        var mctime = worldTime - tipotime;
                        if(mctime < 0 && mctime > 23000){
                            presence.largeImageKey = "morning";
                            presence.largeImageText = Translate.text_morning;
                        } else if(mctime < 6000 && mctime > 0){
                            presence.largeImageKey = "morning";
                            presence.largeImageText = Translate.text_morning;
                        } else if( mctime < 12000 && mctime > 6000){
                            presence.largeImageKey = "day";
                            presence.largeImageText = Translate.text_day;

                        } else if(mctime < 16500 && mctime > 12000){
                            presence.largeImageKey = "evening";
                            presence.largeImageText = Translate.text_evening;

                        } else if(mctime < 23000 && mctime > 16500){
                            presence.largeImageKey = "night";
                            presence.largeImageText = Translate.text_night;
                        }
                    }else{
                        var mctime = worldTime;
                        if(mctime < 0 && mctime > 23000){
                            presence.largeImageKey = "morning";
                            presence.largeImageText = Translate.text_morning;
                        } else if(mctime < 6000 && mctime > 0){
                            presence.largeImageKey = "morning";
                            presence.largeImageText = Translate.text_morning;
                        } else if( mctime < 12000 && mctime > 6000){
                            presence.largeImageKey = "day";
                            presence.largeImageText = Translate.text_day;

                        } else if(mctime < 16500 && mctime > 12000){
                            presence.largeImageKey = "evening";
                            presence.largeImageText = Translate.text_evening;

                        } else if(mctime < 23000 && mctime > 16500){
                            presence.largeImageKey = "night";
                            presence.largeImageText = Translate.text_night;
                        }
                    }
                }
            } else {
                presence.state = PlayerName + " | " + Translate.text_isInSingleplayer;
                if (DimensionType.THE_END_ID.equals(dimKey)) {
                    presence.largeImageKey = "end";
                    presence.largeImageText = Translate.world_end;
                } else if (DimensionType.THE_NETHER_ID.equals(dimKey)) {
                    presence.largeImageKey = "nether";
                    presence.largeImageText = Translate.world_nether;
                } else {
                    presence.smallImageKey = "overworld";
                    presence.smallImageText = Translate.world_overworld;
                    if(worldTime > 24000){
                        var mcdays = worldTime / 24000;
                        var tipotime = mcdays * 24000;
                        var mctime = worldTime - tipotime;
                        if(mctime < 0 && mctime > 23000){
                            presence.largeImageKey = "morning";
                            presence.largeImageText = Translate.text_morning;
                        } else if(mctime < 6000 && mctime > 0){
                            presence.largeImageKey = "morning";
                            presence.largeImageText = Translate.text_morning;
                        } else if( mctime < 12000 && mctime > 6000){
                            presence.largeImageKey = "day";
                            presence.largeImageText = Translate.text_day;

                        } else if(mctime < 16500 && mctime > 12000){
                            presence.largeImageKey = "evening";
                            presence.largeImageText = Translate.text_evening;

                        } else if(mctime < 23000 && mctime > 16500){
                            presence.largeImageKey = "night";
                            presence.largeImageText = Translate.text_night;
                        }
                    }else{
                        var mctime = worldTime;
                        if(mctime < 0 && mctime > 23000){
                            presence.largeImageKey = "morning";
                            presence.largeImageText = Translate.text_morning;
                        } else if(mctime < 6000 && mctime > 0){
                            presence.largeImageKey = "morning";
                            presence.largeImageText = Translate.text_morning;
                        } else if( mctime < 12000 && mctime > 6000){
                            presence.largeImageKey = "day";
                            presence.largeImageText = Translate.text_day;

                        } else if(mctime < 16500 && mctime > 12000){
                            presence.largeImageKey = "evening";
                            presence.largeImageText = Translate.text_evening;

                        } else if(mctime < 23000 && mctime > 16500){
                            presence.largeImageKey = "night";
                            presence.largeImageText = Translate.text_night;
                        }
                    }
                }
            }

            lib.Discord_UpdatePresence(presence);
        }
    }
}

