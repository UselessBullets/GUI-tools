package useless.guitools.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.core.sound.SoundType;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import useless.guitools.gui.elements.GuiButtonAction;
import useless.guitools.gui.elements.GuiPanel;
import useless.guitools.gui.elements.GuiRegion;
import useless.guitools.gui.elements.GuiScrollbar;
import useless.guitools.gui.elements.IGuiButtonSound;
import useless.guitools.gui.elements.IGuiScrollable;

public class GuiDebug extends GuiScreen {
	protected GuiScrollbar scrollbar;
	public GuiDebug(GuiScreen parent) {
		super(parent);
	}
	public void init() {
		scrollbar = add(new GuiScrollbar(0, width/2, 10, 8,  height-20, 1024));
		add(new GuiRegion(2, 20, 0, width/2, height).setScrollAction((x, y, a) -> {
			if (!scrollbar.isHovered(x, y)){
				scrollbar.setScrollAmount(scrollbar.getScrollAmount() + a);
				scrollbar.onScroll();
			}
			return null;
		}));
		add(new GuiButtonAction(1, 0, height/2, 20,  20, "", () -> {mc.displayGuiScreen(null);}));
		add(new GuiPanel(3, 20, 20, 26, 26));
	}
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTick) {
		drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTick);
		scroll(mouseX, mouseY);

	}
	public void scroll(int mouseX, int mouseY){
		int scrollAmount = Mouse.getDWheel() * -1;
		if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL)) {
			scrollAmount *= 5;
		}
		for (GuiButton control : controlList){
			if (control instanceof IGuiScrollable){
				IGuiScrollable scrollable = (IGuiScrollable)control;
				scrollable.scroll(mouseX, mouseY, scrollAmount);
			}
		}
	}
	@Override
	public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
		if (mouseButton == 0) {
			for (GuiButton guiButton : this.controlList) {
				if (!guiButton.mouseClicked(this.mc, mouseX, mouseY)) continue;
				this.selectedButton = guiButton;
				if (guiButton.listener != null) {
					guiButton.listener.listen(guiButton);
				} else {
					this.buttonPressed(guiButton);
				}
				if (guiButton instanceof IGuiButtonSound){
					IGuiButtonSound buttonSound = (IGuiButtonSound) guiButton;
					if (!buttonSound.canPlaySound(mouseX, mouseY)) return;
					this.mc.sndManager.playSound(buttonSound.getSoundPath(mouseX, mouseY), SoundType.GUI_SOUNDS, 1.0f, 1.0f);
				} else {
					this.mc.sndManager.playSound("random.click", SoundType.GUI_SOUNDS, 1.0f, 1.0f);
				}
				return;
			}
		}
	}
}
