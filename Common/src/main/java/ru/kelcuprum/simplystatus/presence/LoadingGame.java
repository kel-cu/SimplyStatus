package ru.kelcuprum.simplystatus.presence;

import com.jagrosh.discordipc.entities.ActivityType;
import com.jagrosh.discordipc.entities.RichPresence;
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
        if(SimplyStatus.isPEnable < 0.03) {
            presence.setLargeImage("https://cdn.kelcuprum.ru/simplystatus/what/wm/en_yawn.png");
            presence.setState("The world machine hasn't started");
        }
        SimplyStatus.sendPresence(presence.build());
    }
}
