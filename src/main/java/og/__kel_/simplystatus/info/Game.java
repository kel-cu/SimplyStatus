package og.__kel_.simplystatus.info;

import club.minnced.discord.rpc.DiscordRichPresence;
import net.minecraft.SharedConstants;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableTextContent;
import net.minecraft.util.Hand;
import og.__kel_.simplystatus.Main;
import og.__kel_.simplystatus.Client;
import og.__kel_.simplystatus.Translate;

import java.text.DecimalFormat;

public class Game {
    public String getVersion(MinecraftClient mc) {
        return "Minecraft " + SharedConstants.getGameVersion().getName() + 
        " (" + mc.getGameVersion() + "/" + ClientBrandRetriever.getClientModName() + ("release".equalsIgnoreCase(mc.getVersionType()) ? "" : "/" + mc.getVersionType()) + ")";
    }
    public void info(MinecraftClient mc, DiscordRichPresence presence, Translate Translate){
        ItemStack main_item = mc.player.getStackInHand(Hand.MAIN_HAND);
        ItemStack off_item = mc.player.getStackInHand(Hand.OFF_HAND);
        String mitem = main_item.getItem().toString();
        String oitem = off_item.getItem().toString();
        if (!mitem.equals("air")) {
            getItem(mc, main_item, presence, Translate);
        } else {
            if(Client.ViewOffHand){
                if (!oitem.equals("air")) {
                    getItem(mc, off_item, presence, Translate);
                } else {
                    getStatic(mc, Translate, presence);
                }
            } else {
                getStatic(mc, Translate, presence);
            }
        }
        for(int i = 0; i < Main.OhNoCringe.length; i = i+1){
            presence.details = presence.details.replace(Main.OhNoCringe[i], "");
        }
    }
    public void time(MinecraftClient mc, DiscordRichPresence presence, Translate Translate, assets_rpc assets){
        String world = mc.player.world.getRegistryKey().getValue().toString();
        var worldTime = mc.player.world.getLunarTime();
        if (world.equals("minecraft:the_end")) {
            presence.largeImageKey = assets.end;
            presence.largeImageText = Translate.world_end;
        } else if (world.equals("minecraft:the_nether")) {
            presence.largeImageKey = assets.nether;
            presence.largeImageText = Translate.world_nether;
        } else if(world.equals("minecraft:overworld")){
            presence.smallImageKey = assets.overworld;
            presence.smallImageText = Translate.world_overworld;
            var mcdays = 0;
            var tipotime = 0;
            var mctime = 0;
            if(worldTime > 24000){
                mcdays = (int) (worldTime / 24000);
                tipotime = mcdays * 24000;
                mctime = (int) (worldTime - tipotime);
            }else{
                mctime = (int) worldTime;
            }
            if (mctime < 6000 && mctime > 0) {
                presence.largeImageKey = assets.morning;
                presence.largeImageText = Translate.text_morning;
            } else if (mctime < 12000 && mctime > 6000) {
                presence.largeImageKey = assets.day;
                presence.largeImageText = Translate.text_day;
            } else if (mctime < 16500 && mctime > 12000) {
                presence.largeImageKey = assets.evening;
                presence.largeImageText = Translate.text_evening;
            } else if (mctime < 24000 && mctime > 16500) {
                presence.largeImageKey = assets.night;
                presence.largeImageText = Translate.text_night;
            } else {
                presence.largeImageKey = assets.overworld;
                presence.largeImageText = Translate.world_overworld;
                presence.smallImageKey = null;
                presence.smallImageText = null;
            }
        } else {
            presence.largeImageKey = assets.Unknown;
            presence.largeImageText = "What?";
        }
    }
    public String getName(MinecraftClient mc){
        if(Client.ViewUsername){
            return mc.getSession().getUsername();
        } else {
            return Main.player.username;
        }
    }
    public void getItem(MinecraftClient mc, ItemStack item, DiscordRichPresence presence, Translate Translate){
        if(item.getCount() == 1){
            presence.details = Translate.text_Item.replace("%item%", item.getName().getString());
        } else if(item.getCount() == item.getMaxCount()){
            int stacks = (int) Math.floor(item.getCount() / item.getMaxCount());
            presence.details = Translate.text_Item.replace("%item%", item.getName().getString()) + Translate.stacks.replace("%count%",String.valueOf(stacks));
        } else {
            if(mc.getGame().getSelectedLanguage().getCode().equals("ru_ru")){
                if(item.getCount() <=4){
                    //presence.details = Translate.text_Item + " " + main_item.getName().getString() + " | "+main_item.getCount()+new TranslatableText("status.simplystatus.pieces-ru-2-4").getString();
                    presence.details = Translate.text_Item.replace("%item%", item.getName().getString()) + Translate.pieces_ru_2_4.replace("%count%",String.valueOf(item.getCount()));
                } else {
                    presence.details = Translate.text_Item.replace("%item%", item.getName().getString()) + Translate.pieces.replace("%count%",String.valueOf(item.getCount()));
                }
            } else {
                presence.details = Translate.text_Item.replace("%item%", item.getName().getString()) + Translate.pieces.replace("%count%",String.valueOf(item.getCount()));
            }
        }
    }
    public void getStatic(MinecraftClient mc, Translate Translate, DiscordRichPresence presence){
        if(Client.ViewStatic){
            if(mc.player.isDead()) {
                var randomNumber = Math.floor(Math.random() * 2);
                if(!Main.lastMessageDeath){
                    if(randomNumber == 0){
                        presence.details = Translate.replaceText(Translate.text_DeathOne, false,false,!mc.isInSingleplayer(), new Game());
                    }else if(randomNumber == 1){
                        presence.details = Translate.replaceText(Translate.text_DeathTwo, false,false,!mc.isInSingleplayer(), new Game());
                    }else if(randomNumber == 2){
                        presence.details = Translate.replaceText(Translate.text_DeathThree, false,false,!mc.isInSingleplayer(), new Game());
                    }
                    Main.lastTextDeath = presence.details;
                    Main.lastMessageDeath = true;
                } else {
                    presence.details = Main.lastTextDeath;
                }
            } else {
                if(Main.lastMessageDeath) {
                    Main.lastMessageDeath = false;
                }
                DecimalFormat df = new DecimalFormat("#.#");
                String playerHealth = df.format(mc.player.getHealth() / 2);
                String playerHealthMax = df.format(mc.player.getMaxHealth() / 2);
                String playerArmor = df.format(mc.player.getArmor() / 2);
                presence.details = playerHealth + " • " + playerHealthMax + "❤ • " + playerArmor + "🛡️";
            }
        } else {
            presence.details = Translate.replaceText(Translate.text_Air, false,false,false, this);
        }
    }
}
