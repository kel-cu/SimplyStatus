package ru.kelcuprum.simplystatus.presence;

import com.jagrosh.discordipc.entities.RichPresence;
import ru.kelcuprum.simplystatus.SimplyStatus;

public class LoadingGame {
    /**
     * Состояние: <br>
     * Причины:
     */
    public LoadingGame(){
        RichPresence.Builder presence = new RichPresence.Builder();
        presence.setDetails("Minecraft Loading Resources...");
        presence.setLargeImage(SimplyStatus.ASSETS.logo);
        if(SimplyStatus.isPEnable < 0.03) {
            presence.setLargeImage("https://cdn.kelcuprum.ru/simplystatus/what/wm/en_yawn.png");
            presence.setState("The world machine hasn't started");
        }
        SimplyStatus.updateDiscordPresence(presence.build());
    }
}
