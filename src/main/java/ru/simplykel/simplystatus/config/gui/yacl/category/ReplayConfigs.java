package ru.simplykel.simplystatus.config.gui.yacl.category;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.impl.controller.BooleanControllerBuilderImpl;
import dev.isxander.yacl3.impl.controller.StringControllerBuilderImpl;
import ru.simplykel.simplystatus.Main;
import ru.simplykel.simplystatus.config.Localization;
import ru.simplykel.simplystatus.config.UserConfig;

public class ReplayConfigs {
    public ConfigCategory getCategory(){
        ConfigCategory.Builder category = ConfigCategory.createBuilder()
                .name(Localization.getText("simplystatus.config.replaymod"));
        category.option(
                Option.createBuilder(boolean.class)
                        .name(Localization.getText("simplystatus.config.replaymod.view_replay_mod_server_name"))
                        .binding(false,
                                () -> UserConfig.VIEW_REPLAY_MOD_SERVER_NAME,
                                newVal -> UserConfig.VIEW_REPLAY_MOD_SERVER_NAME = newVal)
                        .controller(BooleanControllerBuilderImpl::new)
                        .available(Main.replayMod)
                        .build()
        );
        category.option(
                Option.createBuilder(String.class)
                        .name(Localization.getText("simplystatus.config.localization.mod.replaymod"))
                        .binding(Localization.getLcnDefault("mod.replaymod"),
                                () -> Localization.getLocalization("mod.replaymod", false),
                                newVal -> Localization.setLocalization("mod.replaymod", newVal))
                        .controller(StringControllerBuilderImpl::new)
                        .available(Main.replayMod)
                        .build()
        );
        category.option(
                Option.createBuilder(String.class)
                        .name(Localization.getText("simplystatus.config.localization.mod.replaymod.state"))
                        .binding(Localization.getLcnDefault("mod.replaymod.state"),
                                () -> Localization.getLocalization("mod.replaymod.state", false),
                                newVal -> Localization.setLocalization("mod.replaymod.state", newVal))
                        .controller(StringControllerBuilderImpl::new)
                        .available(Main.replayMod)
                        .build()
        );
        category.option(
                Option.createBuilder(String.class)
                        .name(Localization.getText("simplystatus.config.localization.mod.replaymod.date_format"))
                        .binding(Localization.getLcnDefault("mod.replaymod.date_format"),
                                () -> Localization.getLocalization("mod.replaymod.date_format", false),
                                newVal -> Localization.setLocalization("mod.replaymod.date_format", newVal))
                        .controller(StringControllerBuilderImpl::new)
                        .available(Main.replayMod)
                        .build()
        );

        return category.build();
    }
}
