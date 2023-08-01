package ru.simplykel.simplystatus.config.gui.yacl.category;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionGroup;
import dev.isxander.yacl3.impl.controller.BooleanControllerBuilderImpl;
import dev.isxander.yacl3.impl.controller.StringControllerBuilderImpl;
import ru.simplykel.simplystatus.Main;
import ru.simplykel.simplystatus.config.Localization;
import ru.simplykel.simplystatus.config.ModConfig;
import ru.simplykel.simplystatus.config.UserConfig;

public class MusicConfigs {
    public ConfigCategory getCategory(){
        ConfigCategory.Builder category = ConfigCategory.createBuilder()
                .name(Localization.getText("simplystatus.config.musicplayer"));
        category.option(
                Option.createBuilder(String.class)
                        .name(Localization.getText("simplystatus.config.localization.mod.music.menu"))
                        .binding(Localization.getLcnDefault("mod.music.menu"),
                                () -> Localization.getLocalization("mod.music.menu", false),
                                newVal -> Localization.setLocalization("mod.music.menu", newVal))
                        .controller(StringControllerBuilderImpl::new)
                        .available(Main.musicPlayer || Main.kelUtils)
                        .build()
        );
        category.option(
                Option.createBuilder(String.class)
                        .name(Localization.getText("simplystatus.config.localization.mod.music"))
                        .binding(Localization.getLcnDefault("mod.music"),
                                () -> Localization.getLocalization("mod.music", false),
                                newVal -> Localization.setLocalization("mod.music", newVal))
                        .controller(StringControllerBuilderImpl::new)
                        .available(Main.musicPlayer || Main.kelUtils)
                        .build()
        );
        category.option(
                Option.createBuilder(String.class)
                        .name(Localization.getText("simplystatus.config.localization.mod.music.format.artist"))
                        .binding(Localization.getLcnDefault("mod.music.format.artist"),
                                () -> Localization.getLocalization("mod.music.format.artist", false),
                                newVal -> Localization.setLocalization("mod.music.format.artist", newVal))
                        .controller(StringControllerBuilderImpl::new)
                        .available(Main.musicPlayer || Main.kelUtils)
                        .build()
        );
        category.option(
                Option.createBuilder(String.class)
                        .name(Localization.getText("simplystatus.config.localization.mod.music.format"))
                        .binding(Localization.getLcnDefault("mod.music.format"),
                                () -> Localization.getLocalization("mod.music.format", false),
                                newVal -> Localization.setLocalization("mod.music.format", newVal))
                        .controller(StringControllerBuilderImpl::new)
                        .available(Main.musicPlayer || Main.kelUtils)
                        .build()
        );

        return category.build();
    }
}
