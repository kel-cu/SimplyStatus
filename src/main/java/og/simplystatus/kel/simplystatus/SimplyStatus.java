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
        presence.details = "Главное меню";
        presence.largeImageKey = "logo";
        presence.largeImageText = "SimplyStatus";
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
                    presence.details = "Держит хорошое настроение :3";
                }
            }
            presence.startTimestamp = start_time;
            presence.largeImageKey = "logo";
            presence.largeImageText = "SimplyStatus";
            presence.instance = 1;
            if (!issinglePlayer) {
                String serverip = "";
                if (mc.getCurrentServerEntry() != null) {
                    serverip = mc.getCurrentServerEntry().address;
                }
                if(serverip.equals("")) {
                presence.state = "В записи ReplayMod [Возможно]";
                } else {
                    presence.state = "Мултиплеер: " + serverip;
                    presence.smallImageKey = "logo";
                    presence.smallImageText = "Мод сделан Not_Simply_Kel";
                }
            } else {
                presence.state = "Одиночный мир";
                if (DimensionType.THE_END_ID.equals(dimKey)) {
                    presence.smallImageKey = "end";
                    presence.smallImageText = "Находится в \"Эндер мире\"";
                } else if (DimensionType.THE_NETHER_ID.equals(dimKey)) {
                    presence.smallImageKey = "nether";
                    presence.smallImageText = "Находится в \"Нижнем мире\"";
                } else {
                    presence.smallImageKey = "overworld";
                    presence.smallImageText = "Находится в \"Верхнем мире\"";
                }
            }
            lib.Discord_UpdatePresence(presence);

        } else {
            basicPresence();
        }
    }
}