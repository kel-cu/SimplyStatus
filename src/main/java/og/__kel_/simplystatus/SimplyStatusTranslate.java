package og.__kel_.simplystatus;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.TranslatableText;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

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
    public String SelectedLang;

    public void selectedLang() {
        try {
            SelectedLang = mc.getGame().getSelectedLanguage().getCode();
        } catch (Exception e) {
            SelectedLang = "en_us";
        }
        loadLang(SelectedLang);
    }
    public void loadLang(String lang) {
        //String s = Files.readString(path, StandardCharsets.UTF_8);
        try {
            String jsonContent = Files.readString(new File(mc.runDirectory + "/SimplyStatus/lang/"+lang+".json").toPath(), StandardCharsets.UTF_8);
            SimplyStatusLang langConfig = new SimplyStatusLang(jsonContent);
            text_MainMenu = langConfig.text_MainMenu;
            text_DeathOne = langConfig.text_DeathOne;
            text_DeathTwo = langConfig.text_DeathTwo;
            text_DeathThree = langConfig.text_DeathThree;
            text_Air = langConfig.text_Air;
            text_Item = langConfig.text_Item;
            text_UnknownServer = langConfig.text_UnknownServer;
            text_HideIP = langConfig.text_HideIP;
            text_day = langConfig.text_day;
            text_night = langConfig.text_night;
            text_evening = langConfig.text_evening;
            text_morning = langConfig.text_morning;
            world_overworld = langConfig.world_overworld;
            world_nether = langConfig.world_nether;
            world_end = langConfig.world_end;
            text_isInSingleplayer = langConfig.text_isInSingleplayer;
            text_goodPlayer = langConfig.text_goodPlayer;
            text_isSleep = langConfig.text_isSleep;
            text_isSneaking = langConfig.text_isSneaking;
            stacks = langConfig.stacks;
            pieces = langConfig.pieces;
            text_information = langConfig.text_information;
            if(lang.equals("ru_ru")){
                pieces_ru_2_4 = langConfig.pieces_ru_2_4;
            }
        } catch (Exception e) {
            loadDefault(lang);
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
        text_information = new TranslatableText("status.simplystatus.text_information").getString();
        if(Lang.equals("ru_ru")){
            pieces_ru_2_4 = new TranslatableText("status.simplystatus.pieces-ru-2-4").getString();
        }
    }
}

