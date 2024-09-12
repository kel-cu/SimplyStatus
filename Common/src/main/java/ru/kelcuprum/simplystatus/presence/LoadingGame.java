package ru.kelcuprum.simplystatus.presence;

import com.jagrosh.discordipc.entities.*;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.config.Assets;

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
        }else if(SimplyStatus.isPEnable < 0.03) {
            presence.setLargeImage("https://wf.kelcu.ru/mods/simplystatus/what/wm/en_yawn.png");
            presence.setState("The world machine hasn't started");
        }
        SimplyStatus.sendPresence(presence.build());
    }
}
