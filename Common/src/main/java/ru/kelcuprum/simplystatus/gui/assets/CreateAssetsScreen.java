package ru.kelcuprum.simplystatus.gui.assets;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;
import org.apache.logging.log4j.Level;
import ru.kelcuprum.alinlib.AlinLib;
import ru.kelcuprum.alinlib.gui.InterfaceUtils;
import ru.kelcuprum.alinlib.gui.components.buttons.base.Button;
import ru.kelcuprum.alinlib.gui.components.editbox.base.EditBoxString;
import ru.kelcuprum.alinlib.gui.components.text.TextBox;
import ru.kelcuprum.alinlib.gui.toast.ToastBuilder;
import ru.kelcuprum.simplystatus.SimplyStatus;
import ru.kelcuprum.simplystatus.config.Assets;
import ru.kelcuprum.simplystatus.config.ModConfig;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CreateAssetsScreen extends Screen {
    private final Screen parent;
    private String fileName;
    public CreateAssetsScreen(Screen parent) {
        super(Component.translatable("simplystatus.assets.create"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int x = width/2;
        int y = height/2;

        addRenderableWidget(new TextBox(x-150, 20, 300, 20, title, true));
        addRenderableWidget(new EditBoxString(x-150, y-10, 300, 20, false, "", InterfaceUtils.DesignType.FLAT, Component.translatable("simplystatus.assets.create.filename"), (s) -> fileName = s));
        addRenderableWidget(new Button(x-150, y+15, 145, 20, InterfaceUtils.DesignType.FLAT, CommonComponents.GUI_CANCEL, (S) -> onClose()));
        addRenderableWidget(new Button(x+5, y+15, 145, 20, InterfaceUtils.DesignType.FLAT, CommonComponents.GUI_CONTINUE, (s) -> {
            if(fileName == null || fileName.equals("null") || fileName.isBlank()){
                new ToastBuilder().setIcon(Items.CRAFTING_TABLE).setTitle(Component.translatable("simplystatus.name")).setMessage(Component.translatable("simplystatus.assets.create.invalid_name")).buildAndShow(AlinLib.MINECRAFT.getToasts());
                return;
            }
            assert this.minecraft != null;
            String path = "config/SimplyStatus/assets/"+fileName+".json";
            Path file = Path.of(path);
            try {
                Files.createDirectories(file.getParent());
                Files.writeString(file, "{}");
            } catch (IOException e) {
                SimplyStatus.log(e.getLocalizedMessage(), Level.ERROR);
            }
            Assets assets = new Assets(new File(path));
            assets.setDefaultAssets(ModConfig.defaultAssets);
            this.minecraft.setScreen(new AssetsScreen(parent, assets));
        }));
    }

    public void onClose() {
        assert this.minecraft != null;
        this.minecraft.setScreen(parent);
    }
}
