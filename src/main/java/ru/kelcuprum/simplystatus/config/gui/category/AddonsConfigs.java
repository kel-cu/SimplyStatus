package ru.kelcuprum.simplystatus.config.gui.category;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.OptionGroup;
import dev.isxander.yacl3.impl.controller.BooleanControllerBuilderImpl;
import dev.isxander.yacl3.impl.controller.StringControllerBuilderImpl;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.config.ModConfig;
import ru.kelcuprum.simplystatus.localization.Localization;

public class AddonsConfigs {
    public ConfigCategory getCategory(){
        ConfigCategory.Builder category = ConfigCategory.createBuilder()
                .name(Localization.getText("simplystatus.config.addons"));
        category.group(OptionGroup.createBuilder()
                .name(Localization.getText("simplystatus.config.addons.modifications"))
                .option(
                        Option.createBuilder(boolean.class)
                                .name(Localization.getText("simplystatus.config.addons.view_replay_mod"))
                                .description(OptionDescription.createBuilder()
                                        .text(Localization.getText("simplystatus.config.addons.view_replay_mod.description")).build())
                                .binding(false, () -> SimplyStatus.userConfig.getBoolean("VIEW_REPLAY_MOD", true), newVal -> SimplyStatus.userConfig.setBoolean("VIEW_REPLAY_MOD", newVal))
                                .controller(BooleanControllerBuilderImpl::new)
                                .available(SimplyStatus.replayMod)
                                .build()
                )
                .option(
                        Option.createBuilder(boolean.class)
                                .name(Localization.getText("simplystatus.config.addons.view_voice_speak"))
                                .description(OptionDescription.createBuilder()
                                        .text(Localization.getText("simplystatus.config.addons.view_voice_speak.description")).build())
                                .binding(false, () -> SimplyStatus.userConfig.getBoolean("VIEW_VOICE_SPEAK", false), newVal -> SimplyStatus.userConfig.setBoolean("VIEW_VOICE_SPEAK", newVal))
                                .controller(BooleanControllerBuilderImpl::new)
                                .available(SimplyStatus.isVoiceModsEnable)
                                .build()
                )
                .option(
                        Option.createBuilder(boolean.class)
                                .name(Localization.getText("simplystatus.config.addons.view_music_listener"))
                                .description(OptionDescription.createBuilder()
                                        .text(Localization.getText("simplystatus.config.addons.view_music_listener.description")).build())
                                .binding(false, () -> SimplyStatus.userConfig.getBoolean("VIEW_MUSIC_LISTENER", false), newVal -> SimplyStatus.userConfig.setBoolean("VIEW_MUSIC_LISTENER", newVal))
                                .controller(BooleanControllerBuilderImpl::new)
                                .available(SimplyStatus.isMusicModsEnable)
                                .build()
                )

                .build());

        category.group(OptionGroup.createBuilder()
                .name(Localization.getText("simplystatus.config.addons.indicators"))
                .option(
                        Option.createBuilder(boolean.class)
                                .name(Localization.getText("simplystatus.config.addons.show_items"))
                                .description(OptionDescription.createBuilder()
                                        .text(Localization.getText("simplystatus.config.addons.show_items.description")).build())
                                .binding(true, () -> SimplyStatus.userConfig.getBoolean("SHOW_ITEMS", true), newVal -> SimplyStatus.userConfig.setBoolean("SHOW_ITEMS", newVal))
                                .controller(BooleanControllerBuilderImpl::new)
                                .build()
                )
                .option(
                        Option.createBuilder(boolean.class)
                                .name(Localization.getText("simplystatus.config.addons.enable_time_cycle"))
                                .description(OptionDescription.createBuilder()
                                        .text(Localization.getText("simplystatus.config.addons.enable_time_cycle.description")).build())
                                .binding(true, () -> SimplyStatus.userConfig.getBoolean("ENABLE_TIME_CYCLE", true), newVal -> SimplyStatus.userConfig.setBoolean("ENABLE_TIME_CYCLE", newVal))
                                .controller(BooleanControllerBuilderImpl::new)
                                .build()
                )
                .option(
                        Option.createBuilder(boolean.class)
                                .name(Localization.getText("simplystatus.config.addons.enable_world"))
                                .description(OptionDescription.createBuilder()
                                        .text(Localization.getText("simplystatus.config.addons.enable_world.description")).build())
                                .binding(true, () -> SimplyStatus.userConfig.getBoolean("ENABLE_WORLD", true), newVal -> SimplyStatus.userConfig.setBoolean("ENABLE_WORLD", newVal))
                                .controller(BooleanControllerBuilderImpl::new)
                                .build()
                )

                .build());

        category.group(OptionGroup.createBuilder()
                .name(Localization.getText("simplystatus.config.addons.custom"))
                .option(
                        Option.createBuilder(boolean.class)
                                .name(Localization.getText("simplystatus.config.addons.use_custom_assets"))
                                .description(OptionDescription.createBuilder()
                                        .text(Localization.getText("simplystatus.config.addons.use_custom_assets.description")).build())
                                .binding(false, () -> SimplyStatus.userConfig.getBoolean("USE_CUSTOM_ASSETS", false), newVal -> SimplyStatus.userConfig.setBoolean("USE_CUSTOM_ASSETS", newVal))
                                .controller(BooleanControllerBuilderImpl::new)
                                .build()
                )
                .option(
                        Option.createBuilder(boolean.class)
                                .name(Localization.getText("simplystatus.config.client.use_custom_app_id"))
                                .description(OptionDescription.createBuilder()
                                        .text(Localization.getText("simplystatus.config.addons.use_custom_app_id.description")).build())
                                .binding(false, () -> SimplyStatus.userConfig.getBoolean("USE_CUSTOM_APP_ID", false), newVal -> SimplyStatus.userConfig.setBoolean("USE_CUSTOM_APP_ID", newVal))
                                .controller(BooleanControllerBuilderImpl::new)
                                .build()
                )
                .option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.addons.custom_app_id"))
                                .description(OptionDescription.createBuilder()
                                        .text(Localization.getText("simplystatus.config.addons.custom_app_id.description")).build())
                                .binding(ModConfig.baseID, () -> SimplyStatus.userConfig.getString("CUSTOM_APP_ID", ModConfig.baseID), newVal -> SimplyStatus.userConfig.setString("CUSTOM_APP_ID" ,newVal))
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                )
                .build());
        return category.build();
    }
}
