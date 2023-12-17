package ru.kelcuprum.simplystatus.config.gui.config;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.buttons.vanilla.VanillaButton;
import ru.kelcuprum.alinlib.gui.components.buttons.vanilla.VanillaButtonBoolean;
import ru.kelcuprum.alinlib.gui.components.editbox.EditBoxString;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.config.ModConfig;
import ru.kelcuprum.simplystatus.localization.Localization;

public class AddonsConfigs extends Screen {
    private Component TITLE = Component.translatable("simplystatus.name");
    private final Screen parent;
    private int scrolled = 0;
    // Components
    private TextBox titleBox;
    //
    private TextBox titleMods;
    private VanillaButtonBoolean enableReplay;
    private VanillaButtonBoolean enableVoice;
    private VanillaButtonBoolean enableMusic;

    //
    private TextBox titleIndicators;
    private VanillaButtonBoolean showItems;
    private VanillaButtonBoolean enableTimeCycle;
    private VanillaButtonBoolean enableWorld;

    //
    private TextBox titleCustom;
    private VanillaButtonBoolean useCustomAssets;
    private VanillaButtonBoolean useCustomAppID;
    private EditBoxString customAppID;

    //
    public AddonsConfigs(Screen parent){
        super(Component.translatable("simplystatus.config.addons"));
        this.parent = parent;
    }

