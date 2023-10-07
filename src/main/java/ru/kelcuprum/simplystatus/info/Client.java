package ru.kelcuprum.simplystatus.info;

import com.mojang.blaze3d.platform.GlUtil;
import net.minecraft.client.Minecraft;
import ru.kelcuprum.simplystatus.SimplyStatus;

public class Client {
    static Minecraft CLIENT = Minecraft.getInstance();
    public static String getVersion(){
        return SimplyStatus.version;
    }
    public static String getVersionStatusColorCup(){
        return Utils.fixFormatCodes(SimplyStatus.isDevBuild ? "&c☕&r" :
                SimplyStatus.isBetaBuild ? "&6☕&r" :
                        "&a☕&r");
    }
    public static String getScreenResolution(){
        return String.format("%dx%d",CLIENT.getWindow().getWidth(), CLIENT.getWindow().getHeight());
    }
    public static String getGPURender(){
        return GlUtil.getRenderer();
    }
    public static String getVendor(){
        return GlUtil.getVendor();
    }
    public static String getCPUInfo(){
        return GlUtil.getCpuInfo();
    }
}
