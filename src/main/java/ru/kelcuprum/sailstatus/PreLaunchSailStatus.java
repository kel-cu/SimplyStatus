package ru.kelcuprum.sailstatus;

import net.fabricmc.loader.api.entrypoint.PreLaunchEntrypoint;
import ru.kelcuprum.alinlib.AlinLogger;

import java.io.File;

public class PreLaunchSailStatus implements PreLaunchEntrypoint {
    static AlinLogger logger = new AlinLogger("SailStatus/preLaunch");
    @Override
    public void onPreLaunch() {
        checkFolders();
    }
    public static void checkFolders(){
        File file = new File("config/SailStatus");
        File file1 = new File("config/SailStatus");
        if(file.exists() && !file1.exists())
            if(file.renameTo(file1)) logger.log("The configs have been moved");
    }
}
