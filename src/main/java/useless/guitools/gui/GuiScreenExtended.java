package useless.guitools.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.core.sound.SoundType;
import org.lwjgl.input.Mouse;
import useless.guitools.gui.elements.IGuiButtonSound;
import useless.guitools.gui.elements.IGuiScrollable;

import java.util.ArrayList;
import java.util.List;

public class GuiScreenExtended extends GuiScreen {
	protected List<GuiButton> selectedButtons = new ArrayList<>();
	public GuiScreenExtended() {
		super();
	}
	public GuiScreenExtended(GuiScreen parent) {
		super(parent);
	}
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTick) {
		scroll(mouseX, mouseY, Mouse.getDWheel() * -1);
		super.drawScreen(mouseX, mouseY, partialTick);
	}
	public void scroll(int mouseX, int mouseY, int scrollAmount){
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
				selectedButtons.add(guiButton);
				if (guiButton.listener != null) {
					guiButton.listener.listen(guiButton);
				} else {
					this.buttonPressed(guiButton);
				}
				if (guiButton instanceof IGuiButtonSound){
					IGuiButtonSound buttonSound = (IGuiButtonSound) guiButton;
					if (!buttonSound.canPlaySound(mouseX, mouseY)) continue;
					this.mc.sndManager.playSound(buttonSound.getSoundPath(mouseX, mouseY), SoundType.GUI_SOUNDS, 1.0f, 1.0f);
				} else {
					this.mc.sndManager.playSound("random.click", SoundType.GUI_SOUNDS, 1.0f, 1.0f);
				}
			}
		}
	}
	@Override
	public void mouseMovedOrButtonReleased(int mouseX, int mouseY, int mouseButton) {
		List<GuiButton> _selected = new ArrayList<>(selectedButtons);
		if (mouseButton == 0){
			for (GuiButton selectedButton : _selected){
				buttonReleased(selectedButton);
				selectedButton.mouseReleased(mouseX, mouseY);
				selectedButtons.remove(selectedButton);
			}
		}
		selectedButtons = _selected;
	}
}
