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

public class AddonsConfigs {
    public ConfigCategory getCategory(){
        ConfigCategory.Builder category = ConfigCategory.createBuilder()
                .name(Localization.getText("simplystatus.config.addons"));
        category.group(OptionGroup.createBuilder()
                .name(Localization.getText("simplystatus.config.addons.modifications"))
                .option(
                        Option.createBuilder(boolean.class)
                                .name(Localization.getText("simplystatus.config.addons.view_replay_mod"))
                                .binding(false, () -> UserConfig.VIEW_REPLAY_MOD, newVal -> UserConfig.VIEW_REPLAY_MOD = newVal)
                                .controller(BooleanControllerBuilderImpl::new)
                                .available(Main.replayMod)
                                .build()
                )
                .option(
                        Option.createBuilder(boolean.class)
                                .name(Localization.getText("simplystatus.config.addons.view_voice_speak"))
                                .binding(false, () -> UserConfig.VIEW_VOICE_SPEAK, newVal -> UserConfig.VIEW_VOICE_SPEAK = newVal)
                                .controller(BooleanControllerBuilderImpl::new)
                                .available(Main.isVoiceModsEnable)
                                .build()
                )
                .option(
                        Option.createBuilder(boolean.class)
                                .name(Localization.getText("simplystatus.config.addons.view_music_listener"))
                                .binding(false, () -> UserConfig.VIEW_MUSIC_LISTENER, newVal -> UserConfig.VIEW_MUSIC_LISTENER = newVal)
                                .controller(BooleanControllerBuilderImpl::new)
                                .available(Main.musicPlayer || Main.kelUtils)
                                .build()
                )

                .build());

        category.group(OptionGroup.createBuilder()
                .name(Localization.getText("simplystatus.config.addons.indicators"))
                .option(
                        Option.createBuilder(boolean.class)
                                .name(Localization.getText("simplystatus.config.addons.show_items"))
                                .binding(true, () -> UserConfig.SHOW_ITEMS, newVal -> UserConfig.SHOW_ITEMS = newVal)
                                .controller(BooleanControllerBuilderImpl::new)
                                .build()
                )
                .option(
                        Option.createBuilder(boolean.class)
                                .name(Localization.getText("simplystatus.config.addons.enable_time_cycle"))
                                .binding(true, () -> UserConfig.ENABLE_TIME_CYCLE, newVal -> UserConfig.ENABLE_TIME_CYCLE = newVal)
                                .controller(BooleanControllerBuilderImpl::new)
                                .build()
                )
                .option(
                        Option.createBuilder(boolean.class)
                                .name(Localization.getText("simplystatus.config.addons.enable_world"))
                                .binding(true, () -> UserConfig.ENABLE_WORLD, newVal -> UserConfig.ENABLE_WORLD = newVal)
                                .controller(BooleanControllerBuilderImpl::new)
                                .build()
                )

                .build());

        category.group(OptionGroup.createBuilder()
                .name(Localization.getText("simplystatus.config.addons.custom"))
                .option(
                        Option.createBuilder(boolean.class)
                                .name(Localization.getText("simplystatus.config.addons.use_custom_assets"))
                                .binding(false, () -> UserConfig.USE_CUSTOM_ASSETS, newVal -> UserConfig.USE_CUSTOM_ASSETS = newVal)
                                .controller(BooleanControllerBuilderImpl::new)
                                .build()
                )
                .option(
                        Option.createBuilder(boolean.class)
                                .name(Localization.getText("simplystatus.config.client.use_custom_app_id"))
                                .binding(false, () -> UserConfig.USE_CUSTOM_APP_ID, newVal -> UserConfig.USE_CUSTOM_APP_ID = newVal)
                                .controller(BooleanControllerBuilderImpl::new)
                                .build()
                )
                .option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.addons.custom_app_id"))
                                .binding(ModConfig.baseID, () -> UserConfig.CUSTOM_APP_ID, newVal -> UserConfig.CUSTOM_APP_ID = newVal)
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                )
                .build());
        return category.build();
    }
}
