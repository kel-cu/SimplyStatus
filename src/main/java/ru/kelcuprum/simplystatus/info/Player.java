package ru.kelcuprum.simplystatus.info;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.localization.Localization;
import ru.kelcuprum.simplystatus.mods.Voice;

public class Player {
    static boolean lastMessageDeath = false;
    static String lastTextDeath = "";
    static Minecraft CLIENT = Minecraft.getInstance();
    public static String getName(){
        if(SimplyStatus.userConfig.getBoolean("VIEW_PLAYER_NAME", true) || !SimplyStatus.CONNECTED_DISCORD) return CLIENT.getUser().getName();
        else return SimplyStatus.USER.username;
    }
    public static String getURLAvatar(){
        if(CLIENT.getUser().getType().name().toLowerCase().equals("msa") || CLIENT.getUser().getType().name().toLowerCase().equals("mojang")){
            switch (SimplyStatus.userConfig.getInt("USE_API_RENDER", 0)){
                case 1 -> {
                    return "https://api.kelcuprum.ru/skin/render/avatar?name="+CLIENT.getUser().getName()+"&api=1&sendfile=true";
                }
                case 2 -> {
                    return "https://api.kelcuprum.ru/skin/render?name="+CLIENT.getUser().getName()+"&api=1&head=true&sendfile=true";
                }
                default -> {
                    return "https://crafthead.net/helm/"+CLIENT.getUser().getProfileId().toString()+"/512";
                }
            }
        } else {
            if(SimplyStatus.CONNECTED_DISCORD) return "https://cdn.discordapp.com/avatars/"+SimplyStatus.USER.userId+"/"+SimplyStatus.USER.avatar+".png?size=480";
            else return "https://kelcuprum.ru/ass/other/error.png";
        }
    }
    public static String getState(){
        if(CLIENT.player.isDeadOrDying()){
            double randomNumber = Math.floor(Math.random() * 2);
            if(!lastMessageDeath){
                String message;
                if(randomNumber == 0) message = Localization.getLocalization("death.one", true);
                else if(randomNumber == 1) message = Localization.getLocalization("death.two", true);
                else message = Localization.getLocalization("death.three", true);
                lastTextDeath = message;
                lastMessageDeath = true;
                return message;
            } else {
                return lastTextDeath;
            }
        } else if(!SimplyStatus.userConfig.getBoolean("SHOW_ITEMS", true) || (getItemName() == null && SimplyStatus.userConfig.getBoolean("SHOW_ITEMS", true))){
            if(lastMessageDeath) lastMessageDeath = false;
            if(SimplyStatus.userConfig.getBoolean("VIEW_STATISTICS", true)){
                if(CLIENT.player.isSleeping()) return Localization.getLocalization("player.sleep", true);
                else if(CLIENT.player.isCrouching()) return Localization.getLocalization("player.sneak", true);
                else if(CLIENT.player.isOnFire()) return Localization.getLocalization("player.on.fire", true);
                else if(CLIENT.player.isInLava()) return Localization.getLocalization("player.on.lava", true);
                else if(CLIENT.player.isUnderWater()) return Localization.getLocalization("player.on.water", true);
                else if(SimplyStatus.isVoiceModsEnable && SimplyStatus.userConfig.getBoolean("VIEW_VOICE_SPEAK", false)) {
                    Voice mod = new Voice();
                    if(mod.isSpeak){
                        if(mod.isSelfTalk) return Localization.getLocalization("mod.voice", true);
                        else if(mod.isOnePlayer) return Localization.getLocalization("mod.voice.one", true);
                        else return Localization.getLocalization("mod.voice.more", true);
                    }
                    return Localization.getLocalization("player.statistics", true);
                }
                else return Localization.getLocalization("player.statistics", true);
            } else {
                return Localization.getLocalization("item.air", true);
            }
        } else {
            return getItemCount() >= 2 ? Localization.getLocalization("item.count", true) : Localization.getLocalization("item", true);
        }
    }
    public static String getItemName(){
        Minecraft CLIENT = Minecraft.getInstance();
        ItemStack main_hand = CLIENT.player.getItemInHand(InteractionHand.MAIN_HAND);
        String main_hand_item = main_hand.getItem().toString();
        if(main_hand_item.equals("air") && SimplyStatus.userConfig.getBoolean("VIEW_ITEM_OFF_HAND", false)){
            ItemStack off_hand = CLIENT.player.getItemInHand(InteractionHand.OFF_HAND);
            String off_hand_item = off_hand.getItem().toString();
            if(off_hand_item.equals("air") || off_hand.getHoverName() == null) return null;
            else return off_hand.getHoverName().getString();
        } else {
            if(main_hand_item.equals("air") || main_hand.getHoverName() == null) return null;
            else return main_hand.getHoverName().getString();
        }
    }
    public static int getItemCount(){
        Minecraft CLIENT = Minecraft.getInstance();
        ItemStack main_hand = CLIENT.player.getItemInHand(InteractionHand.MAIN_HAND);
        String main_hand_item = main_hand.getItem().toString();
        if(main_hand_item.equals("air") && SimplyStatus.userConfig.getBoolean("VIEW_ITEM_OFF_HAND", false)){
            ItemStack off_hand = CLIENT.player.getItemInHand(InteractionHand.OFF_HAND);
            String off_hand_item = off_hand.getItem().toString();
            if(off_hand_item.equals("air") || off_hand.getHoverName() == null) return 0;
            else return off_hand.getCount();
        } else {
            if(main_hand_item.equals("air") || main_hand.getHoverName() == null) return 0;
            else return main_hand.getCount();
        }
    }
    public static String getHealth(){
        return SimplyStatus.DF.format(CLIENT.player.getHealth()/2);
    }
    public static String getMaxHealth(){
        return SimplyStatus.DF.format(CLIENT.player.getMaxHealth()/2);
    }
    public static String getPercentHealth(){
        return SimplyStatus.DF.format((CLIENT.player.getHealth()*100)/CLIENT.player.getMaxHealth());
    }
    public static String getArmor(){
        return SimplyStatus.DF.format(CLIENT.player.getArmorValue()/2);
    }
    public static String getX(){
        return SimplyStatus.DF.format(CLIENT.player.position().x);
    }
    public static String getY(){
        return SimplyStatus.DF.format(CLIENT.player.position().y);
    }
    public static String getZ(){
        return SimplyStatus.DF.format(CLIENT.player.position().z);
    }
    public static String getDirection(boolean oneSymbol){
        Direction direction = CLIENT.player.getDirection();
        return switch (direction) {
            case NORTH -> oneSymbol ? "N" : Localization.getLocalization("north", false);
            case SOUTH -> oneSymbol ? "S" : Localization.getLocalization("south", false);
            case WEST -> oneSymbol ? "W" : Localization.getLocalization("west", false);
            case EAST -> oneSymbol ? "E" : Localization.getLocalization("east", false);
            default -> oneSymbol ? "?" : Localization.getLocalization("unknown", false);
        };
    }
    static BlockPos.MutableBlockPos BP = new BlockPos.MutableBlockPos();
    public static long getPing() {
        if (CLIENT.getCurrentServer() == null) return 0;
        return CLIENT.getCurrentServer().ping;
    }
}
