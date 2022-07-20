package og.__kel_.simplystatus.presences;

import club.minnced.discord.rpc.DiscordRPC;
import club.minnced.discord.rpc.DiscordRichPresence;
import com.replaymod.compat.ReplayModCompat;
import com.replaymod.core.ReplayMod;
import com.replaymod.core.ReplayModBackend;
import com.replaymod.core.gui.RestoreReplayGui;
import com.replaymod.editor.ReplayModEditor;
import com.replaymod.editor.gui.GuiEditReplay;
import com.replaymod.extras.ReplayModExtras;
import com.replaymod.pathing.player.RealtimeTimelinePlayer;
import com.replaymod.pathing.player.ReplayTimer;
import com.replaymod.replay.ReplayHandler;
import com.replaymod.replay.ReplayModReplay;
import com.replaymod.replay.ReplaySender;
import com.replaymod.replay.Setting;
import com.replaymod.replay.gui.overlay.GuiReplayOverlay;
import com.replaymod.replay.gui.screen.GuiReplayViewer;
import com.replaymod.replaystudio.lib.viaversion.protocols.protocol1_9to1_8.storage.MovementTracker;
import com.replaymod.replaystudio.rar.state.Replay;
import com.replaymod.replaystudio.studio.ReplayStudio;
import com.replaymod.replaystudio.util.Utils;
import com.replaymod.simplepathing.ReplayModSimplePathing;
import com.replaymod.simplepathing.preview.PathPreview;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableTextContent;
import og.__kel_.simplystatus.client.HotKeys;
import og.__kel_.simplystatus.client.MainClient;
import og.__kel_.simplystatus.Translate;
import og.__kel_.simplystatus.info.Game;
import og.__kel_.simplystatus.info.assets_rpc;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class UnknownScene {
    public UnknownScene(DiscordRPC lib, MinecraftClient mc, Translate Translate, Long start_time, NullPointerException err) {
        Game game = new Game();
        assets_rpc assets = new assets_rpc(HotKeys.bedrock, HotKeys.cringeIcons);
        Translate.selectedLang();
        DiscordRichPresence MainPresence = new DiscordRichPresence();
        if(HotKeys.showTime){
            MainPresence.startTimestamp = start_time;
        }
        MainPresence.state = game.getVersion(mc);
        MainPresence.largeImageKey = assets.Unknown;
        boolean IsThereAReplayMod = FabricLoader.getInstance().getModContainer("replaymod").isPresent();
        if(IsThereAReplayMod){
            if(ReplayModReplay.instance.getReplayHandler().getReplayFile() != null){
                try{
                    MainPresence.state =ReplayModReplay.instance.getReplayHandler().getReplayFile().getMetaData().getCustomServerName();
                    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yy, hh:mm");
                    String strDate = dateFormat.format(ReplayModReplay.instance.getReplayHandler().getReplayFile().getMetaData().getDate());
                    MainPresence.details = "Replay from: " + strDate;
                }catch (Exception error){
                    error.printStackTrace();
                }
//                MainPresence.details = "Temporary plug";
                MainPresence.largeImageKey = assets.replay;
                MainPresence.largeImageText = "ReplayMod v"+ReplayMod.instance.getVersion();
            } else{
                MainClient.log.error("Ooh, there's been an error\n"+err.toString());
            }
        } else {
            MainClient.log.error("Ooh, there's been an error\n"+err.toString());
        }
        lib.Discord_UpdatePresence(MainPresence);
    }
}
