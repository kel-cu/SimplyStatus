package og.__kel_.simplystatus.info;

import club.minnced.discord.rpc.DiscordRichPresence;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.SharedConstants;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import og.__kel_.simplystatus.client.MainClient;
import og.__kel_.simplystatus.client.HotKeys;
import og.__kel_.simplystatus.Translate;
import su.plo.voice.client.VoiceClient;

import java.text.DecimalFormat;

public class Client {
    public String getVersion(MinecraftClient mc) {
        return "Minecraft " + SharedConstants.getGameVersion().getName() + 
        " (" + mc.getGameVersion() + "/" + ClientBrandRetriever.getClientModName() + ("release".equalsIgnoreCase(mc.getVersionType()) ? "" : "/" + mc.getVersionType()) + ")";
    }
    public String getName(MinecraftClient mc){
        if(HotKeys.viewUsername){
            return mc.getSession().getUsername();
        } else {
            return MainClient.player.username;
        }
    }
    public void info(MinecraftClient mc, DiscordRichPresence presence, Translate Translate){
        ItemStack main_item = mc.player.getStackInHand(Hand.MAIN_HAND);
        ItemStack off_item = mc.player.getStackInHand(Hand.OFF_HAND);
        String mitem = main_item.getItem().toString();
        String oitem = off_item.getItem().toString();
        if (!mitem.equals("air")) {
            item(mc, main_item, presence, Translate);
        } else {
            if(HotKeys.viewOffHand){
                if (!oitem.equals("air")) {
                    item(mc, off_item, presence, Translate);
                } else {
                    statistics(mc, Translate, presence);
                }
            } else {
                statistics(mc, Translate, presence);
            }
        }
        for(int i = 0; i < MainClient.OhNoCringe.length; i = i+1){
            presence.details = presence.details.replace(MainClient.OhNoCringe[i], "");
        }
    }
    public void time(MinecraftClient mc, DiscordRichPresence presence, Translate Translate, Assets assets){
        String world = mc.player.world.getRegistryKey().getValue().toString();
        long worldTime = mc.player.world.getLunarTime();
        if (world.equals("minecraft:the_end")) {
            presence.largeImageKey = assets.end;
            presence.largeImageText = Translate.end;
        } else if (world.equals("minecraft:the_nether")) {
            presence.largeImageKey = assets.nether;
            presence.largeImageText = Translate.nether;
        } else if(world.equals("minecraft:overworld")){
            presence.smallImageKey = assets.overworld;
            presence.smallImageText = Translate.overworld;
            long worldDays;
            long daysPerTick;
            long dayTime;
            if(worldTime > 24000){
                worldDays = (int) (worldTime / 24000);
                daysPerTick = worldDays * 24000;
                dayTime = (int) (worldTime - daysPerTick);
            }else{
                dayTime = (int) worldTime;
            }
            if (dayTime < 6000 && dayTime > 0) {
                presence.largeImageKey = assets.morning;
                presence.largeImageText = Translate.morning;
            } else if (dayTime < 12000 && dayTime > 6000) {
                presence.largeImageKey = assets.day;
                presence.largeImageText = Translate.day;
            } else if (dayTime < 16500 && dayTime > 12000) {
                presence.largeImageKey = assets.evening;
                presence.largeImageText = Translate.evening;
            } else if (dayTime < 24000 && dayTime > 16500) {
                presence.largeImageKey = assets.night;
                presence.largeImageText = Translate.night;
            } else {
                presence.largeImageKey = assets.overworld;
                presence.largeImageText = Translate.overworld;
                presence.smallImageKey = null;
                presence.smallImageText = null;
            }
        } else {
            presence.largeImageKey = assets.Unknown;
            presence.largeImageText = "What?";
        }
    }
    public void item(MinecraftClient mc, ItemStack item, DiscordRichPresence presence, Translate Translate){
        if(item.getCount() == 1){
            presence.details = Translate.item.replace("%item%", item.getName().getString());
        } else if(item.getCount() == item.getMaxCount()){
            int stacks = (int) Math.floor(item.getCount() / item.getMaxCount());
            presence.details = Translate.item.replace("%item%", item.getName().getString()) + Translate.stacks.replace("%count%",String.valueOf(stacks));
        } else {
            if(mc.getGame().getSelectedLanguage().getCode().equals("ru_ru")){
                if(item.getCount() >= 21){
                    if(String.valueOf(item.getCount()).endsWith("1") ||
                            String.valueOf(item.getCount()).endsWith("2") ||
                            String.valueOf(item.getCount()).endsWith("3") ||
                            String.valueOf(item.getCount()).endsWith("4")){
                        presence.details = Translate.item.replace("%item%", item.getName().getString()) + Translate.pieces_ru_2_4.replace("%count%",String.valueOf(item.getCount()));
                    } else {
                        presence.details = Translate.item.replace("%item%", item.getName().getString()) + Translate.pieces.replace("%count%",String.valueOf(item.getCount()));
                    }
                }
                else {
                    presence.details = Translate.item.replace("%item%", item.getName().getString()) + Translate.pieces.replace("%count%",String.valueOf(item.getCount()));
                }
            } else {
                presence.details = Translate.item.replace("%item%", item.getName().getString()) + Translate.pieces.replace("%count%",String.valueOf(item.getCount()));
            }
        }
    }
    public void statistics(MinecraftClient mc, Translate Translate, DiscordRichPresence presence){
        if(HotKeys.viewStatic){
            if(mc.player.isDead()) {
                var randomNumber = Math.floor(Math.random() * 2);
                if(!MainClient.lastMessageDeath){
                    if(randomNumber == 0){
                        presence.details = Translate.replaceText(Translate.deathOne, false,false,!mc.isInSingleplayer(), new Client());
                    }else if(randomNumber == 1){
                        presence.details = Translate.replaceText(Translate.deathTwo, false,false,!mc.isInSingleplayer(), new Client());
                    }else if(randomNumber == 2){
                        presence.details = Translate.replaceText(Translate.deathThree, false,false,!mc.isInSingleplayer(), new Client());
                    }
                    MainClient.lastTextDeath = presence.details;
                    MainClient.lastMessageDeath = true;
                } else {
                    presence.details = MainClient.lastTextDeath;
                }
            } else {
                if(MainClient.lastMessageDeath) {
                    MainClient.lastMessageDeath = false;
                }
                DecimalFormat df = new DecimalFormat("#.#");
                String playerHealth = df.format(mc.player.getHealth() / 2);
                String playerHealthMax = df.format(mc.player.getMaxHealth() / 2);
                String playerArmor = df.format(mc.player.getArmor() / 2);
                if(voiceSpeaking(presence, mc, Translate)){
                    return;
                };
                presence.details = playerHealth + " • " + playerHealthMax + "❤ • " + playerArmor + "🛡️";
            }
        } else {
            if(voiceSpeaking(presence, mc, Translate)){
                return;
            };
            presence.details = Translate.replaceText(Translate.air, false,false,false, this);
        }
    }
    public boolean voiceSpeaking(DiscordRichPresence presence, MinecraftClient mc, Translate Translate){
        boolean plasmo = FabricLoader.getInstance().getModContainer("plasmo_voice").isPresent();
        if(plasmo && HotKeys.viewPlasmoVoice){
            if(VoiceClient.isConnected()){
                if(VoiceClient.isSpeaking()){
                    //MainClient.log.info("Players: %c", mc.world.getPlayers().size());
                    if(mc.world.getPlayers().size() == 1){
                        presence.details = Translate.replaceText(Translate.voice_one, false,false,false, this);
                    } else if(mc.world.getPlayers().size() == 2){
                        if(mc.world.getPlayers().get(0).getDisplayName().equals(mc.world.getPlayers().get(1).getDisplayName())){
                            presence.details = Translate.replaceText(Translate.voice_one, false,false,false, this);
                        } else {
                            PlayerEntity player = mc.world.getPlayers().get(0);
                            if(player == mc.player){
                                player = mc.world.getPlayers().get(1);
                            }
                            presence.details = Translate.replaceText(Translate.voice_two.replace("%pl2%", player.getDisplayName().getString()), false,false,false, this);
                        }
                    } else {
                        presence.details = Translate.replaceText(Translate.voice, false, false, false, this);
                    }
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
