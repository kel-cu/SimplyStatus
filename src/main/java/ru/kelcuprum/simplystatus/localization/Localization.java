package ru.kelcuprum.simplystatus.localization;

import meteordevelopment.starscript.Script;
import meteordevelopment.starscript.Section;
import meteordevelopment.starscript.StandardLib;
import meteordevelopment.starscript.Starscript;
import meteordevelopment.starscript.compiler.Compiler;
import meteordevelopment.starscript.compiler.Parser;
import meteordevelopment.starscript.utils.Error;
import meteordevelopment.starscript.utils.StarscriptError;
import meteordevelopment.starscript.value.Value;
import meteordevelopment.starscript.value.ValueMap;

import net.minecraft.SharedConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import org.apache.logging.log4j.Level;
import org.json.JSONObject;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.info.Client;
import ru.kelcuprum.simplystatus.info.Player;
import ru.kelcuprum.simplystatus.info.Utils;
import ru.kelcuprum.simplystatus.info.World;
import ru.kelcuprum.simplystatus.mods.Music;
import ru.kelcuprum.simplystatus.mods.ReplayMod;
import ru.kelcuprum.simplystatus.mods.Voice;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Localization {

    public static String getCodeLocalization(){
        Minecraft CLIENT = Minecraft.getInstance();
        try{
//            return CLIENT.getGame().getSelectedLanguage().getCode();
            return CLIENT.options.languageCode;
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
        Minecraft CLIENT = Minecraft.getInstance();
        File localizationFile = new File(CLIENT.gameDirectory + "/config/SimplyStatus/lang/"+getCodeLocalization()+".json");
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
        return getLocalization(type, parse, false);
    }
    public static String getLocalization(String type, boolean parse, boolean clearColor){
        String text = "";
        try {
            JSONObject JSONLocalization = getJSONFile();
            if(JSONLocalization.isNull(type)) text = getText("simplystatus.presence." + type).getString();
            else text = JSONLocalization.getString(type);
        } catch (Exception e){
            e.printStackTrace();
            text = getText("simplystatus.presence." + type).getString();
        }
        if(parse) text = run(compile(text));
        return clearColor ? Utils.clearFormatCodes(text) : text;
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
            Minecraft CLIENT = Minecraft.getInstance();
            File localizationFile = new File(CLIENT.gameDirectory + "/config/SimplyStatus/lang/"+getCodeLocalization()+".json");
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
    public static Component getText(String key){
        return Component.translatable(key);
    }

    /**
     * Перевод String в MutableText
     * @param text
     * @return MutableText
     */
    public static Component toText(String text){
        return Component.literal(text);
    }

    /**
     * Перевод Text в String
     * @param text
     * @return MutableText
     */
    public static String toString(Component text){
        return text.getString();
    }

    // PARSE USE STARSCRIPT
    public static Starscript ss = new Starscript();

    private static final BlockPos.MutableBlockPos BP = new BlockPos.MutableBlockPos();
    private static final StringBuilder SB = new StringBuilder();
    static Minecraft mc = Minecraft.getInstance();

    public static void init() {
        StandardLib.init(ss);

        // General
        ss.set("minecraft", new ValueMap()
                .set("version", SharedConstants.getCurrentVersion().getName())
                .set("loader", Minecraft.getInstance().getVersionType())
                .set("tech", new ValueMap()
                        .set("cpu", () -> Value.string(Client.getCPUInfo()))
                        .set("vendor", () -> Value.string(Client.getVendor()))
                        .set("gpu", () -> Value.string(Client.getGPURender()))
                        .set("screen_resolution", () -> Value.string(Client.getScreenResolution()))
                )
        );
        ss.set("fps", () -> Value.number(mc.getFps()));
        ss.set("ping", () -> Value.number(Player.getPing()));
        ss.set("time", () -> Value.string(LocalTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))));
        // Discord
        ss.set("discord", new ValueMap()
                .set("name", () -> Value.string(SimplyStatus.USER.getName()))
                .set("discriminator", () -> Value.string(SimplyStatus.USER.getDiscriminator()))
                .set("id", () -> Value.string(SimplyStatus.USER.getId()))
                .set("avatar", () -> Value.string(SimplyStatus.USER.getAvatarId()))
        );
        // Player
        ss.set("player", new ValueMap()
                .set("name", () -> Value.string(Player.getName()))
                .set("health", () -> Value.string(mc.player != null ? Player.getHealth() : ""))
                .set("health_max", () -> Value.string(mc.player != null ? Player.getMaxHealth() : ""))
                .set("health_percent", () -> Value.string(mc.player != null ? Player.getPercentHealth() : ""))
                .set("armor", () -> Value.string(mc.player != null ? Player.getArmor() : ""))
                .set("direction", () -> Value.string(mc.player != null ? Player.getDirection(false) : ""))
                .set("direction_symbol", () -> Value.string(mc.player != null ? Player.getDirection(true) : ""))
                .set("hunger", () -> Value.number(mc.player != null ? mc.player.getFoodData().getFoodLevel() : 0))
                .set("pos", new ValueMap()
                        .set("x", () -> Value.string(Player.getX()))
                        .set("y", () -> Value.string(Player.getY()))
                        .set("z", () -> Value.string(Player.getZ()))
                )

                .set("item", () -> Value.string(mc.player != null ? Player.getItemName() : ""))
                .set("item_count", () -> Value.number(mc.player != null ? Player.getItemCount() : 0))

                .set("xp", new ValueMap()
                        .set("level", () -> Value.number(mc.player != null ? mc.player.experienceLevel : 0))
                        .set("progress", () -> Value.number(mc.player != null ? mc.player.experienceProgress : 0))
                        .set("total", () -> Value.number(mc.player != null ? mc.player.totalExperience : 0))
                )
                .set("scene", () -> Value.string(World.getScene()))
        );
        // World
        ss.set("world", new ValueMap()
                .set("name", () -> Value.string(mc.level != null ? World.getName() : ""))
                .set("time_type", () -> Value.string(mc.level != null ? World.getTimeType() : ""))
                .set("time", () -> Value.string(mc.level != null ? World.getTime() : ""))
                .set("difficulty", () -> Value.string(mc.level != null ? mc.level.getDifficulty().getDisplayName().getString() : ""))
        );
        if(SimplyStatus.isMusicModsEnable){
            ss.set("music", new ValueMap()
                    .set("title", () -> Value.string(new Music().useFile ? new Music().file : new Music().title))
                    .set("author", () -> Value.string(new Music().artistIsNull ? "" : new Music().artist))
                    .set("uri", () -> Value.string(new Music().file))
            );
        }
        if(SimplyStatus.isVoiceModsEnable){
            ss.set("voice", new ValueMap()
                    .set("listener", () -> Value.string(new Voice().listener))
            );
        }
        if(SimplyStatus.replayMod){
            ss.set("replay", new ValueMap()
                    .set("date", () -> Value.string(getReplayDateFormat(ReplayMod.date)))
                    .set("name", () -> Value.string(SimplyStatus.userConfig.getBoolean("VIEW_REPLAY_MOD_SERVER_NAME", true) ? ReplayMod.name : ReplayMod.address))
            );
        }
    }
    public static String getReplayDateFormat(long time){
        String strDateFormat = Localization.getLocalization("mod.replaymod.date_format", false);
        DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
        return dateFormat.format(time);
    }
    // Helpers

    public static Script compile(String source) {
        Parser.Result result = Parser.parse(source);

        if (result.hasErrors()) {
            for (Error error : result.errors) SimplyStatus.log(error.message, Level.ERROR);
            return null;
        }

        return Compiler.compile(result);
    }

    public static Section runSection(Script script, StringBuilder sb) {
        try {
            return ss.run(script, sb);
        }
        catch (StarscriptError error) {
            error.printStackTrace();
            return null;
        }
    }
    public static String run(Script script, StringBuilder sb) {
        Section section = runSection(script, sb);
        return section != null ? section.toString() : null;
    }

    public static Section runSection(Script script) {
        return runSection(script, new StringBuilder());
    }
    public static String run(Script script) {
        return run(script, new StringBuilder());
    }

}
