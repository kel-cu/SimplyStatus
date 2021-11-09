package og.simplystatus.kel.simplystatus;

import com.google.gson.Gson;
import net.minecraft.client.MinecraftClient;

import java.io.FileReader;

public class SimplyStatusTranslate{

    public String messageViewIPtrue;
    public String messageViewIPfalse;
    public String messageViewStatictrue;
    public String messageViewStaticfalse;
    public String textMainMenu;
    public String textDeathOne;
    public String textDeathTwo;
    public String textDeathThree;
    public String textAir;
    public String textItem;
    public String textUnknownServer;
    public String textHideIP;
    public String text_day;
    public String text_night;
    public String text_evening;
    public String text_morning;
    public String world_overworld;
    public String world_nether;
    public String world_end;
    public String text_isInSingleplayer;

    MinecraftClient mc = MinecraftClient.getInstance();
    String SelectedLang;

    public void selectedLang() {
        try {
            SelectedLang = mc.getGame().getSelectedLanguage().getCode();
        } catch (Exception e) {
            SelectedLang = "en_us";
        }
        loadLang(SelectedLang);
    }
    public void loadLang(String lang) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(mc.runDirectory + "/config/simplystatus/" + lang + ".json")) {
            SimplyStatusLang root = gson.fromJson(reader, SimplyStatusLang.class);
            messageViewIPfalse = root.getMessageViewIPfalse();
            messageViewIPtrue = root.getMessageViewIPtrue();
            messageViewStaticfalse = root.getMessageViewStaticfalse();
            messageViewStatictrue = root.getMessageViewStatictrue();
            textMainMenu = root.getTextMainMenu();
            textDeathOne = root.getTextDeathOne();
            textDeathTwo = root.getTextDeathTwo();
            textDeathThree = root.getTextDeathThree();
            textAir = root.getTextAir();
            textItem = root.getTextItem();
            textUnknownServer = root.getTextUnknownServer();
            textHideIP = root.getTextHideIP();
            text_day = root.getText_day();
            text_night = root.getText_night();
            text_evening = root.getText_evening();
            text_morning = root.getText_morning();
            world_overworld = root.getWorld_overworld();
            world_nether = root.getWorld_nether();
            world_end = root.getWorld_end();
            text_isInSingleplayer = root.getText_isInSingleplayer();
        } catch (Exception e) {
            if (lang.equals("ru_ru")) {
                ruLoad();
            } else if (lang.equals("en_us")) {
                en_usLoad();
            } else {
                en_usLoad();
            }
        }
    }
    private void ruLoad() {
        messageViewIPtrue =  "[§6SimplyStatus§r] IP адрес сервера теперь виден!";
        messageViewIPfalse = "[§6SimplyStatus§r] IP адрес сервера теперь скрыт!";
        messageViewStatictrue = "[§6SimplyStatus§r] Ваша статистика теперь видна!";
        messageViewStaticfalse = "[§6SimplyStatus§r] Ваша статистика теперь скрыта!";
        textMainMenu = "В главном меню";
        textDeathOne =  "Игрока нет живых :(";
        textDeathTwo = "Игрок умер :(";
        textDeathThree = "Отдыхает от мира...";
        textAir = "Держит хорошое настроение :3";
        textItem = "Держит ";
        textUnknownServer = "Неизвестный мультиплеер ¯\\_(\u30c4)_/¯";
        textHideIP = "*IP адрес скрыт*";
        text_day = "День";
        text_night = "Ночь";
        text_evening = "Вечер";
        text_morning = "Утро";
        world_overworld = "Верхний мир";
        world_nether = "Нижний мир";
        world_end = "Эндер мир";
        text_isInSingleplayer = "Одиночный мир";
    }

    private void en_usLoad() {
        messageViewIPtrue =  "[§6SimplyStatus§r] Server IP is now visible!";
        messageViewIPfalse = "[§6SimplyStatus§r] Server IP address is now hidden!";
        messageViewStatictrue = "[§6SimplyStatus§r] Your stats are now visible!";
        messageViewStaticfalse = "[§6SimplyStatus§r] Your stats are now hidden!";
        textMainMenu = "In the main menu";
        textDeathOne =  "The player died :(";
        textDeathTwo = "The player is no longer alive :(";
        textDeathThree = "Sits resting from the world ...";
        textAir = "Keeps a good mood";
        textItem = "Holding a ";
        textUnknownServer = "Unknown multiplayer ¯\\_(ツ)_/¯";
        textHideIP = "*IP address is hidden*";
        text_day = "Day";
        text_night = "Night";
        text_evening = "Evening";
        text_morning = "Morning";
        world_overworld = "The Overworld";
        world_nether = "The Nether";
        world_end = "The End";
        text_isInSingleplayer = "Single Player";
    }
}
