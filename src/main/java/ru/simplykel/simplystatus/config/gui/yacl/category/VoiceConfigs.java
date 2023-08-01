package ru.simplykel.simplystatus.config.gui.yacl.category;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.impl.controller.BooleanControllerBuilderImpl;
import dev.isxander.yacl3.impl.controller.StringControllerBuilderImpl;
import ru.simplykel.simplystatus.Main;
import ru.simplykel.simplystatus.config.Localization;
import ru.simplykel.simplystatus.config.UserConfig;

public class VoiceConfigs {
    public ConfigCategory getCategory(){
        ConfigCategory.Builder category = ConfigCategory.createBuilder()
                .name(Localization.getText("simplystatus.config.voice"));
        category.option(
                Option.createBuilder(String.class)
                        .name(Localization.getText("simplystatus.config.localization.mod.voice"))
                        .binding(Localization.getLcnDefault("mod.voice"),
                                () -> Localization.getLocalization("mod.voice", false),
                                newVal -> Localization.setLocalization("mod.voice", newVal))
                        .controller(StringControllerBuilderImpl::new)
                        .available(Main.isVoiceModsEnable)
                        .build()
        );
        category.option(
                Option.createBuilder(String.class)
                        .name(Localization.getText("simplystatus.config.localization.mod.voice.players.one"))
                        .binding(Localization.getLcnDefault("mod.voice.players.one"),
                                () -> Localization.getLocalization("mod.voice.players.one", false),
                                newVal -> Localization.setLocalization("mod.voice.players.one", newVal))
                        .controller(StringControllerBuilderImpl::new)
                        .available(Main.isVoiceModsEnable)
                        .build()
        );
        category.option(
                Option.createBuilder(String.class)
                        .name(Localization.getText("simplystatus.config.localization.mod.voice.players.more"))
                        .binding(Localization.getLcnDefault("mod.voice.players.more"),
                                () -> Localization.getLocalization("mod.voice.players.more", false),
                                newVal -> Localization.setLocalization("mod.voice.players.more", newVal))
                        .controller(StringControllerBuilderImpl::new)
                        .available(Main.isVoiceModsEnable)
                        .build()
        );

        return category.build();
    }
}
