package ru.kelcuprum.simplystatus.presence;

import com.jagrosh.discordipc.entities.ActivityType;
import com.jagrosh.discordipc.entities.RichPresence;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.config.Assets;

public class MainMenu {
    public MainMenu(){
        RichPresence.Builder presence = new RichPresence.Builder();
        presence.setActivityType(ActivityType.Playing);
        presence.setLargeImage(Assets.getSelected().getIcon("logo"), SimplyStatus.localization.getLocalization("mainmenu.icon", true));
        if(SimplyStatus.localization.getLocalization("mainmenu", false).equals("simplystatus.presence.mainmenu")) presence.setState("Resources unready!");
        else  {
            presence.setDetails(SimplyStatus.localization.getLocalization("mainmenu", true));
            if(SimplyStatus.localization.getLocalization("mainmenu.state", true).equals("simplystatus.presence.mainmenu.state"))  presence.setDetails("Please wait...");
            else presence.setState(SimplyStatus.localization.getLocalization("mainmenu.state", true));
        }
        SimplyStatus.updateContentPresenceByConfigs(presence, false, true);

        if(SimplyStatus.isMusicModsEnable){
            if(SimplyStatus.userConfig.getBoolean("VIEW_MUSIC_LISTENER", false) && !SimplyStatus.waterPlayerSupport.paused){
                presence.setState(SimplyStatus.waterPlayerSupport.artistIsNull ? SimplyStatus.localization.getLocalization("mod.music.menu.noauthor", true) : SimplyStatus.localization.getLocalization("mod.music.menu", true));
            }
        }

        SimplyStatus.sendPresence(presence.build());
    }
}
