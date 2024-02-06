package ru.kelcuprum.simplystatus.info;

import net.minecraft.client.Minecraft;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.mods.Voice;

public class Player {
    static boolean lastMessageDeath = false;
    static String lastTextDeath = "";
    static Minecraft CLIENT = Minecraft.getInstance();
    public static String getName(){
        if(SimplyStatus.userConfig.getBoolean("VIEW_PLAYER_NAME", true) || !SimplyStatus.CONNECTED_DISCORD) return CLIENT.getUser().getName();
        else return SimplyStatus.USER.getName();
    }
    public static String getURLAvatar(){
        if(CLIENT.getUser().getType().name().equalsIgnoreCase("msa") || CLIENT.getUser().getType().name().equalsIgnoreCase("mojang")){
            switch (SimplyStatus.userConfig.getNumber("USE_API_RENDER", 0).intValue()){
                case 1 -> {
                    return "https://api.kelcuprum.ru/skin/render/avatar?name="+CLIENT.getUser().getName()+"&api=0&sendfile=true";
                }
                case 2 -> {
                    return "https://api.kelcuprum.ru/skin/render?name="+CLIENT.getUser().getName()+"&api=0&head=true&sendfile=true";
                }
                default -> {
                    return "https://crafthead.net/helm/"+CLIENT.getUser().getProfileId()+"/512";
                }
            }
        } else {
            if(SimplyStatus.CONNECTED_DISCORD) return SimplyStatus.USER.getAvatarUrl();
            else return "https://kelcuprum.ru/ass/other/error.png";
        }
    }
    public static String getState(){
        if(CLIENT.player == null) return "";
        if(CLIENT.player.isDeadOrDying()){
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
                if(CLIENT.player.isSleeping()) return SimplyStatus.localization.getLocalization("player.sleep", true);
                else if(CLIENT.player.isCrouching()) return SimplyStatus.localization.getLocalization("player.sneak", true);
                else if(CLIENT.player.isOnFire()) return SimplyStatus.localization.getLocalization("player.on.fire", true);
                else if(CLIENT.player.isInLava()) return SimplyStatus.localization.getLocalization("player.on.lava", true);
                else if(CLIENT.player.isUnderWater()) return SimplyStatus.localization.getLocalization("player.on.water", true);
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
        if(CLIENT.player == null) return "";
        ItemStack main_hand = CLIENT.player.getItemInHand(InteractionHand.MAIN_HAND);
        ItemStack off_hand = CLIENT.player.getItemInHand(InteractionHand.OFF_HAND);
        if(!main_hand.isEmpty()) return main_hand.getHoverName().getString();
        else if(!off_hand.isEmpty() && SimplyStatus.userConfig.getBoolean("VIEW_ITEM_OFF_HAND", false)) return off_hand.getHoverName().getString();
        else return "";
    }
    public static int getItemCount(){
        if(CLIENT.player == null) return 0;
        ItemStack main_hand = CLIENT.player.getItemInHand(InteractionHand.MAIN_HAND);
        ItemStack off_hand = CLIENT.player.getItemInHand(InteractionHand.OFF_HAND);
        if(!main_hand.isEmpty()) return main_hand.getCount();
        else if(!off_hand.isEmpty() && SimplyStatus.userConfig.getBoolean("VIEW_ITEM_OFF_HAND", false)) return off_hand.getCount();
        else return 0;
    }
    public static String getHealth(){
        if(CLIENT.player == null) return "";
        return SimplyStatus.DF.format(CLIENT.player.getHealth()/2);
    }
    public static String getMaxHealth(){
        if(CLIENT.player == null) return "";
        return SimplyStatus.DF.format(CLIENT.player.getMaxHealth()/2);
    }
    public static String getPercentHealth(){
        if(CLIENT.player == null) return "";
        return SimplyStatus.DF.format((CLIENT.player.getHealth()*100)/CLIENT.player.getMaxHealth());
    }
    public static String getArmor(){
        if(CLIENT.player == null) return "";
        return SimplyStatus.DF.format(CLIENT.player.getArmorValue()/2);
    }
    public static String getX(){
        if(CLIENT.player == null) return "";
        return SimplyStatus.DF.format(CLIENT.player.position().x);
    }
    public static String getY(){
        if(CLIENT.player == null) return "";
        return SimplyStatus.DF.format(CLIENT.player.position().y);
    }
    public static String getZ(){
        if(CLIENT.player == null) return "";
        return SimplyStatus.DF.format(CLIENT.player.position().z);
    }
    public static String getDirection(boolean oneSymbol){
        if(CLIENT.player == null) return "";
        Direction direction = CLIENT.player.getDirection();
        return switch (direction) {
            case NORTH -> oneSymbol ? "N" : SimplyStatus.localization.getLocalization("north", false);
            case SOUTH -> oneSymbol ? "S" : SimplyStatus.localization.getLocalization("south", false);
            case WEST -> oneSymbol ? "W" : SimplyStatus.localization.getLocalization("west", false);
            case EAST -> oneSymbol ? "E" : SimplyStatus.localization.getLocalization("east", false);
            default -> oneSymbol ? "?" : SimplyStatus.localization.getLocalization("unknown", false);
        };
    }
    public static long getPing() {
        if (CLIENT.getCurrentServer() == null) return 0;
        return CLIENT.getCurrentServer().ping;
    }
}
