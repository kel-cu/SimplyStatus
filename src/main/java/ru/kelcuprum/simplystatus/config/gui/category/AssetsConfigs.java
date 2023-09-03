package ru.kelcuprum.simplystatus.config.gui.category;


import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.OptionGroup;
import dev.isxander.yacl3.impl.controller.BooleanControllerBuilderImpl;
import dev.isxander.yacl3.impl.controller.StringControllerBuilderImpl;
import net.minecraft.client.Minecraft;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.config.ModConfig;
import ru.kelcuprum.simplystatus.localization.Localization;

public class AssetsConfigs {
    public ConfigCategory getCategory(){
        Minecraft minecraft = Minecraft.getInstance();
        ConfigCategory.Builder category = ConfigCategory.createBuilder()
                .name(Localization.getText("simplystatus.config.assets"));
        category.group(OptionGroup.createBuilder()
                .name(Localization.getText("simplystatus.config.assets.title.menu"))
                .option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.assets.logo"))
                                .binding(ModConfig.defaultAssets.logo,
                                        () -> SimplyStatus.ASSETS.logo,
                                        newVal -> SimplyStatus.ASSETS.logo = newVal)
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).build());
        category.group(OptionGroup.createBuilder()
                .name(Localization.getText("simplystatus.config.assets.title.time"))
                .option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.assets.morning"))
                                .binding(ModConfig.defaultAssets.morning,
                                        () -> SimplyStatus.ASSETS.morning,
                                        newVal -> SimplyStatus.ASSETS.morning = newVal)
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.assets.day"))
                                .binding(ModConfig.defaultAssets.day,
                                        () -> SimplyStatus.ASSETS.day,
                                        newVal -> SimplyStatus.ASSETS.day = newVal)
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.assets.evening"))
                                .binding(ModConfig.defaultAssets.evening,
                                        () -> SimplyStatus.ASSETS.evening,
                                        newVal -> SimplyStatus.ASSETS.evening = newVal)
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.assets.night"))
                                .binding(ModConfig.defaultAssets.night,
                                        () -> SimplyStatus.ASSETS.night,
                                        newVal -> SimplyStatus.ASSETS.night = newVal)
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).build());
        category.group(OptionGroup.createBuilder()
                .name(Localization.getText("simplystatus.config.assets.title.worlds"))
                .option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.assets.world"))
                                .binding(ModConfig.defaultAssets.world,
                                        () -> SimplyStatus.ASSETS.world,
                                        newVal -> SimplyStatus.ASSETS.world = newVal)
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.assets.world_nether"))
                                .binding(ModConfig.defaultAssets.world_nether,
                                        () -> SimplyStatus.ASSETS.world_nether,
                                        newVal -> SimplyStatus.ASSETS.world_nether = newVal)
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.assets.world_the_end"))
                                .binding(ModConfig.defaultAssets.world_the_end,
                                        () -> SimplyStatus.ASSETS.world_the_end,
                                        newVal -> SimplyStatus.ASSETS.world_the_end = newVal)
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.assets.world_moon"))
                                .binding(ModConfig.defaultAssets.world_moon,
                                        () -> SimplyStatus.ASSETS.world_moon,
                                        newVal -> SimplyStatus.ASSETS.world_moon = newVal)
                                .controller(StringControllerBuilderImpl::new)
                                .available(minecraft.getLaunchedVersion().equals("23w13a_or_b"))
                                .build()
                ).build());
        category.group(OptionGroup.createBuilder()
                .name(Localization.getText("simplystatus.config.assets.title.modification"))
                .option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.assets.music"))
                                .binding(ModConfig.defaultAssets.music,
                                        () -> SimplyStatus.ASSETS.music,
                                        newVal -> SimplyStatus.ASSETS.music = newVal)
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.assets.replaymod"))
                                .binding(ModConfig.defaultAssets.replaymod,
                                        () -> SimplyStatus.ASSETS.replaymod,
                                        newVal -> SimplyStatus.ASSETS.replaymod = newVal)
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.assets.voice"))
                                .binding(ModConfig.defaultAssets.voice,
                                        () -> SimplyStatus.ASSETS.voice,
                                        newVal -> SimplyStatus.ASSETS.voice = newVal)
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).build());
        category.group(OptionGroup.createBuilder()
                .name(Localization.getText("simplystatus.config.assets.title.unknown"))
                .option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.assets.unknown"))
                                .binding(ModConfig.defaultAssets.unknown,
                                        () -> SimplyStatus.ASSETS.unknown,
                                        newVal -> SimplyStatus.ASSETS.unknown = newVal)
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.assets.unknown_world"))
                                .binding(ModConfig.defaultAssets.unknown_world,
                                        () -> SimplyStatus.ASSETS.unknown_world,
                                        newVal -> SimplyStatus.ASSETS.unknown_world = newVal)
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).build());
        return category.build();
    }
}
