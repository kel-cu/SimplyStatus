package og.simplystatus.kel.simplystatus;

import club.minnced.discord.rpc.*;
import net.fabricmc.api.ModInitializer;

import java.util.Timer;
import java.util.TimerTask;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.dimension.DimensionType;

public class SimplyStatus implements ModInitializer {
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
        handlers.ready = (user) -> System.out.println("Ready!");

        lib.Discord_Initialize(applicationId, handlers, true, steamId);

        basicPresence();
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

    private void basicPresence() {
        Translate.selectedLang();
        DiscordRichPresence presence = new DiscordRichPresence();
        presence.startTimestamp = start_time; // epoch second
        presence.details = Translate.textMainMenu;
        presence.state = "Minecraft " + mc.getGame().getVersion().getName() + "/" + mc.getVersionType();
        presence.largeImageKey = "logo";
        presence.instance = 1;
        lib.Discord_UpdatePresence(presence);

    }
    private void updatePresence() {
        Translate.selectedLang();
        if (mc.world != null) {
            times++;
            boolean issinglePlayer = mc.isInSingleplayer();
            DimensionType dimtype = mc.world.getDimension();
            Identifier dimKey = mc.world.getRegistryManager().get(Registry.DIMENSION_TYPE_KEY).getId(dimtype);
            DiscordRichPresence presence = new DiscordRichPresence();
            if(mc.player!=null){
                ItemStack held_item = mc.player.getStackInHand(Hand.MAIN_HAND);
                String item = held_item.getItem().toString();
                if (!item.equals("air")) {
                    presence.details = Translate.textItem + "«" + held_item.getName().getString() + "»";
                } else {
                        var playerHealth = mc.player.getHealth() / 2;
                        var playerHealthMax = mc.player.getMaxHealth() / 2;
                        var playerArmor = mc.player.getArmor() / 2;
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
                            if(SimplyStatusClient.ViewStatic == true){
                            presence.details = Math.ceil(playerHealth) + "/" + Math.ceil(playerHealthMax) + "❤ | " + Math.ceil(playerArmor) + "🛡️";
                        } else {
                            presence.details = Translate.textAir;
                        }
                        }


                }
            }
            presence.startTimestamp = start_time;
            presence.largeImageKey = "logo";
            presence.largeImageText = "SimplyStatus";
            presence.instance = 1;
            var worldTime = mc.world.getLunarTime();

            if (!issinglePlayer) {
                String serverip = "";
                if (mc.getCurrentServerEntry() != null) {
                    serverip = mc.getCurrentServerEntry().address;
                }
                if(serverip.equals("")) {
                    presence.state = Translate.textUnknownServer;
                } else {
                    if(SimplyStatusClient.ViewIP == true){
                        presence.state = mc.player.getName().getString() + " | " + serverip;
                    } else {
                        presence.state = mc.player.getName().getString() + " | " + Translate.textHideIP;
                    }
                    if(worldTime > 24000){
                        var mcdays = worldTime / 24000;
                        var tipotime = mcdays * 24000;
                        var mctime = worldTime - tipotime;
                        if(mctime < 0 && mctime > 23000){
                            presence.smallImageKey = "morning";
                            presence.largeImageText = Translate.text_morning;
                        } else if(mctime < 6000 && mctime > 0){
                            presence.smallImageKey = "morning";
                            presence.largeImageText = Translate.text_morning;
                        } else if( mctime < 12000 && mctime > 6000){
                            presence.smallImageKey = "day";
                            presence.largeImageText = Translate.text_day;

                        } else if(mctime < 16500 && mctime > 12000){
                            presence.smallImageKey = "evening";
                            presence.largeImageText = Translate.text_evening;

                        } else if(mctime < 23000 && mctime > 16500){
                            presence.smallImageKey = "night";
                            presence.largeImageText = Translate.text_night;
                        }
                    }else{
                        var mctime = worldTime;
                        if(mctime < 0 && mctime > 23000){
                            presence.smallImageKey = "morning";
                            presence.largeImageText = Translate.text_morning;
                        } else if(mctime < 6000 && mctime > 0){
                            presence.smallImageKey = "morning";
                            presence.largeImageText = Translate.text_morning;
                        } else if( mctime < 12000 && mctime > 6000){
                            presence.smallImageKey = "day";
                            presence.largeImageText = Translate.text_day;

                        } else if(mctime < 16500 && mctime > 12000){
                            presence.smallImageKey = "evening";
                            presence.largeImageText = Translate.text_evening;

                        } else if(mctime < 23000 && mctime > 16500){
                            presence.smallImageKey = "night";
                            presence.largeImageText = Translate.text_night;
                        }
                    }
                }
            } else {
                presence.state = mc.player.getName().getString() + " | " + Translate.text_isInSingleplayer;
                if (DimensionType.THE_END_ID.equals(dimKey)) {
                    presence.smallImageKey = "end";
                    presence.smallImageText = "Эндер мире";
                } else if (DimensionType.THE_NETHER_ID.equals(dimKey)) {
                    presence.smallImageKey = "nether";
                    presence.smallImageText = "Нижнем мире";
                } else {
                    presence.smallImageKey = "overworld";
                    presence.smallImageText = "Верхнем мире";
                    if(worldTime > 24000){
                        var mcdays = worldTime / 24000;
                        var tipotime = mcdays * 24000;
                        var mctime = worldTime - tipotime;
                        if(mctime < 0 && mctime > 23000){
                            presence.largeImageKey = "morning";
                            presence.largeImageText = Translate.text_morning + " | " + Translate.world_overworld;
                            presence.smallImageKey = "overworld";
                            presence.smallImageText = "SimplyStatus";
                        } else if(mctime < 6000 && mctime > 0){
                            presence.largeImageKey = "morning";
                            presence.largeImageText = Translate.text_morning + " | " + Translate.world_overworld;
                            presence.smallImageKey = "overworld";
                            presence.smallImageText = "SimplyStatus";
                        } else if( mctime < 12000 && mctime > 6000){
                            presence.largeImageKey = "day";
                            presence.largeImageText = Translate.text_day + " | " + Translate.world_overworld;
                            presence.smallImageKey = "overworld";
                            presence.smallImageText = "SimplyStatus";
                        } else if(mctime < 16500 && mctime > 12000){
                            presence.largeImageKey = "evening";
                            presence.largeImageText = Translate.text_evening + " | " + Translate.world_overworld;
                            presence.smallImageKey = "overworld";
                            presence.smallImageText = "SimplyStatus";
                        } else if(mctime < 23000 && mctime > 16500){
                            presence.largeImageKey = "night";
                            presence.largeImageText = Translate.text_night + " | " + Translate.world_overworld;
                            presence.smallImageKey = "overworld";
                            presence.smallImageText = "SimplyStatus";
                        }
                    }else{
                        var mctime = worldTime;
                        if(mctime < 0 && mctime > 23000){
                            presence.largeImageKey = "morning";
                            presence.largeImageText = Translate.text_morning + " | " + Translate.world_overworld;
                            presence.smallImageKey = "overworld";
                            presence.smallImageText = "SimplyStatus";
                        } else if(mctime < 6000 && mctime > 0){
                            presence.largeImageKey = "morning";
                            presence.largeImageText = Translate.text_morning + " | " + Translate.world_overworld;
                            presence.smallImageKey = "overworld";
                            presence.smallImageText = "SimplyStatus";
                        } else if( mctime < 12000 && mctime > 6000){
                            presence.largeImageKey = "day";
                            presence.largeImageText = Translate.text_day + " | " + Translate.world_overworld;
                            presence.smallImageKey = "overworld";
                            presence.smallImageText = "SimplyStatus";
                        } else if(mctime < 16500 && mctime > 12000){
                            presence.largeImageKey = "evening";
                            presence.largeImageText = Translate.text_evening + " | " + Translate.world_overworld;
                            presence.smallImageKey = "overworld";
                            presence.smallImageText = "SimplyStatus";
                        } else if(mctime < 23000 && mctime > 16500){
                            presence.largeImageKey = "night";
                            presence.largeImageText = Translate.text_night + " | " + Translate.world_overworld;
                            presence.smallImageKey = "overworld";
                            presence.smallImageText = "SimplyStatus";
                        }
                    }
                }
            }
            lib.Discord_UpdatePresence(presence);

        } else {
            basicPresence();
        }
    }
}