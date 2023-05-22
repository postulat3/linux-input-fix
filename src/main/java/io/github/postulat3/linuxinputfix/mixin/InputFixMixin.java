package io.github.postulat3.linuxinputfix.mixin;

import net.minecraft.client.input.KeyboardInput;
import net.minecraft.client.option.KeyBinding;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(KeyboardInput.class)
public class InputFixMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    // fix MC-12410, where shift + 2 and shift + 6 are not registered properly
    public void keyCodeFix(CallbackInfo ci) {
		switch (Keyboard.getEventCharacter()) {
			case '^': // shift + 6
				KeyBinding.onKeyPressed(Keyboard.KEY_6);
			case '@': // shift + 2
				KeyBinding.onKeyPressed(Keyboard.KEY_2);
		}


    }
}
