package ru.simplykel.simplystatus.config.gui.yacl.category;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionGroup;
import dev.isxander.yacl3.impl.controller.StringControllerBuilderImpl;
import net.minecraft.client.MinecraftClient;
import ru.simplykel.simplystatus.config.Localization;

public class LocalizationConfigs {
    public ConfigCategory getCategory(){
        MinecraftClient CLIENT = MinecraftClient.getInstance();
        ConfigCategory.Builder category = ConfigCategory.createBuilder()
                .name(Localization.getText("simplystatus.config.localization"));
        category.group(OptionGroup.createBuilder()
                .name(Localization.getText("simplystatus.config.localization.title.menu"))
                .option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.localization.mainmenu"))
                                .binding(Localization.getLcnDefault("mainmenu"),
                                        () -> Localization.getLocalization("mainmenu", false),
                                        newVal -> Localization.setLocalization("mainmenu", newVal))
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.localization.mainmenu.state"))
                                .binding(Localization.getLcnDefault("mainmenu.state"),
                                        () -> Localization.getLocalization("mainmenu.state", false),
                                        newVal -> Localization.setLocalization("mainmenu.state", newVal))
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).build());
        category.group(OptionGroup.createBuilder()
                .name(Localization.getText("simplystatus.config.localization.title.singleplayer"))
                .option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.localization.singleplayer"))
                                .binding(Localization.getLcnDefault("singleplayer"),
                                        () -> Localization.getLocalization("singleplayer", false),
                                        newVal -> Localization.setLocalization("singleplayer", newVal))
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.localization.world.loading"))
                                .binding(Localization.getLcnDefault("world.loading"),
                                        () -> Localization.getLocalization("world.loading", false),
                                        newVal -> Localization.setLocalization("world.loading", newVal))
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).build());
        category.group(OptionGroup.createBuilder()
                .name(Localization.getText("simplystatus.config.localization.title.multiplayer"))
                .option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.localization.address.hidden"))
                                .binding(Localization.getLcnDefault("address.hidden"),
                                        () -> Localization.getLocalization("address.hidden", false),
                                        newVal -> Localization.setLocalization("address.hidden", newVal))
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.localization.address"))
                                .binding(Localization.getLcnDefault("address"),
                                        () -> Localization.getLocalization("address", false),
                                        newVal -> Localization.setLocalization("address", newVal))
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.localization.server.connecting"))
                                .binding(Localization.getLcnDefault("server.connecting"),
                                        () -> Localization.getLocalization("server.connecting", false),
                                        newVal -> Localization.setLocalization("server.connecting", newVal))
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.localization.server.disconnected"))
                                .binding(Localization.getLcnDefault("server.disconnected"),
                                        () -> Localization.getLocalization("server.disconnected", false),
                                        newVal -> Localization.setLocalization("server.disconnected", newVal))
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).build());
        category.group(OptionGroup.createBuilder()
                .name(Localization.getText("simplystatus.config.localization.title.player"))
                .option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.localization.player.sleep"))
                                .binding(Localization.getLcnDefault("player.sleep"),
                                        () -> Localization.getLocalization("player.sleep", false),
                                        newVal -> Localization.setLocalization("player.sleep", newVal))
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.localization.player.sneak"))
                                .binding(Localization.getLcnDefault("player.sneak"),
                                        () -> Localization.getLocalization("player.sneak", false),
                                        newVal -> Localization.setLocalization("player.sneak", newVal))
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.localization.player.on.fire"))
                                .binding(Localization.getLcnDefault("player.on.fire"),
                                        () -> Localization.getLocalization("player.on.fire", false),
                                        newVal -> Localization.setLocalization("player.on.fire", newVal))
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.localization.player.on.water"))
                                .binding(Localization.getLcnDefault("player.on.water"),
                                        () -> Localization.getLocalization("player.on.water", false),
                                        newVal -> Localization.setLocalization("player.on.water", newVal))
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.localization.player.statistics"))
                                .binding(Localization.getLcnDefault("player.statistics"),
                                        () -> Localization.getLocalization("player.statistics", false),
                                        newVal -> Localization.setLocalization("player.statistics", newVal))
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.localization.player.world.state"))
                                .binding(Localization.getLcnDefault("player.world.state"),
                                        () -> Localization.getLocalization("player.world.state", false),
                                        newVal -> Localization.setLocalization("player.world.state", newVal))
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).build());

        category.group(OptionGroup.createBuilder()
                .name(Localization.getText("simplystatus.config.localization.title.death"))
                .option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.localization.death.one"))
                                .binding(Localization.getLcnDefault("death.one"),
                                        () -> Localization.getLocalization("death.one", false),
                                        newVal -> Localization.setLocalization("death.one", newVal))
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.localization.death.two"))
                                .binding(Localization.getLcnDefault("death.two"),
                                        () -> Localization.getLocalization("death.two", false),
                                        newVal -> Localization.setLocalization("death.two", newVal))
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.localization.death.three"))
                                .binding(Localization.getLcnDefault("death.three"),
                                        () -> Localization.getLocalization("death.three", false),
                                        newVal -> Localization.setLocalization("death.three", newVal))
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).build());

        category.group(OptionGroup.createBuilder()
                .name(Localization.getText("simplystatus.config.localization.title.worlds"))
                .option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.localization.world.overworld"))
                                .binding(Localization.getLcnDefault("world.overworld"),
                                        () -> Localization.getLocalization("world.overworld", false),
                                        newVal -> Localization.setLocalization("world.overworld", newVal))
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.localization.world.nether"))
                                .binding(Localization.getLcnDefault("world.nether"),
                                        () -> Localization.getLocalization("world.nether", false),
                                        newVal -> Localization.setLocalization("world.nether", newVal))
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.localization.world.the_end"))
                                .binding(Localization.getLcnDefault("world.the_end"),
                                        () -> Localization.getLocalization("world.the_end", false),
                                        newVal -> Localization.setLocalization("world.the_end", newVal))
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.localization.world.moon"))
                                .binding(Localization.getLcnDefault("world.moon"),
                                        () -> Localization.getLocalization("world.moon", false),
                                        newVal -> Localization.setLocalization("world.moon", newVal))
                                .controller(StringControllerBuilderImpl::new)
                                .available(CLIENT.getGameVersion().equals("23w13a_or_b"))
                                .build()
                ).build());

        category.group(OptionGroup.createBuilder()
                .name(Localization.getText("simplystatus.config.localization.title.time"))
                .option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.localization.time.day"))
                                .binding(Localization.getLcnDefault("time.day"),
                                        () -> Localization.getLocalization("time.day", false),
                                        newVal -> Localization.setLocalization("time.day", newVal))
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.localization.time.night"))
                                .binding(Localization.getLcnDefault("time.night"),
                                        () -> Localization.getLocalization("time.night", false),
                                        newVal -> Localization.setLocalization("time.night", newVal))
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.localization.time.morning"))
                                .binding(Localization.getLcnDefault("time.morning"),
                                        () -> Localization.getLocalization("time.morning", false),
                                        newVal -> Localization.setLocalization("time.morning", newVal))
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.localization.time.evening"))
                                .binding(Localization.getLcnDefault("time.evening"),
                                        () -> Localization.getLocalization("time.evening", false),
                                        newVal -> Localization.setLocalization("time.evening", newVal))
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).build());

        category.group(OptionGroup.createBuilder()
                .name(Localization.getText("simplystatus.config.localization.title.unknown"))
                .option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.localization.unknown.world"))
                                .binding(Localization.getLcnDefault("unknown.world"),
                                        () -> Localization.getLocalization("unknown.world", false),
                                        newVal -> Localization.setLocalization("unknown.world", newVal))
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.localization.unknown.server"))
                                .binding(Localization.getLcnDefault("unknown.server"),
                                        () -> Localization.getLocalization("unknown.server", false),
                                        newVal -> Localization.setLocalization("unknown.server", newVal))
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).option(
                        Option.createBuilder(String.class)
                                .name(Localization.getText("simplystatus.config.localization.unknown"))
                                .binding(Localization.getLcnDefault("unknown"),
                                        () -> Localization.getLocalization("unknown", false),
                                        newVal -> Localization.setLocalization("unknown", newVal))
                                .controller(StringControllerBuilderImpl::new)
                                .build()
                ).build());


        return category.build();
    }
}
