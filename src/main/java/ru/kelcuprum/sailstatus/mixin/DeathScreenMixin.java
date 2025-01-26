package ru.kelcuprum.sailstatus.mixin;

import net.minecraft.client.gui.screens.DeathScreen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.kelcuprum.sailstatus.info.PresencePlayer;

@Mixin(DeathScreen.class)
public class DeathScreenMixin {
    @Inject(method = "<init>", at=@At("RETURN"))
    public void screenInit(@Nullable Component component, boolean bl, CallbackInfo cl){
        if(component == null) component = Component.literal(":(");
        PresencePlayer.lastTextDeath = component.getString();
    }

}
