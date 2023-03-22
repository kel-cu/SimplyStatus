package ru.simplykel.simplystatus.config;

import net.minecraft.SharedConstants;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import org.json.JSONObject;
import ru.simplykel.simplystatus.Client;
import ru.simplykel.simplystatus.Main;
import ru.simplykel.simplystatus.info.Game;
import ru.simplykel.simplystatus.info.Player;
import ru.simplykel.simplystatus.mods.MusicPlayer;
import ru.simplykel.simplystatus.mods.PlasmoVoice;
import ru.simplykel.simplystatus.mods.ReplayMod;
import ru.simplykel.simplystatus.mixin.MinecraftClientAccess;
import ru.simplykel.simplystatus.mods.SVC;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Localization {
    /**
     * Получение кода локализации игры который выбрал игрок<br>
     * При запуске может быть null, поэтому иногда en_us;
     */
    public static String getCodeLocalization(){
        MinecraftClient CLIENT = MinecraftClient.getInstance();
        try{
//            return CLIENT.getGame().getSelectedLanguage().getCode();
            return CLIENT.getLanguageManager().getLanguage();
        } catch (Exception e){
            return "en_us";
        }
    }

    /**
     * Получение JSON файл локализации
     * @return JSONObject
     * @throws IOException
     */
    public static JSONObject getJSONFile() throws IOException {
        MinecraftClient CLIENT = MinecraftClient.getInstance();
        File localizationFile = new File(CLIENT.runDirectory + "/SimplyStatus/lang/"+getCodeLocalization()+".json");
        if(localizationFile.exists()){
            return new JSONObject(Files.readString(localizationFile.toPath()));
        } else {
            return new JSONObject();
        }
    }

    /**
     * Получение текста локализации
     * @param type
     * @param parse
     * @return String
     */
    public static String getLocalization(String type, boolean parse){
        String text = "";
        try {
            JSONObject JSONLocalization = getJSONFile();
            if(JSONLocalization.isNull(type)) text = getText("simplystatus.presence." + type).getString();
            else text = JSONLocalization.getString(type);
        } catch (Exception e){
            e.printStackTrace();
            text = getText("simplystatus.presence." + type).getString();
        }
        if(parse) return getParsedText(text);
        else return text;
    }
    public static String getLcnDefault(String type){
        String text = getText("simplystatus.presence." + type).getString();;
        return text;
    }
    /**
     * Задать значение локализации на определённый текст в JSON файле
     * @param type
     * @param text
     */
    public static void setLocalization(String type, String text){
        try {
            JSONObject JSONLocalization = getJSONFile();
            JSONLocalization.put(type, text);
            MinecraftClient CLIENT = MinecraftClient.getInstance();
            File localizationFile = new File(CLIENT.runDirectory + "/SimplyStatus/lang/"+getCodeLocalization()+".json");
            Files.createDirectories(localizationFile.toPath().getParent());
            Files.writeString(localizationFile.toPath(), JSONLocalization.toString());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * Хуета которая может быть спасёт от Mojang которые сука постоянно меняют либо название класса либо еще что-то
     * @return MutableText
     * @param key
     */
    public static MutableText getText(String key){
        return Text.translatable(key);
    }


    /**
     * Кто прочтёл описание, тот крутой, как мега база PWGood'а на Майншилде
     * @param text
     * @return String
     */
    public static String getParsedText(String text){
        String parsedText = text;
        MinecraftClient CLIENT = MinecraftClient.getInstance();
        parsedText = parsedText.replace("%version%", SharedConstants.getGameVersion().getName());
        parsedText = parsedText.replace("%modded%", ClientBrandRetriever.getClientModName());
        parsedText = parsedText.replace("%version_type%", ("release".equalsIgnoreCase(CLIENT.getVersionType()) ? "" : CLIENT.getVersionType()));
        parsedText = parsedText.replace("%name%", Player.getName());
        parsedText = parsedText.replace("%discord_name%", Client.USER.username);
        parsedText = parsedText.replace("%discord_discriminator%", Client.USER.discriminator);
        parsedText = parsedText.replace("%discord_id%", Client.USER.userId);
        parsedText = parsedText.replace("%discord_tag%", Client.USER.username+"#"+Client.USER.discriminator);
        // Данные функции связаны с замены discord на siscord
        parsedText = parsedText.replace("%siscord_name%", Client.USER.username);
        parsedText = parsedText.replace("%siscord_discriminator%", Client.USER.discriminator);
        parsedText = parsedText.replace("%siscord_id%", Client.USER.userId);
        parsedText = parsedText.replace("%siscord_tag%", Client.USER.username+"#"+Client.USER.discriminator);
        if(Main.musicPlayer){
            MusicPlayer music = new MusicPlayer();
            if(!music.paused){
                parsedText = parsedText.replace("%music%", getLocalization("mod.music.format", false));
                if(music.artistIsNull) parsedText = parsedText.replace("%artist%", "");
                else {
                    parsedText = parsedText.replace("%artist%", getLocalization("mod.music.format.artist", false));
                    parsedText = parsedText.replace("%artist_name%", music.artist);
                }
                if(music.useFile) parsedText = parsedText.replace("%title%", music.file);
                else parsedText = parsedText.replace("%title%", music.title);
            }
        }
        if(CLIENT.world != null && CLIENT.player != null){
            parsedText = parsedText.replace("%item%", Localization.getLocalization("item.format", false));
            parsedText = parsedText.replace("%item_name%", Player.getItemName()+"");
            if(Player.getItemCount() >= 2) {
                parsedText = parsedText.replace("%item_pcs%", Localization.getLocalization("item.format.count", false));
                parsedText = parsedText.replace("%item_count%", Player.getItemCount() + "");
            } else {
                parsedText = parsedText.replace("%item_pcs%", "");
                parsedText = parsedText.replace("%item_count%", "");
            }
            parsedText = parsedText.replace("%scene%", Player.getTypeWorld());
            parsedText = parsedText.replace("%x%", Player.getX());
            parsedText = parsedText.replace("%y%", Player.getY());
            parsedText = parsedText.replace("%z%", Player.getZ());
            if(!CLIENT.isInSingleplayer() && CLIENT.getCurrentServerEntry() != null){
                if(ServerConfig.SHOW_ADDRESS){
                    if(ServerConfig.SHOW_CUSTOM_NAME) parsedText = parsedText.replace("%address%", ServerConfig.CUSTOM_NAME);
                    else if(ServerConfig.SHOW_NAME_IN_LIST) parsedText = parsedText.replace("%address%", CLIENT.getCurrentServerEntry().name);
                    else parsedText = parsedText.replace("%address%", CLIENT.getCurrentServerEntry().address);
                } else parsedText = parsedText.replace("%address%", getLocalization("address.hidden", false));
                if(Game.getGameState() == 0) parsedText = parsedText.replace("%ping%", CLIENT.player.networkHandler.getPlayerListEntry(CLIENT.player.getUuid()).getLatency()+"ms");
                else parsedText = parsedText.replace("%ping%", "-ms");
            } else if(!CLIENT.isInSingleplayer() && UserConfig.VIEW_REPLAY_MOD){
                try {
                    ReplayMod replay = new ReplayMod();
                    DateFormat dateFormat = new SimpleDateFormat(Localization.getLocalization("mod.replaymod.date_format", false));
                    parsedText = parsedText.replace("%date%", dateFormat.format(replay.date));
                    parsedText = parsedText.replace("%sate%", dateFormat.format(replay.date));
                    if(UserConfig.VIEW_REPLAY_MOD_SERVER_NAME) parsedText = parsedText.replace("%replay_server%", replay.name);
                    else parsedText = parsedText.replace("%replay_server%", Localization.getLocalization("unknown.server", false));
                    if(UserConfig.VIEW_REPLAY_MOD_SERVER_NAME) parsedText = parsedText.replace("%replay_address%", replay.address);
                    else parsedText = parsedText.replace("%replay_address%", Localization.getLocalization("unknown.server", false));
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
            parsedText = parsedText.replace("%health%", Player.getHealth());
            parsedText = parsedText.replace("%health_max%", Player.getMaxHealth());
            parsedText = parsedText.replace("%health_percent%", Player.getPercentHealth());
            parsedText = parsedText.replace("%armor%", Player.getArmor());
            parsedText = parsedText.replace("%fps%", MinecraftClientAccess.getCurrentFps()+"FPS");
            parsedText = parsedText.replace("%sps%", MinecraftClientAccess.getCurrentFps()+"FPS");
            if(Main.isVoiceModsEnable){
                if(Main.plasmo){
                    PlasmoVoice mod = new PlasmoVoice();
                    if(mod.isSpeak) parsedText = parsedText.replace("%listener%", mod.listener);
                } else if(Main.svc) {
                    SVC mod = new SVC();
                    if(mod.isSpeak) parsedText = parsedText.replace("%listener%", mod.listener);
                }
            }
        }
        return parsedText;
    }
}
