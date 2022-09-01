package og.__kel_.simplystatus.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
@Mixin(MinecraftClient.class)
public interface MinecraftClientMixin {
    @Accessor("currentFps")
    int getCurrentFPS();

}
