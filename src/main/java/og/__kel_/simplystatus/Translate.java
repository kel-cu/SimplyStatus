package og.__kel_.simplystatus;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.text.TranslatableTextContent;
import og.__kel_.simplystatus.client.HotKeys;
import og.__kel_.simplystatus.client.MainClient;
import og.__kel_.simplystatus.info.Client;
import og.__kel_.simplystatus.mixin.MinecraftClientMixin;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DecimalFormat;

public class Translate{
    public String mainMenu;
    public String mainMenu_state;
    public String deathOne;
    public String deathTwo;
    public String deathThree;
    public String air;
    public String item;
    public String unknownServer;
    public String hideIP;
    public String day;
    public String night;
    public String evening;
    public String morning;
    public String overworld;
    public String nether;
    public String end;
    public String singlePlayer;
    public String maybeGoodPlayer;
    public String sleep;
    public String sneaking;

    public String stacks;
    public String pieces;
    //pieces-ru-2-4
    public String pieces_ru_2_4;
    public String information;
    public String voice;
    public String voice_one;
    public String voice_two;
    public String onFire;

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
    public String getSelectedLang() {
        try {
            return mc.getGame().getSelectedLanguage().getCode();
        } catch (Exception e) {
            return "en_us";
        }
    }
    public void loadLang(String lang) {
        //String s = Files.readString(path, StandardCharsets.UTF_8);
        try {
            String jsonContent = Files.readString(new File(mc.runDirectory + "/SimplyStatus/lang/"+lang+".json").toPath(), StandardCharsets.UTF_8);
            SimplyStatusLang langConfig = new SimplyStatusLang(jsonContent);
            mainMenu = langConfig.text_MainMenu;
            mainMenu_state = langConfig.text_MainMenu_state;
            deathOne = langConfig.text_DeathOne;
            deathTwo = langConfig.text_DeathTwo;
            deathThree = langConfig.text_DeathThree;
            air = langConfig.text_Air;
            item = langConfig.text_Item;
            unknownServer = langConfig.text_UnknownServer;
            hideIP = langConfig.text_HideIP;
            day = langConfig.text_day;
            night = langConfig.text_night;
            evening = langConfig.text_evening;
            morning = langConfig.text_morning;
            overworld = langConfig.world_overworld;
            nether = langConfig.world_nether;
            end = langConfig.world_end;
            singlePlayer = langConfig.text_isInSingleplayer;
            maybeGoodPlayer = langConfig.text_goodPlayer;
            sleep = langConfig.text_isSleep;
            sneaking = langConfig.text_isSneaking;
            stacks = langConfig.stacks;
            pieces = langConfig.pieces;
            information = langConfig.text_information;
            voice = langConfig.voice;
            voice_one = langConfig.voice_one;
            voice_two = langConfig.voice_two;
            onFire = langConfig.onFire;
            if(lang.equals("ru_ru")){
                pieces_ru_2_4 = langConfig.pieces_ru_2_4;
            }
        } catch (Exception e) {
            loadDefault(lang);
        }
    }
    private void loadDefault(String Lang) {
        mainMenu = MutableText.of(new TranslatableTextContent("status.simplystatus.text_MainMenu")).getString();
        mainMenu_state = MutableText.of(new TranslatableTextContent("status.simplystatus.text_MainMenu.state")).getString();
        deathOne =  MutableText.of(new TranslatableTextContent("status.simplystatus.text_DeathOne")).getString();
        deathTwo = MutableText.of(new TranslatableTextContent("status.simplystatus.text_DeathTwo")).getString();
        deathThree = MutableText.of(new TranslatableTextContent("status.simplystatus.text_DeathThree")).getString();
        air = MutableText.of(new TranslatableTextContent("status.simplystatus.text_Air")).getString();
        item = MutableText.of(new TranslatableTextContent("status.simplystatus.text_Item")).getString();
        unknownServer = MutableText.of(new TranslatableTextContent("status.simplystatus.text_UnknownServer")).getString();
        hideIP = MutableText.of(new TranslatableTextContent("status.simplystatus.text_HideIP")).getString();
        day = MutableText.of(new TranslatableTextContent("status.simplystatus.text_day")).getString();
        night = MutableText.of(new TranslatableTextContent("status.simplystatus.text_night")).getString();
        evening = MutableText.of(new TranslatableTextContent("status.simplystatus.text_evening")).getString();
        morning = MutableText.of(new TranslatableTextContent("status.simplystatus.text_morning")).getString();
        overworld = MutableText.of(new TranslatableTextContent("status.simplystatus.world_overworld")).getString();
        nether = MutableText.of(new TranslatableTextContent("status.simplystatus.world_nether")).getString();
        end = MutableText.of(new TranslatableTextContent("status.simplystatus.world_end")).getString();
        singlePlayer = MutableText.of(new TranslatableTextContent("status.simplystatus.text_isInSingleplayer")).getString();
        maybeGoodPlayer = MutableText.of(new TranslatableTextContent("status.simplystatus.text_goodPlayer")).getString();
        sleep = MutableText.of(new TranslatableTextContent("status.simplystatus.text_isSleep")).getString();
        sneaking = MutableText.of(new TranslatableTextContent("status.simplystatus.text_isSneaking")).getString();
        stacks = MutableText.of(new TranslatableTextContent("status.simplystatus.stacks")).getString();
        pieces = MutableText.of(new TranslatableTextContent("status.simplystatus.pieces")).getString();
        information = MutableText.of(new TranslatableTextContent("status.simplystatus.text_information")).getString();
        voice = MutableText.of(new TranslatableTextContent("status.simplystatus.voice")).getString();
        voice_one = MutableText.of(new TranslatableTextContent("status.simplystatus.voice_one")).getString();
        voice_two = MutableText.of(new TranslatableTextContent("status.simplystatus.voice_two")).getString();
        onFire = MutableText.of(new TranslatableTextContent("status.simplystatus.onFire")).getString();
        if(Lang.equals("ru_ru")){
            pieces_ru_2_4 = MutableText.of(new TranslatableTextContent("status.simplystatus.pieces-ru")).getString();
        }
    }
    public String replaceText(String text, Boolean menu, Boolean error, Boolean multiplayer, Client client){
        DecimalFormat df = new DecimalFormat("#.#");
        text = text.replace("%version%", client.getVersion(mc));
        text = text.replace("%username%", client.getName(mc));
        text = text.replace("%discord%", MainClient.player.username);
        text = text.replace("%discordTag%", MainClient.player.username+"#"+ MainClient.player.discriminator);
        text = text.replace("%discriminator%", MainClient.player.discriminator);
        text = text.replace("%siscord%", MainClient.player.username);
        text = text.replace("%siscordTag%", MainClient.player.username+"#"+ MainClient.player.discriminator);
        text = text.replace("%siscriminator%", MainClient.player.discriminator);
        if(menu || error){
            return text;
        }
        if(multiplayer){
            String server_string = mc.getCurrentServerEntry().name;
            if(HotKeys.viewIPAddress){
                if(HotKeys.customNameEnable){
                    if(HotKeys.customName.length() <= 0){
                        if(mc.getCurrentServerEntry().name == null){
                            server_string = MutableText.of(new TranslatableTextContent("status.simplystatus.unknownNameServer")).getString();
                        } else {
                            server_string = mc.getCurrentServerEntry().name;
                        }
                    } else {
                        server_string = HotKeys.customName;
                    }
                } else if (HotKeys.viewName) {
                    if(mc.getCurrentServerEntry().name == null){
                        server_string = MutableText.of(new TranslatableTextContent("status.simplystatus.unknownNameServer")).getString();
                    } else {
                        server_string = mc.getCurrentServerEntry().name;
                    }
                } else {
                    server_string = mc.getCurrentServerEntry().address;
                }
                text = text.replace("%scene%", server_string);
            } else {
                text = text.replace("%scene%", this.hideIP);
            }
        } else {
            text = text.replace("%scene%", this.singlePlayer);
        }
        if(mc.player != null){
            text = text.replace("%x%", df.format(mc.player.capeX));
            text = text.replace("%y%",df.format(mc.player.capeY));
            text = text.replace("%z%",df.format(mc.player.capeZ));
            if(mc.player.isCreative()){
                text = text.replace("%gamemode%",MutableText.of(new TranslatableTextContent("gameMode.creative")).getString());
            } else if(mc.player.isSpectator()){
                text = text.replace("%gamemode%",MutableText.of(new TranslatableTextContent("gameMode.spectator")).getString());
            } else {
                text = text.replace("%gamemode%",MutableText.of(new TranslatableTextContent("gameMode.survival")).getString());
            }
        } else {
            text = text.replace("%x%", "•-•");
            text = text.replace("%y%","•~•");
            text = text.replace("%z%","=~=");
            text = text.replace("%gamemode%","0w0");
        }
        text = text.replace("%fps%",((MinecraftClientMixin) mc).getCurrentFPS() + "FPS");
        text = text.replace("%sps%",((MinecraftClientMixin) mc).getCurrentFPS() + "FPS");
        text = text.replace("%minecraft%", client.getVersion(mc));

        return text;
    }
    public void save(){
        String lang = this.getSelectedLang();
        try {
            final Path langFile = mc.runDirectory.toPath().resolve("SimplyStatus/lang/"+ lang +".json");
            JSONObject langJSON = new JSONObject();
            langJSON.put("main_menu", this.mainMenu);
            langJSON.put("main_menu_state", this.mainMenu_state);
            langJSON.put("deathOne", this.deathOne);
            langJSON.put("deathTwo", this.deathTwo);
            langJSON.put("deathThree", this.deathThree);
            langJSON.put("information", this.information);
            langJSON.put("air", this.air);
            langJSON.put("item", this.item);
            langJSON.put("unknownServer", this.unknownServer);
            langJSON.put("hideIP", this.hideIP);
            langJSON.put("stacks", this.stacks);
            langJSON.put("pieces", this.pieces);
            langJSON.put("pieces-ru", this.pieces_ru_2_4);
            langJSON.put("single", this.singlePlayer);
            langJSON.put("goodPlayer", this.maybeGoodPlayer);
            langJSON.put("sleep", this.sleep);
            langJSON.put("sneaking", this.sneaking);
            langJSON.put("overworld", this.overworld);
            langJSON.put("nether", this.nether);
            langJSON.put("end", this.end);
            langJSON.put("day", this.day);
            langJSON.put("night", this.night);
            langJSON.put("evening", this.evening);
            langJSON.put("morning", this.morning);
            langJSON.put("voice", this.voice);
            langJSON.put("voice_one", this.voice_one);
            langJSON.put("voice_two", this.voice_two);
            langJSON.put("onFire", this.onFire);

            Files.createDirectories(langFile.getParent());
            Files.writeString(langFile, langJSON.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class SimplyStatusLang {
    public String text_MainMenu = MutableText.of(new TranslatableTextContent("status.simplystatus.text_MainMenu")).getString();
    public String text_MainMenu_state = MutableText.of(new TranslatableTextContent("status.simplystatus.text_MainMenu.state")).getString();
    public String text_DeathOne = MutableText.of(new TranslatableTextContent("status.simplystatus.text_DeathOne")).getString();
    public String text_DeathTwo = MutableText.of(new TranslatableTextContent("status.simplystatus.text_DeathTwo")).getString();
    public String text_DeathThree = MutableText.of(new TranslatableTextContent("status.simplystatus.text_DeathThree")).getString();
    public String text_Air = MutableText.of(new TranslatableTextContent("status.simplystatus.text_Air")).getString();
    public String text_Item = MutableText.of(new TranslatableTextContent("status.simplystatus.text_Item")).getString();
    public String text_UnknownServer = MutableText.of(new TranslatableTextContent("status.simplystatus.text_UnknownServer")).getString();
    public String text_HideIP = MutableText.of(new TranslatableTextContent("status.simplystatus.text_HideIP")).getString();
    public String text_day = MutableText.of(new TranslatableTextContent("status.simplystatus.text_day")).getString();
    public String text_night = MutableText.of(new TranslatableTextContent("status.simplystatus.text_night")).getString();
    public String text_evening = MutableText.of(new TranslatableTextContent("status.simplystatus.text_evening")).getString();
    public String text_morning = MutableText.of(new TranslatableTextContent("status.simplystatus.text_morning")).getString();
    public String world_overworld = MutableText.of(new TranslatableTextContent("status.simplystatus.world_overworld")).getString();
    public String world_nether =MutableText.of(new TranslatableTextContent("status.simplystatus.world_nether")).getString();
    public String world_end = MutableText.of(new TranslatableTextContent("status.simplystatus.world_end")).getString();
    public String text_isInSingleplayer = MutableText.of(new TranslatableTextContent("status.simplystatus.text_isInSingleplayer")).getString();
    public String text_goodPlayer = MutableText.of(new TranslatableTextContent("status.simplystatus.text_goodPlayer")).getString();
    public String text_isSleep = MutableText.of(new TranslatableTextContent("status.simplystatus.text_isSleep")).getString();
    public String text_isSneaking = MutableText.of(new TranslatableTextContent("status.simplystatus.text_isSneaking")).getString();
    public String text_information = MutableText.of(new TranslatableTextContent("status.simplystatus.text_information")).getString();
    public String pieces = MutableText.of(new TranslatableTextContent("status.simplystatus.pieces")).getString();
    public String pieces_ru_2_4 = MutableText.of(new TranslatableTextContent("status.simplystatus.pieces-ru")).getString();
    public String stacks = MutableText.of(new TranslatableTextContent("status.simplystatus.stacks")).getString();
    public String voice = MutableText.of(new TranslatableTextContent("status.simplystatus.voice")).getString();
    public String voice_one = MutableText.of(new TranslatableTextContent("status.simplystatus.voice_one")).getString();
    public String voice_two = MutableText.of(new TranslatableTextContent("status.simplystatus.voice_two")).getString();
    public String onFire = MutableText.of(new TranslatableTextContent("status.simplystatus.onFire")).getString();
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
            this.text_MainMenu_state = json.getString("main_menu_state");
        } catch(Exception e){
            //System.out.println(e.getMessage());
        }
        try{
            this.voice = json.getString("voice");
        } catch(Exception e){
            //System.out.println(e.getMessage());
        }
        try{
            this.voice_one = json.getString("voice_one");
        } catch(Exception e){
            //System.out.println(e.getMessage());
        }
        try{
            this.voice_two = json.getString("voice_two");
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
        try{
            this.onFire = json.getString("onFire");
        } catch(Exception e){
            //System.out.println(e.getMessage());
        }
    }
}