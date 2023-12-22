package ru.kelcuprum.simplystatus.presence;

import ru.kelcuprum.simplystatus.info.DiscordRichPresence;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.localization.Localization;
import ru.kelcuprum.simplystatus.mods.Music;

public class MainMenu {
    public MainMenu(){
        DiscordRichPresence presence = new DiscordRichPresence();
        if(Localization.getLocalization("mainmenu", false).equals("simplystatus.presence.mainmenu")) presence.details = "Resources unready!";
        else  {
            presence.details = Localization.getLocalization("mainmenu", true);
            if(Localization.getLocalization("mainmenu.state", true).equals("simplystatus.presence.mainmenu.state"))  presence.details = "Please wait...";
            else presence.state = Localization.getLocalization("mainmenu.state", true);
        }
        if(SimplyStatus.isMusicModsEnable){
            Music music = new Music();
            if(SimplyStatus.userConfig.getBoolean("VIEW_MUSIC_LISTENER", false) && !music.paused){
                presence.state = music.artistIsNull ? Localization.getLocalization("mod.music.menu.noauthor", true) : Localization.getLocalization("mod.music.menu", true);
            }
        }
        presence.largeImageKey = SimplyStatus.ASSETS.logo;
        SimplyStatus.updateContentPresenceByConfigs(presence);
        SimplyStatus.updateDiscordPresence(presence);
    }
}
