package dev.ouroya.unshare.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.server.MinecraftServer;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
    @Shadow
    private boolean demo;

    @Inject(method = "setDemo", at = @At(value = "HEAD"), cancellable = true)
    private void noDemo(CallbackInfo ci) {
        demo = false;
        ci.cancel();
    }
}
