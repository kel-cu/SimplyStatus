package og.__kel_.simplystatus.info;

import club.minnced.discord.rpc.DiscordRichPresence;
import info.u_team.music_player.lavaplayer.api.queue.ITrackManager;
import info.u_team.music_player.musicplayer.MusicPlayerManager;
import og.__kel_.simplystatus.client.MainClient;

public class  Music {
    private final ITrackManager manager;
    private boolean paused = false;
    private String author = "";
    private String track = "";
    private boolean authorEnable = false;

    public Music() {
        manager = MusicPlayerManager.getPlayer().getTrackManager();
        if (manager.isPaused() || manager.getCurrentTrack() == null) {
            paused = true;
            return;
        }
        if (manager.getCurrentTrack().getInfo().getAuthor().equals("Unknown artist") && manager.getCurrentTrack().getInfo().getTitle().equals("Unknown title")) {
            track = manager.getCurrentTrack().getInfo().getURI();
        } else {
            if (!manager.getCurrentTrack().getInfo().getAuthor().equals("")) {
                author = manager.getCurrentTrack().getInfo().getAuthor();
                authorEnable = true;
            }
            track = manager.getCurrentTrack().getInfo().getTitle();
            if(track.startsWith(author)){
                author = "";
                authorEnable = false;
            }
        }

    }

    public boolean isAuthorEnable() {
        return authorEnable;
    }

    public String getAuthor() {
        return author;
    }

    public String getTrack() {
        return track;
    }

    public boolean isPaused() {
        return paused;
    }

    public ITrackManager getManager() {
        return manager;
    }
}
