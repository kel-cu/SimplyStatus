package og.__kel_.simplystatus;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.TranslatableText;

import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class SimplyStatusTranslate{

    public String text_MainMenu;
    public String text_DeathOne;
    public String text_DeathTwo;
    public String text_DeathThree;
    public String text_Air;
    public String text_Item;
    public String text_UnknownServer;
    public String text_HideIP;
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
    public String text_isSneaking;

    public String stacks;
    public String pieces;
    //pieces-ru-2-4
    public String pieces_ru_2_4;
    public String text_information;

    MinecraftClient mc = MinecraftClient.getInstance();
    public static String SelectedLang;

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
        try (FileReader reader = new FileReader(mc.runDirectory + "/config/SimplyStatus-Configs/" + lang + ".json")) {
            SimplyStatusLang root = gson.fromJson(reader, SimplyStatusLang.class);
            text_MainMenu = root.getTextMainMenu();
            text_DeathOne = root.getTextDeathOne();
            text_DeathTwo = root.getTextDeathTwo();
            text_DeathThree = root.getTextDeathThree();
            text_Air = root.getTextAir();
            text_Item = root.getTextItem();
            text_UnknownServer = root.getTextUnknownServer();
            text_HideIP = root.getTextHideIP();
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
            text_isSneaking = root.getText_isSneaking();
            stacks = root.getStacks();
            pieces = root.getPieces();
            text_information = root.getText_information();
            if(lang.equals("ru_ru")){
                pieces_ru_2_4 = root.getPieces_ru_2_4();
            }
            SaveJSONLang(lang);
        } catch (Exception e) {
            try (FileReader reader = new FileReader(mc.runDirectory + "/config/simplystatus/" + lang + ".json")) {
                SimplyStatusLang root = gson.fromJson(reader, SimplyStatusLang.class);
                text_MainMenu = root.getTextMainMenu();
                text_DeathOne = root.getTextDeathOne();
                text_DeathTwo = root.getTextDeathTwo();
                text_DeathThree = root.getTextDeathThree();
                text_Air = root.getTextAir();
                text_Item = root.getTextItem();
                text_UnknownServer = root.getTextUnknownServer();
                text_HideIP = root.getTextHideIP();
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
                text_isSneaking = root.getText_isSneaking();
                stacks = root.getStacks();
                pieces = root.getPieces();
                text_information = root.getText_information();
                if(lang.equals("ru_ru")){
                    pieces_ru_2_4 = root.getPieces_ru_2_4();
                }
                SaveJSONLang(lang);
            } catch (Exception er) {
                loadDefault(lang);
            }
        }
    }
    public void SaveJSONLang(String Lang){
        final Path configFile = mc.runDirectory.toPath().resolve("config/SimplyStatus-Configs/"+Lang+".json");
        Gson gson = new Gson();
        JsonObject root = new JsonObject();
        root.addProperty("text_MainMenu", text_MainMenu);
        root.addProperty("text_DeathOne", text_DeathOne);
        root.addProperty("text_DeathTwo", text_DeathTwo);
        root.addProperty("text_DeathThree", text_DeathThree);
        root.addProperty("text_Air", text_Air);
        root.addProperty("text_Item", text_Item);
        root.addProperty("text_UnknownServer", text_UnknownServer);
        root.addProperty("text_HideIP", text_HideIP);
        root.addProperty("text_day", text_day);
        root.addProperty("text_night", text_night);
        root.addProperty("text_morning", text_morning);
        root.addProperty("text_evening", text_evening);
        root.addProperty("world_overworld", world_overworld);
        root.addProperty("world_nether", world_nether);
        root.addProperty("world_end", world_end);
        root.addProperty("text_goodPlayer", text_goodPlayer);
        root.addProperty("text_isInSingleplayer", text_isInSingleplayer);
        root.addProperty("text_isSleep", text_isSleep);
        root.addProperty("text_isSneaking", text_isSneaking);
        root.addProperty("pieces", pieces);
        root.addProperty("stacks", stacks);
        root.addProperty("text_information", text_information);
        if(Lang.equals("ru_ru")){
            //pieces-ru-2-4
            root.addProperty("pieces_ru_2_4", pieces_ru_2_4);
        }
        String config = gson.toJson(root);
        try {
            Files.createDirectories(configFile.getParent());
            Files.write(configFile, config.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void loadDefault(String Lang) {
        text_MainMenu = new TranslatableText("status.simplystatus.text_MainMenu").getString();
        text_DeathOne =  new TranslatableText("status.simplystatus.text_DeathOne").getString();
        text_DeathTwo = new TranslatableText("status.simplystatus.text_DeathTwo").getString();
        text_DeathThree = new TranslatableText("status.simplystatus.text_DeathThree").getString();
        text_Air = new TranslatableText("status.simplystatus.text_Air").getString();
        text_Item = new TranslatableText("status.simplystatus.text_Item").getString();
        text_UnknownServer = new TranslatableText("status.simplystatus.text_UnknownServer").getString();
        text_HideIP = new TranslatableText("status.simplystatus.text_HideIP").getString();
        text_day = new TranslatableText("status.simplystatus.text_day").getString();
        text_night = new TranslatableText("status.simplystatus.text_night").getString();
        text_evening = new TranslatableText("status.simplystatus.text_evening").getString();
        text_morning = new TranslatableText("status.simplystatus.text_morning").getString();
        world_overworld = new TranslatableText("status.simplystatus.world_overworld").getString();
        world_nether = new TranslatableText("status.simplystatus.world_nether").getString();
        world_end = new TranslatableText("status.simplystatus.world_end").getString();
        text_isInSingleplayer = new TranslatableText("status.simplystatus.text_isInSingleplayer").getString();
        text_goodPlayer = new TranslatableText("status.simplystatus.text_goodPlayer").getString();
        text_isSleep = new TranslatableText("status.simplystatus.text_isSleep").getString();
        text_isSneaking = new TranslatableText("status.simplystatus.text_isSneaking").getString();
        stacks = new TranslatableText("status.simplystatus.stacks").getString();
        pieces = new TranslatableText("status.simplystatus.pieces").getString();
        text_information = new TranslatableText("status.simplystatus.information").getString();
        if(Lang.equals("ru_ru")){
            pieces_ru_2_4 = new TranslatableText("status.simplystatus.pieces-ru-2-4").getString();
        }
        SaveJSONLang(Lang);
    }
}

class SimplyStatusLang {
    public String getTextMainMenu(){
        if(text_MainMenu != null){
            return text_MainMenu;
        } else {
            return new TranslatableText("status.simplystatus.text_MainMenu").getString();
        }
    }
    public String getTextDeathOne(){
        if(text_DeathOne != null){
            return text_DeathOne;
        } else {
            return new TranslatableText("status.simplystatus.text_DeathOne").getString();
        }
    }
    public String getTextDeathTwo(){
        if(text_DeathTwo != null){
            return text_DeathTwo;
        } else {
            return new TranslatableText("status.simplystatus.text_DeathTwo").getString();
        }
    }
    public String getTextDeathThree(){
        if(text_DeathThree != null){
            return text_DeathThree;
        } else {
            return new TranslatableText("status.simplystatus.text_DeathThree").getString();
        }
    }
    public String getTextAir(){
        if(text_Air != null){
            return text_Air;
        } else {
            return new TranslatableText("status.simplystatus.text_Air").getString();
        }
    }
    public String getTextItem(){
        if(text_Item != null){
            return text_Item;
        } else {
            return new TranslatableText("status.simplystatus.text_Item").getString();
        }
    }
    public String getTextUnknownServer(){
        if(text_UnknownServer != null){
            return text_UnknownServer;
        } else {
            return new TranslatableText("status.simplystatus.text_UnknownServer").getString();
        }
    }
    public String getTextHideIP(){
        if(text_HideIP != null){
            return text_HideIP;
        } else {
            return new TranslatableText("status.simplystatus.text_HideIP").getString();
        }
    }
    public String getText_day(){
        if(text_day != null){
            return text_day;
        } else {
            return new TranslatableText("status.simplystatus.text_day").getString();
        }
    }
    public String getText_night(){
        if(text_night != null){
            return text_night;
        } else {
            return new TranslatableText("status.simplystatus.text_night").getString();
        }
    }
    public String getText_morning(){
        if(text_morning != null){
            return text_morning;
        } else {
            return new TranslatableText("status.simplystatus.text_morning").getString();
        }
    }
    public String getText_evening(){
        if(text_evening != null){
            return text_evening;
        } else {
            return new TranslatableText("status.simplystatus.text_evening").getString();
        }
    }
    public String getWorld_overworld(){
        if(world_overworld != null){
            return world_overworld;
        } else {
            return new TranslatableText("status.simplystatus.world_overworld").getString();
        }
    }
    public String getWorld_nether(){
        if(world_nether != null){
            return world_nether;
        } else {
            return new TranslatableText("status.simplystatus.world_nether").getString();
        }
    }
    public String getWorld_end(){
        if(world_end != null){
            return world_end;
        } else {
            return new TranslatableText("status.simplystatus.world_end").getString();
        }
    }
    public String getText_isInSingleplayer(){
        if(text_isInSingleplayer != null){
            return text_isInSingleplayer;
        } else {
            return new TranslatableText("status.simplystatus.text_isInSingleplayer").getString();
        }
    }
    public String getText_goodPlayer(){
        if(text_goodPlayer != null){
            return text_goodPlayer;
        } else {
            return new TranslatableText("status.simplystatus.text_goodPlayer").getString();
        }
    }
    public String getText_isSleep(){
        if(text_isSleep != null){
            return text_isSleep;
        } else {
            return new TranslatableText("status.simplystatus.text_isSleep").getString();
        }
    }
    public String getText_isSneaking(){
        if(text_isSneaking != null){
            return text_isSneaking;
        } else {
            return new TranslatableText("status.simplystatus.text_isSneaking").getString();
        }
    }

    public String getPieces(){
        if(pieces != null){
            return pieces;
        } else {
            return new TranslatableText("status.simplystatus.pieces").getString();
        }
    }
    public String getPieces_ru_2_4(){
        if(pieces_ru_2_4 != null){
            return pieces_ru_2_4;
        } else {
            return new TranslatableText("status.simplystatus.pieces-ru-2-4").getString();
        }
    }
    public String getStacks(){
        if(stacks != null){
            return stacks;
        } else {
            return new TranslatableText("status.simplystatus.stacks").getString();
        }
    }
    public String getText_information(){
        if(text_information != null){
            return text_information;
        } else {
            return new TranslatableText("status.simplystatus.information").getString();
        }
    }

    private String text_MainMenu;
    private String text_DeathOne;
    private String text_DeathTwo;
    private String text_DeathThree;
    private String text_Air;
    private String text_Item;
    private String text_UnknownServer;
    private String text_HideIP;
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
    private String text_isSneaking;
    private String text_information;

    private String pieces;
    private String pieces_ru_2_4;
    private String stacks;
}