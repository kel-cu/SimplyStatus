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
import org.apache.logging.log4j.Level;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.info.SimplyPlayer;
import ru.kelcuprum.simplystatus.info.World;
import ru.kelcuprum.simplystatus.mods.WaterPlayerSupport;
import ru.kelcuprum.simplystatus.mods.ReplayMod;
import ru.kelcuprum.simplystatus.mods.Voice;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import static ru.kelcuprum.simplystatus.SimplyStatus.MINECRAFT;

public class StarScript {
    public static Starscript ss = new Starscript();


    public static void init() {
        StandardLib.init(ss);

        // General
        ss.set("minecraft", new ValueMap()
                .set("version", SharedConstants.getCurrentVersion().getName())
                .set("loader", Minecraft.getInstance().getVersionType())
                .set("fps", () -> Value.number(MINECRAFT.getFps()))
        );
        ss.set("time", () -> Value.string(LocalTime.now().format(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT))));
        // Discord
        ss.set("discord", new ValueMap()
                .set("name", () -> Value.string(SimplyStatus.USER.getName()))
                .set("nickname", () -> Value.string(SimplyStatus.USER.getNickname()))
                .set("discriminator", () -> Value.string(SimplyStatus.USER.getDiscriminator()))
                .set("id", () -> Value.string(SimplyStatus.USER.getId()))
                .set("avatar", () -> Value.string(SimplyStatus.USER.getAvatarUrl()))
        );
        // Player
        ss.set("player", new ValueMap()
                .set("ping", () -> Value.number(SimplyPlayer.getPing()))
                .set("name", () -> Value.string(SimplyPlayer.getName()))
                .set("health", () -> Value.string(MINECRAFT.player != null ? SimplyPlayer.getHealth() : ""))
                .set("health_max", () -> Value.string(MINECRAFT.player != null ? SimplyPlayer.getMaxHealth() : ""))
                .set("health_percent", () -> Value.string(MINECRAFT.player != null ? SimplyPlayer.getPercentHealth() : ""))
                .set("armor", () -> Value.string(MINECRAFT.player != null ? SimplyPlayer.getArmor() : ""))
                .set("direction", () -> Value.string(MINECRAFT.player != null ? SimplyPlayer.getDirection(false) : ""))
                .set("direction_symbol", () -> Value.string(MINECRAFT.player != null ? SimplyPlayer.getDirection(true) : ""))
                .set("hunger", () -> Value.number(MINECRAFT.player != null ? MINECRAFT.player.getFoodData().getFoodLevel() : 0))
                .set("pos", new ValueMap()
                        .set("x", () -> Value.string(SimplyPlayer.getX()))
                        .set("y", () -> Value.string(SimplyPlayer.getY()))
                        .set("z", () -> Value.string(SimplyPlayer.getZ()))
                )

                .set("item", () -> Value.string(MINECRAFT.player != null ? SimplyPlayer.getItemName() : ""))
                .set("item_count", () -> Value.number(MINECRAFT.player != null ? SimplyPlayer.getItemCount() : 0))

                .set("xp", new ValueMap()
                        .set("level", () -> Value.number(MINECRAFT.player != null ? MINECRAFT.player.experienceLevel : 0))
                        .set("progress", () -> Value.number(MINECRAFT.player != null ? MINECRAFT.player.experienceProgress : 0))
                        .set("total", () -> Value.number(MINECRAFT.player != null ? MINECRAFT.player.totalExperience : 0))
                )
                .set("scene", () -> Value.string(World.getScene()))
        );
        // World
        ss.set("world", new ValueMap()
                .set("name", () -> Value.string(MINECRAFT.level != null ? World.getName() : ""))
                .set("time_type", () -> Value.string(MINECRAFT.level != null ? World.getTimeType() : ""))
                .set("time", () -> Value.string(MINECRAFT.level != null ? World.getTime() : ""))
                .set("difficulty", () -> Value.string(MINECRAFT.level != null ? MINECRAFT.level.getDifficulty().getDisplayName().getString() : ""))
        );
        if(SimplyStatus.isVoiceModsEnable){
            ss.set("voice", new ValueMap()
                    .set("listener", () -> Value.string(new Voice().listener))
            );
        }
        if(SimplyStatus.isMusicModsEnable){
            ss.set("music", new ValueMap()
                    .set("title", () -> Value.string(new WaterPlayerSupport().useFile ? new WaterPlayerSupport().file : new WaterPlayerSupport().title))
                    .set("author", () -> Value.string(new WaterPlayerSupport().artistIsNull ? "" : new WaterPlayerSupport().artist))
                    .set("uri", () -> Value.string(new WaterPlayerSupport().file))
            );
        }
        if(SimplyStatus.replayMod){
            ss.set("replay", new ValueMap()
                    .set("date", () -> Value.string(getReplayDateFormat(new ReplayMod().date)))
                    .set("name", () -> Value.string(SimplyStatus.userConfig.getBoolean("VIEW_REPLAY_MOD_SERVER_NAME", true) ? new ReplayMod().name : new ReplayMod().address))
            );
        }
    }
    public static String getReplayDateFormat(long time){
        String strDateFormat = SimplyStatus.localization.getLocalization("mod.replaymod.date_format", false);
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
            SimplyStatus.log(error.getMessage(), Level.ERROR);
            return null;
        }
    }
    public static String run(Script script, StringBuilder sb) {
        Section section = runSection(script, sb);
        return section != null ? section.toString() : null;
    }
    public static String run(Script script) {
        return run(script, new StringBuilder());
    }

}
