package ru.kelcuprum.simplystatus.config.gui.category;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.impl.controller.BooleanControllerBuilderImpl;
import dev.isxander.yacl3.impl.controller.StringControllerBuilderImpl;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.localization.Localization;

public class ReplayConfigs {
    public ConfigCategory getCategory(){
        ConfigCategory.Builder category = ConfigCategory.createBuilder()
                .name(Localization.getText("simplystatus.config.replaymod"));
        category.option(
                Option.createBuilder(boolean.class)
                        .name(Localization.getText("simplystatus.config.replaymod.view_replay_mod_server_name"))
                        .binding(true,
                                () -> SimplyStatus.userConfig.getBoolean("VIEW_REPLAY_MOD_SERVER_NAME", true),
                                newVal -> SimplyStatus.userConfig.setBoolean("VIEW_REPLAY_MOD_SERVER_NAME", newVal))
                        .controller(BooleanControllerBuilderImpl::new)
                        .available(SimplyStatus.replayMod)
                        .build()
        );
        category.option(
                Option.createBuilder(String.class)
                        .name(Localization.getText("simplystatus.config.localization.mod.replaymod"))
                        .binding(Localization.getLcnDefault("mod.replaymod"),
                                () -> Localization.getLocalization("mod.replaymod", false),
                                newVal -> Localization.setLocalization("mod.replaymod", newVal))
                        .controller(StringControllerBuilderImpl::new)
                        .available(SimplyStatus.replayMod)
                        .build()
        );
        category.option(
                Option.createBuilder(String.class)
                        .name(Localization.getText("simplystatus.config.localization.mod.replaymod.state"))
                        .binding(Localization.getLcnDefault("mod.replaymod.state"),
                                () -> Localization.getLocalization("mod.replaymod.state", false),
                                newVal -> Localization.setLocalization("mod.replaymod.state", newVal))
                        .controller(StringControllerBuilderImpl::new)
                        .available(SimplyStatus.replayMod)
                        .build()
        );

        return category.build();
    }
}
