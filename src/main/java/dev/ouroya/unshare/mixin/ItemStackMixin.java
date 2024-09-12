package dev.ouroya.unshare.mixin;

import java.util.List;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.llamalad7.mixinextras.sugar.Local;
import com.llamalad7.mixinextras.sugar.ref.LocalRef;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.PotionUtil;
import net.minecraft.text.TextComponent;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

    // injects after the item name is added
    @Inject(method = "getTooltipText", at = @At(value = "INVOKE", target = "Ljava/util/List;add(Ljava/lang/Object;)Z", ordinal = 0, shift = At.Shift.AFTER))
    private void getTooltip(@Nullable PlayerEntity playerEntity, TooltipContext tooltipContext,
            CallbackInfoReturnable<List<TextComponent>> ci, @Local LocalRef<List<TextComponent>> localList) {
        ItemStack item = (ItemStack) (Object) this;
        List<TextComponent> list = localList.get();
        if (item.getItem() == Items.ARROW) {
            PotionUtil.buildTooltip(item, list, 0.125F);
        }
    }

}
