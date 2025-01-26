package ru.kelcuprum.sailstatus.info;

import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.info.Player;
import ru.kelcuprum.sailstatus.SailStatus;
import ru.kelcuprum.sailstatus.mods.Voice;

public class PresencePlayer {
    static boolean lastMessageDeath = false;
    public static String lastTextDeath = "";
    public static String getName(){
        if(SailStatus.userConfig.getBoolean("VIEW_PLAYER_NAME", true) || !SailStatus.CONNECTED || SailStatus.USER == null) return Player.getName();
        else return SailStatus.USER.getNickname();
    }
    public static String getURLAvatar(){
        if(Player.isLicenseAccount()){
           return switch (SailStatus.userConfig.getNumber("USE_API_RENDER", 0).intValue()){
                case 1 -> "https://api.kelcuprum.ru/skin/render/avatar?name="+AlinLib.MINECRAFT.getUser().getName()+"&api=0&sendfile=true";
                case 2 -> "https://api.kelcuprum.ru/skin/render?name="+AlinLib.MINECRAFT.getUser().getName()+"&api=0&head=true&sendfile=true";
                case 3 -> SailStatus.USER.getAvatarUrl();
                default -> "https://crafthead.net/helm/"+AlinLib.MINECRAFT.getUser().getProfileId()+"/512";
            };
        } else {
            if(SailStatus.CONNECTED) return SailStatus.USER.getAvatarUrl();
            else return "https://kelcuprum.ru/ass/other/error.png";
        }
    }
    public static String getState(){
        if(AlinLib.MINECRAFT.player == null) return "";
        if(AlinLib.MINECRAFT.player.isDeadOrDying()) return lastTextDeath;
        else if(!SailStatus.userConfig.getBoolean("SHOW_ITEMS", true) || (Player.getItemName().isBlank() && SailStatus.userConfig.getBoolean("SHOW_ITEMS", true))){
            if(lastMessageDeath) lastMessageDeath = false;
            if(SailStatus.userConfig.getBoolean("VIEW_STATISTICS", true)){
                if(AlinLib.MINECRAFT.player.isSleeping()) return SailStatus.localization.getLocalization("player.sleep", true);
                else if(AlinLib.MINECRAFT.player.isCrouching()) return SailStatus.localization.getLocalization("player.sneak", true);
                else if(AlinLib.MINECRAFT.player.isOnFire()) return SailStatus.localization.getLocalization("player.on.fire", true);
                else if(AlinLib.MINECRAFT.player.isInLava()) return SailStatus.localization.getLocalization("player.on.lava", true);
                else if(AlinLib.MINECRAFT.player.isUnderWater()) return SailStatus.localization.getLocalization("player.on.water", true);
                else if(SailStatus.isVoiceModsEnable && SailStatus.userConfig.getBoolean("VIEW_VOICE_SPEAK", false)) {
                    Voice mod = new Voice();
                    if(mod.isSpeak){
                        if(mod.isSelfTalk) return SailStatus.localization.getLocalization("mod.voice", true);
                        else if(mod.isOnePlayer) return SailStatus.localization.getLocalization("mod.voice.players.one", true);
                        else return SailStatus.localization.getLocalization("mod.voice.players.more", true);
                    }
                    return SailStatus.localization.getLocalization("player.statistics", true);
                }
                else return SailStatus.localization.getLocalization("player.statistics", true);
            } else {
                return SailStatus.localization.getLocalization("item.air", true);
            }
        } else {
            return Player.getItemCount() >= 2 ? SailStatus.localization.getLocalization("item.count", true) : SailStatus.localization.getLocalization("item", true);
        }
    }
}
