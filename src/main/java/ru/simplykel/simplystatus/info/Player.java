package ru.simplykel.simplystatus.info;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import ru.simplykel.simplystatus.Client;
import ru.simplykel.simplystatus.Main;
import ru.simplykel.simplystatus.config.Localization;
import ru.simplykel.simplystatus.config.UserConfig;
import ru.simplykel.simplystatus.mods.PlasmoVoice;
import ru.simplykel.simplystatus.mods.SVC;

public class Player {
    public static boolean lastMessageDeath = false;
    public static String lastTextDeath = "";
    public static String getName(){
        MinecraftClient CLIENT = MinecraftClient.getInstance();
        if(UserConfig.VIEW_PLAYER_NAME || !Client.CONNECTED_DISCORD) return CLIENT.getSession().getUsername();
        else return Client.USER.username;
    }
    public static String getURLAvatar(){
        MinecraftClient CLIENT = MinecraftClient.getInstance();
        if(CLIENT.getSession().getAccountType().getName().equals("msa") || CLIENT.getSession().getAccountType().getName().equals("mojang")){
            switch (UserConfig.USE_API_RENDER){
                case 1 -> {
                    return "https://api.kelcuprum.ru/skin/render/avatar?name="+CLIENT.getSession().getUsername()+"&api=1&sendfile=true";
                }
                case 2 -> {
                    return "https://api.kelcuprum.ru/skin/render?name="+CLIENT.getSession().getUsername()+"&api=1&head=true&sendfile=true";
                }
                default -> {
                    return "https://crafthead.net/helm/"+CLIENT.getSession().getUuidOrNull()+"/512";
                }
            }
//
        } else {
            if(Client.CONNECTED_DISCORD) return "https://cdn.discordapp.com/avatars/"+Client.USER.userId+"/"+Client.USER.avatar+".png?size=480";
            else return "https://kelcuprum.ru/ass/other/error.png";
        }
    }
    public static String getItemName(){
        MinecraftClient CLIENT = MinecraftClient.getInstance();
        ItemStack main_hand = CLIENT.player.getStackInHand(Hand.MAIN_HAND);
        String main_hand_item = main_hand.getItem().toString();
        if(main_hand_item.equals("air") && UserConfig.VIEW_ITEM_OFF_HAND){
            ItemStack off_hand = CLIENT.player.getStackInHand(Hand.OFF_HAND);
            String off_hand_item = off_hand.getItem().toString();
            if(off_hand_item.equals("air") || off_hand.getName() == null) return null;
            else return off_hand.getName().getString();
        } else {
            if(main_hand_item.equals("air") || main_hand.getName() == null) return null;
            else return main_hand.getName().getString();
        }
    }
    public static int getItemCount(){
        MinecraftClient CLIENT = MinecraftClient.getInstance();
        ItemStack main_hand = CLIENT.player.getStackInHand(Hand.MAIN_HAND);
        String main_hand_item = main_hand.getItem().toString();
        if(main_hand_item.equals("air") && UserConfig.VIEW_ITEM_OFF_HAND){
            ItemStack off_hand = CLIENT.player.getStackInHand(Hand.OFF_HAND);
            String off_hand_item = off_hand.getItem().toString();
            if(off_hand_item.equals("air") || off_hand.getName() == null) return 0;
            else return off_hand.getCount();
        } else {
            if(main_hand_item.equals("air") || main_hand.getName() == null) return 0;
            else return main_hand.getCount();
        }
    }
    public static String getState(){
        MinecraftClient CLIENT = MinecraftClient.getInstance();
        if(CLIENT.player.isDead()){
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
        } else if(!UserConfig.SHOW_ITEMS || (getItemName() == null && UserConfig.SHOW_ITEMS)){
            if(lastMessageDeath) lastMessageDeath = false;
            if(UserConfig.VIEW_STATISTICS){
                if(CLIENT.player.isSleeping()) return Localization.getLocalization("player.sleep", true);
                else if(CLIENT.player.isSneaking()) return Localization.getLocalization("player.sneak", true);
                else if(CLIENT.player.isOnFire()) return Localization.getLocalization("player.on.fire", true);
                else if(CLIENT.player.isSubmergedInWater()) return Localization.getLocalization("player.on.water", true);
                else if(Main.isVoiceModsEnable && UserConfig.VIEW_VOICE_SPEAK) {
                    if(Main.plasmo){
                        // NOT READY
                        PlasmoVoice mod = new PlasmoVoice();
                        if(mod.isSpeak){
                            if(mod.isSelfTalk) return Localization.getLocalization("mod.voice", false);
                            else if(mod.isOnePlayer) return Localization.getLocalization("mod.voice.one", false);
                            else return Localization.getLocalization("mod.voice.more", false);
                        }
                        return Localization.getLocalization("player.statistics", true);
                    } else if (Main.svc){
                        SVC mod = new SVC();
                        if(mod.isSpeak){
                            if(mod.isSelfTalk) return Localization.getLocalization("mod.voice", false);
                            else if(mod.isOnePlayer) return Localization.getLocalization("mod.voice.one", false);
                            else return Localization.getLocalization("mod.voice.more", false);
                        }
                        return Localization.getLocalization("player.statistics", true);
                    } else return Localization.getLocalization("player.statistics", true);
                }
                else return Localization.getLocalization("player.statistics", true);
            } else {
                return Localization.getLocalization("item.air", true);
            }
        } else {
            return Localization.getLocalization("item", true);
        }
    }
    public static String getTypeWorld(){
        MinecraftClient CLIENT = MinecraftClient.getInstance();
        if(CLIENT.isInSingleplayer()) return Localization.getLocalization("singleplayer", false);
        else return Localization.getLocalization("address", false);
    }
    public static String getHealth(){
        MinecraftClient CLIENT = MinecraftClient.getInstance();
        return Main.DF.format(CLIENT.player.getHealth()/2);
    }
    public static String getMaxHealth(){
        MinecraftClient CLIENT = MinecraftClient.getInstance();
        return Main.DF.format(CLIENT.player.getMaxHealth()/2);
    }
    public static String getPercentHealth(){
        MinecraftClient CLIENT = MinecraftClient.getInstance();
        return Main.DF.format((CLIENT.player.getHealth()*100)/CLIENT.player.getMaxHealth());
    }
    public static String getArmor(){
        MinecraftClient CLIENT = MinecraftClient.getInstance();
        return Main.DF.format(CLIENT.player.getArmor()/2);
    }
    public static String getX(){
        MinecraftClient CLIENT = MinecraftClient.getInstance();
        return Main.DF.format(CLIENT.player.capeX);
    }
    public static String getY(){
        MinecraftClient CLIENT = MinecraftClient.getInstance();
        return Main.DF.format(CLIENT.player.capeY);
    }
    public static String getZ(){
        MinecraftClient CLIENT = MinecraftClient.getInstance();
        return Main.DF.format(CLIENT.player.capeZ);
    }

}
