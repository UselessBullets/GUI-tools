package useless.guitools.gui.elements;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.render.Scissor;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

public class GuiPanel extends GuiRegion  {
	public List<GuiButton> elementList = new ArrayList<GuiButton>();
	public GuiPanel(int id, int xPosition, int yPosition, int width, int height) {
		super(id, xPosition, yPosition, width, height);
	}
	@Override
	public boolean mouseClicked(Minecraft mc, int mouseX, int mouseY) {
		for (GuiButton button : elementList){
			button.mouseClicked(mc, mouseX - getLeft(), mouseY - getTop());
		}
		return super.mouseClicked(mc, mouseX, mouseY);
	}
	@Override
	public void mouseDragged(Minecraft mc, int mouseX, int mouseY) {
		for (GuiButton button : elementList){
			button.mouseDragged(mc, mouseX - getLeft(), mouseY - getTop());
		}
	}
	@Override
	public void mouseReleased(int mouseX, int mouseY) {
		for (GuiButton button : elementList){
			button.mouseReleased(mouseX - getLeft(), mouseY - getTop());
		}
	}
	@Override
	public void scroll(int mouseX, int mouseY, float amount) {
		super.scroll(mouseX, mouseY, amount);
		for (GuiButton button : elementList){
			if (button instanceof IGuiScrollable){
				((IGuiScrollable) button).scroll(mouseX - getLeft(), mouseY - getTop(), amount);
			}
		}
	}
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		super.drawButton(mc, mouseX, mouseY);

		drawPanel(mc, mouseX, mouseY);
		GL11.glTranslatef(xPosition, yPosition, 0);
		Scissor.enable(xPosition, yPosition, width, height);
		for (GuiButton button : elementList){
			button.drawButton(mc, mouseX - getLeft(), mouseY - getTop());
		}
		Scissor.disable();

	}
	protected void drawPanel(Minecraft mc, int mouseX, int mouseY){
		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
		GL11.glBindTexture(3553, mc.renderEngine.getTexture("/assets/guitools/gui/panel.png"));

		// corners
		drawTexturedModalRect(getLeft(), getTop(), 0, 0, 4, 4);
		drawTexturedModalRect(getRight() - 4, getTop(), 10, 0, 4, 4);
		drawTexturedModalRect(getLeft(), getBottom() - 4, 0, 10, 4, 4);
		drawTexturedModalRect(getRight() - 4, getBottom() - 4, 10, 10, 4, 4);

		// sides
		int subWidth = getWidth() - 8;
		int subHeight = getHeight() - 8;
		int wSections = (int) Math.ceil(subWidth/4f);
		int hSections = (int) Math.ceil(subHeight/4f);;
		if (subWidth > 0){ // Top bottom sides
			for (int i = 0; i < wSections; i++) {
				if (i == wSections - 1){
					drawTexturedModalRect(getLeft() + (4 * (i + 1)), getTop(), 5, 0, subWidth - ((wSections - 1) * 4), 4);
					drawTexturedModalRect(getLeft() + (4 * (i + 1)), getBottom() - 4, 5, 10, subWidth - ((wSections - 1) * 4), 4);
					break;
				}
				drawTexturedModalRect(getLeft() + (4 * (i + 1)), getTop(), 5, 0, 4, 4);
				drawTexturedModalRect(getLeft() + (4 * (i + 1)), getBottom() - 4, 5, 10, 4, 4);
			}
		}
		if (subHeight > 0){ // Left Right sides
			for (int i = 0; i < hSections; i++) {
				if (i == hSections - 1){
					drawTexturedModalRect(getLeft(), getTop() + (4 * (i + 1)), 0, 5, 4, subHeight - ((hSections - 1) * 4));
					drawTexturedModalRect(getRight() - 4, getTop() + (4 * (i + 1)), 10, 5, 4, subHeight - ((hSections - 1) * 4));
					break;
				}
				drawTexturedModalRect(getLeft(), getTop() + (4 * (i + 1)), 0, 5, 4, 4);
				drawTexturedModalRect(getRight() - 4, getTop() + (4 * (i + 1)), 10, 5, 4, 4);
			}
		}
		if (subHeight > 0 && subWidth > 0){ // Center
			for (int x = 0; x < wSections; x++) {
				for (int y = 0; y < hSections; y++) {
					int width = (x < wSections - 1) ? 4: subWidth - ((wSections - 1) * 4);
					int height = (y < hSections - 1) ? 4: subHeight - ((hSections - 1) * 4);
					drawTexturedModalRect(getLeft() + 4 + (4 * x), getTop() + 4 + (4 * y), 5, 5, width, height);
				}
			}
		}
	}
	public GuiPanel addElement(GuiButton element){
		elementList.add(element);
		return this;
	}
}
