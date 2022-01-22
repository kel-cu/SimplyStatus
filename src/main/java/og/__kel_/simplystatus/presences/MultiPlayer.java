package og.__kel_.simplystatus.presences;

import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import com.pequla.server.ping.ServerPing;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import og.__kel_.simplystatus.SimplyStatusClient;
import og.__kel_.simplystatus.SimplyStatusConfig;
import og.__kel_.simplystatus.SimplyStatusMain;
import og.__kel_.simplystatus.SimplyStatusTranslate;
import og.__kel_.simplystatus.info.Game;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Objects;

public class MultiPlayer {
    public MultiPlayer(DiscordRPC lib, MinecraftClient mc, SimplyStatusTranslate Translate, Long start_time, SimplyStatusConfig cfg){
        Game game = new Game();
        DiscordRichPresence presence = new DiscordRichPresence();
        String PlayerName = new TranslatableText("status.simplystatus.unknownNamePlayer").getString();
        var worldTime = mc.world.getLunarTime();
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
                presence.details = Translate.text_isSleep.replace("%username%", SimplyStatusMain.DiscordName);
            }else if(mc.player.isSneaking()){
                presence.details = Translate.text_isSneaking.replace("%username%", SimplyStatusMain.DiscordName);
            }else {
                game.item(mc, presence,Translate,PlayerName);
            }
        }
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
            presence.state = Translate.text_UnknownServer;
        } else {
            if (SimplyStatusClient.ViewIPAddress) {
                //presence.state = PlayerName + " | " +
                if(SimplyStatusClient.ViewUsername) {
                    presence.state = Translate.text_information.replace("%username%", PlayerName).replace("%scene%", server_string);
                } else {
                    presence.state = Translate.text_information.replace("%username%", SimplyStatusMain.DiscordName).replace("%scene%", server_string);
                }
            } else {
                //presence.state = PlayerName + " | " + Translate.text_HideIP;
                if(SimplyStatusClient.ViewUsername) {
                    presence.state = Translate.text_information.replace("%username%", PlayerName).replace("%scene%", Translate.text_HideIP);
                } else {
                    presence.state = Translate.text_information.replace("%username%", SimplyStatusMain.DiscordName).replace("%scene%", Translate.text_HideIP);
                }
            }
            presence.smallImageKey = "logo";
            presence.smallImageText = game.getVersion(mc);

            // time
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
            if(mctime < 0 && mctime > 24000){
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

            } else if(mctime < 24000 && mctime > 16500){
                presence.largeImageKey = "night";
                presence.largeImageText = Translate.text_night;
            }
        }
        presence.startTimestamp = start_time;

        lib.Discord_UpdatePresence(presence);
    }
}
