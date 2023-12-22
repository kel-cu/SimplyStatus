package ru.kelcuprum.simplystatus.config.gui.config;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.buttons.Button;
import ru.kelcuprum.alinlib.gui.components.buttons.ButtonBoolean;
import ru.kelcuprum.alinlib.gui.components.editbox.EditBoxString;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.localization.Localization;

import java.util.Arrays;

public class ModsConfigs extends Screen {
    private Component TITLE = Component.translatable("simplystatus.name");
    private final Screen parent;
    private int scrolled = 0;
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
        initPanel();
        initCategory();

    }
    @Override
    public void tick(){
        this.titleBox.setY(15-scrolled);
        this.titleReplay.setY(40-scrolled);
        this.viewReplayModServerName.setY(65-scrolled);
        this.replay.setY(90-scrolled);
        this.replayState.setY(115-scrolled);
        //
        this.titleVoice.setY(140-scrolled);
        this.voice.setY(165-scrolled);
        this.voiceOne.setY(190-scrolled);
        this.voiceMore.setY(215-scrolled);
        //
        this.titleMusic.setY(240-scrolled);
        this.music.setY(265-scrolled);
        this.musicMenu.setY(290-scrolled);
        this.musicNoAuthor.setY(315-scrolled);
        this.musicMenuNoAuthor.setY(340-scrolled);
    }
    private void initCategory(){
        int x = this.width - 150;
        this.titleBox = this.addRenderableWidget(new TextBox(140, 15, x, 9, this.title, true));
        addRenderableWidget(titleBox);
        //
        this.titleReplay = this.addRenderableWidget(new TextBox(140, 40, x, 20, Component.translatable("simplystatus.config.replaymod"), true));
        addRenderableWidget(titleReplay);
        this.viewReplayModServerName = new ButtonBoolean(140, 65, x, 20, SimplyStatus.userConfig, "VIEW_REPLAY_MOD_SERVER_NAME", true, Component.translatable("simplystatus.config.server.show_name"));
        addRenderableWidget(viewReplayModServerName);
        this.replay = new EditBoxString(140, 90, x, 20, Component.translatable("simplystatus.config.localization.mod.replaymod"));
        this.replay.setValue(Localization.getLocalization("mod.replaymod", false));
        this.replay.setResponder(s -> Localization.setLocalization("mod.replaymod", s));
        addRenderableWidget(replay);
        this.replayState = new EditBoxString(140, 115, x, 20, Component.translatable("simplystatus.config.localization.mod.replaymod.state"));
        this.replayState.setValue(Localization.getLocalization("mod.replaymod.state", false));
        this.replayState.setResponder(s -> Localization.setLocalization("mod.replaymod.state", s));
        addRenderableWidget(replayState);
        //
        this.titleVoice = this.addRenderableWidget(new TextBox(140, 140, x, 20, Component.translatable("simplystatus.config.voice"), true));
        addRenderableWidget(titleVoice);
        this.voice = new EditBoxString(140, 165, x, 20, Component.translatable("simplystatus.config.localization.mod.voice"));
        this.voice.setValue(Localization.getLocalization("mod.voice", false));
        this.voice.setResponder(s -> Localization.setLocalization("mod.voice", s));
        addRenderableWidget(voice);
        this.voiceOne = new EditBoxString(140, 190, x, 20, Component.translatable("simplystatus.config.localization.mod.voice.players.one"));
        this.voiceOne.setValue(Localization.getLocalization("mod.voice.players.one", false));
        this.voiceOne.setResponder(s -> Localization.setLocalization("mod.voice.players.one", s));
        addRenderableWidget(voiceOne);
        this.voiceMore = new EditBoxString(140, 215, x, 20, Component.translatable("simplystatus.config.localization.mod.voice.players.more"));
        this.voiceMore.setValue(Localization.getLocalization("mod.voice.players.more", false));
        this.voiceMore.setResponder(s -> Localization.setLocalization("mod.voice.players.more", s));
        addRenderableWidget(voiceMore);
        //
        this.titleMusic = this.addRenderableWidget(new TextBox(140, 240, x, 20, Component.translatable("simplystatus.config.music"), false));
        addRenderableWidget(titleVoice);
        this.music = new EditBoxString(140, 265, x, 20, Component.translatable("simplystatus.config.localization.mod.music"));
        this.music.setValue(Localization.getLocalization("mod.music", false));
        this.music.setResponder(s -> Localization.setLocalization("mod.music", s));
        addRenderableWidget(music);
        this.musicMenu = new EditBoxString(140, 290, x, 20, Component.translatable("simplystatus.config.localization.mod.music.menu"));
        this.musicMenu.setValue(Localization.getLocalization("mod.music.menu", false));
        this.musicMenu.setResponder(s -> Localization.setLocalization("mod.music.menu", s));
        addRenderableWidget(musicMenu);
        this.musicNoAuthor = new EditBoxString(140, 315, x, 20, Component.translatable("simplystatus.config.localization.mod.music.noauthor"));
        this.musicNoAuthor.setValue(Localization.getLocalization("mod.music.noauthor", false));
        this.musicNoAuthor.setResponder(s -> Localization.setLocalization("mod.music.noauthor", s));
        addRenderableWidget(musicNoAuthor);
        this.musicMenuNoAuthor = new EditBoxString(140, 340, x, 20, Component.translatable("simplystatus.config.localization.mod.music.menu.noauthor"));
        this.musicMenuNoAuthor.setValue(Localization.getLocalization("mod.music.menu.noauthor", false));
        this.musicMenuNoAuthor.setResponder(s -> Localization.setLocalization("mod.music.menu.noauthor", s));
        addRenderableWidget(musicMenuNoAuthor);

    }
    public void initPanel(){
        addRenderableWidget(new TextBox(10, 15, 110, font.lineHeight, TITLE, true));

        addRenderableWidget(new Button(10, 40, 110, 20, Component.translatable("simplystatus.config.client"), (OnPress) -> {
            this.minecraft.setScreen(new ClientConfigs(this.parent));
        }));

        addRenderableWidget(new Button(10, 65, 110, 20, Component.translatable("simplystatus.config.server"), (OnPress) -> {
            this.minecraft.setScreen(new ServerConfigs(this.parent));
        })).setActive(this.minecraft.getCurrentServer() != null);

        addRenderableWidget(new Button(10, 90, 110, 20, Component.translatable("simplystatus.config.localization"), (OnPress) -> {
            this.minecraft.setScreen(new LocalizationConfigs(this.parent));
        }));

        addRenderableWidget(new Button(10, 115, 110, 20, Component.translatable("simplystatus.config.addons"), (OnPress) -> {
            this.minecraft.setScreen(new AddonsConfigs(this.parent));
        }));

        addRenderableWidget(new Button(10, 140, 110, 20, Component.translatable("simplystatus.config.assets"), (OnPress) -> {
            this.minecraft.setScreen(new AssetConfigs(this.parent));
        })).setActive(SimplyStatus.userConfig.getBoolean("USE_CUSTOM_ASSETS", false) || SimplyStatus.userConfig.getBoolean("USE_CUSTOM_APP_ID", false));

        addRenderableWidget(new Button(10, 165, 110, 20, Component.translatable("simplystatus.config.mods"), (OnPress) -> {
            this.minecraft.setScreen(new ModsConfigs(this.parent));
        }));

        addRenderableWidget(new Button(10, height-30, 110, 20, Component.translatable("simplystatus.config.exit"), (OnPress) -> {
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
