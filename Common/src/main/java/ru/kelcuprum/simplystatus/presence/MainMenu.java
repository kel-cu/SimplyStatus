package ru.kelcuprum.simplystatus.presence;

import com.jagrosh.discordipc.entities.RichPresence;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.mods.Music;

public class MainMenu {
    public MainMenu(){
        RichPresence.Builder presence = new RichPresence.Builder();
        presence.setLargeImage(SimplyStatus.ASSETS.logo);
        if(SimplyStatus.localization.getLocalization("mainmenu", false).equals("simplystatus.presence.mainmenu")) presence.setState("Resources unready!");
        else  {
            presence.setDetails(SimplyStatus.localization.getLocalization("mainmenu", true));
            if(SimplyStatus.localization.getLocalization("mainmenu.state", true).equals("simplystatus.presence.mainmenu.state"))  presence.setDetails("Please wait...");
            else presence.setState(SimplyStatus.localization.getLocalization("mainmenu.state", true));
        }
        SimplyStatus.updateContentPresenceByConfigs(presence);

        if(SimplyStatus.isMusicModsEnable){
            Music music = new Music();
            if(SimplyStatus.userConfig.getBoolean("VIEW_MUSIC_LISTENER", false) && !music.paused){
                presence.setState(music.artistIsNull ? SimplyStatus.localization.getLocalization("mod.music.menu.noauthor", true) : SimplyStatus.localization.getLocalization("mod.music.menu", true));
            }
        }

        SimplyStatus.updateDiscordPresence(presence.build());
    }
}
