package ru.kelcuprum.simplystatus.info;

import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.info.Player;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.mods.Voice;

public class SimplyPlayer {
    static boolean lastMessageDeath = false;
    static String lastTextDeath = "";
    public static String getName(){
        if(SimplyStatus.userConfig.getBoolean("VIEW_PLAYER_NAME", true) || !SimplyStatus.CONNECTED_DISCORD) return AlinLib.MINECRAFT.getUser().getName();
        else return SimplyStatus.USER.getNickname();
    }
    public static String getURLAvatar(){
        if(Player.isLicenseAccount()){
            switch (SimplyStatus.userConfig.getNumber("USE_API_RENDER", 0).intValue()){
                case 1 -> {
                    return "https://api.kelcuprum.ru/skin/render/avatar?name="+AlinLib.MINECRAFT.getUser().getName()+"&api=0&sendfile=true";
                }
                case 2 -> {
                    return "https://api.kelcuprum.ru/skin/render?name="+AlinLib.MINECRAFT.getUser().getName()+"&api=0&head=true&sendfile=true";
                }
                case 3 -> {
                    return SimplyStatus.USER.getAvatarUrl();
                }
                default -> {
                    return "https://crafthead.net/helm/"+AlinLib.MINECRAFT.getUser().getProfileId()+"/512";
                }
            }
        } else {
            if(SimplyStatus.CONNECTED_DISCORD) return SimplyStatus.USER.getAvatarUrl();
            else return "https://kelcuprum.ru/ass/other/error.png";
        }
    }
    public static String getState(){
        if(AlinLib.MINECRAFT.player == null) return "";
        if(AlinLib.MINECRAFT.player.isDeadOrDying()){
            double randomNumber = Math.floor(Math.random() * 2);
            if(!lastMessageDeath){
                String message;
                if(randomNumber == 0) message = SimplyStatus.localization.getLocalization("death.one", true);
                else if(randomNumber == 1) message = SimplyStatus.localization.getLocalization("death.two", true);
                else message = SimplyStatus.localization.getLocalization("death.three", true);
                lastTextDeath = message;
                lastMessageDeath = true;
                return message;
            } else {
                return lastTextDeath;
            }
        } else if(!SimplyStatus.userConfig.getBoolean("SHOW_ITEMS", true) || (getItemName().isBlank() && SimplyStatus.userConfig.getBoolean("SHOW_ITEMS", true))){
            if(lastMessageDeath) lastMessageDeath = false;
            if(SimplyStatus.userConfig.getBoolean("VIEW_STATISTICS", true)){
                if(AlinLib.MINECRAFT.player.isSleeping()) return SimplyStatus.localization.getLocalization("player.sleep", true);
                else if(AlinLib.MINECRAFT.player.isCrouching()) return SimplyStatus.localization.getLocalization("player.sneak", true);
                else if(AlinLib.MINECRAFT.player.isOnFire()) return SimplyStatus.localization.getLocalization("player.on.fire", true);
                else if(AlinLib.MINECRAFT.player.isInLava()) return SimplyStatus.localization.getLocalization("player.on.lava", true);
                else if(AlinLib.MINECRAFT.player.isUnderWater()) return SimplyStatus.localization.getLocalization("player.on.water", true);
                else if(SimplyStatus.isVoiceModsEnable && SimplyStatus.userConfig.getBoolean("VIEW_VOICE_SPEAK", false)) {
                    Voice mod = new Voice();
                    if(mod.isSpeak){
                        if(mod.isSelfTalk) return SimplyStatus.localization.getLocalization("mod.voice", true);
                        else if(mod.isOnePlayer) return SimplyStatus.localization.getLocalization("mod.voice.players.one", true);
                        else return SimplyStatus.localization.getLocalization("mod.voice.players.more", true);
                    }
                    return SimplyStatus.localization.getLocalization("player.statistics", true);
                }
                else return SimplyStatus.localization.getLocalization("player.statistics", true);
            } else {
                return SimplyStatus.localization.getLocalization("item.air", true);
            }
        } else {
            return getItemCount() >= 2 ? SimplyStatus.localization.getLocalization("item.count", true) : SimplyStatus.localization.getLocalization("item", true);
        }
    }
    public static String getItemName(){
        if(AlinLib.MINECRAFT.player == null) return "";
        ItemStack main_hand = AlinLib.MINECRAFT.player.getItemInHand(InteractionHand.MAIN_HAND);
        ItemStack off_hand = AlinLib.MINECRAFT.player.getItemInHand(InteractionHand.OFF_HAND);
        if(!main_hand.isEmpty()) return main_hand.getHoverName().getString();
        else if(!off_hand.isEmpty() && SimplyStatus.userConfig.getBoolean("VIEW_ITEM_OFF_HAND", false)) return off_hand.getHoverName().getString();
        else return "";
    }
    public static int getItemCount(){
        if(AlinLib.MINECRAFT.player == null) return 0;
        ItemStack main_hand = AlinLib.MINECRAFT.player.getItemInHand(InteractionHand.MAIN_HAND);
        ItemStack off_hand = AlinLib.MINECRAFT.player.getItemInHand(InteractionHand.OFF_HAND);
        if(!main_hand.isEmpty()) return main_hand.getCount();
        else if(!off_hand.isEmpty() && SimplyStatus.userConfig.getBoolean("VIEW_ITEM_OFF_HAND", false)) return off_hand.getCount();
        else return 0;
    }
    public static String getHealth(){
        if(AlinLib.MINECRAFT.player == null) return "";
        return SimplyStatus.DF.format(AlinLib.MINECRAFT.player.getHealth()/2);
    }
    public static String getMaxHealth(){
        if(AlinLib.MINECRAFT.player == null) return "";
        return SimplyStatus.DF.format(AlinLib.MINECRAFT.player.getAttributeValue(Attributes.MAX_HEALTH)/2);
    }
    public static String getPercentHealth(){
        if(AlinLib.MINECRAFT.player == null) return "";
        return SimplyStatus.DF.format((AlinLib.MINECRAFT.player.getHealth()*100)/AlinLib.MINECRAFT.player.getAttributeValue(Attributes.MAX_HEALTH));
    }
    public static String getArmor(){
        if(AlinLib.MINECRAFT.player == null) return "";
        return SimplyStatus.DF.format(AlinLib.MINECRAFT.player.getArmorValue()/2);
    }
    public static String getX(){
        if(AlinLib.MINECRAFT.player == null) return "";
        return SimplyStatus.DF.format(AlinLib.MINECRAFT.player.position().x);
    }
    public static String getY(){
        if(AlinLib.MINECRAFT.player == null) return "";
        return SimplyStatus.DF.format(AlinLib.MINECRAFT.player.position().y);
    }
    public static String getZ(){
        if(AlinLib.MINECRAFT.player == null) return "";
        return SimplyStatus.DF.format(AlinLib.MINECRAFT.player.position().z);
    }
    public static String getDirection(boolean oneSymbol){
        if(AlinLib.MINECRAFT.player == null) return "";
        Direction direction = AlinLib.MINECRAFT.player.getDirection();
        return switch (direction) {
            case NORTH -> oneSymbol ? "N" : SimplyStatus.localization.getLocalization("north", false);
            case SOUTH -> oneSymbol ? "S" : SimplyStatus.localization.getLocalization("south", false);
            case WEST -> oneSymbol ? "W" : SimplyStatus.localization.getLocalization("west", false);
            case EAST -> oneSymbol ? "E" : SimplyStatus.localization.getLocalization("east", false);
            default -> oneSymbol ? "?" : SimplyStatus.localization.getLocalization("unknown", false);
        };
    }
    public static long getPing() {
        if (AlinLib.MINECRAFT.getCurrentServer() == null) return 0;
        return AlinLib.MINECRAFT.getCurrentServer().ping;
    }
}
