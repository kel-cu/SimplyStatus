package og.__kel_.simplystatus.presences;

import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.TranslatableText;
import og.__kel_.simplystatus.SimplyStatusClient;
import og.__kel_.simplystatus.SimplyStatusConfig;
import og.__kel_.simplystatus.SimplyStatusMain;
import og.__kel_.simplystatus.SimplyStatusTranslate;
import og.__kel_.simplystatus.info.Game;
import og.__kel_.simplystatus.info.assets_rpc;

public class MultiPlayer {
    public MultiPlayer(DiscordRPC lib, MinecraftClient mc, SimplyStatusTranslate Translate, Long start_time, SimplyStatusConfig cfg){
        Game game = new Game();
        assets_rpc assets = new assets_rpc(SimplyStatusClient.Bedrock, SimplyStatusClient.CringeIcons);
        DiscordRichPresence presence = new DiscordRichPresence();
        String PlayerName = new TranslatableText("status.simplystatus.unknownNamePlayer").getString();
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
                    presence.details = Translate.text_isSleep.replace("%username%", SimplyStatusMain.player.username);
                }
            }else if(mc.player.isSneaking()){
                if(SimplyStatusClient.ViewUsername){
                    presence.details = Translate.text_isSneaking.replace("%username%", PlayerName);
                } else {
                    presence.details = Translate.text_isSneaking.replace("%username%", SimplyStatusMain.player.username);
                }
            }else {
                game.item(mc, presence,Translate,PlayerName);
            }
            String world = mc.player.world.getRegistryKey().getValue().toString();
            var worldTime = mc.player.world.getLunarTime();
            if (world.equals("minecraft:the_end")) {
                presence.largeImageKey = assets.end;
                presence.largeImageText = Translate.world_end;
            } else if (world.equals("minecraft:the_nether")) {
                presence.largeImageKey = assets.nether;
                presence.largeImageText = Translate.world_nether;
            } else if(world.equals("minecraft:overworld")){
                presence.smallImageKey = assets.overworld;
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
                    presence.largeImageKey = assets.night;
                    presence.largeImageText = Translate.text_night;
                } else if (mctime < 6000 && mctime > 0) {
                    presence.largeImageKey = assets.morning;
                    presence.largeImageText = Translate.text_morning;
                } else if (mctime < 12000 && mctime > 6000) {
                    presence.largeImageKey = assets.day;
                    presence.largeImageText = Translate.text_day;
                } else if (mctime < 16500 && mctime > 12000) {
                    presence.largeImageKey = assets.evening;
                    presence.largeImageText = Translate.text_evening;
                } else if (mctime < 24000 && mctime > 16500) {
                    presence.largeImageKey = assets.night;
                    presence.largeImageText = Translate.text_night;
                }
            } else {
                presence.largeImageKey = assets.Unknown;
                presence.largeImageText = "What?";
            }
        }
        String server_string = "";
        cfg.load(mc.getCurrentServerEntry().address);

        if (SimplyStatusClient.ViewName) {
            if(mc.getCurrentServerEntry().name == null){
                server_string = new TranslatableText("status.simplystatus.unknownNameServer").getString();
            } else {
                server_string = mc.getCurrentServerEntry().name;
            }

        } else {
            server_string = mc.getCurrentServerEntry().address;
        }
        if (server_string.equals("")) {
            presence.state = Translate.text_UnknownServer;
        } else {
            if (SimplyStatusClient.ViewIPAddress) {
                if(SimplyStatusClient.ViewUsername) {
                    presence.state = Translate.text_information.replace("%username%", PlayerName).replace("%scene%", server_string);
                } else {
                    presence.state = Translate.text_information.replace("%username%", SimplyStatusMain.DiscordName).replace("%scene%", server_string);
                }
            } else {
                if(SimplyStatusClient.ViewUsername) {
                    presence.state = Translate.text_information.replace("%username%", PlayerName).replace("%scene%", Translate.text_HideIP);
                } else {
                    presence.state = Translate.text_information.replace("%username%", SimplyStatusMain.DiscordName).replace("%scene%", Translate.text_HideIP);
                }
            }
        }
        presence.startTimestamp = start_time;

        lib.Discord_UpdatePresence(presence);
    }
}
