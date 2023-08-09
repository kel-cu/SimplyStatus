package ru.simplykel.simplystatus.config.gui.yacl.category;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionGroup;
import dev.isxander.yacl3.impl.controller.StringControllerBuilderImpl;
import net.minecraft.client.MinecraftClient;
import ru.simplykel.simplystatus.Client;
import ru.simplykel.simplystatus.config.Localization;
import ru.simplykel.simplystatus.config.ModConfig;

public class AssetsConfigs {
    public ConfigCategory getCategory(){
        MinecraftClient CLIENT = MinecraftClient.getInstance();
        ConfigCategory.Builder category = ConfigCategory.createBuilder()
                .name(Localization.getText("simplystatus.config.assets"));
        category.group(OptionGroup.createBuilder()
                .name(Localization.getText("simplystatus.config.assets.title.menu"))
                .option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.assets.logo"))
                                .binding(ModConfig.defaultAssets.logo,
                                        () -> Client.ASSETS.logo,
                                        newVal -> Client.ASSETS.logo = newVal)
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).build());
        category.group(OptionGroup.createBuilder()
                .name(Localization.getText("simplystatus.config.assets.title.time"))
                .option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.assets.morning"))
                                .binding(ModConfig.defaultAssets.morning,
                                        () -> Client.ASSETS.morning,
                                        newVal -> Client.ASSETS.morning = newVal)
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.assets.day"))
                                .binding(ModConfig.defaultAssets.day,
                                        () -> Client.ASSETS.day,
                                        newVal -> Client.ASSETS.day = newVal)
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.assets.evening"))
                                .binding(ModConfig.defaultAssets.evening,
                                        () -> Client.ASSETS.evening,
                                        newVal -> Client.ASSETS.evening = newVal)
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.assets.night"))
                                .binding(ModConfig.defaultAssets.night,
                                        () -> Client.ASSETS.night,
                                        newVal -> Client.ASSETS.night = newVal)
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).build());
        category.group(OptionGroup.createBuilder()
                .name(Localization.getText("simplystatus.config.assets.title.worlds"))
                .option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.assets.world"))
                                .binding(ModConfig.defaultAssets.world,
                                        () -> Client.ASSETS.world,
                                        newVal -> Client.ASSETS.world = newVal)
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.assets.world_nether"))
                                .binding(ModConfig.defaultAssets.world_nether,
                                        () -> Client.ASSETS.world_nether,
                                        newVal -> Client.ASSETS.world_nether = newVal)
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.assets.world_the_end"))
                                .binding(ModConfig.defaultAssets.world_the_end,
                                        () -> Client.ASSETS.world_the_end,
                                        newVal -> Client.ASSETS.world_the_end = newVal)
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.assets.world_moon"))
                                .binding(ModConfig.defaultAssets.world_moon,
                                        () -> Client.ASSETS.world_moon,
                                        newVal -> Client.ASSETS.world_moon = newVal)
                                .controller(StringControllerBuilderImpl::new)
                                .available(CLIENT.getGameVersion().equals("23w13a_or_b"))
                                .build()
                ).build());
        category.group(OptionGroup.createBuilder()
                .name(Localization.getText("simplystatus.config.assets.title.modification"))
                .option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.assets.music"))
                                .binding(ModConfig.defaultAssets.music,
                                        () -> Client.ASSETS.music,
                                        newVal -> Client.ASSETS.music = newVal)
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.assets.replaymod"))
                                .binding(ModConfig.defaultAssets.replaymod,
                                        () -> Client.ASSETS.replaymod,
                                        newVal -> Client.ASSETS.replaymod = newVal)
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.assets.voice"))
                                .binding(ModConfig.defaultAssets.voice,
                                        () -> Client.ASSETS.voice,
                                        newVal -> Client.ASSETS.voice = newVal)
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).build());
        category.group(OptionGroup.createBuilder()
                .name(Localization.getText("simplystatus.config.assets.title.unknown"))
                .option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.assets.unknown"))
                                .binding(ModConfig.defaultAssets.unknown,
                                        () -> Client.ASSETS.unknown,
                                        newVal -> Client.ASSETS.unknown = newVal)
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.assets.unknown_world"))
                                .binding(ModConfig.defaultAssets.unknown_world,
                                        () -> Client.ASSETS.unknown_world,
                                        newVal -> Client.ASSETS.unknown_world = newVal)
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).build());
        return category.build();
    }
}
