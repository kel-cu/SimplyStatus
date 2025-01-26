package ru.kelcuprum.sailstatus.presence.ingame;

import com.jagrosh.discordipc.entities.ActivityType;
import com.jagrosh.discordipc.entities.RichPresence;
import ru.kelcuprum.sailstatus.SailStatus;
import ru.kelcuprum.sailstatus.config.Assets;

public class SaveWorld {
    public SaveWorld(){
        RichPresence.Builder presence = new RichPresence.Builder().setActivityType(ActivityType.Playing);
        presence.setState(SailStatus.localization.getLocalization("save.world", true));
        presence.setLargeImage(Assets.getSelected().getIcon("unknown"));
        SailStatus.sendPresence(presence.build());
    }
}
