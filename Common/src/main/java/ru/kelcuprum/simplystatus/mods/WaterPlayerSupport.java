package ru.kelcuprum.simplystatus.mods;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.waterplayer.WaterPlayer;
import ru.kelcuprum.waterplayer.frontend.localization.Music;

public class WaterPlayerSupport {
    public String artist = "";
    public String title = "";
    public Boolean useFile = false;
    public String file = "file.mp4";
    public String cover = "";
    public Boolean artistIsNull = true;
    public Boolean paused = false;
    public WaterPlayerSupport(){
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
        AudioTrack track = manager.getPlayingTrack();
        if(track.getInfo().artworkUrl != null) cover = track.getInfo().artworkUrl;
        artist = Music.getAuthor(track);
        artistIsNull = artist.isBlank();
        useFile = Music.isFile(track);
        title = Music.getTitle(track);
        file = useFile ? title : "";

    }
}
