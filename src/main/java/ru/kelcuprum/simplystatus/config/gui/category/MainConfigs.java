package ru.kelcuprum.simplystatus.config.gui.category;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import dev.isxander.yacl3.impl.controller.BooleanControllerBuilderImpl;
import net.minecraft.client.Minecraft;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.config.ModConfig;
import ru.kelcuprum.simplystatus.info.Client;
import ru.kelcuprum.simplystatus.localization.Localization;

import java.util.Arrays;

public class MainConfigs {
    public ConfigCategory getCategory(){
        Minecraft CLIENT = Minecraft.getInstance();
        ConfigCategory.Builder category = ConfigCategory.createBuilder()
                .name(Localization.getText("simplystatus.config.client"));
        category.tooltip(Localization.toText(String.format("%s SimplyStatus %s", Client.getVersionStatusColorCup(), Client.getVersion())));
        category.option(Option.createBuilder(boolean.class)
                .name(Localization.getText("simplystatus.config.client.use_minecraft_id"))
                .description(OptionDescription.createBuilder()
                        .text(Localization.getText("simplystatus.config.client.use_minecraft_id.description")).build())
                .binding(false, () -> SimplyStatus.userConfig.getBoolean("USE_ANOTHER_ID", false), newVal -> SimplyStatus.userConfig.setBoolean("USE_ANOTHER_ID", newVal))
                .controller(BooleanControllerBuilderImpl::new)
                .available(!SimplyStatus.userConfig.getBoolean("USE_CUSTOM_APP_ID", false))
                .build());
        try{
            category.option(Option.createBuilder(Integer.class)
                    .name(Localization.getText("simplystatus.config.client.assets"))
                    .description(OptionDescription.createBuilder()
                            .text(Localization.getText("simplystatus.config.client.assets.description")).build())
                    .binding(1, () -> Arrays.asList(ModConfig.assetsList).indexOf(SimplyStatus.userConfig.getString("USE_ASSETS", ModConfig.assetsList[0])) + 1, newVal -> SimplyStatus.userConfig.setString("USE_ASSETS", ModConfig.assetsList[newVal-1]))
                    .controller(opt -> IntegerSliderControllerBuilder.create(opt)
                            .range(1, ModConfig.assetsList.length)
                            .step(1)
                            .valueFormatter(val -> Localization.toText(ModConfig.assetsList[val-1])))
                    .available(!SimplyStatus.userConfig.getBoolean("USE_CUSTOM_ASSETS", false) && !SimplyStatus.userConfig.getBoolean("USE_CUSTOM_APP_ID", false))
                    .build());
        } catch (Exception e){
            e.printStackTrace();
        }
        if(SimplyStatus.apiNames.length > 1) {
            category.option(Option.createBuilder(Integer.class)
                    .name(Localization.getText("simplystatus.config.client.api"))
                    .description(OptionDescription.createBuilder()
                            .text(Localization.getText("simplystatus.config.client.api.description")).build())
                    .binding(1, () -> SimplyStatus.userConfig.getInt("USE_API_RENDER", 0) + 1, newVal -> SimplyStatus.userConfig.setInt("USE_API_RENDER", newVal - 1))
                    .controller(opt -> IntegerSliderControllerBuilder.create(opt)
                            .range(1, SimplyStatus.apiNames.length)
                            .step(1)
                            .valueFormatter(val -> Localization.toText(SimplyStatus.apiNames[val - 1])))
                    .available(CLIENT.getUser().getType().getName().equals("msa") || CLIENT.getUser().getType().getName().equals("mojang"))
                    .build());
        }
        category.option(Option.createBuilder(boolean.class)
                .name(Localization.getText("simplystatus.config.client.show_game_started"))
                .description(OptionDescription.createBuilder()
                        .text(Localization.getText("simplystatus.config.client.show_game_started.description")).build())
                .binding(true, () -> SimplyStatus.userConfig.getBoolean("SHOW_GAME_TIME", true), newVal -> SimplyStatus.userConfig.setBoolean("SHOW_GAME_TIME", newVal))
                .controller(BooleanControllerBuilderImpl::new)
                .build());
        category.option(Option.createBuilder(boolean.class)
                .name(Localization.getText("simplystatus.config.client.show_avatar_player"))
                .description(OptionDescription.createBuilder()
                        .text(Localization.getText("simplystatus.config.client.show_avatar_player.description")).build())
                .binding(true, () -> SimplyStatus.userConfig.getBoolean("SHOW_AVATAR_PLAYER",true), newVal -> SimplyStatus.userConfig.setBoolean("SHOW_AVATAR_PLAYER", newVal))
                .controller(BooleanControllerBuilderImpl::new)
                .build());
        category.option(Option.createBuilder(boolean.class)
                .name(Localization.getText("simplystatus.config.client.view_item_off_hand"))
                .description(OptionDescription.createBuilder()
                        .text(Localization.getText("simplystatus.config.client.view_item_off_hand.description")).build())
                .binding(false, () -> SimplyStatus.userConfig.getBoolean("VIEW_ITEM_OFF_HAND",false), newVal -> SimplyStatus.userConfig.setBoolean("VIEW_ITEM_OFF_HAND", newVal))
                .controller(BooleanControllerBuilderImpl::new)
                .build());
        category.option(Option.createBuilder(boolean.class)
                .name(Localization.getText("simplystatus.config.client.view_statistics"))
                .description(OptionDescription.createBuilder()
                        .text(Localization.getText("simplystatus.config.client.view_statistics.description")).build())
                .binding(true, () -> SimplyStatus.userConfig.getBoolean("VIEW_STATISTICS",true), newVal -> SimplyStatus.userConfig.setBoolean("VIEW_STATISTICS", newVal))
                .controller(BooleanControllerBuilderImpl::new)
                .build());
        category.option(Option.createBuilder(boolean.class)
                .name(Localization.getText("simplystatus.config.client.view_player_name"))
                .description(OptionDescription.createBuilder()
                        .text(Localization.getText("simplystatus.config.client.view_player_name.description")).build())
                .binding(true, () -> SimplyStatus.userConfig.getBoolean("VIEW_PLAYER_NAME",true), newVal -> SimplyStatus.userConfig.setBoolean("VIEW_PLAYER_NAME", newVal))
                .controller(BooleanControllerBuilderImpl::new)
                .build());
        return category.build();
    }
}
