package og.__kel_.simplystatus.mixin;

import net.minecraft.client.render.GameRenderer;
//import net.morimori0317.bettertaskbar.TaskbarHandler;
import og.__kel_.simplystatus.info.GameStateHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Inject(method = "render", at = @At("TAIL"))
    private void render(float f, long l, boolean bl, CallbackInfo ci) {
//        TaskbarHandler.tick();
        GameStateHandler.tick();
    }
}