package useless.guitools.gui;

import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;
import useless.guitools.gui.elements.GuiButtonAction;
import useless.guitools.gui.elements.GuiPanel;
import useless.guitools.gui.elements.GuiScrollbar;

public class GuiTest extends GuiScreenExtended{
	public long tick = 0;
	protected int xVel = 1;
	protected int yVel = 1;
	protected GuiScrollbar subBar;
	protected GuiPanel panel;
	public GuiTest(GuiScreen parent) {
		super(parent);
	}
	@Override
	public void tick(){
		super.tick();
		tick++;
		int x = panel.getX();
		int y = panel.getY();
		if (x > width - panel.getWidth()){
			xVel *= -1;
			panel.setX(x = width - panel.getWidth());
		}
		if (y > height - panel.getHeight()){
			yVel *= -1;
			panel.setY(y = height - panel.getHeight());
		}
		if (x < 0){
			xVel *= -1;
			panel.setX(x = 0);
		}
		if (y < 0){
			yVel *= -1;
			panel.setY(y = 0);
		}
		panel.setX(x + (xVel));
		panel.setY(y + (yVel));
	}
	@Override
	public void init() {
		add(new GuiButtonAction(1, 0, height/2, 20,  20, "", () -> {mc.displayGuiScreen(null);}));
		panel = add(new GuiPanel(3, 20, 20, 120, 120));
		subBar = new GuiScrollbar(0, panel.getWidth() - 10, 4, 6, panel.getHeight() - 8, 300);
		panel.addElement(subBar);
		panel.setScrollAction((x, y, a) -> {
			if (!subBar.isHovered(x, y)){
				subBar.setScrollAmount(subBar.getScrollAmount() + a);
				subBar.onScroll();
			}
			return null;
		});
	}
	@Override
	public void scroll(int mouseX, int mouseY, int scrollAmount){
		if (Keyboard.isKeyDown(Keyboard.KEY_LCONTROL) || Keyboard.isKeyDown(Keyboard.KEY_RCONTROL)) {
			scrollAmount *= 5;
		}
		super.scroll(mouseX, mouseY, scrollAmount);
	}
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTick) {
		drawDefaultBackground();
		super.drawScreen(mouseX, mouseY, partialTick);
	}
}
