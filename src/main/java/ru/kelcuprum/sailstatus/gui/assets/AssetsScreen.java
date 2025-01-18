package ru.kelcuprum.sailstatus.gui.assets;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.*;
import org.jetbrains.annotations.NotNull;
import ru.kelcuprum.alinlib.config.Localization;
import ru.kelcuprum.alinlib.gui.Colors;
import ru.kelcuprum.alinlib.gui.components.ConfigureScrolWidget;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.editbox.EditBoxBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.selector.SelectorBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.text.TextBuilder;
import ru.kelcuprum.alinlib.gui.components.text.*;
import ru.kelcuprum.sailstatus.SailStatus;
import ru.kelcuprum.sailstatus.config.*;

import java.util.ArrayList;
import java.util.List;

import static ru.kelcuprum.alinlib.gui.Icons.DONT;
import static ru.kelcuprum.alinlib.gui.Icons.RESET;

public class AssetsScreen extends Screen {
    private final Assets assets;
    private final Screen parent;

    public AssetsScreen(Screen parent, Assets assets) {
        super(Component.translatable("simplystatus.config.assets"));
        this.parent = parent;
        this.assets = assets;
    }

    boolean isDeleted = false;
    boolean isSelected = false;
    @Override
    protected void init() {
        assert this.minecraft != null;
        isSelected = SailStatus.userConfig.getString("USE_ASSETS", ModConfig.defaultAssets.id).equals(assets.id);
        initPanel();
        initContent();
    }
    public DescriptionBox descriptionBox;
    public void initPanel() {
        int x = 5;
        int size = 180;
        addRenderableWidget(new TextBuilder(title).setSize(size, 9).setPosition(x, 15).build());

        addRenderableWidget(new EditBoxBuilder(Component.translatable("simplystatus.assets.title"), (s) -> {
            assets.setName(s); if(descriptionBox != null) descriptionBox.setDescription(Localization.toText(String.format(Component.translatable("simplystatus.assets.description").getString(), assets.name)));
        }).setValue(assets.name).setPosition(x, 40).setSize(size, 20).build());

        addRenderableWidget(new EditBoxBuilder(Component.translatable("simplystatus.assets.author"), assets::setAuthor)
                .setValue(assets.author).setPosition(x, 65).setSize(size, 20).build());
        addRenderableWidget(new SelectorBuilder(Component.translatable("simplystatus.assets.base"), selectorButton -> {
            assets.setBaseAssets(Assets.getByName(selectorButton.getList()[selectorButton.getPosition()]).id);
            SailStatus.log(assets.getBaseAssets().name);
            assets.save();
            rebuildContentWidgets();
        }).setList(Assets.getAssetsNames(assets.name))
                .setValue(Assets.getPositionOnAssetsNames(assets.getBaseAssets() != null ? assets.getBaseAssets().name : ModConfig.defaultAssets.name, assets.name))
                .setPosition(x, 90)
                .setWidth(size)
                .build());
        descriptionBox = addRenderableWidget(new DescriptionBox(x, 115, size, height-150, Component.empty()).setDescription(Localization.toText(String.format(Component.translatable("simplystatus.assets.description").getString(), assets.name))));
        addRenderableWidget(new ButtonBuilder(CommonComponents.GUI_BACK, (s) -> onClose()).setPosition(x, height - 30).setSize(size - 50, 20).build());
        addRenderableWidget(new ButtonBuilder(Localization.getText("simplystatus.assets.remove"), (OnPress) -> {
            isDeleted = true;
            if(isSelected) SailStatus.userConfig.setString("USE_ASSETS", ModConfig.defaultAssets.id);
            assets.delete();
            onClose();
        }).setIcon(DONT).setPosition(x+size-45, height-30).setSize(20,20).build());
        addRenderableWidget(new ButtonBuilder(Localization.getText("simplystatus.assets.reload"), (OnPress) -> {
            assets.save();
            rebuildWidgets();
        }).setIcon(RESET).setPosition(x+size-20, height-30).setSize(20,20).build());
    }

    private ConfigureScrolWidget scroller;
    private List<AbstractWidget> widgets = new ArrayList<>();

