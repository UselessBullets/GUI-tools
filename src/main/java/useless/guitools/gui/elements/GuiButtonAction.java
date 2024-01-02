package useless.guitools.gui.elements;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class GuiButtonAction extends GuiButton {
	protected Runnable action;
	public GuiButtonAction(int id, int xPosition, int yPosition, String text, Runnable action) {
		super(id, xPosition, yPosition, text);
		this.action = action;
	}

	public GuiButtonAction(int id, int xPosition, int yPosition, int width, int height, String text, Runnable action) {
		super(id, xPosition, yPosition, width, height, text);
		this.action = action;
	}
	public boolean mouseClicked(Minecraft mc, int mouseX, int mouseY) {
		boolean returnVal = super.mouseClicked(mc, mouseX, mouseY);
		if (returnVal){
			action.run();
		}
		return returnVal;
	}
}
