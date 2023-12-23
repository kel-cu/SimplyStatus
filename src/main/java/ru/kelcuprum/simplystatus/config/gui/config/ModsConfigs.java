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
import ru.kelcuprum.simplystatus.localization.Localization;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ModsConfigs extends Screen {
    private Component TITLE = Component.translatable("simplystatus.name");
    private final Screen parent;
    private int scrolled = 0;
    private List<AbstractWidget> widgets = new ArrayList<AbstractWidget>();
    // Components
    private TextBox titleBox;
    //
    private TextBox titleMusic;
    private EditBoxString musicMenu;
    private EditBoxString music;
    private EditBoxString musicMenuNoAuthor;
    private EditBoxString musicNoAuthor;
    //
    private TextBox titleReplay;
    private ButtonBoolean viewReplayModServerName;
    private EditBoxString replay;
    private EditBoxString replayState;
    //
    private TextBox titleVoice;
    private EditBoxString voice;
    private EditBoxString voiceOne;
    private EditBoxString voiceMore;

    //
    public ModsConfigs(Screen parent){
        super(Component.translatable("simplystatus.config.mods"));
        this.parent = parent;
    }

    @Override
    public void init(){
        this.scrolled = 0;
        initPanel();
        initCategory();

    }
    @Override
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
        addRenderableWidget(titleBox);
        //
        this.titleReplay = this.addRenderableWidget(new TextBox(140, 40, x, 20, Component.translatable("simplystatus.config.replaymod"), false));
        widgets.add(titleReplay);
        addRenderableWidget(titleReplay);
        this.viewReplayModServerName = new ButtonBoolean(140, 65, x, 20, InterfaceUtils.DesignType.VANILLA, SimplyStatus.userConfig, "VIEW_REPLAY_MOD_SERVER_NAME", true, Component.translatable("simplystatus.config.server.show_name"));
        widgets.add(viewReplayModServerName);
        addRenderableWidget(viewReplayModServerName);
        this.replay = new EditBoxString(140, 90, x, 20, Localization.getLocalization("mod.replaymod", false), Component.translatable("simplystatus.config.localization.mod.replaymod"), s -> Localization.setLocalization("mod.replaymod", s));
        widgets.add(replay);
        addRenderableWidget(replay);
        this.replayState = new EditBoxString(140, 115, x, 20, Localization.getLocalization("mod.replaymod.state", false), Component.translatable("simplystatus.config.localization.mod.replaymod.state"), s -> Localization.setLocalization("mod.replaymod.state", s));
        widgets.add(replayState);
        addRenderableWidget(replayState);
        //
        this.titleVoice = this.addRenderableWidget(new TextBox(140, 140, x, 20, Component.translatable("simplystatus.config.voice"), false));
        widgets.add(titleVoice);
        addRenderableWidget(titleVoice);
        this.voice = new EditBoxString(140, 165, x, 20, Localization.getLocalization("mod.voice", false), Component.translatable("simplystatus.config.localization.mod.voice"), s -> Localization.setLocalization("mod.voice", s));
        widgets.add(voice);
        addRenderableWidget(voice);
        this.voiceOne = new EditBoxString(140, 190, x, 20, Localization.getLocalization("mod.voice.players.one", false), Component.translatable("simplystatus.config.localization.mod.voice.players.one"), s -> Localization.setLocalization("mod.voice.players.one", s));
        widgets.add(voiceOne);
        addRenderableWidget(voiceOne);
        this.voiceMore = new EditBoxString(140, 215, x, 20, Localization.getLocalization("mod.voice.players.more", false), Component.translatable("simplystatus.config.localization.mod.voice.players.more"), s -> Localization.setLocalization("mod.voice.players.more", s));
        widgets.add(voiceMore);
        addRenderableWidget(voiceMore);
        //
        this.titleMusic = this.addRenderableWidget(new TextBox(140, 240, x, 20, Component.translatable("simplystatus.config.music"), false));
        widgets.add(titleMusic);
        addRenderableWidget(titleMusic);
        this.music = new EditBoxString(140, 265, x, 20, Localization.getLocalization("mod.music", false), Component.translatable("simplystatus.config.localization.mod.music"), s -> Localization.setLocalization("mod.music", s));
        widgets.add(music);
        addRenderableWidget(music);
        this.musicMenu = new EditBoxString(140, 290, x, 20, Localization.getLocalization("mod.music.menu", false), Component.translatable("simplystatus.config.localization.mod.music.menu"), s -> Localization.setLocalization("mod.music.menu", s));
        widgets.add(musicMenu);
        addRenderableWidget(musicMenu);
        this.musicNoAuthor = new EditBoxString(140, 315, x, 20, Localization.getLocalization("mod.music.noauthor", false), Component.translatable("simplystatus.config.localization.mod.music.noauthor"), s -> Localization.setLocalization("mod.music.noauthor", s));
        widgets.add(musicNoAuthor);
        addRenderableWidget(musicNoAuthor);
        this.musicMenuNoAuthor = new EditBoxString(140, 340, x, 20, Localization.getLocalization("mod.music.menu.noauthor", false), Component.translatable("simplystatus.config.localization.mod.music.menu.noauthor"), s -> Localization.setLocalization("mod.music.menu.noauthor", s));
        widgets.add(musicMenuNoAuthor);
        addRenderableWidget(musicMenuNoAuthor);

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
        int size = 365;
        if (scrolled <= 0 || size <= this.height) {
            this.scrolled = 0;
        } else this.scrolled = Math.min(scrolled, size - this.height);

        return super.mouseScrolled(d, e, f, g);
    }
}
