package ru.kelcuprum.simplystatus.config.gui.config;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.buttons.Button;
import ru.kelcuprum.alinlib.gui.components.editbox.EditBoxString;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.localization.Localization;

import java.util.ArrayList;
import java.util.List;

public class LocalizationConfigs extends Screen {
    private Component TITLE = Component.translatable("simplystatus.name");
    private final Screen parent;
    private int scrolled = 0;
    private List<AbstractWidget> widgets = new ArrayList<AbstractWidget>();
    // Components
    private TextBox titleBox;
    ///
    private TextBox titleMenu;
    private EditBox mainMenu;
    private EditBox mainMenuState;
    ///
    private TextBox titleSinglePlayer;
    private EditBox singleplayer;
    private EditBox loadingWorld;
    ///
    private TextBox titleMultiPlayer;
    private EditBox hiddenAddress;
    private EditBox connecting;
    private EditBox disconnected;
    ///
    private TextBox titlePlayer;
    private EditBox itemHold;
    private EditBox itemHoldCount;
    private EditBox sleep;
    private EditBox sneak;
    private EditBox burning;
    private EditBox submerged;
    private EditBox statistics;
    private EditBox worldState;
    ///
    private TextBox titleDead;
    private EditBox deathMsg1;
    private EditBox deathMsg2;
    private EditBox deathMsg3;
    ///
    private TextBox titleWorlds;
    private EditBox overworld;
    private EditBox nether;
    private EditBox end;
    private EditBox moon;
    ///
    private TextBox titleTime;
    private EditBox day;
    private EditBox night;
    private EditBox morning;
    private EditBox evening;
    ///
    private TextBox titleUnknown;
    private EditBox unknownWorld;
    private EditBox unknownServer;
    private EditBox what;

    //
    public LocalizationConfigs(Screen parent){
        super(Component.translatable("simplystatus.config.localization"));
        this.parent = parent;
    }

    public void tick() {
        for(int i=0; i<widgets.size();i++){
            if(i==0) widgets.get(i).setY(15-scrolled);
            else widgets.get(i).setY(40 + (25*(i-1))-scrolled);
        }
    }

