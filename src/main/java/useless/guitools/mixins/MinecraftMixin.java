package useless.guitools.mixins;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import useless.guitools.gui.GuiTest;

@Mixin(value = Minecraft.class, remap = false)
public abstract class MinecraftMixin {
	@Shadow
	public abstract void displayGuiScreen(GuiScreen guiscreen);

	@Shadow
	public GuiScreen currentScreen;

	@Inject(method = "runTick()V", at = @At("TAIL"))
	private void debugGUI(CallbackInfo ci){
		if (Keyboard.isKeyDown(Keyboard.KEY_NUMPAD0)){
			displayGuiScreen(new GuiTest(currentScreen));
		}
	}
}
