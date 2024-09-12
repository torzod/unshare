package dev.ouroya.unshare.mixin;

import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/GameRenderer;method_20281()V"))
    private void render(GameRenderer renderer, float f, long l, boolean bl) {

    }
    @Redirect(method = "render", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/GameRenderer;method_20280()V"))
    private void dirtBorder(GameRenderer renderer, float f, long l, boolean bl) {

    }
}
