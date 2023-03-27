package ru.simplykel.simplystatus.mods;

import com.replaymod.replay.ReplayHandler;
import com.replaymod.replay.ReplayModReplay;

import java.io.IOException;

public class ReplayMod {
    public String name;
    public String address;
    public long date;
    /**
     * ReplayMod support
     */
    public ReplayMod() throws IOException {
        ReplayHandler handler = ReplayModReplay.instance.getReplayHandler();
        name = handler.getReplayFile().getMetaData().getCustomServerName();
        address = handler.getReplayFile().getMetaData().getServerName();
        date = handler.getReplayFile().getMetaData().getDate();
    }
}
