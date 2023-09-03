package ru.kelcuprum.simplystatus.info;

import io.github.bumblesoftware.fastload.client.BuildingTerrainScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ConnectScreen;
import net.minecraft.client.gui.screens.DisconnectedScreen;
import net.minecraft.client.gui.screens.LevelLoadingScreen;
import net.minecraft.client.gui.screens.ProgressScreen;
import net.minecraft.client.gui.screens.ReceivingLevelScreen;
import ru.kelcuprum.simplystatus.SimplyStatus;

public class Game {
    static Minecraft CLIENT = Minecraft.getInstance();
    public static int getState(){
        if (CLIENT.screen instanceof LevelLoadingScreen || (SimplyStatus.fastload && CLIENT.screen instanceof BuildingTerrainScreen)) {
            return 1;
        } else if (CLIENT.screen instanceof ProgressScreen || CLIENT.screen instanceof ConnectScreen || CLIENT.screen instanceof ReceivingLevelScreen) {
            return 2;
        } else  if (CLIENT.screen instanceof DisconnectedScreen) {
            return 3;
        } else {
            return 0;
        }
    }
}
