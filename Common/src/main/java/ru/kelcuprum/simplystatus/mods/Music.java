package ru.kelcuprum.simplystatus.mods;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.waterplayer.WaterPlayer;

public class Music {
    public String artist = "";
    public String title = "";
    public Boolean useFile = false;
    public String file = "file.mp4";
    public String cover = "";
    public Boolean artistIsNull = true;
    public Boolean paused = false;
    public Music(){
        if(SimplyStatus.waterPlayer) useWaterPlayer();
    }
    private void useWaterPlayer(){
        if(WaterPlayer.config.getBoolean("ENABLE_DISCORD_RPC", false)){
            paused = true;
            return;
        }
        if(WaterPlayer.player.getAudioPlayer() == null){
            paused = true;
            return;
        }
        AudioPlayer manager = WaterPlayer.player.getAudioPlayer();
        if (manager.isPaused() || manager.getPlayingTrack() == null) {
            paused = true;
            return;
        }
        AudioTrackInfo Track = manager.getPlayingTrack().getInfo();
        if(Track.artworkUrl != null) cover = Track.artworkUrl;
        if(Track.author.equals("Unknown artist") && Track.title.equals("Unknown title")) {
            useFile = true;
            String[] fileArgs = Track.uri.split("/");
            if(fileArgs.length == 1) fileArgs = Track.uri.split("\\\\");
            file = fileArgs[fileArgs.length-1];
        } else {
            if (!Track.author.isEmpty()) {
                artist = Track.author;
                artistIsNull = false;
            }
            title = Track.title;
            if(title.contains(artist)){
                artist = "";
                artistIsNull = true;
            }
        }
    }
}
