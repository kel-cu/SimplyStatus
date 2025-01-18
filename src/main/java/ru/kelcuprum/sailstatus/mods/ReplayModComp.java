package ru.kelcuprum.sailstatus.mods;

import com.replaymod.replay.*;
import org.apache.logging.log4j.Level;
import ru.kelcuprum.sailstatus.SailStatus;

public class ReplayModComp {

    public static boolean isInReplay(){
        return SailStatus.replayMod && ReplayModReplay.instance.getReplayHandler() != null;
    }

    public String name = "";
    public String address = "";
    public long date = 0;
    /**
     * ReplayMod support
     */
    public ReplayModComp() {
        try {
            ReplayHandler handler = ReplayModReplay.instance.getReplayHandler();
            name = handler.getReplayFile().getMetaData().getCustomServerName();
            address = handler.getReplayFile().getMetaData().getServerName();
            date = handler.getReplayFile().getMetaData().getDate();
        } catch(Exception e){
            SailStatus.log(e.getLocalizedMessage(), Level.ERROR);
        }
    }
}