    @Override
    public void init(){
        this.scrolled = 0;
        initPanel();
        initCategory();

    }
    private void initCategory(){
        int x = this.width - 150;
        widgets = new ArrayList<>();
        this.titleBox = this.addRenderableWidget(new TextBox(140, 15, x, 9, this.title, true));
        widgets.add(titleBox);
        //
        this.titleMenu = this.addRenderableWidget(new TextBox(140, 15, x, 20, Component.translatable("simplystatus.config.localization.title.menu"), false));
        widgets.add(titleMenu);
        this.mainMenu = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, Localization.getLocalization("mainmenu", false), InterfaceUtils.DesignType.ALINA, Component.translatable("simplystatus.config.localization.mainmenu"), newVal -> Localization.setLocalization("mainmenu", newVal))
        );
        this.mainMenu.setMaxLength(64);
        widgets.add(mainMenu);

        this.mainMenuState = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, Localization.getLocalization("mainmenu.state", false), InterfaceUtils.DesignType.ALINA, Component.translatable("simplystatus.config.localization.mainmenu.state"), newVal -> Localization.setLocalization("mainmenu.state", newVal))
        );
        this.mainMenuState.setMaxLength(64);
        widgets.add(mainMenuState);
        //
        this.titleSinglePlayer = this.addRenderableWidget(new TextBox(140, 15, x, 20, Component.translatable("simplystatus.config.localization.title.singleplayer"), false));
        widgets.add(titleSinglePlayer);

        this.singleplayer = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, Localization.getLocalization("singleplayer", false), InterfaceUtils.DesignType.ALINA, Component.translatable("simplystatus.config.localization.singleplayer"), newVal -> Localization.setLocalization("singleplayer", newVal))
        );
        this.singleplayer.setMaxLength(64);
        widgets.add(singleplayer);

        this.loadingWorld = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, Localization.getLocalization("world.loading", false), InterfaceUtils.DesignType.ALINA, Component.translatable("simplystatus.config.localization.world.loading"), newVal -> Localization.setLocalization("world.loading", newVal))
        );
        this.loadingWorld.setMaxLength(64);
        widgets.add(loadingWorld);
        //
        this.titleMultiPlayer = this.addRenderableWidget(new TextBox(140, 15, x, 20, Component.translatable("simplystatus.config.localization.title.multiplayer"), false));
        widgets.add(titleMultiPlayer);

        this.hiddenAddress = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, Localization.getLocalization("address.hidden", false), InterfaceUtils.DesignType.ALINA, Component.translatable("simplystatus.config.localization.address.hidden"), newVal -> Localization.setLocalization("address.hidden", newVal))
        );
        this.hiddenAddress.setMaxLength(64);
        widgets.add(hiddenAddress);

        this.connecting = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, Localization.getLocalization("server.connecting", false), InterfaceUtils.DesignType.ALINA, Component.translatable("simplystatus.config.localization.server.connecting"), newVal -> Localization.setLocalization("server.connecting", newVal))
        );
        this.connecting.setMaxLength(64);
        widgets.add(connecting);

        this.disconnected = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, Localization.getLocalization("server.disconnected", false), InterfaceUtils.DesignType.ALINA, Component.translatable("simplystatus.config.localization.server.disconnected"), newVal -> Localization.setLocalization("server.disconnected", newVal))
        );
        this.disconnected.setMaxLength(64);
        widgets.add(disconnected);
        //
        this.titlePlayer = this.addRenderableWidget(new TextBox(140, 15, x, 20, Component.translatable("simplystatus.config.localization.title.player"), false));
        widgets.add(titlePlayer);
        ///
        this.itemHold = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, Localization.getLocalization("item", false), InterfaceUtils.DesignType.ALINA, Component.translatable("simplystatus.config.localization.item"), newVal -> Localization.setLocalization("item", newVal))
        );
        this.itemHold.setMaxLength(64);
        widgets.add(itemHold);
        ///
        this.itemHoldCount = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, Localization.getLocalization("item.count", false), InterfaceUtils.DesignType.ALINA, Component.translatable("simplystatus.config.localization.item.count"), newVal -> Localization.setLocalization("item.count", newVal))
        );
        this.itemHoldCount.setMaxLength(64);
        widgets.add(itemHoldCount);
        ///
        this.sleep = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, Localization.getLocalization("player.sleep", false), InterfaceUtils.DesignType.ALINA, Component.translatable("simplystatus.config.localization.player.sleep"), newVal -> Localization.setLocalization("player.sleep", newVal))
        );
        this.sleep.setMaxLength(64);
        widgets.add(sleep);
        ///
        this.sneak = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, Localization.getLocalization("player.sneak", false), InterfaceUtils.DesignType.ALINA, Component.translatable("simplystatus.config.localization.player.sneak"), newVal -> Localization.setLocalization("player.sneak", newVal))
        );
        this.sneak.setMaxLength(64);
        widgets.add(sneak);
        ///
        this.burning = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, Localization.getLocalization("player.on.fire", false), InterfaceUtils.DesignType.ALINA, Component.translatable("simplystatus.config.localization.player.on.fire"), newVal -> Localization.setLocalization("player.on.fire", newVal))
        );
        this.burning.setMaxLength(64);
        widgets.add(burning);
        ///
        this.submerged = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, Localization.getLocalization("player.on.water", false), InterfaceUtils.DesignType.ALINA, Component.translatable("simplystatus.config.localization.player.on.water"), newVal -> Localization.setLocalization("player.on.water", newVal))
        );
        this.submerged.setMaxLength(64);
        widgets.add(submerged);
        ///
        this.statistics = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, Localization.getLocalization("player.statistics", false), InterfaceUtils.DesignType.ALINA, Component.translatable("simplystatus.config.localization.player.statistics"), newVal -> Localization.setLocalization("player.statistics", newVal))
        );
        this.statistics.setMaxLength(64);
        widgets.add(statistics);
        ///
        this.worldState = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, Localization.getLocalization("player.world.state", false), InterfaceUtils.DesignType.ALINA, Component.translatable("simplystatus.config.localization.player.world.state"), newVal -> Localization.setLocalization("player.world.state", newVal))
        );
        this.worldState.setMaxLength(64);
        widgets.add(worldState);
        //
        this.titleDead = this.addRenderableWidget(new TextBox(140, 15, x, 20, Component.translatable("simplystatus.config.localization.title.death"), false));
        widgets.add(titleDead);
        ///
        this.deathMsg1 = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, Localization.getLocalization("death.one", false), InterfaceUtils.DesignType.ALINA, Component.translatable("simplystatus.config.localization.death.one"), newVal -> Localization.setLocalization("death.one", newVal))
        );
        this.deathMsg1.setMaxLength(64);
        widgets.add(deathMsg1);
        ///
        this.deathMsg2 = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, Localization.getLocalization("death.two", false), InterfaceUtils.DesignType.ALINA, Component.translatable("simplystatus.config.localization.death.two"), newVal -> Localization.setLocalization("death.two", newVal))
        );
        this.deathMsg2.setMaxLength(64);
        widgets.add(deathMsg2);
        ///
        this.deathMsg3 = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, Localization.getLocalization("death.three", false), InterfaceUtils.DesignType.ALINA, Component.translatable("simplystatus.config.localization.death.three"), newVal -> Localization.setLocalization("death.three", newVal))
        );
        this.deathMsg3.setMaxLength(64);
        widgets.add(deathMsg3);
        //
        this.titleWorlds = this.addRenderableWidget(new TextBox(140, 15, x, 20, Component.translatable("simplystatus.config.localization.title.worlds"), false));
        widgets.add(titleWorlds);
        ///
        this.overworld = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, Localization.getLocalization("world.overworld", false), InterfaceUtils.DesignType.ALINA, Component.translatable("simplystatus.config.localization.world.overworld"), newVal -> Localization.setLocalization("world.overworld", newVal))
        );
        this.overworld.setMaxLength(64);
        widgets.add(overworld);
        ///
        this.nether = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, Localization.getLocalization("world.nether", false), InterfaceUtils.DesignType.ALINA, Component.translatable("simplystatus.config.localization.world.nether"), newVal -> Localization.setLocalization("world.nether", newVal))
        );
        this.nether.setMaxLength(64);
        widgets.add(nether);
        ///
        this.end = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, Localization.getLocalization("world.the_end", false), InterfaceUtils.DesignType.ALINA, Component.translatable("simplystatus.config.localization.world.the_end"), newVal -> Localization.setLocalization("world.the_end", newVal))
        );
        this.end.setMaxLength(64);
        widgets.add(end);
        ///
        this.moon = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, Localization.getLocalization("world.moon", false), InterfaceUtils.DesignType.ALINA, Component.translatable("simplystatus.config.localization.world.moon"), newVal -> Localization.setLocalization("world.moon", newVal))
        );
        this.moon.active = this.minecraft.getLaunchedVersion().equals("23w13a_or_b");
        this.moon.setMaxLength(64);
        widgets.add(moon);
        //
        this.titleTime = this.addRenderableWidget(new TextBox(140, 15, x, 20, Component.translatable("simplystatus.config.localization.title.time"), false));
        widgets.add(titleTime);
        ///
        this.day = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, Localization.getLocalization("time.day", false), InterfaceUtils.DesignType.ALINA, Component.translatable("simplystatus.config.localization.time.day"), newVal -> Localization.setLocalization("time.day", newVal))
        );
        this.day.setMaxLength(64);
        widgets.add(day);
        ///
        this.night = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, Localization.getLocalization("time.night", false), InterfaceUtils.DesignType.ALINA, Component.translatable("simplystatus.config.localization.time.night"), newVal -> Localization.setLocalization("time.night", newVal))
        );
        this.night.setMaxLength(64);
        widgets.add(night);
        ///
        this.morning = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, Localization.getLocalization("time.morning", false), InterfaceUtils.DesignType.ALINA, Component.translatable("simplystatus.config.localization.time.morning"), newVal -> Localization.setLocalization("time.morning", newVal))
        );
        this.morning.setMaxLength(64);
        widgets.add(morning);
        ///
        this.evening = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, Localization.getLocalization("time.evening", false), InterfaceUtils.DesignType.ALINA, Component.translatable("simplystatus.config.localization.time.evening"), newVal -> Localization.setLocalization("time.evening", newVal))
        );
        this.evening.setMaxLength(64);
        widgets.add(evening);
        //
        this.titleUnknown = this.addRenderableWidget(new TextBox(140, 15, x, 20, Component.translatable("simplystatus.config.localization.title.unknown"), false));
        widgets.add(titleUnknown);
        ///
        this.unknownServer = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, Localization.getLocalization("unknown.server", false), InterfaceUtils.DesignType.ALINA, Component.translatable("simplystatus.config.localization.unknown.server"), newVal -> Localization.setLocalization("unknown.server", newVal))
        );
        this.unknownServer.setMaxLength(64);
        widgets.add(unknownServer);
        ///
        this.unknownWorld = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, Localization.getLocalization("unknown.world", false), InterfaceUtils.DesignType.ALINA, Component.translatable("simplystatus.config.localization.unknown.world"), newVal -> Localization.setLocalization("unknown.world", newVal))
        );
        this.unknownWorld.setMaxLength(64);
        widgets.add(unknownWorld);
        ///
        this.what = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, Localization.getLocalization("unknown", false), InterfaceUtils.DesignType.ALINA, Component.translatable("simplystatus.config.localization.unknown"), newVal -> Localization.setLocalization("unknown", newVal))
        );
        this.what.setMaxLength(64);
        widgets.add(what);
        //

