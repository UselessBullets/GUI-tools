package useless.guitools.gui.elements;

import net.minecraft.client.gui.GuiElement;

public interface IGuiRect extends GuiElement {
	default int getLeft(){
		return getX();
	}
	default int getTop(){
		return getY();
	}
	default int getRight(){
		return getX() + getWidth();
	}
	default int getBottom(){
		return getY() + getHeight();
	}
}
