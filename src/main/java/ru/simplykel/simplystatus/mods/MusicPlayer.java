package ru.simplykel.simplystatus.mods;

import info.u_team.music_player.lavaplayer.api.audio.IAudioTrackInfo;
import info.u_team.music_player.lavaplayer.api.queue.ITrackManager;
import info.u_team.music_player.musicplayer.MusicPlayerManager;

/**
 * Music Player Fabric support
 */
public class MusicPlayer {
    public String artist = "";
    public String title = "";
    public Boolean useFile = false;
    public String file = "file.mp4";
    public Boolean artistIsNull = true;
    public Boolean paused = false;
    public MusicPlayer(){
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
            file = Track.getURI();
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
}
