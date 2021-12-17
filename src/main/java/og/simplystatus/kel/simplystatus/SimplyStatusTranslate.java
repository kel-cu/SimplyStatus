package og.simplystatus.kel.simplystatus;

import com.google.gson.Gson;
import net.minecraft.client.MinecraftClient;

import java.io.FileReader;

public class SimplyStatusTranslate{

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
    public String text_goodPlayer;

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
            text_goodPlayer = root.getText_goodPlayer();
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
        text_isInSingleplayer = "Одиночная игра";
        text_goodPlayer = "Этот игрок очень хороший :3";
    }

    private void en_usLoad() {
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
        text_goodPlayer = "This player is very good :3";
    }
}

class SimplyStatusLang {
    public String getTextMainMenu() { return textMainMenu;}
    public String getTextDeathOne() { return textDeathOne;}
    public String getTextDeathTwo() { return textDeathTwo;}
    public String getTextDeathThree() { return textDeathThree;}
    public String getTextAir() { return textAir;}
    public String getTextItem() { return textItem;}
    public String getTextUnknownServer() { return textUnknownServer;}
    public String getTextHideIP() { return textHideIP;}
    public String getText_day() { return text_day;}
    public String getText_night() {return text_night;}
    public String getText_morning() {return text_morning;}
    public String getText_evening() {return text_evening;}
    public String getWorld_overworld(){return world_overworld;}
    public String getWorld_nether(){return world_nether;}
    public String getWorld_end(){return world_end;}
    public String getText_isInSingleplayer(){return text_isInSingleplayer;}
    public String getText_goodPlayer(){return text_goodPlayer;}

    private String textMainMenu;
    private String textDeathOne;
    private String textDeathTwo;
    private String textDeathThree;
    private String textAir;
    private String textItem;
    private String textUnknownServer;
    private String textHideIP;
    private String text_day;
    private String text_night;
    private String text_evening;
    private String text_morning;
    private String world_overworld;
    private String world_nether;
    private String world_end;
    private String text_isInSingleplayer;
    private String text_goodPlayer;
}