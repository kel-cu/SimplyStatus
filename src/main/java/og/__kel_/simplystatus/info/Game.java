package og.__kel_.simplystatus.info;

import club.minnced.discord.rpc.DiscordRichPresence;
import net.minecraft.SharedConstants;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Hand;
import og.__kel_.simplystatus.SimplyStatusClient;
import og.__kel_.simplystatus.SimplyStatusMain;
import og.__kel_.simplystatus.SimplyStatusTranslate;

public class Game {
    public static String getVersion(MinecraftClient mc) {
        return "Minecraft " + SharedConstants.getGameVersion().getName() + " (" + mc.getGameVersion() + "/" + ClientBrandRetriever.getClientModName() + ("release".equalsIgnoreCase(mc.getVersionType()) ? "" : "/" + mc.getVersionType()) + ")";
    }
    public void item(MinecraftClient mc, DiscordRichPresence presence, SimplyStatusTranslate Translate, String PlayerName){
        ItemStack main_item = mc.player.getStackInHand(Hand.MAIN_HAND);
        ItemStack off_item = mc.player.getStackInHand(Hand.OFF_HAND);
        String mitem = main_item.getItem().toString();
        String oitem = off_item.getItem().toString();
        if (!mitem.equals("air")) {
            if(main_item.getCount() == 1){
                presence.details = Translate.text_Item.replace("%item%", main_item.getName().getString());
            } else if(main_item.getCount() == main_item.getMaxCount()){
                int stacks = (int) Math.floor(main_item.getCount() / main_item.getMaxCount());
                presence.details = Translate.text_Item.replace("%item%", main_item.getName().getString()) + Translate.stacks.replace("%count%",String.valueOf(stacks));
            } else {
                if(mc.getGame().getSelectedLanguage().getCode().equals("ru_ru")){
                    if(main_item.getCount() <=4){
                        //presence.details = Translate.text_Item + " " + main_item.getName().getString() + " | "+main_item.getCount()+new TranslatableText("status.simplystatus.pieces-ru-2-4").getString();
                        presence.details = Translate.text_Item.replace("%item%", main_item.getName().getString()) + new TranslatableText("status.simplystatus.pieces-ru-2-4").getString().replace("%count%",String.valueOf(main_item.getCount()));
                    } else {
                        presence.details = Translate.text_Item.replace("%item%", main_item.getName().getString()) + Translate.pieces.replace("%count%",String.valueOf(main_item.getCount()));
                    }
                } else {
                    presence.details = Translate.text_Item.replace("%item%", main_item.getName().getString()) + Translate.pieces.replace("%count%",String.valueOf(main_item.getCount()));
                }
            }
        } else {
            if(SimplyStatusClient.ViewOffHand){
                if (!oitem.equals("air")) {
                    if(off_item.getCount() == 1){
                        presence.details = Translate.text_Item.replace("%item%", off_item.getName().getString());
                    } else if(off_item.getCount() == off_item.getMaxCount()){
                        int stacks = (int) Math.floor(main_item.getCount() / off_item.getMaxCount());
                        presence.details = Translate.text_Item.replace("%item%", off_item.getName().getString()) + Translate.stacks.replace("%count%",String.valueOf(stacks));
                    } else {
                        if(mc.getGame().getSelectedLanguage().getCode().equals("ru_ru")){
                            if(main_item.getCount() <=4){
                                //presence.details = Translate.text_Item + " " + main_item.getName().getString() + " | "+main_item.getCount()+new TranslatableText("status.simplystatus.pieces-ru-2-4").getString();
                                presence.details = Translate.text_Item.replace("%item%", off_item.getName().getString()) + new TranslatableText("status.simplystatus.pieces-ru-2-4").getString().replace("%count%",String.valueOf(off_item.getCount()));
                            } else {
                                presence.details = Translate.text_Item.replace("%item%", off_item.getName().getString()) + Translate.pieces.replace("%count%",String.valueOf(off_item.getCount()));
                            }
                        } else {
                            presence.details = Translate.text_Item.replace("%item%", off_item.getName().getString()) + Translate.pieces.replace("%count%",String.valueOf(off_item.getCount()));
                        }
                    }
                } else {
                    if(SimplyStatusClient.ViewStatic == true){
                        if(mc.player.isDead()) {
                            var randomNumber = Math.floor(Math.random() * 2);
                            if(randomNumber == 0){
                                presence.details = Translate.text_DeathOne.replace("%username%", SimplyStatusMain.DiscordName);
                            }else if(randomNumber == 1){
                                presence.details = Translate.text_DeathTwo.replace("%username%", SimplyStatusMain.DiscordName);
                            }else if(randomNumber == 2){
                                presence.details = Translate.text_DeathThree.replace("%username%", SimplyStatusMain.DiscordName);
                            }
                        } else {
                            var playerHealth = mc.player.getHealth() / 2;
                            var playerHealthMax = mc.player.getMaxHealth() / 2;
                            var playerArmor = mc.player.getArmor() / 2;
                            presence.details = Math.ceil(playerHealth) + "/" + Math.ceil(playerHealthMax) + "❤ | " + Math.ceil(playerArmor) + "🛡️";
                        }
                    } else {
                        presence.details = Translate.text_Air.replace("%username%", SimplyStatusMain.DiscordName);
                    }
                }
            } else {
                if(SimplyStatusClient.ViewStatic == true){
                    if(mc.player.isDead()) {
                        var randomNumber = Math.floor(Math.random() * 2);
                        if(randomNumber == 0){
                            presence.details = Translate.text_DeathOne.replace("%username%", SimplyStatusMain.DiscordName);
                        }else if(randomNumber == 1){
                            presence.details = Translate.text_DeathTwo.replace("%username%", SimplyStatusMain.DiscordName);
                        }else if(randomNumber == 2){
                            presence.details = Translate.text_DeathThree.replace("%username%", SimplyStatusMain.DiscordName);
                        }
                    } else {
                        var playerHealth = Math.ceil(mc.player.getHealth() / 2);
                        var playerHealthMax = Math.ceil(mc.player.getMaxHealth() / 2);
                        var playerArmor = Math.ceil(mc.player.getArmor() / 2);
                        presence.details = playerHealth + "/" + playerHealthMax + "❤ | " + playerArmor + "🛡️";
                    }
                } else {
                    presence.details = Translate.text_Air.replace("%username%", SimplyStatusMain.DiscordName);
                }
            }
        }
        for(int i = 0; i < SimplyStatusMain.OhNoCringe.length; i = i+1){
            presence.details = presence.details.replace(SimplyStatusMain.OhNoCringe[i], "");
        }
    }
}
