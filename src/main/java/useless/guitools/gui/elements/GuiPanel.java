package useless.guitools.gui.elements;

import net.minecraft.client.Minecraft;
import org.lwjgl.opengl.GL11;

public class GuiPanel extends GuiRegion  {
	public GuiPanel(int id, int xPosition, int yPosition, int width, int height) {
		super(id, xPosition, yPosition, width, height);
	}
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		super.drawButton(mc, mouseX, mouseY);

		GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);

		GL11.glBindTexture(3553, mc.renderEngine.getTexture("/assets/guitools/gui/panel/top_left.png"));
		drawTexturedModalRect(xPosition, yPosition, 0, 0, 4, 4, 4, 1/4f);
		GL11.glBindTexture(3553, mc.renderEngine.getTexture("/assets/guitools/gui/panel/top_right.png"));
		drawTexturedModalRect(xPosition + width - 4, yPosition, 0, 0, 4, 4, 4, 1/4f);
		GL11.glBindTexture(3553, mc.renderEngine.getTexture("/assets/guitools/gui/panel/bottom_left.png"));
		drawTexturedModalRect(xPosition, yPosition + height - 4, 0, 0, 4, 4, 4, 1/4f);
		GL11.glBindTexture(3553, mc.renderEngine.getTexture("/assets/guitools/gui/panel/bottom_right.png"));
		drawTexturedModalRect(xPosition + width - 4, yPosition + height - 4, 0, 0, 4, 4, 4, 1/4f);
		int subWidth = Math.max(width - 8, 0);
		int subHeight = Math.max(height - 8, 0);
		GL11.glBindTexture(3553, mc.renderEngine.getTexture("/assets/guitools/gui/panel/top_center.png"));
		drawTexturedModalRect(xPosition + 4, yPosition, 0, 0, subWidth, 4, 4, 1/4f);
		GL11.glBindTexture(3553, mc.renderEngine.getTexture("/assets/guitools/gui/panel/center_left.png"));
		drawTexturedModalRect(xPosition, yPosition + 4, 0, 0, 4, subHeight, 4, 1/4f);
		GL11.glBindTexture(3553, mc.renderEngine.getTexture("/assets/guitools/gui/panel/center_right.png"));
		drawTexturedModalRect(xPosition + width - 4, yPosition + 4, 0, 0, 4, subHeight, 4, 1/4f);
		GL11.glBindTexture(3553, mc.renderEngine.getTexture("/assets/guitools/gui/panel/bottom_center.png"));
		drawTexturedModalRect(xPosition + 4, yPosition + height - 4, 0, 0, subWidth, 4, 4, 1/4f);

		GL11.glBindTexture(3553, mc.renderEngine.getTexture("/assets/guitools/gui/panel/center.png"));
		drawTexturedModalRect(xPosition + 4, yPosition + 4, 0, 0, subWidth, subHeight, 4, 1/4f);
	}
}
