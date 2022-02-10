package og.__kel_.simplystatus.presences;

import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.dimension.DimensionType;
import og.__kel_.simplystatus.SimplyStatusClient;
import og.__kel_.simplystatus.SimplyStatusConfig;
import og.__kel_.simplystatus.SimplyStatusMain;
import og.__kel_.simplystatus.SimplyStatusTranslate;
import og.__kel_.simplystatus.info.Game;

public class SinglePlayer {
    public SinglePlayer(DiscordRPC lib, MinecraftClient mc, SimplyStatusTranslate Translate, Long start_time, SimplyStatusConfig cfg){
        Game game = new Game();
        DimensionType dimtype = mc.world.getDimension();
        Identifier dimKey = mc.world.getRegistryManager().get(Registry.DIMENSION_TYPE_KEY).getId(dimtype);
        DiscordRichPresence presence = new DiscordRichPresence();
        String PlayerName = new TranslatableText("status.simplystatus.unknownNamePlayer").getString();;
        if(mc.player!=null){
            if(mc.player.getDisplayName() != null){
                for(int i = 0; i < SimplyStatusMain.OhNoCringe.length; i = i+1){
                    if(i == 0){
                        PlayerName = mc.player.getDisplayName().getString().replace(SimplyStatusMain.OhNoCringe[i], "");
                    } else {
                        PlayerName = PlayerName.replace(SimplyStatusMain.OhNoCringe[i], "");
                    }
                }
            } else if(mc.player.getName() != null){
                for(int i = 0; i < SimplyStatusMain.OhNoCringe.length; i = i+1) {
                    if(i==0) {
                        PlayerName = mc.player.getName().getString().replace(SimplyStatusMain.OhNoCringe[i], "");
                    } else {
                        PlayerName = PlayerName.replace(SimplyStatusMain.OhNoCringe[i], "");
                    }
                }
            }
            if(mc.player.isSleeping()){
                if(SimplyStatusClient.ViewUsername){
                    presence.details = Translate.text_isSleep.replace("%username%", PlayerName);
                } else {
                    presence.details = Translate.text_isSleep.replace("%username%", SimplyStatusMain.DiscordName);
                }
            }else if(mc.player.isSneaking()){
                if(SimplyStatusClient.ViewUsername){
                    presence.details = Translate.text_isSneaking.replace("%username%", PlayerName);
                } else {
                    presence.details = Translate.text_isSneaking.replace("%username%", SimplyStatusMain.DiscordName);
                }
            }else {
                game.item(mc, presence,Translate, PlayerName);
            }
        }
        var worldTime = mc.world.getLunarTime();
            if (DimensionType.THE_END_ID.equals(dimKey)) {
                presence.largeImageKey = "end";
                presence.largeImageText = Translate.world_end;
            } else if (DimensionType.THE_NETHER_ID.equals(dimKey)) {
                presence.largeImageKey = "nether";
                presence.largeImageText = Translate.world_nether;
            } else {
                presence.smallImageKey = "overworld";
                presence.smallImageText = Translate.world_overworld;
                var mcdays = 0;
                var tipotime = 0;
                var mctime = 0;
                if(worldTime > 24000){
                    mcdays = (int) (worldTime / 24000);
                    tipotime = mcdays * 24000;
                    mctime = (int) (worldTime - tipotime);
                }else{
                    mctime = (int) worldTime;
                }
                if (mctime < 0 && mctime > 24000) {
                    presence.largeImageKey = "nigth_update_2";
                    presence.largeImageText = Translate.text_night;
                } else if (mctime < 6000 && mctime > 0) {
                    presence.largeImageKey = "morning_update_2";
                    presence.largeImageText = Translate.text_morning;
                } else if (mctime < 12000 && mctime > 6000) {
                    presence.largeImageKey = "day_update_2";
                    presence.largeImageText = Translate.text_day;
                } else if (mctime < 16500 && mctime > 12000) {
                    presence.largeImageKey = "evening_update_2";
                    presence.largeImageText = Translate.text_evening;
                } else if (mctime < 24000 && mctime > 16500) {
                    presence.largeImageKey = "nigth_update_2";
                    presence.largeImageText = Translate.text_night;
                }
            }

        //presence.state = PlayerName + " | " + Translate.text_isInSingleplayer;
        if(SimplyStatusClient.ViewUsername) {
            presence.state = Translate.text_information.replace("%username%", PlayerName).replace("%scene%", Translate.text_isInSingleplayer);
        } else {
            presence.state = Translate.text_information.replace("%username%", SimplyStatusMain.DiscordName).replace("%scene%", Translate.text_isInSingleplayer);
        }
        presence.startTimestamp = start_time;
        lib.Discord_UpdatePresence(presence);
    }
}
