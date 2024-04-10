package dev.tildejustin.shareware.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Screen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;openScreen(Lnet/minecraft/client/gui/Screen;)V"))
    private void disableDiskScreen(MinecraftClient instance, Screen screen) {
    }
}
