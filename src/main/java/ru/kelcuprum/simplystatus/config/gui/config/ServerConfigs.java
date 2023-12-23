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

import java.util.ArrayList;
import java.util.List;

public class ServerConfigs extends Screen {
    private Component TITLE = Component.translatable("simplystatus.name");
    private final Screen parent;
    private int scrolled = 0;
    private List<AbstractWidget> widgets = new ArrayList<AbstractWidget>();
    // Components
    private TextBox titleBox;

    private ButtonBoolean showCustomName;
    private EditBoxString customName;
    private ButtonBoolean showName;
    private ButtonBoolean showAddress;
    private ButtonBoolean showIcon;
    private EditBoxString iconUrl;

    //
    public ServerConfigs(Screen parent){
        super(Component.translatable("simplystatus.config.server"));
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
        addRenderableWidget(titleBox);


        this.showCustomName = new ButtonBoolean(140, 40, x, 20, InterfaceUtils.DesignType.VANILLA, SimplyStatus.serverConfig, "USE_CUSTOM_NAME", false, Component.translatable("simplystatus.config.server.show_custom_name"));
        widgets.add(showCustomName);
        addRenderableWidget(showCustomName);
        //
        this.customName = new EditBoxString(140, 65, x, 20, SimplyStatus.serverConfig.getString("CUSTOM_NAME", ""), Component.translatable("simplystatus.config.server.custom_name"), s -> SimplyStatus.serverConfig.setString("CUSTOM_NAME", s));
        widgets.add(customName);
        addRenderableWidget(customName);
        //
        this.showName = new ButtonBoolean(140, 90, x, 20, InterfaceUtils.DesignType.VANILLA, SimplyStatus.serverConfig, "SHOW_NAME", true, Component.translatable("simplystatus.config.server.show_name"));
        widgets.add(showName);
        addRenderableWidget(showName);
        this.showAddress = new ButtonBoolean(140, 115, x, 20, InterfaceUtils.DesignType.VANILLA, SimplyStatus.serverConfig, "SHOW_ADDRESS", false, Component.translatable("simplystatus.config.server.show_address"));
        widgets.add(showAddress);
        addRenderableWidget(showAddress);
        this.showIcon = new ButtonBoolean(140, 140, x, 20, InterfaceUtils.DesignType.VANILLA, SimplyStatus.serverConfig, "SHOW_ICON", false, Component.translatable("simplystatus.config.server.show_icon"));
        widgets.add(showIcon);
        addRenderableWidget(showIcon);
        //
        this.iconUrl = new EditBoxString(140, 65, x, 20, SimplyStatus.serverConfig.getString("ICON_URL", "https://api.mcstatus.io/v2/icon/%address%"), Component.translatable("simplystatus.config.server.icon_url"), s -> SimplyStatus.serverConfig.setString("ICON_URL", s));
        widgets.add(iconUrl);
        addRenderableWidget(iconUrl);

        //
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
}
