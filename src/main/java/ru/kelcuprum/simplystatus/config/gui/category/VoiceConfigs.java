package ru.kelcuprum.simplystatus.config.gui.category;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.impl.controller.StringControllerBuilderImpl;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.localization.Localization;

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
                        .available(SimplyStatus.isVoiceModsEnable)
                        .build()
        );
        category.option(
                Option.createBuilder(String.class)
                        .name(Localization.getText("simplystatus.config.localization.mod.voice.players.one"))
                        .binding(Localization.getLcnDefault("mod.voice.players.one"),
                                () -> Localization.getLocalization("mod.voice.players.one", false),
                                newVal -> Localization.setLocalization("mod.voice.players.one", newVal))
                        .controller(StringControllerBuilderImpl::new)
                        .available(SimplyStatus.isVoiceModsEnable)
                        .build()
        );
        category.option(
                Option.createBuilder(String.class)
                        .name(Localization.getText("simplystatus.config.localization.mod.voice.players.more"))
                        .binding(Localization.getLcnDefault("mod.voice.players.more"),
                                () -> Localization.getLocalization("mod.voice.players.more", false),
                                newVal -> Localization.setLocalization("mod.voice.players.more", newVal))
                        .controller(StringControllerBuilderImpl::new)
                        .available(SimplyStatus.isVoiceModsEnable)
                        .build()
        );

        return category.build();
    }
}
