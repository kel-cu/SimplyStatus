package ru.kelcuprum.sailstatus.mods;

import com.moulberry.flashback.Flashback;
import org.apache.logging.log4j.Level;
import ru.kelcuprum.sailstatus.SailStatus;

import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;

public class FlashbackComp {
    public static boolean isInReplay(){
        return SailStatus.klashback && Flashback.isInReplay();
    }
    public String name = "";
    public long date = 0;
    /**
     * ReplayMod support
     */
    public FlashbackComp() {
        try {
            if(isInReplay()){
                name = Flashback.getReplayServer().getMetadata().name;
                BasicFileAttributeView attributeView = Files.getFileAttributeView(Flashback.getReplayFolder(), BasicFileAttributeView.class);
                BasicFileAttributes basicFileAttributes = attributeView.readAttributes();
                date = Math.max(basicFileAttributes.creationTime().toMillis(), basicFileAttributes.lastModifiedTime().toMillis());
            }
        } catch(Exception e){
            SailStatus.log(e.getLocalizedMessage(), Level.ERROR);
        }
    }
}
