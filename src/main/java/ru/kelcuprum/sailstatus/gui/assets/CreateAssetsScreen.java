package ru.kelcuprum.sailstatus.gui.assets;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.*;
import net.minecraft.world.item.Items;
import org.apache.logging.log4j.Level;
import ru.kelcuprum.alinlib.gui.components.builder.button.ButtonBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.editbox.EditBoxBuilder;
import ru.kelcuprum.alinlib.gui.components.builder.text.TextBuilder;
import ru.kelcuprum.alinlib.gui.toast.ToastBuilder;
import ru.kelcuprum.sailstatus.SailStatus;
import ru.kelcuprum.sailstatus.config.*;

import java.io.*;
import java.nio.file.*;

public class CreateAssetsScreen extends Screen {
    private final Screen parent;
    private String fileName;
    public CreateAssetsScreen(Screen parent) {
        super(Component.translatable("sailstatus.assets.create"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int x = width/2;
        int y = height/2;

        addRenderableWidget(new TextBuilder(title).setPosition(x-150, 20).setSize(300, 20).build());
        addRenderableWidget(new EditBoxBuilder(Component.translatable("sailstatus.assets.create.filename"), (s) -> fileName = s)
                .setPosition(x-150, y-10).setSize(300, 20).build());
        addRenderableWidget(new ButtonBuilder(CommonComponents.GUI_CANCEL, (S) -> onClose())
                .setPosition(x-150, y+15).setSize(145, 20).build());
        addRenderableWidget(new ButtonBuilder(CommonComponents.GUI_CONTINUE, (s) -> {
            if(fileName == null || fileName.equals("null") || fileName.isBlank() || !naturalSelectionSocieties(fileName)){
                new ToastBuilder().setIcon(Items.CRAFTING_TABLE).setTitle(Component.translatable("sailstatus.name")).setMessage(Component.translatable("sailstatus.assets.create.invalid_name")).buildAndShow();
                return;
            }
            for(String id : SailStatus.assetsNames){
                if(id.equals(fileName)){
                    new ToastBuilder().setIcon(Items.CRAFTING_TABLE).setTitle(Component.translatable("sailstatus.name")).setMessage(Component.translatable("sailstatus.assets.create.id_not_correct")).buildAndShow();
                    return;
                }
            }
            assert this.minecraft != null;
            String path = "config/SailStatus/assets/"+fileName+".json";
            Path file = Path.of(path);
            try {
                Files.createDirectories(file.getParent());
                Files.writeString(file, "{}");
            } catch (IOException e) {
                SailStatus.log(e.getLocalizedMessage(), Level.ERROR);
            }
            Assets assets = new Assets(new File(path));
            assets.setDefaultAssets(ModConfig.defaultAssets);
            Assets.registerAsset(assets);
            this.minecraft.setScreen(new AssetsScreen(parent, assets));
        }).setPosition(x+5, y+15).setSize(145, 20).build());
    }

    public boolean naturalSelectionSocieties(String string){
        String refactor = string.replaceAll("[<>:\"?|\\\\/*]", "");
        return refactor.length() == string.length();
    }

    public void onClose() {
        assert this.minecraft != null;
        this.minecraft.setScreen(parent);
    }
}