class SimplyStatusLang {
    public String text_MainMenu = new TranslatableText("status.simplystatus.text_MainMenu").getString();
    public String text_DeathOne = new TranslatableText("status.simplystatus.text_DeathOne").getString();
    public String text_DeathTwo = new TranslatableText("status.simplystatus.text_DeathTwo").getString();
    public String text_DeathThree = new TranslatableText("status.simplystatus.text_DeathThree").getString();
    public String text_Air = new TranslatableText("status.simplystatus.text_Air").getString();
    public String text_Item = new TranslatableText("status.simplystatus.text_Item").getString();
    public String text_UnknownServer = new TranslatableText("status.simplystatus.text_UnknownServer").getString();
    public String text_HideIP = new TranslatableText("status.simplystatus.text_HideIP").getString();
    public String text_day = new TranslatableText("status.simplystatus.text_day").getString();
    public String text_night = new TranslatableText("status.simplystatus.text_night").getString();
    public String text_evening = new TranslatableText("status.simplystatus.text_evening").getString();
    public String text_morning = new TranslatableText("status.simplystatus.text_morning").getString();
    public String world_overworld = new TranslatableText("status.simplystatus.world_overworld").getString();
    public String world_nether =new TranslatableText("status.simplystatus.world_nether").getString();
    public String world_end = new TranslatableText("status.simplystatus.world_end").getString();
    public String text_isInSingleplayer = new TranslatableText("status.simplystatus.text_isInSingleplayer").getString();
    public String text_goodPlayer = new TranslatableText("status.simplystatus.text_goodPlayer").getString();
    public String text_isSleep = new TranslatableText("status.simplystatus.text_isSleep").getString();
    public String text_isSneaking = new TranslatableText("status.simplystatus.text_isSneaking").getString();
    public String text_information = new TranslatableText("status.simplystatus.text_information").getString();
    public String pieces = new TranslatableText("status.simplystatus.pieces").getString();
    public String pieces_ru_2_4 = new TranslatableText("status.simplystatus.pieces-ru-2-4").getString();
    public String stacks = new TranslatableText("status.simplystatus.stacks").getString();
    public SimplyStatusLang(String jsonContent){
        JSONObject json = null;
        try{
             json = new JSONObject(jsonContent);
        } catch (JSONException ej){
            System.out.println(ej);
        }
        try{
            this.text_MainMenu = json.getString("main_menu");
        } catch(Exception e){
            //System.out.println(e.getMessage());
        }
        try{
            this.text_DeathOne = json.getString("deathOne");
        } catch(Exception e){
            //System.out.println(e.getMessage());
        }
        try{
            this.text_DeathTwo = json.getString("deathTwo");
        } catch(Exception e){
            //System.out.println(e.getMessage());
        }
        try{
            this.text_DeathThree = json.getString("deathThree");
        } catch(Exception e){
            //System.out.println(e.getMessage());
        }
        try{
            this.text_Air = json.getString("air");
        } catch(Exception e){
            //System.out.println(e.getMessage());
        }
        try{
            this.text_Item = json.getString("item");
        } catch(Exception e){
            //System.out.println(e.getMessage());
        }
        try{
            this.text_UnknownServer = json.getString("unknownServer");
        } catch(Exception e){
            //System.out.println(e.getMessage());
        }

        try{
            this.text_HideIP = json.getString("hideIP");
        } catch(Exception e){
            //System.out.println(e.getMessage());
        }
        try{
            this.text_day = json.getString("day");
        } catch(Exception e){
            //System.out.println(e.getMessage());
        }
        try{
            this.text_night = json.getString("night");
        } catch(Exception e){
            //System.out.println(e.getMessage());
        }
        try{
            this.text_morning = json.getString("morning");
        } catch(Exception e){
            //System.out.println(e.getMessage());
        }
        try{
            this.text_evening = json.getString("evening");
        } catch(Exception e){
            //System.out.println(e.getMessage());
        }
        try{
            this.world_overworld = json.getString("overworld");
        } catch(Exception e){
            //System.out.println(e.getMessage());
        }
        try{
            this.world_nether = json.getString("nether");
        } catch(Exception e){
            //System.out.println(e.getMessage());
        }

        try{
            this.world_end = json.getString("end");
        } catch(Exception e){
            //System.out.println(e.getMessage());
        }
        try{
            this.text_isInSingleplayer = json.getString("single");
        } catch(Exception e){
            //System.out.println(e.getMessage());
        }
        try{
            this.text_goodPlayer = json.getString("goodPlayer");
        } catch(Exception e){
            //System.out.println(e.getMessage());
        }
        try{
            this.text_isSleep = json.getString("sleep");
        } catch(Exception e){
            //System.out.println(e.getMessage());
        }
        try{
            this.text_isSneaking = json.getString("sneaking");
        } catch(Exception e){
            //System.out.println(e.getMessage());
        }
        try{
            this.text_information = json.getString("information");
        } catch(Exception e){
            //System.out.println(e.getMessage());
        }
        try{
            this.pieces = json.getString("pieces");
        } catch(Exception e){
            //System.out.println(e.getMessage());
        }
        try{
            this.pieces_ru_2_4 = json.getString("ru-pieces");
        } catch(Exception e){
            //System.out.println(e.getMessage());
        }
        try{
            this.stacks = json.getString("stacks");
        } catch(Exception e){
            //System.out.println(e.getMessage());
        }
    }
}