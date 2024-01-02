package useless.guitools.gui.elements;

public interface IGuiScrollable {
	void scroll(int mouseX, int mouseY, float amount);

	void onScroll();
}
