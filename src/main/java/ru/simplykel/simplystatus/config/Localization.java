package ru.simplykel.simplystatus.config;

import net.minecraft.SharedConstants;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableTextContent;
import org.json.JSONObject;
import ru.simplykel.simplystatus.Client;
import ru.simplykel.simplystatus.info.Player;
import ru.simplykel.simplystatus.mixin.MinecraftClientAccess;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class Localization {
    /**
     * Получение кода локализации игры который выбрал игрок<br>
     * При запуске может быть null, поэтому иногда en_us;
     */
    public static String getCodeLocalization(){
        MinecraftClient CLIENT = MinecraftClient.getInstance();
        try{
            return CLIENT.getGame().getSelectedLanguage().getCode();
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
            if(JSONLocalization.isNull(type)) text = MutableText.of(new TranslatableTextContent("simplystatus.presence."+type)).getString();
            else text = JSONLocalization.getString(type);
        } catch (Exception e){
            e.printStackTrace();
            text = MutableText.of(new TranslatableTextContent("simplystatus.presence."+type)).getString();
        }
        if(parse) return getParsedText(text);
        else return text;
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
            if(localizationFile.getParent().isEmpty()) Files.createDirectories(localizationFile.toPath().getParent());
            Files.writeString(localizationFile.toPath(), JSONLocalization.toString());
        } catch (Exception e){
            e.printStackTrace();
        }
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
        if(CLIENT.world != null && CLIENT.player != null){
            parsedText = parsedText.replace("%item%", Player.getItemName()+"");
            parsedText = parsedText.replace("%scene%", Player.getTypeWorld());
            if(!CLIENT.isInSingleplayer() && CLIENT.getCurrentServerEntry() != null){
                if(ServerConfig.SHOW_ADDRESS){
                    if(ServerConfig.SHOW_CUSTOM_NAME) parsedText = parsedText.replace("%address%", ServerConfig.CUSTOM_NAME);
                    else if(ServerConfig.SHOW_NAME_IN_LIST) parsedText = parsedText.replace("%address%", CLIENT.getCurrentServerEntry().name);
                    else parsedText = parsedText.replace("%address%", CLIENT.getCurrentServerEntry().address);
                } else parsedText = parsedText.replace("%address%", getLocalization("address.hidden", false));
            }
            parsedText = parsedText.replace("%health%", Player.getHealth());
            parsedText = parsedText.replace("%health_max%", Player.getMaxHealth());
            parsedText = parsedText.replace("%health_percent%", Player.getPercentHealth());
            parsedText = parsedText.replace("%armor%", Player.getArmor());
            parsedText = parsedText.replace("%fps%", MinecraftClientAccess.getCurrentFps()+"FPS");
        }
        return parsedText;
    }
}
