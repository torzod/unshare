package dev.ouroya.unshare.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Screen;
import net.minecraft.resource.ReloadableResourceManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import dev.ouroya.unshare.Unshade;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Shadow
    private ReloadableResourceManager resourceManager;

    @Inject(method = "init",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/resource/ReloadableResourceManager;registerListener(Lnet/minecraft/resource/ResourceReloadListener;)V", ordinal = 0, shift = At.Shift.AFTER))
    private void init(CallbackInfo ci) {
        if (Unshade.INSTANCE.modPack != null) {
            Unshade.LOGGER.info("Adding resource pack");
            this.resourceManager.addPack(Unshade.INSTANCE.modPack);
        }
    }

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/MinecraftClient;openScreen(Lnet/minecraft/client/gui/Screen;)V"))
    private void disableDiskScreen(MinecraftClient instance, Screen screen) {
    }
}
