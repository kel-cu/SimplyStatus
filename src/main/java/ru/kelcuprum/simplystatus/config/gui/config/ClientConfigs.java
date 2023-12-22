package ru.kelcuprum.simplystatus.config.gui.config;

import com.jagrosh.discordipc.IPCClient;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.buttons.Button;
import ru.kelcuprum.alinlib.gui.components.buttons.ButtonBoolean;
import ru.kelcuprum.alinlib.gui.components.selector.SelectorStringButton;
import ru.kelcuprum.alinlib.gui.components.selector.SelectorStringWithIntButton;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.config.ModConfig;
import ru.kelcuprum.simplystatus.config.gui.YACLConfigScreen;

public class ClientConfigs extends Screen {
    private Component TITLE = Component.translatable("simplystatus.name");
    private final Screen parent;
    private int scrolled = 0;
    // Components
    private TextBox titleBox;
    //
    private ButtonBoolean useMinecraftID;
    private SelectorStringButton assets;
    private SelectorStringWithIntButton api;
    private ButtonBoolean showGameStarted;
    private ButtonBoolean showAvatarPlayer;
    private ButtonBoolean viewItemOffHand;
    private ButtonBoolean viewStatistics;
    private ButtonBoolean viewPlayerName;

    //
    public ClientConfigs(Screen parent){
        super(Component.translatable("simplystatus.config.client"));
        this.parent = parent;
    }

    @Override
    public void tick(){
        if(SimplyStatus.userConfig.getBoolean("USE_CUSTOM_APP_ID", false) && !SimplyStatus.customID.equals(SimplyStatus.userConfig.getString("CUSTOM_APP_ID", ModConfig.baseID))){
            SimplyStatus.useCustomID = true;
            String APPLICATION_ID = SimplyStatus.userConfig.getString("CUSTOM_APP_ID", ModConfig.baseID);
            if(APPLICATION_ID.isBlank()){
                APPLICATION_ID = ModConfig.baseID;
                SimplyStatus.userConfig.setString("CUSTOM_APP_ID", APPLICATION_ID);
            }
            if(!SimplyStatus.customID.equals(APPLICATION_ID)) {
                SimplyStatus.customID = APPLICATION_ID;
                SimplyStatus.client.close();
                SimplyStatus.client = new IPCClient(Long.parseLong(APPLICATION_ID));
                SimplyStatus.setupListener();
                try {
                    SimplyStatus.client.connect();
                } catch (Exception ex){
                    ex.printStackTrace();
                }
                SimplyStatus.lastPresence = null;
            }
        } else if((SimplyStatus.useAnotherID != SimplyStatus.userConfig.getBoolean("USE_ANOTHER_ID", false)) || (SimplyStatus.useCustomID != SimplyStatus.userConfig.getBoolean("USE_CUSTOM_APP_ID", false))){
            SimplyStatus.useAnotherID = SimplyStatus.userConfig.getBoolean("USE_ANOTHER_ID", false);
            SimplyStatus.useCustomID = SimplyStatus.userConfig.getBoolean("USE_CUSTOM_APP_ID", false);
            SimplyStatus.customID = "";
            String APPLICATION_ID = SimplyStatus.userConfig.getBoolean("USE_ANOTHER_ID", false) ? ModConfig.mineID : ModConfig.baseID;
            SimplyStatus.client.close();
            SimplyStatus.client = new IPCClient(Long.parseLong(APPLICATION_ID));
            SimplyStatus.setupListener();
            try {
                SimplyStatus.client.connect();
            } catch (Exception ex){
                ex.printStackTrace();
            }
            SimplyStatus.lastPresence = null;
        }
        //
        titleBox.setY(15-scrolled);
        this.useMinecraftID.setY(40-scrolled);
        this.assets.setY(65-scrolled);
        this.api.setY(90-scrolled);
        this.showGameStarted.setY(115-scrolled);
        this.showAvatarPlayer.setY(140-scrolled);
        this.viewItemOffHand.setY(165-scrolled);
        this.viewStatistics.setY(190-scrolled);
        this.viewPlayerName.setY(215-scrolled);

    }

    @Override
    public void init(){
        initPanel();
        initCategory();

    }
    private void initCategory(){
        int x = this.width - 150;
        this.titleBox = this.addRenderableWidget(new TextBox(140, 15, x, 9, this.title, true));
        addRenderableWidget(titleBox);

        this.useMinecraftID = new ButtonBoolean(140, 40, x, 20, SimplyStatus.userConfig, "USE_ANOTHER_ID", false, Component.translatable("simplystatus.config.client.use_minecraft_id"));
        addRenderableWidget(useMinecraftID);
        this.assets = new SelectorStringButton(140, 65, x, 20, ModConfig.assetsList, SimplyStatus.userConfig, "USE_ASSETS", ModConfig.assetsList[0], Component.translatable("simplystatus.config.client.assets"));
        addRenderableWidget(assets);
        this.api = new SelectorStringWithIntButton(140, 90, x, 20, SimplyStatus.apiNames, SimplyStatus.userConfig, "USE_API_RENDER", 0, Component.translatable("simplystatus.config.client.api"));
        addRenderableWidget(api);
        this.showGameStarted = new ButtonBoolean(140, 115, x, 20, SimplyStatus.userConfig, "SHOW_GAME_TIME", true, Component.translatable("simplystatus.config.client.show_game_started"));
        addRenderableWidget(showGameStarted);
        this.showAvatarPlayer = new ButtonBoolean(140, 140, x, 20, SimplyStatus.userConfig, "SHOW_AVATAR_PLAYER", true, Component.translatable("simplystatus.config.client.show_avatar_player"));
        addRenderableWidget(showAvatarPlayer);
        this.viewItemOffHand = new ButtonBoolean(140, 165, x, 20, SimplyStatus.userConfig, "VIEW_ITEM_OFF_HAND", false, Component.translatable("simplystatus.config.client.view_item_off_hand"));
        addRenderableWidget(viewItemOffHand);
        this.viewStatistics = new ButtonBoolean(140, 190, x, 20, SimplyStatus.userConfig, "VIEW_STATISTICS", true, Component.translatable("simplystatus.config.client.view_statistics"));
        addRenderableWidget(viewStatistics);
        this.viewPlayerName = new ButtonBoolean(140, 215, x, 20, SimplyStatus.userConfig, "VIEW_PLAYER_NAME", true, Component.translatable("simplystatus.config.client.view_player_name"));
        addRenderableWidget(viewPlayerName);

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

        addRenderableWidget(new Button(10, height-30, 85, 20, Component.translatable("simplystatus.config.exit"), (OnPress) -> {
            this.minecraft.setScreen(parent);
        }));
        addRenderableWidget(new Button(100, height-30, 20, 20, Component.literal("l"), (OnPress) -> {
            this.minecraft.setScreen(YACLConfigScreen.buildScreen(this.parent));
        })).setActive(SimplyStatus.yacl);
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
        int size = 240;
        if (scrolled <= 0 || size <= this.height) {
            this.scrolled = 0;
        } else this.scrolled = Math.min(scrolled, size - this.height);

        return super.mouseScrolled(d, e, f, g);
    }
}
