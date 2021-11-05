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

    Integer times = 0;
    Timer timer = new Timer();
    @Override
    public void onInitialize() {

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

        DiscordRichPresence presence = new DiscordRichPresence();
        presence.startTimestamp = start_time; // epoch second
        presence.details = "В главном меню.";
        presence.state = "Minecraft " + mc.getGame().getVersion().getName() + "/" + mc.getVersionType();
        presence.largeImageKey = "logo";
        presence.instance = 1;
        lib.Discord_UpdatePresence(presence);

    }
    private void updatePresence() {
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
                    presence.details = "Держит \"" + held_item.getName().getString() + "\"";
                } else {
                    var playerHealth = mc.player.getHealth() / 2;
                    var playerHealthMax = mc.player.getMaxHealth() / 2;
                    var playerArmor = mc.player.getArmor() / 2;
                    if(playerHealth <= 0.0) {
                        presence.details = "Игрок умер :(";
                    } else {
                        presence.details = Math.ceil(playerHealth) + "/" + Math.ceil(playerHealthMax) + "❤️ | " + Math.ceil(playerArmor) + "🛡️";
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
                presence.state = "Неизвестный мультимлеер ¯\\_(ツ)_/¯";
                } else {
                    presence.state = mc.player.getName().getString() + " | " + serverip;
                    if(worldTime > 24000){
                        var mcdays = worldTime / 24000;
                        var tipotime = mcdays * 24000;
                        var mctime = worldTime - tipotime;
                        if(mctime < 0 && mctime > 23000){
                            presence.smallImageKey = "morning";
                            presence.smallImageText = "Утро";
                        } else if(mctime < 6000 && mctime > 0){
                            presence.smallImageKey = "morning";
                            presence.smallImageText = "Утро";
                        } else if( mctime < 12000 && mctime > 6000){
                            presence.smallImageKey = "day";
                            presence.smallImageText = "День";

                        } else if(mctime < 16500 && mctime > 12000){
                            presence.smallImageKey = "evening";
                            presence.smallImageText = "Вечер";

                        } else if(mctime < 23000 && mctime > 16500){
                            presence.smallImageKey = "night";
                            presence.smallImageText = "Ночь";
                        }
                    }else{
                        var mctime = worldTime;
                        if(mctime < 0 && mctime > 23000){
                            presence.smallImageKey = "morning";
                            presence.smallImageText = "Утро";
                        } else if(mctime < 6000 && mctime > 0){
                            presence.smallImageKey = "morning";
                            presence.smallImageText = "Утро";
                        } else if( mctime < 12000 && mctime > 6000){
                            presence.smallImageKey = "day";
                            presence.smallImageText = "День";

                        } else if(mctime < 16500 && mctime > 12000){
                            presence.smallImageKey = "evening";
                            presence.smallImageText = "Вечер";

                        } else if(mctime < 23000 && mctime > 16500){
                            presence.smallImageKey = "night";
                            presence.smallImageText = "Ночь";
                        }
                    }
                }
            } else {
                presence.state = mc.player.getName().getString() + " | Одиночный мир";
                if (DimensionType.THE_END_ID.equals(dimKey)) {
                    presence.smallImageKey = "end";
                    presence.smallImageText = "Находится в \"Эндер мире\"";
                } else if (DimensionType.THE_NETHER_ID.equals(dimKey)) {
                    presence.smallImageKey = "nether";
                    presence.smallImageText = "Находится в \"Нижнем мире\"";
                } else {
                    presence.smallImageKey = "overworld";
                    presence.smallImageText = "Находится в \"Верхнем мире\"";
                    if(worldTime > 24000){
                        var mcdays = worldTime / 24000;
                        var tipotime = mcdays * 24000;
                        var mctime = worldTime - tipotime;
                        if(mctime < 0 && mctime > 23000){
                            presence.largeImageKey = "morning";
                            presence.largeImageText = "Утро | Верхний мир";
                            presence.smallImageKey = "overworld";
                            presence.smallImageText = "SimplyStatus";
                        } else if(mctime < 6000 && mctime > 0){
                            presence.largeImageKey = "morning";
                            presence.largeImageText = "Утро | Верхний мир";
                            presence.smallImageKey = "overworld";
                            presence.smallImageText = "SimplyStatus";
                        } else if( mctime < 12000 && mctime > 6000){
                            presence.largeImageKey = "day";
                            presence.largeImageText = "День | Верхний мир";
                            presence.smallImageKey = "overworld";
                            presence.smallImageText = "SimplyStatus";
                        } else if(mctime < 16500 && mctime > 12000){
                            presence.largeImageKey = "evening";
                            presence.largeImageText = "Вечер | Верхний мир";
                            presence.smallImageKey = "overworld";
                            presence.smallImageText = "SimplyStatus";
                        } else if(mctime < 23000 && mctime > 16500){
                            presence.largeImageKey = "night";
                            presence.largeImageText = "Ночь | Верхний мир";
                            presence.smallImageKey = "overworld";
                            presence.smallImageText = "SimplyStatus";
                        }
                    }else{
                        var mctime = worldTime;
                        if(mctime < 0 && mctime > 23000){
                            presence.largeImageKey = "morning";
                            presence.largeImageText = "Утро | Верхний мир";
                            presence.smallImageKey = "overworld";
                            presence.smallImageText = "SimplyStatus";
                        } else if(mctime < 6000 && mctime > 0){
                            presence.largeImageKey = "morning";
                            presence.largeImageText = "Утро | Верхний мир";
                            presence.smallImageKey = "overworld";
                            presence.smallImageText = "SimplyStatus";
                        } else if( mctime < 12000 && mctime > 6000){
                            presence.largeImageKey = "day";
                            presence.largeImageText = "День | Верхний мир";
                            presence.smallImageKey = "overworld";
                            presence.smallImageText = "SimplyStatus";
                        } else if(mctime < 16500 && mctime > 12000){
                            presence.largeImageKey = "evening";
                            presence.largeImageText = "Вечер | Верхний мир";
                            presence.smallImageKey = "overworld";
                            presence.smallImageText = "SimplyStatus";
                        } else if(mctime < 23000 && mctime > 16500){
                            presence.largeImageKey = "night";
                            presence.largeImageText = "Ночь | Верхний мир";
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