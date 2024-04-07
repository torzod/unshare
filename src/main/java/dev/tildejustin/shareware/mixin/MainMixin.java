package dev.tildejustin.shareware.mixin;

import net.minecraft.client.main.Main;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(Main.class)
public class MainMixin {
    @ModifyArg(method = "main", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/RunArgs$Game;<init>(ZLjava/lang/String;Ljava/lang/String;)V"))
    private static boolean disableDemo(boolean demo) {
        return false;
    }
}
