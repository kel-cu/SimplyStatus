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
    public String text_isSleep;

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
            text_isSleep = root.getText_isSleep();
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
        text_isSleep = "Игрок спит 😪";
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
        text_isSleep = "Player a sleep 😪";
    }
}

class SimplyStatusLang {
    public String getTextMainMenu(){
        if(textMainMenu != null){
            return textMainMenu;
        } else {
            return "Text a type textMainMenu not found";
        }
    }
    public String getTextDeathOne(){
        if(textDeathOne != null){
            return textDeathOne;
        } else {
            return "Text a type textDeathOne not found";
        }
    }
    public String getTextDeathTwo(){
        if(textDeathTwo != null){
            return textDeathTwo;
        } else {
            return "Text a type textDeathTwo not found";
        }
    }
    public String getTextDeathThree(){
        if(textDeathThree != null){
            return textDeathThree;
        } else {
            return "Text a type textDeathThree not found";
        }
    }
    public String getTextAir(){
        if(textAir != null){
            return textAir;
        } else {
            return "Text a type textAir not found";
        }
    }
    public String getTextItem(){
        if(textItem != null){
            return textItem;
        } else {
            return "Text a type textItem not found";
        }
    }
    public String getTextUnknownServer(){
        if(textUnknownServer != null){
            return textUnknownServer;
        } else {
            return "Text a type textUnknownServer not found";
        }
    }
    public String getTextHideIP(){
        if(textHideIP != null){
            return textHideIP;
        } else {
            return "Text a type textHideIP not found";
        }
    }
    public String getText_day(){
        if(text_day != null){
            return text_day;
        } else {
            return "Text a type text_day not found";
        }
    }
    public String getText_night(){
        if(text_night != null){
            return text_night;
        } else {
            return "Text a type text_night not found";
        }
    }
    public String getText_morning(){
        if(text_morning != null){
            return text_morning;
        } else {
            return "Text a type text_morning not found";
        }
    }
    public String getText_evening(){
        if(text_isSleep != null){
            return text_isSleep;
        } else {
            return "Text a type text_evening not found";
        }
    }
    public String getWorld_overworld(){
        if(world_overworld != null){
            return world_overworld;
        } else {
            return "Text a type world_overworld not found";
        }
    }
    public String getWorld_nether(){
        if(world_nether != null){
            return world_nether;
        } else {
            return "Text a type world_nether not found";
        }
    }
    public String getWorld_end(){
        if(world_end != null){
            return world_end;
        } else {
            return "Text a type world_end not found";
        }
    }
    public String getText_isInSingleplayer(){
        if(text_isInSingleplayer != null){
            return text_isInSingleplayer;
        } else {
            return "Text a type text_isInSingleplayer not found";
        }
    }
    public String getText_goodPlayer(){
        if(text_goodPlayer != null){
            return text_goodPlayer;
        } else {
            return "Text a type text_goodPlayer not found";
        }
    }
    public String getText_isSleep(){
        if(text_isSleep != null){
            return text_isSleep;
        } else {
            return "Text a type text_isSleep not found";
        }
    }

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
    private String text_isSleep;
}