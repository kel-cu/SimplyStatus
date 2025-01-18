package ru.kelcuprum.sailstatus.presence;

import com.jagrosh.discordipc.entities.*;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.sailstatus.SailStatus;
import ru.kelcuprum.sailstatus.config.Assets;

public class LoadingGame {
    /**
     * Состояние: <br>
     * Причины:
     */
    public LoadingGame(){
        RichPresence.Builder presence = new RichPresence.Builder().setActivityType(ActivityType.Playing);
        presence.setDetails("Loading Resources...");
        presence.setLargeImage(Assets.getSelected().getIcon("logo"));
        if(AlinLib.isAprilFool()){
            presence.setLargeImage("https://wf.kelcu.ru/mods/waterplayer/icons/clownfish.png");
            presence.setState("The cackling carousel, it spins and never stops");
        }else if(SailStatus.isPEnable < 0.03) {
            presence.setLargeImage("https://wf.kelcu.ru/mods/simplystatus/what/wm/en_yawn.png");
            presence.setState("The world machine hasn't started");
        }
        SailStatus.sendPresence(presence.build());
    }
}
