package ru.kelcuprum.simplystatus.config.gui.category;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.impl.controller.StringControllerBuilderImpl;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.localization.Localization;

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
                        .available(SimplyStatus.isMusicModsEnable)
                        .build()
        );
        category.option(
                Option.createBuilder(String.class)
                        .name(Localization.getText("simplystatus.config.localization.mod.music"))
                        .binding(Localization.getLcnDefault("mod.music"),
                                () -> Localization.getLocalization("mod.music", false),
                                newVal -> Localization.setLocalization("mod.music", newVal))
                        .controller(StringControllerBuilderImpl::new)
                        .available(SimplyStatus.isMusicModsEnable)
                        .build()
        );
        //
        category.option(
                Option.createBuilder(String.class)
                        .name(Localization.getText("simplystatus.config.localization.mod.music.menu.noauthor"))
                        .binding(Localization.getLcnDefault("mod.music.menu.noauthor"),
                                () -> Localization.getLocalization("mod.music.menu.noauthor", false),
                                newVal -> Localization.setLocalization("mod.music.menu.noauthor", newVal))
                        .controller(StringControllerBuilderImpl::new)
                        .available(SimplyStatus.isMusicModsEnable)
                        .build()
        );
        category.option(
                Option.createBuilder(String.class)
                        .name(Localization.getText("simplystatus.config.localization.mod.music.noauthor"))
                        .binding(Localization.getLcnDefault("mod.music.noauthor"),
                                () -> Localization.getLocalization("mod.music.noauthor", false),
                                newVal -> Localization.setLocalization("mod.music.noauthor", newVal))
                        .controller(StringControllerBuilderImpl::new)
                        .available(SimplyStatus.isMusicModsEnable)
                        .build()
        );
        return category.build();
    }
}
