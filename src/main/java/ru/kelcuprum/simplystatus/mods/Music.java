package ru.kelcuprum.simplystatus.mods;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import info.u_team.music_player.lavaplayer.api.audio.IAudioTrackInfo;
import info.u_team.music_player.lavaplayer.api.queue.ITrackManager;
import info.u_team.music_player.musicplayer.MusicPlayerManager;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.waterplayer.WaterPlayer;

public class Music {
    public String artist = "";
    public String title = "";
    public Boolean useFile = false;
    public String file = "file.mp4";
    public Boolean artistIsNull = true;
    public Boolean paused = false;
    public Music(){
        if(SimplyStatus.waterPlayer) useWaterPlayer();
        else if(SimplyStatus.musicPlayer) useMusicPlayer();
    }
    private void useMusicPlayer(){
        if(MusicPlayerManager.getPlayer() == null){
            paused = true;
            return;
        }
        ITrackManager manager = MusicPlayerManager.getPlayer().getTrackManager();
        if (manager.isPaused() || manager.getCurrentTrack() == null) {
            paused = true;
            return;
        }
        IAudioTrackInfo Track = manager.getCurrentTrack().getInfo();
        if(Track.getAuthor().equals("Unknown artist") && Track.getTitle().equals("Unknown title")) {
            useFile = true;
            String[] fileArgs = Track.getURI().split("/");
            file = fileArgs[fileArgs.length-1];
        } else {
            if (!Track.getAuthor().equals("")) {
                artist = Track.getAuthor();
                artistIsNull = false;
            }
            title = Track.getTitle();
            if(title.contains(artist)){
                artist = "";
                artistIsNull = true;
            }
        }
    }
    private void useWaterPlayer(){
        if(WaterPlayer.music.getAudioPlayer() == null){
            paused = true;
            return;
        }
        AudioPlayer manager = WaterPlayer.music.getAudioPlayer();
        if (manager.isPaused() || manager.getPlayingTrack() == null) {
            paused = true;
            return;
        }
        AudioTrackInfo Track = manager.getPlayingTrack().getInfo();
        if(Track.author.equals("Unknown artist") && Track.title.equals("Unknown title")) {
            useFile = true;
            String[] fileArgs = Track.uri.split("/");
            if(fileArgs.length == 1) fileArgs = Track.uri.split("\\\\");
            file = fileArgs[fileArgs.length-1];
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