//        this.addRenderableWidget(new TextBox(140, height/2-10, x, 20, Component.literal("Coming soon..."), true));

    }
    public void initPanel(){
        addRenderableWidget(new TextBox(10, 15, 110, font.lineHeight, TITLE, true));

        addRenderableWidget(new Button(10, 40, 110, 20, InterfaceUtils.DesignType.VANILLA, Component.translatable("simplystatus.config.client"), (OnPress) -> {
            this.minecraft.setScreen(new ClientConfigs(this.parent));
        }));

        addRenderableWidget(new Button(10, 65, 110, 20, InterfaceUtils.DesignType.VANILLA, Component.translatable("simplystatus.config.server"), (OnPress) -> {
            this.minecraft.setScreen(new ServerConfigs(this.parent));
        })).setActive(this.minecraft.getCurrentServer() != null);

        addRenderableWidget(new Button(10, 90, 110, 20, InterfaceUtils.DesignType.VANILLA, Component.translatable("simplystatus.config.localization"), (OnPress) -> {
            this.minecraft.setScreen(new LocalizationConfigs(this.parent));
        }));

        addRenderableWidget(new Button(10, 115, 110, 20, InterfaceUtils.DesignType.VANILLA, Component.translatable("simplystatus.config.addons"), (OnPress) -> {
            this.minecraft.setScreen(new AddonsConfigs(this.parent));
        }));

        addRenderableWidget(new Button(10, 140, 110, 20, InterfaceUtils.DesignType.VANILLA, Component.translatable("simplystatus.config.assets"), (OnPress) -> {
            this.minecraft.setScreen(new AssetConfigs(this.parent));
        })).setActive(SimplyStatus.userConfig.getBoolean("USE_CUSTOM_ASSETS", false) || SimplyStatus.userConfig.getBoolean("USE_CUSTOM_APP_ID", false));

        addRenderableWidget(new Button(10, 165, 110, 20, InterfaceUtils.DesignType.VANILLA, Component.translatable("simplystatus.config.mods"), (OnPress) -> {
            this.minecraft.setScreen(new ModsConfigs(this.parent));
        }));

        addRenderableWidget(new Button(10, height-30, 110, 20, InterfaceUtils.DesignType.VANILLA, Component.translatable("simplystatus.config.exit"), (OnPress) -> {
            this.minecraft.setScreen(parent);
        }));
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int i, int j, float f) {
        InterfaceUtils.renderBackground(guiGraphics, this.minecraft);
        InterfaceUtils.renderTextureLeftPanel(guiGraphics, 130, this.height);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
        super.render(guiGraphics, i, j, f);
        guiGraphics.drawCenteredString(this.minecraft.font, TITLE, 65, 15, -1);
    }
    @Override
    public boolean mouseScrolled(double d, double e, double f, double g) {
        int scrolled = (int)((double)this.scrolled + g * 10.0 * -1.0);
        int size = 965;
        if (scrolled <= 0 || size <= this.height) {
            this.scrolled = 0;
        } else this.scrolled = Math.min(scrolled, size - this.height);

        return super.mouseScrolled(d, e, f, g);
    }
}
