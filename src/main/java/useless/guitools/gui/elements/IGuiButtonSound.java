package useless.guitools.gui.elements;

import javax.annotation.Nullable;

public interface IGuiButtonSound {
	boolean canPlaySound(int mouseX, int mouseY);
	@Nullable
	String getSoundPath(int mouseX, int mouseY);
}