    public void initContent() {
        widgets = new ArrayList<>();
        this.scroller = addRenderableWidget(new ConfigureScrolWidget(this.width - 8, 0, 4, this.height, Component.empty(), scroller -> {
            scroller.innerHeight = 5;
            for (AbstractWidget widget : widgets) {
                if (widget.visible) {
                    widget.setY((int) (scroller.innerHeight - scroller.scrollAmount()));
                    widget.setX(200);
                    widget.setWidth(width - 210);
                    scroller.innerHeight += (widget.getHeight() + 5);
                } else widget.setY(-widget.getHeight());
            }
        }));
        int x = 195;
        addWidget(new TextBuilder(Component.translatable("simplystatus.assets.icons")).setPosition(x, 5).setSize(width-200, 20).build());
        addWidget(new CategoryBox(Component.translatable("simplystatus.config.assets.title.menu"))
                .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.assets.logo"), newVal -> assets.setIcon("logo", newVal)).setValue(assets.getIcon("logo")).build())
        );
        addWidget(new CategoryBox(Component.translatable("simplystatus.config.assets.title.time"))
                .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.assets.day"), newVal -> assets.setIcon("day", newVal)).setValue(assets.getIcon("day")).build())
                .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.assets.night"), newVal -> assets.setIcon("night", newVal)).setValue(assets.getIcon("night")).build())
                .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.assets.evening"), newVal -> assets.setIcon("evening", newVal)).setValue(assets.getIcon("evening")).build())
                .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.assets.morning"), newVal -> assets.setIcon("morning", newVal)).setValue(assets.getIcon("morning")).build())
        );
        addWidget(new CategoryBox(Component.translatable("simplystatus.config.assets.title.worlds"))
                .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.assets.world"), newVal -> assets.setIcon("world", newVal)).setValue(assets.getIcon("world")).build())
                .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.assets.world_nether"), newVal -> assets.setIcon("world_nether", newVal)).setValue(assets.getIcon("world_nether")).build())
                .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.assets.world_the_end"), newVal -> assets.setIcon("world_the_end", newVal)).setValue(assets.getIcon("world_the_end")).build())
                .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.assets.world_moon"), newVal -> assets.setIcon("world_moon", newVal)).setValue(assets.getIcon("world_moon")).build())
        );
        addWidget(new CategoryBox(Component.translatable("simplystatus.config.assets.title.modification"))
                .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.assets.voice"), newVal -> assets.setIcon("voice", newVal)).setValue(assets.getIcon("voice")).build())
                .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.assets.music"), newVal -> assets.setIcon("music", newVal)).setValue(assets.getIcon("music")).build())
                .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.assets.replaymod"), newVal -> assets.setIcon("replaymod", newVal)).setValue(assets.getIcon("replaymod")).build())
        );
        addWidget(new CategoryBox(Component.translatable("simplystatus.config.assets.title.unknown"))
                .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.assets.unknown_world"), newVal -> assets.setIcon("unknown_world", newVal)).setValue(assets.getIcon("unknown_world")).build())
                .addValue(new EditBoxBuilder(Component.translatable("simplystatus.config.assets.unknown"), newVal -> assets.setIcon("unknown", newVal)).setValue(assets.getIcon("unknown")).build())
        );
    }

    protected void addWidget(AbstractWidget widget) {
        widgets.add(widget);
        widget.setWidth(width - 210);
        widget.setX(200);
        if (widget instanceof CategoryBox) {
            widgets.addAll(((CategoryBox) widget).values);
            addWidgets(((CategoryBox) widget).values);
        }
        this.addRenderableWidget(widget);
    }

    protected void rebuildContentWidgets(){
        removeWidget(scroller);
        scroller = null;
        for(AbstractWidget widget : widgets){
            removeWidget(widget);
        }
        initContent();
    }

    protected void addWidgets(@NotNull List<AbstractWidget> widgets) {
        for (AbstractWidget widget : widgets) {
            widget.setWidth(width - 210);
            widget.setX(200);
            this.addRenderableWidget(widget);
        }
    }

    @Override
    public void renderBackground(GuiGraphics guiGraphics, int i, int j, float f) {
        super.renderBackground(guiGraphics, i, j, f);
        guiGraphics.fill(0, 0, 190, height, Colors.BLACK_ALPHA);
    }

    @Override
    public void tick() {
        if (scroller != null) scroller.onScroll.accept(scroller);
        super.tick();
    }

    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double scrollX, double scrollY) {
        boolean scr = super.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
        if (!scr && scroller != null) {
            scr = scroller.mouseScrolled(mouseX, mouseY, scrollX, scrollY);
        }
        return scr;
    }

    public void onClose() {
        if (!isDeleted) assets.save();
        assert this.minecraft != null;
        this.minecraft.setScreen(parent);
    }
}
