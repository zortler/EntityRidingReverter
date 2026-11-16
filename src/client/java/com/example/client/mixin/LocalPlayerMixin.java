package com.example.client.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.Mth;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin {
	@Inject(at = @At("HEAD"), method = "getViewYRot", cancellable = true)
	private void onViewYRot(float a, CallbackInfoReturnable<Float> cir) {
		LocalPlayer p = (LocalPlayer) (Object) this;
		if (p.isPassenger()) {
			cir.setReturnValue(a == 1.0F ? p.yHeadRot : Mth.rotLerp(a, p.yHeadRotO, p.yHeadRot));
		} else {
			cir.setReturnValue(p.getYRot());
		}
	}
}