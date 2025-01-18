package ru.kelcuprum.simplystatus.mods;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.waterplayer.WaterPlayer;
import ru.kelcuprum.waterplayer.frontend.localization.MusicHelper;

public class WaterPlayerSupport {
    public Boolean artistIsNull = true;
    public Boolean paused = false;
    public void update(){
        if(SimplyStatus.waterPlayer) useWaterPlayer();
    }
    private void useWaterPlayer(){
        if(WaterPlayer.player == null) return;
        AudioPlayer manager = WaterPlayer.player.getAudioPlayer();
        if(WaterPlayer.config.getBoolean("ENABLE_DISCORD_RPC", false) || WaterPlayer.player.getAudioPlayer() == null || manager.isPaused() || manager.getPlayingTrack() == null){
            paused = true;
            return;
        }
        paused = false;
        artistIsNull = MusicHelper.isAuthorNull();
    }
}
