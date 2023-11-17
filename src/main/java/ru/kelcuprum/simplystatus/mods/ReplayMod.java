package ru.kelcuprum.simplystatus.mods;

import club.minnced.discord.rpc.DiscordRichPresence;
import com.replaymod.replay.ReplayHandler;
import com.replaymod.replay.ReplayModReplay;

import java.io.IOException;

public class ReplayMod {
    public static String name = "";
    public static String address = "";
    public static long date = 0;
    private static String lastException;
    /**
     * ReplayMod support
     */
    public static void update(){
        try {
            ReplayHandler handler = ReplayModReplay.instance.getReplayHandler();
            name = handler.getReplayFile().getMetaData().getCustomServerName();
            address = handler.getReplayFile().getMetaData().getServerName();
            date = handler.getReplayFile().getMetaData().getDate();
        } catch(Exception ex){
            if(lastException == null || !lastException.equals(ex.getMessage())){
                ex.printStackTrace();
                lastException = ex.getMessage();
            }
        }
    }
}
