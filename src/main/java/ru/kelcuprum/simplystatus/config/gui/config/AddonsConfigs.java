package ru.kelcuprum.simplystatus.config.gui.config;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.buttons.Button;
import ru.kelcuprum.alinlib.gui.components.buttons.ButtonBoolean;
import ru.kelcuprum.alinlib.gui.components.editbox.EditBoxString;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.config.ModConfig;

import java.util.ArrayList;
import java.util.List;

public class AddonsConfigs extends Screen {
    private Component TITLE = Component.translatable("simplystatus.name");
    private final Screen parent;
    private int scrolled = 0;
    private List<AbstractWidget> widgets = new ArrayList<AbstractWidget>();
    // Components
    private TextBox titleBox;
    //
    private TextBox titleMods;
    private ButtonBoolean enableReplay;
    private ButtonBoolean enableVoice;
    private ButtonBoolean enableMusic;

    //
    private TextBox titleIndicators;
    private ButtonBoolean showItems;
    private ButtonBoolean enableTimeCycle;
    private ButtonBoolean enableWorld;

    //
    private TextBox titleCustom;
    private ButtonBoolean useCustomAssets;
    private ButtonBoolean useCustomAppID;
    private EditBoxString customAppID;

    //
    public AddonsConfigs(Screen parent){
        super(Component.translatable("simplystatus.config.addons"));
        this.parent = parent;
    }

    @Override
    public void init(){
        this.scrolled = 0;
        initPanel();
        initCategory();

    }
    @Override
    public void tick() {
        for(int i=0; i<widgets.size();i++){
            if(i==0) widgets.get(i).setY(15-scrolled);
            else widgets.get(i).setY(40 + (25*(i-1))-scrolled);
        }
    }
    private void initCategory(){
        int x = this.width - 150;
        widgets = new ArrayList<>();
        this.titleBox = this.addRenderableWidget(new TextBox(140, 15, x, 9, this.title, true));
        widgets.add(this.titleBox);
        addRenderableWidget(titleBox);
        //
        this.titleMods = this.addRenderableWidget(new TextBox(140, 40, x, 20, Component.translatable("simplystatus.config.addons.modifications"), false));
        widgets.add(this.titleMods);
        addRenderableWidget(titleMods);
        this.enableReplay = new ButtonBoolean(140, 65, x, 20, InterfaceUtils.DesignType.VANILLA, SimplyStatus.userConfig, "VIEW_REPLAY_MOD", true, Component.translatable("simplystatus.config.addons.view_replay_mod"));
        widgets.add(this.enableReplay);
        addRenderableWidget(this.enableReplay).setActive(SimplyStatus.replayMod);
        this.enableVoice = new ButtonBoolean(140, 90, x, 20, InterfaceUtils.DesignType.VANILLA, SimplyStatus.userConfig, "VIEW_VOICE_SPEAK", true, Component.translatable("simplystatus.config.addons.view_voice_speak"));
        widgets.add(this.enableVoice);
        addRenderableWidget(this.enableVoice).setActive(SimplyStatus.isVoiceModsEnable);
        this.enableMusic = new ButtonBoolean(140, 115, x, 20, InterfaceUtils.DesignType.VANILLA, SimplyStatus.userConfig, "VIEW_MUSIC_LISTENER", true, Component.translatable("simplystatus.config.addons.view_music_listener"));
        widgets.add(this.enableMusic);
        addRenderableWidget(this.enableMusic).setActive(SimplyStatus.isMusicModsEnable);
        //
        this.titleIndicators = this.addRenderableWidget(new TextBox(140, 140, x, 20, Component.translatable("simplystatus.config.addons.indicators"), false));
        widgets.add(this.titleIndicators);
        addRenderableWidget(titleIndicators);
        this.showItems = new ButtonBoolean(140, 165, x, 20, InterfaceUtils.DesignType.VANILLA, SimplyStatus.userConfig, "SHOW_ITEMS", true, Component.translatable("simplystatus.config.addons.show_items"));
        widgets.add(this.showItems);
        addRenderableWidget(this.showItems);
        this.enableTimeCycle = new ButtonBoolean(140, 190, x, 20, InterfaceUtils.DesignType.VANILLA, SimplyStatus.userConfig, "ENABLE_TIME_CYCLE", true, Component.translatable("simplystatus.config.addons.enable_time_cycle"));
        widgets.add(this.enableTimeCycle);
        addRenderableWidget(this.enableTimeCycle);
        this.enableWorld = new ButtonBoolean(140, 215, x, 20, InterfaceUtils.DesignType.VANILLA, SimplyStatus.userConfig, "ENABLE_WORLD", true, Component.translatable("simplystatus.config.addons.enable_world"));
        widgets.add(this.enableWorld);
        addRenderableWidget(this.enableWorld);
        //
        this.titleCustom = this.addRenderableWidget(new TextBox(140, 240, x, 20, Component.translatable("simplystatus.config.addons.custom"), false));
        widgets.add(this.titleCustom);
        addRenderableWidget(titleCustom);
        this.useCustomAssets = new ButtonBoolean(140, 265, x, 20, InterfaceUtils.DesignType.VANILLA, SimplyStatus.userConfig, "USE_CUSTOM_ASSETS", false, Component.translatable("simplystatus.config.addons.use_custom_assets"));
        widgets.add(this.useCustomAssets);
        addRenderableWidget(this.useCustomAssets);
        this.useCustomAppID = new ButtonBoolean(140, 290, x, 20, InterfaceUtils.DesignType.VANILLA, SimplyStatus.userConfig, "USE_CUSTOM_APP_ID", false, Component.translatable("simplystatus.config.client.use_custom_app_id"));
        widgets.add(this.useCustomAppID);
        addRenderableWidget(this.useCustomAppID);
        this.customAppID = new EditBoxString(140, 315, x, 20, SimplyStatus.userConfig.getString("CUSTOM_APP_ID", ModConfig.baseID), InterfaceUtils.DesignType.ALINA, Component.translatable("simplystatus.config.addons.custom_app_id"), (s) -> SimplyStatus.userConfig.getString("CUSTOM_APP_ID", s));
        widgets.add(this.customAppID);
        addRenderableWidget(this.customAppID);

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
        int size = 340;
        if (scrolled <= 0 || size <= this.height) {
            this.scrolled = 0;
        } else this.scrolled = Math.min(scrolled, size - this.height);

        return super.mouseScrolled(d, e, f, g);
    }
}
