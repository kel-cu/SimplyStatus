package ru.simplykel.simplystatus.mods;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import ru.simplykel.kelutils.client.Main;

/**
 * Kel Utils/Music Player support
 */
public class KelUtils {
    public String artist = "";
    public String title = "";
    public Boolean useFile = false;
    public String file = "file.mp4";
    public Boolean artistIsNull = true;
    public Boolean paused = false;
    public KelUtils(){
        if(Main.music.getAudioPlayer() == null){
            paused = true;
            return;
        }
        AudioPlayer manager = Main.music.getAudioPlayer();
        if (manager.isPaused() || manager.getPlayingTrack() == null) {
            paused = true;
            return;
        }
        AudioTrackInfo Track = manager.getPlayingTrack().getInfo();
        if(Track.author.equals("Unknown artist") && Track.title.equals("Unknown title")) {
            useFile = true;
            file = Track.uri;
        } else {
            if (!Track.author.equals("")) {
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
