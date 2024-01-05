package useless.guitools.gui.elements;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import org.jetbrains.annotations.Nullable;
import useless.guitools.TriFunction;

import java.util.function.BiFunction;

public class GuiRegion extends GuiButton implements IGuiButtonSound, IGuiScrollable, IGuiRect {
	protected BiFunction<Integer, Integer, Void> clickAction;
	protected TriFunction<Integer, Integer, Float, Void> scrollAction;

	public GuiRegion(int id, int xPosition, int yPosition, int width, int height) {
		super(id, xPosition, yPosition, width, height, "");
	}
	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY) {
		this.mouseDragged(mc, mouseX, mouseY);
	}
	public GuiRegion setClickAction(BiFunction<Integer, Integer, Void> action){
		this.clickAction = action;
		return this;
	}
	public GuiRegion setScrollAction(TriFunction<Integer, Integer, Float, Void> action){
		this.scrollAction = action;
		return this;
	}
	@Override
	public boolean mouseClicked(Minecraft mc, int mouseX, int mouseY) {
		boolean returnVal = super.mouseClicked(mc, mouseX, mouseY);
		if (returnVal && clickAction != null){
			clickAction.apply(mouseX, mouseY);
		}
		return returnVal;
	}

	@Override
	public boolean canPlaySound(int mouseX, int mouseY) {
		return false;
	}

	@Nullable
	@Override
	public String getSoundPath(int mouseX, int mouseY) {
		return null;
	}

	@Override
	public void scroll(int mouseX, int mouseY, float amount) {
		if (isHovered(mouseX, mouseY) && scrollAction != null){
			scrollAction.apply(mouseX, mouseY, amount);
		}
	}

	@Override
	public void onScroll() {

	}

}
