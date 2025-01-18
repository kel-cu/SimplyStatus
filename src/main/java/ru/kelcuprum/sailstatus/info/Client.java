package ru.kelcuprum.sailstatus.info;

import net.minecraft.client.gui.screens.*;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.sailstatus.SailStatus;

public class Client {
    public static int getState(){
        if(!SailStatus.GAME_STARTED) return 1;
        else if (AlinLib.MINECRAFT.screen instanceof LevelLoadingScreen) return 2;
        else if (AlinLib.MINECRAFT.screen instanceof ProgressScreen || AlinLib.MINECRAFT.screen instanceof ConnectScreen || AlinLib.MINECRAFT.screen instanceof ReceivingLevelScreen) return 3;
        else if (AlinLib.MINECRAFT.screen instanceof DisconnectedScreen) return 4;
        else return 0;
    }
}
