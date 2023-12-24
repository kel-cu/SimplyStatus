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

public class AssetConfigs extends Screen {
    private Component TITLE = Component.translatable("simplystatus.name");
    private final Screen parent;
    private int scrolled = 0;
    private List<AbstractWidget> widgets = new ArrayList<AbstractWidget>();
    // Components
    private TextBox titleBox;
    ///
    private TextBox titleMenu;
    private EditBox logo;
    ///
    private TextBox titleTime;
    private EditBox morning;
    private EditBox day;
    private EditBox evening;
    private EditBox night;
    ///
    private TextBox titleWorld;
    private EditBox overworld;
    private EditBox nether;
    private EditBox end;
    private EditBox moon;
    ///
    private TextBox titleModification;
    private EditBox music;
    private EditBox replaymod;
    private EditBox voice;
    ///
    private TextBox titleUnknown;
    private EditBox unknown;
    private EditBox unknownWorld;

    //
    public AssetConfigs(Screen parent){
        super(Component.translatable("simplystatus.config.assets"));
        this.parent = parent;
    }

    @Override
    public void init(){
        SimplyStatus.ASSETS.loadUserAssets();
        this.scrolled = 0;
        initPanel();
        initCategory();

    }
    public void tick(){
        for(int i=0; i<widgets.size();i++){
            if(i==0) widgets.get(i).setY(15-scrolled);
            else widgets.get(i).setY(40 + (25*(i-1))-scrolled);
        }
    }
    private void initCategory(){
        int x = this.width - 150;
        widgets = new ArrayList<>();
        this.titleBox = this.addRenderableWidget(new TextBox(140, 15, x, 9, this.title, true));
        widgets.add(titleBox);
        //
        this.titleMenu = this.addRenderableWidget(new TextBox(140, 15, x, 20, Component.translatable("simplystatus.config.assets.title.menu"), false));
        widgets.add(titleMenu);
        ///
        this.logo = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, SimplyStatus.ASSETS.logo, InterfaceUtils.DesignType.ALINA,
                        Component.translatable("simplystatus.config.assets.logo"), newVal -> SimplyStatus.ASSETS.setValue("logo", newVal))
        );
        widgets.add(logo);
        //
        this.titleTime = this.addRenderableWidget(new TextBox(140, 15, x, 20, Component.translatable("simplystatus.config.assets.title.time"), false));
        widgets.add(titleTime);
        ///
        this.day = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, SimplyStatus.ASSETS.day, InterfaceUtils.DesignType.ALINA,
                        Component.translatable("simplystatus.config.assets.day"), newVal -> SimplyStatus.ASSETS.setValue("day", newVal))
        );
        widgets.add(day);
        ///
        this.night = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, SimplyStatus.ASSETS.night, InterfaceUtils.DesignType.ALINA,
                        Component.translatable("simplystatus.config.assets.night"), newVal -> SimplyStatus.ASSETS.setValue("night", newVal))
        );
        widgets.add(night);
        ///
        this.morning = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, SimplyStatus.ASSETS.morning, InterfaceUtils.DesignType.ALINA,
                        Component.translatable("simplystatus.config.assets.morning"), newVal -> SimplyStatus.ASSETS.setValue("morning", newVal))
        );
        widgets.add(morning);
        ///
        this.evening = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, SimplyStatus.ASSETS.evening, InterfaceUtils.DesignType.ALINA,
                        Component.translatable("simplystatus.config.assets.evening"), newVal -> SimplyStatus.ASSETS.setValue("evening", newVal))
        );
        widgets.add(evening);
        //
        this.titleWorld = this.addRenderableWidget(new TextBox(140, 15, x, 20, Component.translatable("simplystatus.config.assets.title.worlds"), false));
        widgets.add(titleWorld);
        ///
        this.overworld = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, SimplyStatus.ASSETS.world, InterfaceUtils.DesignType.ALINA,
                        Component.translatable("simplystatus.config.assets.world"), newVal -> SimplyStatus.ASSETS.setValue("world", newVal))
        );
        widgets.add(overworld);
        ///
        this.nether = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, SimplyStatus.ASSETS.world_nether, InterfaceUtils.DesignType.ALINA,
                        Component.translatable("simplystatus.config.assets.world_nether"), newVal -> SimplyStatus.ASSETS.setValue("world_nether", newVal))
        );
        widgets.add(nether);
        ///
        this.end = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, SimplyStatus.ASSETS.world_the_end, InterfaceUtils.DesignType.ALINA,
                        Component.translatable("simplystatus.config.assets.world_the_end"), newVal -> SimplyStatus.ASSETS.setValue("world_the_end", newVal))
        );
        widgets.add(end);
        ///
        this.moon = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, SimplyStatus.ASSETS.world_moon, InterfaceUtils.DesignType.ALINA,
                        Component.translatable("simplystatus.config.assets.world_moon"), newVal -> SimplyStatus.ASSETS.setValue("world_moon", newVal))
        );
        widgets.add(moon);
        //
        this.titleModification = this.addRenderableWidget(new TextBox(140, 15, x, 20, Component.translatable("simplystatus.config.assets.title.modification"), false));
        widgets.add(titleModification);
        ///
        this.voice = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, SimplyStatus.ASSETS.voice, InterfaceUtils.DesignType.ALINA,
                        Component.translatable("simplystatus.config.assets.voice"), newVal -> SimplyStatus.ASSETS.setValue("voice", newVal))
        );
        widgets.add(voice);
        ///
        this.replaymod = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, SimplyStatus.ASSETS.replaymod, InterfaceUtils.DesignType.ALINA,
                        Component.translatable("simplystatus.config.assets.replaymod"), newVal -> SimplyStatus.ASSETS.setValue("replaymod", newVal))
        );
        widgets.add(replaymod);
        ///
        this.music = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, SimplyStatus.ASSETS.music, InterfaceUtils.DesignType.ALINA,
                        Component.translatable("simplystatus.config.assets.music"), newVal -> SimplyStatus.ASSETS.setValue("musicmusic", newVal))
        );
        widgets.add(music);
        //
        this.titleUnknown = this.addRenderableWidget(new TextBox(140, 15, x, 20, Component.translatable("simplystatus.config.assets.title.unknown"), false));
        widgets.add(titleUnknown);
        ///
        this.unknownWorld = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, SimplyStatus.ASSETS.unknown_world, InterfaceUtils.DesignType.ALINA,
                        Component.translatable("simplystatus.config.assets.unknown_world"), newVal -> SimplyStatus.ASSETS.setValue("unknown_world", newVal))
        );
        widgets.add(unknownWorld);
        ///
        this.unknown = this.addRenderableWidget(
                new EditBoxString(140, 40, x, 20, SimplyStatus.ASSETS.unknown, InterfaceUtils.DesignType.ALINA,
                        Component.translatable("simplystatus.config.assets.unknown"), newVal -> SimplyStatus.ASSETS.setValue("unknown", newVal))
        );
        widgets.add(unknown);
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
        int size = 515;
        if (scrolled <= 0 || size <= this.height) {
            this.scrolled = 0;
        } else this.scrolled = Math.min(scrolled, size - this.height);

        return super.mouseScrolled(d, e, f, g);
    }
}
