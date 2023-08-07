package ru.simplykel.simplystatus.config.gui.yacl.category;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import dev.isxander.yacl3.impl.controller.BooleanControllerBuilderImpl;
import net.minecraft.client.MinecraftClient;
import ru.simplykel.simplystatus.config.Localization;
import ru.simplykel.simplystatus.config.ModConfig;
import ru.simplykel.simplystatus.config.UserConfig;

import java.util.Arrays;

public class MainConfigs {
    public static String[] apiNames = {
            "CraftHead",
            "Alina API: 2D",
            "Alina API: 3D"
    };
    public ConfigCategory getCategory(){
        MinecraftClient CLIENT = MinecraftClient.getInstance();
        ConfigCategory.Builder category = ConfigCategory.createBuilder()
                .name(Localization.getText("simplystatus.config.client"));
        category.option(Option.createBuilder(boolean.class)
                .description(OptionDescription.createBuilder().text(Localization.getText("simplystatus.config.client.use_yacl.description")).build())
                .name(Localization.getText("simplystatus.config.client.use_yacl"))
                .binding(false, () -> UserConfig.USE_YACL_CONFIGURATION, newVal -> UserConfig.USE_YACL_CONFIGURATION = newVal)
                .controller(BooleanControllerBuilderImpl::new)
                .build());
        category.option(Option.createBuilder(boolean.class)
                .name(Localization.getText("simplystatus.config.client.use_minecraft_id"))
                .description(OptionDescription.createBuilder()
                        .text(Localization.getText("simplystatus.config.client.use_minecraft_id.description")).build())
                .binding(false, () -> UserConfig.USE_ANOTHER_ID, newVal -> UserConfig.USE_ANOTHER_ID = newVal)
                .controller(BooleanControllerBuilderImpl::new)
                .available(!UserConfig.USE_CUSTOM_APP_ID)
                .build());
        category.option(Option.createBuilder(Integer.class)
                .name(Localization.getText("simplystatus.config.client.assets"))
                .description(OptionDescription.createBuilder()
                        .text(Localization.getText("simplystatus.config.client.assets.description")).build())
                .binding(1, () -> Arrays.asList(ModConfig.assetsList).indexOf(UserConfig.USE_ASSETS) + 1, newVal -> UserConfig.USE_ASSETS = ModConfig.assetsList[newVal-1])
                .controller(opt -> IntegerSliderControllerBuilder.create(opt)
                        .range(1, ModConfig.assetsList.length)
                        .step(1)
                        .valueFormatter(val -> Localization.toText(ModConfig.assetsList[val-1])))
                .available(!UserConfig.USE_CUSTOM_ASSETS && !UserConfig.USE_CUSTOM_APP_ID)
                .build());
        category.option(Option.createBuilder(Integer.class)
                .name(Localization.getText("simplystatus.config.client.api"))
                .description(OptionDescription.createBuilder()
                        .text(Localization.getText("simplystatus.config.client.api.description")).build())
                .binding(1, () -> UserConfig.USE_API_RENDER + 1, newVal -> UserConfig.USE_API_RENDER = newVal-1)
                .controller(opt -> IntegerSliderControllerBuilder.create(opt)
                        .range(1, apiNames.length)
                        .step(1)
                        .valueFormatter(val -> Localization.toText(apiNames[val-1])))
                .available(CLIENT.getSession().getAccountType().getName().equals("msa") || CLIENT.getSession().getAccountType().getName().equals("mojang"))
                .build());
        category.option(Option.createBuilder(boolean.class)
                .name(Localization.getText("simplystatus.config.client.show_game_started"))
                .description(OptionDescription.createBuilder()
                        .text(Localization.getText("simplystatus.config.client.show_game_started.description")).build())
                .binding(true, () -> UserConfig.SHOW_GAME_STARTED, newVal -> UserConfig.SHOW_GAME_STARTED = newVal)
                .controller(BooleanControllerBuilderImpl::new)
                .build());
        category.option(Option.createBuilder(boolean.class)
                .name(Localization.getText("simplystatus.config.client.show_avatar_player"))
                .description(OptionDescription.createBuilder()
                        .text(Localization.getText("simplystatus.config.client.show_avatar_player.description")).build())
                .binding(true, () -> UserConfig.SHOW_AVATAR_PLAYER, newVal -> UserConfig.SHOW_AVATAR_PLAYER = newVal)
                .controller(BooleanControllerBuilderImpl::new)
                .build());
        category.option(Option.createBuilder(boolean.class)
                .name(Localization.getText("simplystatus.config.client.view_item_off_hand"))
                .description(OptionDescription.createBuilder()
                        .text(Localization.getText("simplystatus.config.client.view_item_off_hand.description")).build())
                .binding(false, () -> UserConfig.VIEW_ITEM_OFF_HAND, newVal -> UserConfig.VIEW_ITEM_OFF_HAND = newVal)
                .controller(BooleanControllerBuilderImpl::new)
                .build());
        category.option(Option.createBuilder(boolean.class)
                .name(Localization.getText("simplystatus.config.client.view_statistics"))
                .description(OptionDescription.createBuilder()
                        .text(Localization.getText("simplystatus.config.client.view_statistics.description")).build())
                .binding(true, () -> UserConfig.VIEW_STATISTICS, newVal -> UserConfig.VIEW_STATISTICS = newVal)
                .controller(BooleanControllerBuilderImpl::new)
                .build());
        category.option(Option.createBuilder(boolean.class)
                .name(Localization.getText("simplystatus.config.client.view_player_name"))
                .description(OptionDescription.createBuilder()
                        .text(Localization.getText("simplystatus.config.client.view_player_name.description")).build())
                .binding(true, () -> UserConfig.VIEW_PLAYER_NAME, newVal -> UserConfig.VIEW_PLAYER_NAME = newVal)
                .controller(BooleanControllerBuilderImpl::new)
                .build());
        return category.build();
    }
}
