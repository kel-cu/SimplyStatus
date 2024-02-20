package ru.kelcuprum.simplystatus.info;

import net.minecraft.client.gui.screens.ConnectScreen;
import net.minecraft.client.gui.screens.DisconnectedScreen;
import net.minecraft.client.gui.screens.LevelLoadingScreen;
import net.minecraft.client.gui.screens.ProgressScreen;
import net.minecraft.client.gui.screens.ReceivingLevelScreen;

import static ru.kelcuprum.simplystatus.SimplyStatus.MINECRAFT;

public class Client {
    public static int getState(){
        if (MINECRAFT.screen instanceof LevelLoadingScreen) return 1;
        else if (MINECRAFT.screen instanceof ProgressScreen || MINECRAFT.screen instanceof ConnectScreen || MINECRAFT.screen instanceof ReceivingLevelScreen) return 2;
        else if (MINECRAFT.screen instanceof DisconnectedScreen) return 3;
        else return 0;
    }
}
