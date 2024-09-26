package dev.ouroya.unshare.mixin;

import dev.ouroya.unshare.Unshade;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.resource.ReloadableResourceManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public abstract class MinecraftClientMixin {
    @Shadow
    private ReloadableResourceManager resourceManager;

    @Final
    @Shadow
    private RenderTickCounter renderTickCounter;

    @Shadow
    private boolean isPaused;

    @Shadow
    private float pausedTickDelta;

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

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/GameRenderer;render(FJZ)V"))
    private void enableLerp(GameRenderer instance, float f, long l, boolean bl) {
        instance.render(this.isPaused ? this.pausedTickDelta : this.renderTickCounter.tickDelta, l, bl);
    }
}