    @Override
    public void init(){
        initPanel();
        initCategory();

    }
    @Override
    public void tick() {
        this.titleBox.setYPos(15 - scrolled);
        //
        this.titleMods.setYPos(40 - scrolled);
        this.enableReplay.setYPos(65 - scrolled);
        this.enableVoice.setYPos(90 - scrolled);
        this.enableMusic.setYPos(115 - scrolled);
        //
        this.titleIndicators.setYPos(140 - scrolled);
        this.showItems.setYPos(165 - scrolled);
        this.enableTimeCycle.setYPos(190 - scrolled);
        this.enableWorld.setYPos(215 - scrolled);
        //
        this.titleCustom.setYPos(240 - scrolled);
        this.useCustomAssets.setYPos(265 - scrolled);
        this.useCustomAppID.setYPos(290 - scrolled);
        this.customAppID.setYPos(315 - scrolled);
    }
    private void initCategory(){
        int x = this.width - 150;
        this.titleBox = this.addRenderableWidget(new TextBox(140, 15, x, 9, this.title, true));
        addRenderableWidget(titleBox);
        //
        this.titleMods = this.addRenderableWidget(new TextBox(140, 40, x, 20, Component.translatable("simplystatus.config.addons.modifications"), false));
        addRenderableWidget(titleMods);
        this.enableReplay = new VanillaButtonBoolean(140, 65, x, 20, SimplyStatus.userConfig, "VIEW_REPLAY_MOD", true, Component.translatable("simplystatus.config.addons.view_replay_mod"));
        addRenderableWidget(this.enableReplay).setActive(SimplyStatus.replayMod);
        this.enableVoice = new VanillaButtonBoolean(140, 90, x, 20, SimplyStatus.userConfig, "VIEW_VOICE_SPEAK", true, Component.translatable("simplystatus.config.addons.view_voice_speak"));
        addRenderableWidget(this.enableVoice).setActive(SimplyStatus.isVoiceModsEnable);
        this.enableMusic = new VanillaButtonBoolean(140, 115, x, 20, SimplyStatus.userConfig, "VIEW_MUSIC_LISTENER", true, Component.translatable("simplystatus.config.addons.view_music_listener"));
        addRenderableWidget(this.enableMusic).setActive(SimplyStatus.isMusicModsEnable);
        //
        this.titleIndicators = this.addRenderableWidget(new TextBox(240, 140, x, 20, Component.translatable("simplystatus.config.addons.indicators"), false));
        addRenderableWidget(titleIndicators);
        this.showItems = new VanillaButtonBoolean(140, 165, x, 20, SimplyStatus.userConfig, "SHOW_ITEMS", true, Component.translatable("simplystatus.config.addons.show_items"));
        addRenderableWidget(this.showItems);
        this.enableTimeCycle = new VanillaButtonBoolean(140, 190, x, 20, SimplyStatus.userConfig, "ENABLE_TIME_CYCLE", true, Component.translatable("simplystatus.config.addons.enable_time_cycle"));
        addRenderableWidget(this.enableTimeCycle);
        this.enableWorld = new VanillaButtonBoolean(140, 215, x, 20, SimplyStatus.userConfig, "ENABLE_WORLD", true, Component.translatable("simplystatus.config.addons.enable_world"));
        addRenderableWidget(this.enableWorld);
        //
        this.titleCustom = this.addRenderableWidget(new TextBox(140, 240, x, 20, Component.translatable("simplystatus.config.addons.custom"), false));
        addRenderableWidget(titleCustom);
        this.useCustomAssets = new VanillaButtonBoolean(140, 265, x, 20, SimplyStatus.userConfig, "USE_CUSTOM_ASSETS", false, Component.translatable("simplystatus.config.addons.use_custom_assets"));
        addRenderableWidget(this.useCustomAssets);
        this.useCustomAppID = new VanillaButtonBoolean(140, 290, x, 20, SimplyStatus.userConfig, "USE_CUSTOM_APP_ID", false, Component.translatable("simplystatus.config.client.use_custom_app_id"));
        addRenderableWidget(this.useCustomAppID);
        this.customAppID = new EditBoxString(140, 315, x, 20, Component.translatable("simplystatus.config.addons.custom_app_id"));
        this.customAppID.setContent(SimplyStatus.userConfig.getString("CUSTOM_APP_ID", ModConfig.baseID));
        this.customAppID.setResponse(s -> SimplyStatus.userConfig.getString("CUSTOM_APP_ID", s));
        addRenderableWidget(this.customAppID);

    }
    public void initPanel(){
        addRenderableWidget(new TextBox(10, 15, 110, font.lineHeight, TITLE, true));

        addRenderableWidget(new VanillaButton(10, 40, 110, 20, Component.translatable("simplystatus.config.client"), (OnPress) -> {
            this.minecraft.setScreen(new ClientConfigs(this.parent));
        }));

        addRenderableWidget(new VanillaButton(10, 65, 110, 20, Component.translatable("simplystatus.config.server"), (OnPress) -> {
            this.minecraft.setScreen(new ServerConfigs(this.parent));
        })).setActive(this.minecraft.getCurrentServer() != null);

        addRenderableWidget(new VanillaButton(10, 90, 110, 20, Component.translatable("simplystatus.config.localization"), (OnPress) -> {
            this.minecraft.setScreen(new LocalizationConfigs(this.parent));
        }));

        addRenderableWidget(new VanillaButton(10, 115, 110, 20, Component.translatable("simplystatus.config.addons"), (OnPress) -> {
            this.minecraft.setScreen(new AddonsConfigs(this.parent));
        }));

        addRenderableWidget(new VanillaButton(10, 140, 110, 20, Component.translatable("simplystatus.config.assets"), (OnPress) -> {
            this.minecraft.setScreen(new AssetConfigs(this.parent));
        })).setActive(SimplyStatus.userConfig.getBoolean("USE_CUSTOM_ASSETS", false) || SimplyStatus.userConfig.getBoolean("USE_CUSTOM_APP_ID", false));

        addRenderableWidget(new VanillaButton(10, 165, 110, 20, Component.translatable("simplystatus.config.mods"), (OnPress) -> {
            this.minecraft.setScreen(new ModsConfigs(this.parent));
        }));

        addRenderableWidget(new VanillaButton(10, height-30, 110, 20, Component.translatable("simplystatus.config.exit"), (OnPress) -> {
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
